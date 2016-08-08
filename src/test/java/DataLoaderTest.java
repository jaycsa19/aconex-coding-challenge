import com.aconex.loader.DataLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Set;

public class DataLoaderTest {

    private DataLoader dataLoader;

    @Before
    public void setup() {
        dataLoader = new DataLoader();
    }

    @Test
    public void testLoadDefaultDictionaryIfInputNullLoadDictionaryFile() throws FileNotFoundException {
        Set<String> data = dataLoader.loadDictionaryFile(null);
        Assert.assertEquals(354984, data.size());
    }

    @Test
    public void testLoadDictionaryFile() throws FileNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Set<String> data = dataLoader.loadDictionaryFile(loader.getResourceAsStream("test-dictionary.txt"));
        Assert.assertEquals(3, data.size());
    }

    @Test
    public void testLoadPhoneNumbers() throws FileNotFoundException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Set<String> data = dataLoader.loadPhoneNumbers(loader.getResourceAsStream("test-phone.txt"));
        Assert.assertEquals(4, data.size());
    }

}
