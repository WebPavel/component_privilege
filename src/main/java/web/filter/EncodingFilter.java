package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest enhanceRequest = new EnhanceHttpServletRequest(request);
        response.setContentType("text/html;charset=UTF-8");
        chain.doFilter(enhanceRequest, response);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}

class EnhanceHttpServletRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private boolean isEncoded;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public EnhanceHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        if (name == null) {
            return null;
        }
        Map<String, String[]> parameterMap = getParameterMap();
        String[] values = parameterMap.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        String method = request.getMethod();
        if ("post".equalsIgnoreCase(method)) {
            try {
                request.setCharacterEncoding("UTF-8");
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if ("get".equalsIgnoreCase(method)) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (!isEncoded) {
                for (String parameterName : parameterMap.keySet()) {
                    String[] values = parameterMap.get(parameterName);
                    for (int i = 0; i < values.length; i++) {
                        try {
                            values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                isEncoded = true;
            }
            return parameterMap;
        }
        return super.getParameterMap();
    }

    @Override
    public String[] getParameterValues(String name) {
        if (name == null) {
            return null;
        }
        Map<String, String[]> parameterMap = getParameterMap();
        return parameterMap.get(name);
    }
}