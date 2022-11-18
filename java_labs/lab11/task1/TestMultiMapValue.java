package lab11.task1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestMultiMapValue {
    private MultiMapValue<String, String> map;

    @BeforeEach
    public void setup() {
        map = new MultiMapValue<String, String>();
    }

    @AfterEach
    public void clean() {
        map = null;
    }

    @Test
    public void testAddElementWithOneValue() {
        map.add("key", "value");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(1, map.getValues("key").size());
    }

    @Test
    public void testAddElementWithMoreValues() {
        map.add("key", "value1");
        map.add("key", "value2");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(2, map.getValues("key").size());
    }

    @Test
    public void testAddTwoElements() {
        // TODO
        map.add("key1", "value1");
        map.add("key2", "value2");
        map.add("key1", "value3");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals(2, map.getValues("key1").size());
        Assertions.assertEquals(1, map.getValues("key2").size());
    }

    @Test
    public void testGetFirst() {
        // TODO
        map.add("key1", "value1");
        map.add("key2", "value2");
        map.add("key3", "value3");
        map.add("key1", "value4");
        map.add("key2", "value5");
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals("value1", map.getFirst("key1"));
        Assertions.assertEquals("value2", map.getFirst("key2"));
    }

    @Test
    public void testContainsKey() {
        // TODO
        map.add("key1", "value1");
        map.add("key2", "value2");
        map.add("key3", "value3");
        map.add("key4", "value4");
        map.add("key5", "value5");
        Assertions.assertEquals(5, map.size());
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map.containsKey("key3"));
        Assertions.assertTrue(map.containsKey("key4"));
        Assertions.assertTrue(map.containsKey("key2"));
        Assertions.assertFalse(map.containsKey("key0"));
    }

    @Test
    public void testSize() {
        // TODO
        map.add("key1", "value1");
        map.add("key2", "value2");
        map.add("key3", "value3");
        map.add("key4", "value4");
        map.add("key5", "value5");
        map.add("key1", "value6");
        map.add("key2", "value7");
        map.add("key6", "value8");
        map.add("key7", "value9");
        map.add("key8", "value10");
        Assertions.assertEquals(8, map.size());
        Assertions.assertFalse(map.isEmpty());

    }

    @Test
    public void testRemoveKey() {
        // TODO
        map.add("key1", "value1");
        map.add("key2", "value2");
        map.add("key3", "value3");
        map.add("key4", "value4");
        map.add("key5", "value5");
        map.add("key1", "value6");
        map.add("key2", "value7");
        map.add("key3", "value8");
        map.add("key6", "value9");
        map.add("key7", "value10");
        Assertions.assertEquals(7, map.size());
        Assertions.assertFalse(map.isEmpty());
        map.remove("key1");
        map.remove("key2");
        Assertions.assertFalse(map.containsKey("key1"));
        Assertions.assertFalse(map.containsKey("key2"));
        Assertions.assertEquals(5, map.size());
    }

    @Test
    public void testAddAllWithList() {
        // TODO
        List<String> mylist = new ArrayList<>();
        mylist.add("value1");
        mylist.add("value2");
        mylist.add("value3");
        map.addAll("key1", mylist);
        Assertions.assertEquals(1, map.size());
        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map.containsKey("key1"));
        Assertions.assertEquals("value1", map.getFirst("key1"));
        Assertions.assertEquals(3, map.getValues("key1").size());
    }

    @Test
    public void testAddAllWithMultiMapValue() {
        // TODO
        map.add("key1", "value1");
        map.add("key2", "value2");
        map.add("key3", "value3");
        map.add("key4", "value4");
        map.add("key5", "value5");
        MultiMapValue<String, String> map2 = new MultiMapValue<>();
        map2.addAll(map);
        Assertions.assertEquals(5, map2.size());
        Assertions.assertFalse(map2.isEmpty());
        Assertions.assertTrue(map2.containsKey("key1"));
        Assertions.assertFalse(map2.containsKey("key0"));
    }
}
