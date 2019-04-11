package utils;

public class StringUtils {
    public static Integer[] toInteger(String[] arr) {
        if (arr!=null && arr.length > 0) {
            Integer[] ints = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) {
                ints[i] = Integer.parseInt(arr[i]);
            }
            return ints;
        }
        return null;
    }
}
