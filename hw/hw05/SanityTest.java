import static org.junit.Assert.*;

import java.util.Deque;

import org.junit.Test;

public class SanityTest {
  @Test
  public void extendsMap() {
    assertTrue(Map.class.isAssignableFrom(BSTMap.class));
  }

  @Test
  public void size1put() {
    BSTMap<Integer,String> map = new BSTMap<>();
    map.put(10, "bagel");
    assertEquals(1, map.size());
    assertEquals("bagel", map.get(10));
    assertNull(map.get(15));
    assertTrue(map.containsKey(10));
    assertFalse(map.containsKey(5));
  }

  @Test
  public void size1get() {
    BSTMap<Integer,String> map = new BSTMap<>();
    map.put(10, "bagel");
    assertEquals("bagel", map.get(10));
    assertNull(map.get(15));
  }

  @Test
  public void size1contains() {
    BSTMap<Integer,String> map = new BSTMap<>();
    map.put(10, "bagel");
    assertTrue(map.containsKey(10));
    assertFalse(map.containsKey(5));
  }

  @Test
  public void size3traverse() {
    BSTMap<Integer,String> map = new BSTMap<>();
    map.put(10, "bagel");
    map.put(7, "muffin");
    map.put(18, "toast");
    Deque<Integer> deq = map.traverse();
    assertEquals(3, deq.size());
    assertEquals(Integer.valueOf(7), deq.removeFirst());
    assertEquals(Integer.valueOf(10), deq.removeFirst());
    assertEquals(Integer.valueOf(18), deq.removeFirst());
  }

  @Test
  public void size4put() {
    BSTMap<String,Integer> map = new BSTMap<>();
    map.put("croissant", 100);
    map.put("pain chocolat", 50);
    map.put("gateau", 71);
    map.put("profiterole", 200);
    assertTrue(map.containsKey("gateau"));
    assertTrue(map.containsKey("profiterole"));
    assertFalse(map.containsKey("twinkies"));
    assertEquals(4, map.size());
    assertEquals(Integer.valueOf(200), map.get("profiterole"));
    assertEquals(Integer.valueOf(50), map.get("pain chocolat"));
    assertEquals(Integer.valueOf(100), map.get("croissant"));
    assertEquals(Integer.valueOf(71), map.get("gateau"));
    assertNull(map.get("scone"));
  }

  @Test
  public void duplicates() {
    BSTMap<Integer,String> map = new BSTMap<>();
    map.put(10, "bagel");
    map.put(10, "bagel");
    assertEquals(1, map.size());
    assertEquals("bagel", map.get(10));
    assertNull(map.get(15));
    assertTrue(map.containsKey(10));
    assertFalse(map.containsKey(5));
  }

  @Test
  public void randomIntsWithRemovals() {
    BSTMap<Integer,Integer> map = new BSTMap<>();
    map.put(10, 1);
    map.put(11, 741130);
    map.remove(10);
    map.put(12, 670647);
    assertNull(map.get(10));
    assertFalse(map.containsKey(10));
  }
}