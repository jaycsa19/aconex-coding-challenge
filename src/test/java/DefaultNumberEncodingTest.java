import com.aconex.encoding.DefaultNumberEncoding;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DefaultNumberEncodingTest {

    private DefaultNumberEncoding defaultNumberEncoding;

    @Before
    public void setup() {
        defaultNumberEncoding = new DefaultNumberEncoding();
    }

    @Test
    public void testGetNumberEncodingOfWord() {
        Integer number = defaultNumberEncoding.getLetterToNumberEncoding('b');
        Assert.assertEquals(new Integer(2), number);
    }

    @Test
    public void testNullIfNoEncodingGetNumberEncodingOfWord() {
        Integer number = defaultNumberEncoding.getLetterToNumberEncoding('#');
        Assert.assertEquals(null, number);
    }
}
