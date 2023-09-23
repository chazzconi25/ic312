import static org.junit.Assert.*;

import org.junit.Test;

public class Part2SanityTest {
  @Test
  public void cap2() {
    BoundedStack<String> stk = new MyBoundedStack<>(2);
    stk.push("what's");
    stk.push("up");
    stk.push("with");
    stk.push("that");
    assertFalse(stk.isEmpty());
    assertEquals("that", stk.pop());
    assertEquals("with", stk.pop());
    assertTrue(stk.isEmpty());
  }

  @Test
  public void changeCap() {
    BoundedStack<Integer> stk = new MyBoundedStack<>(3);
    stk.push(10);
    stk.push(20);
    stk.setCapacity(5);
    stk.push(30);
    stk.push(40);
    assertEquals(40, (int)stk.pop());
    assertEquals(30, (int)stk.pop());
    assertEquals(20, (int)stk.pop());
    assertEquals(10, (int)stk.pop());
    stk.push(100);
    stk.push(200);
    stk.push(300);
    stk.setCapacity(1);
    assertFalse(stk.isEmpty());
    assertEquals(300, (int)stk.pop());
    assertTrue(stk.isEmpty());
  }

  @Test
  public void clear() {
    BoundedStack<Integer> stk = new MyBoundedStack<>(2);
    stk.push(8);
    stk.push(4);
    assertFalse(stk.isEmpty());
    stk.clear();
    assertTrue(stk.isEmpty());
    stk.push(5);
    assertFalse(stk.isEmpty());
    assertEquals(5, (int)stk.pop());
    assertTrue(stk.isEmpty());
  }
}