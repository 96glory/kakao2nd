package org.glory;

import static org.glory.util.GsonUtils.getJsonStringFromObject;
import static org.glory.util.GsonUtils.getListFromJsonString;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import org.glory.model.TestVO;
import org.glory.model.UserTestVO;
import org.glory.util.HttpUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

//        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("name", "glory");
//        jsonObject.addProperty("id", 1);
//        System.out.println(getJsonStringFromObject(jsonObject));

        try {
            String jsonString = HttpUtils.get("http://jsonplaceholder.typicode.com/posts");

            List<TestVO> testVOs = getListFromJsonString(jsonString, TestVO[].class);

//            testVOs.forEach(System.out::println);

            String jsonString2 = HttpUtils.get("http://jsonplaceholder.typicode.com/users");

            List<UserTestVO> test2VOs = getListFromJsonString(jsonString2, UserTestVO[].class);

            UserTestVO userTestVO = test2VOs.get(0);
//            test2VOs.forEach(System.out::println);
            System.out.println(userTestVO.getAddress().getGeo().getLat());
//            System.out.println(userTestVO.getId());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}