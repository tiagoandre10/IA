import junit.framework.Assert;
import org.junit.Test;

import static src.linesofaction.Rules.GetString;

public class RulesTest {

    @Test
    public void testGetStringA1(){
        Assert Assertions = null;
        Assertions.assertEquals("A1", GetString(0,0));
    }

    @Test
    public void testGetStringD6(){
        Assert Assertions = null;
        Assertions.assertEquals("D6", GetString(3,5));
    }

    @Test
    public void testGetStringG7(){
        Assert Assertions = null;
        Assertions.assertEquals("G7", GetString(6,6));
    }
}
