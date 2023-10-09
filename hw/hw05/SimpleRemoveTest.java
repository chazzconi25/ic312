import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleRemoveTest {
  @Test
  public void size4remove2() {
    BSTMap<String,Integer> map = new BSTMap<>();
    map.put("croissant", 100);
    map.put("pain chocolat", 50);
    map.put("gateau", 71);
    map.put("gateau", 71);
    map.put("profiterole", 200);
    map.remove("pain chocolat");
    map.remove("gateau");
    map.remove("snickerdoodle");

    assertEquals(2, map.size());
    map.put("gateau", 71);
    assertEquals(Integer.valueOf(100), map.get("croissant"));
    assertEquals(Integer.valueOf(200), map.get("profiterole"));
    map.remove("gateau");
    assertNull(map.get("gateau"));

    assertFalse(map.containsKey("pain chocolat"));
    assertTrue(map.containsKey("croissant"));
  }
}
