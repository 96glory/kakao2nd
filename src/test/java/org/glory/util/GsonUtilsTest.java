package org.glory.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

// https://hianna.tistory.com/629

public class GsonUtilsTest {

    @Test
    public void jsonParsingTest() {
        String json = "{\"id\":1,\"students\": [\"Anna\", \"Jerry\"],\"subject\":{\"name\":\"Java\",\"professor\":\"Tony\"}}";

        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();

        long id = jsonObject.get("id").getAsLong();

        assertEquals(1, id);

        JsonArray students = jsonObject.get("students").getAsJsonArray();

        students.forEach(student -> {
            String studentString = student.getAsString();
            System.out.println(studentString);
        });

        JsonObject subjects = jsonObject.get("subject").getAsJsonObject();

        String name = subjects.get("name").getAsString();
        String professor = subjects.get("professor").getAsString();

        assertEquals("Java", name);
        assertEquals("Tony", professor);
    }

}
