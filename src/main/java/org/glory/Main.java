package org.glory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.glory.model.TestVO;
import org.glory.util.HttpUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            String s = HttpUtil.get("https://jsonplaceholder.typicode.com/posts");

            // https://hianna.tistory.com/629
            // https://howtodoinjava.com/gson/gson-parse-json-array/
            Gson testGson = new Gson();
            Type listType = new TypeToken<ArrayList<TestVO>>(){}.getType();
            List<TestVO> testVOs = testGson.fromJson(s, listType);

            testVOs.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}