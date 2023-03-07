package org.glory.util;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;

// https://hianna.tistory.com/629
// https://juntcom.tistory.com/126
// https://hianna.tistory.com/629
public class GsonUtils {

    public static String getJsonStringFromObject(Object object) {
        return new Gson().toJson(object);
    }

    public static <T> T getObjectFromJsonString(String jsonString, Class<T> clazz) {
        return new Gson().fromJson(jsonString, clazz);
    }

    public static <T> List<T> getListFromJsonString(String jsonString, Class<T[]> clazz) {
        T[] array = new Gson().fromJson(jsonString, clazz);
        return Arrays.asList(array);
    }

}
