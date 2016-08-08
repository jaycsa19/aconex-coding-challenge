import com.aconex.loader.ArgumentParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ArgumentParserTest {
    @Test
    public void testForInputDictionaryFileProcessArgs() throws FileNotFoundException {
        String currentDir = System.getProperty("user.dir");
        String[] args = new String[]{"-d", currentDir + "/src/test/resources/test-dictionary.txt"};
        ArgumentParser argumentParser = new ArgumentParser(args);
        Assert.assertTrue(argumentParser.dictionaryInputStream != null);
        Assert.assertEquals(null, argumentParser.phoneNumberInputStream);
    }

    @Test
    public void testForInputPhoneFileProcessArgs() throws FileNotFoundException {
        String currentDir = System.getProperty("user.dir");
        String[] args = new String[]{"-p", currentDir + "/src/test/resources/test-phone.txt"};
        ArgumentParser argumentParser = new ArgumentParser(args);
        Assert.assertTrue(argumentParser.phoneNumberInputStream != null);
        Assert.assertEquals(null, argumentParser.dictionaryInputStream);
    }

    @Test
    public void testForInvalidPhoneFileProcessArgs() throws FileNotFoundException {
        String currentDir = System.getProperty("user.dir");
        String[] args = new String[]{"-p", "phonefile"};
        ArgumentParser argumentParser = new ArgumentParser(args);
        Assert.assertEquals(null, argumentParser.phoneNumberInputStream);
        Assert.assertEquals(null, argumentParser.dictionaryInputStream);
    }

    @Test
    public void testForInvalidDictionaryFileProcessArgs() throws FileNotFoundException {
        String currentDir = System.getProperty("user.dir");
        String[] args = new String[]{"-d", "dictfile"};
        ArgumentParser argumentParser = new ArgumentParser(args);
        Assert.assertEquals(null, argumentParser.phoneNumberInputStream);
        Assert.assertEquals(null, argumentParser.dictionaryInputStream);
    }
}
