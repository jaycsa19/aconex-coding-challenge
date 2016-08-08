import com.aconex.Dictionary;
import com.aconex.encoding.NumberEncoding;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DictionaryTest {

    private NumberEncoding numberEncoding;
    private Dictionary dictionary;

    @Before
    public void setup() {
        numberEncoding = mock(NumberEncoding.class);
        when(numberEncoding.getLetterToNumberEncoding('a')).thenReturn(1);
        when(numberEncoding.getLetterToNumberEncoding('c')).thenReturn(2);
        when(numberEncoding.getLetterToNumberEncoding('b')).thenReturn(null);
        when(numberEncoding.getLetterToNumberEncoding('p')).thenReturn(1);
        when(numberEncoding.getLetterToNumberEncoding('j')).thenReturn(0);
    }

    @Test
    public void testReturnWordIfMatchingGetNumberEncodingOfWord() {
        Set<String> words = new HashSet<String>();
        words.add("ac");
        dictionary = new Dictionary(words, numberEncoding);
        Set<String> actual = dictionary.getMatchedWords("12");
        Set<String> expected = new HashSet<String>();
        expected.add("ac");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReturnMultipleWordsIfMatchingGetMatchedWords() {
        Set<String> words = new HashSet<String>();
        words.add("ac");
        words.add("pc");
        dictionary = new Dictionary(words, numberEncoding);
        Set<String> actual = dictionary.getMatchedWords("12");
        Set<String> expected = new HashSet<String>();
        expected.add("ac");
        expected.add("pc");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReturnNullIfNoMatchedWordGetMatchedWords() {
        Set<String> words = new HashSet<String>();
        words.add("me");
        dictionary = new Dictionary(words, numberEncoding);
        Set<String> actual = dictionary.getMatchedWords("12");
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testNullIfEmptyDictionaryGetMatchedWords() {
        Set<String> words = new HashSet<String>();
        dictionary = new Dictionary(words, numberEncoding);
        Set<String> actual = dictionary.getMatchedWords("12");
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testWordNotReturnedIfNoEncodingForLetterGetMatchedWords() {
        Set<String> words = new HashSet<String>();
        words.add("abc");
        words.add("ajc");
        dictionary = new Dictionary(words, numberEncoding);
        Set<String> actual = dictionary.getMatchedWords("102");
        Set<String> expected = new HashSet<String>();
        expected.add("ajc");
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(!actual.contains("abc"));
    }

    @Test
    public void testReturnWordsThatExistInDictionaryGetMatchedWords() {
        Set<String> words = new HashSet<String>();
        words.add("ac");
        dictionary = new Dictionary(words, numberEncoding);
        Set<String> actual = dictionary.getMatchedWords("12");
        Set<String> expected = new HashSet<String>();
        expected.add("ac");
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(!actual.contains("pc"));
    }

}
