package org.glory;

import static org.glory.util.GsonUtils.getListFromJsonString;

import java.io.IOException;
import java.util.List;
import org.glory.model.TestVO;
import org.glory.util.HttpUtils;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            String jsonString = HttpUtils.get("http://jsonplaceholder.typicode.com/posts");

            List<TestVO> testVOs = getListFromJsonString(jsonString, TestVO[].class);

            testVOs.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}