import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TopKSanityTest {
  @Test
  public void add4get3() {
    TopK<String> tt = new TopK<>(3);
    tt.add("what");
    tt.add("is");
    tt.add("the");
    tt.add("what");
    List<String> top = tt.getTop();
    assertEquals(3, top.size());
    assertEquals("what", top.get(0));
    assertEquals("what", top.get(1));
    assertEquals("the", top.get(2));
  }

  @Test
  public void add3get5() {
    TopK<Integer> tt = new TopK<>(5);
    tt.add(5);
    tt.add(10);
    tt.add(2);
    List<Integer> top = tt.getTop();
    assertEquals(3, top.size());
    assertEquals(10, (int)top.get(0));
    assertEquals(5, (int)top.get(1));
    assertEquals(2, (int)top.get(2));
  }
  
  @Test
  public void doubles100() {
    TopK<Double> tt = new TopK<>(100);
    List<Double> ret = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      Double next = Math.random();
      tt.add(next);
      ret.add(next);
    }
    Collections.sort(ret, Collections.reverseOrder());
    List<Double> top = tt.getTop();
    assertEquals(100, top.size());
    for(int i = 0; i < 100; i++) {
      assertEquals(ret.get(i), top.get(i));
    }
  }
}
