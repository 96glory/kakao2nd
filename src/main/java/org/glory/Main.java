package org.glory;

import static org.glory.util.GsonUtils.getJsonStringFromObject;
import static org.glory.util.GsonUtils.getListFromJsonString;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import org.glory.model.TestVO;
import org.glory.util.HttpUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "glory");
        jsonObject.addProperty("id", 1);
        System.out.println(getJsonStringFromObject(jsonObject));

        try {
            String jsonString = HttpUtils.get("http://jsonplaceholder.typicode.com/posts");

            List<TestVO> testVOs = getListFromJsonString(jsonString, TestVO[].class);

            testVOs.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}