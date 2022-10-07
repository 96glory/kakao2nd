package org.glory.util;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://hianna.tistory.com/629
// https://juntcom.tistory.com/126

public class GsonUtils {

    public static <T> String getJsonStringFromList(ArrayList<T> list) {
        return new Gson().toJson(list);
    }

    public static <T> List<T> getListFromJsonString(String jsonString, Class<T[]> clazz) {
        T[] array = new Gson().fromJson(jsonString, clazz);
        return Arrays.asList(array);
    }

}
