import com.aconex.inputadapter.ConsoleInputAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Set;

public class ConsoleInputAdapterTest {
    ConsoleInputAdapter inputAdapter;

    @Before
    public void setup() {
        inputAdapter = new ConsoleInputAdapter();
    }

    @Test
    public void testReturnNullIfInputIsNullReadData() {
        Assert.assertEquals(null, inputAdapter.readData(null));
    }

    @Test
    public void testIfDataLoadedTillQReadData() {
        Set<String> actual = inputAdapter.readData(new ByteArrayInputStream("1\n2\nq\n3".getBytes()));
        Set<String> expected = new HashSet<String>();
        expected.add("1");
        expected.add("2");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfDataLoadedTillQuitReadData() {
        Set<String> actual = inputAdapter.readData(new ByteArrayInputStream("1\n2\nquit\n3".getBytes()));
        Set<String> expected = new HashSet<String>();
        expected.add("1");
        expected.add("2");
        Assert.assertEquals(expected, actual);
    }

}
