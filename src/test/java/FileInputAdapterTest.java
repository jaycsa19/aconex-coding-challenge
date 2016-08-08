import com.aconex.inputadapter.FileInputAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FileInputAdapterTest {
    FileInputAdapter inputAdapter;

    @Before
    public void setup() {
        inputAdapter = new FileInputAdapter();
    }

    @Test
    public void testReturnNullIfInputIsNullReadData() {
        Assert.assertEquals(null, inputAdapter.readData(null));
    }

    @Test
    public void readData() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Set<String> actual = inputAdapter.readData(loader.getResourceAsStream("test-dictionary.txt"));
        Set<String> expected = new HashSet<String>();
        expected.add("abc");
        expected.add("ef");
        expected.add("#rt");
        Assert.assertEquals(expected, actual);
    }

}
