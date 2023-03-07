package org.glory.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

public class CollectionOfTest {

    @Test(expected = NullPointerException.class)
    public void mapOfTest() {
        Map<String, Object> map = Map.of("key01", null);

        map.put("key11", "test");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void immutableTest() {
        Map<String, Object> map = Map.of("key01", "a");

        map.put("key2", "test");
    }

    @Test(expected = NullPointerException.class)
    public void listOfTest() {
        List<String> list = List.of(
            "a","a","a","a","a","a","a","a","a","a"
        );
    }

    @Test(expected = NullPointerException.class)
    public void setOfTest() {
        Set<String> set = Set.of("a", null);
    }

}
