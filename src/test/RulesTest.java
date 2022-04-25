package src.test;

import junit.framework.Assert;
import org.junit.Test;

import static src.linesofaction.Rules.GetString;

public class RulesTest {

    @Test
    public void testGetStringA8(){
        Assert Assertions = null;
        Assertions.assertEquals("A8", GetString(0,0));
    }

    @Test
    public void testGetStringD2(){
        Assert Assertions = null;
        Assertions.assertEquals("D2", GetString(6,3));
    }

    @Test
    public void testGetStringG1(){
        Assert Assertions = null;
        Assertions.assertEquals("G1", GetString(7,6));
    }

  @Test
  public void testGetStringA6(){
    Assert Assertions = null;
    Assertions.assertEquals("A6", GetString(2,0));
  }

  @Test
  public void testGetStringC4(){
    Assert Assertions = null;
    Assertions.assertEquals("C4", GetString(4,2));
  }
}
