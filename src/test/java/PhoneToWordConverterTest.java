import com.aconex.Dictionary;
import com.aconex.PhoneToWordConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneToWordConverterTest {

    private Dictionary dictionary;
    private PhoneToWordConverter phoneToWordConverter;

    @Before
    public void setup() {
        dictionary = mock(Dictionary.class);
        phoneToWordConverter = new PhoneToWordConverter(dictionary);
        Set<String> w1 = new HashSet<String>();
        w1.add("ab");
        w1.add("cd");
        Set<String> w2 = new HashSet<String>();
        w2.add("pq");
        w2.add("rs");
        Set<String> w3 = new HashSet<String>();
        w3.add("x");
        Set<String> w4 = new HashSet<String>();
        w4.add("aby");
        when(dictionary.getMatchedWords("9")).thenReturn(w3);
        when(dictionary.getMatchedWords("12")).thenReturn(w1);
        when(dictionary.getMatchedWords("34")).thenReturn(w2);
        when(dictionary.getMatchedWords("34")).thenReturn(w2);
        when(dictionary.getMatchedWords("6")).thenReturn(w3);
        when(dictionary.getMatchedWords("312")).thenReturn(null);
        when(dictionary.getMatchedWords("341")).thenReturn(null);
        when(dictionary.getMatchedWords("34512")).thenReturn(null);
        when(dictionary.getMatchedWords("345127")).thenReturn(null);
        when(dictionary.getMatchedWords("127")).thenReturn(w4);
        when(dictionary.getMatchedWords("7")).thenReturn(null);
        when(dictionary.getMatchedWords("17")).thenReturn(null);
    }

    @Test
    public void testIfNoDotSeparatorGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("12");
        Set<String> expected = new HashSet<String>();
        expected.add("ab");
        expected.add("cd");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfOneDotSeparatorGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("12.34");
        Set<String> expected = new HashSet<String>();
        expected.add("ab-pq");
        expected.add("cd-pq");
        expected.add("ab-rs");
        expected.add("cd-rs");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfMultipleDotSeparatorsGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("12.34.6");
        Set<String> expected = new HashSet<String>();
        expected.add("ab-pq-x");
        expected.add("cd-pq-x");
        expected.add("ab-rs-x");
        expected.add("cd-rs-x");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfFirstDigitSkipGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("312");
        Set<String> expected = new HashSet<String>();
        expected.add("3-ab");
        expected.add("3-cd");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfLastDigitSkipGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("341");
        Set<String> expected = new HashSet<String>();
        expected.add("pq-1");
        expected.add("rs-1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfDigitSkipGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("345127");
        Set<String> expected = new HashSet<String>();
        expected.add("pq-5-aby");
        expected.add("rs-5-aby");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfDigitSkipInAllDotPhrasesGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("312.341");
        Set<String> expected = new HashSet<String>();
        expected.add("3-ab-pq-1");
        expected.add("3-ab-rs-1");
        expected.add("3-cd-pq-1");
        expected.add("3-cd-rs-1");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfDigitSkipInOneDotPhraseGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("312.34");
        Set<String> expected = new HashSet<String>();
        expected.add("3-ab-pq");
        expected.add("3-ab-rs");
        expected.add("3-cd-pq");
        expected.add("3-cd-rs");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfSingleDigitNumberGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("9");
        Set<String> expected = new HashSet<String>();
        expected.add("x");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfSingleDigitNumberNoMatchGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("7");
        Set<String> expected = new HashSet<String>();
        expected.add("7");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testIfSingleDigitNumberNoMatchWithDotGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("17.9");
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testIfFirstDotGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords(".1");
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testIfLastDotGetMatchedWords() {
        Set<String> actual = phoneToWordConverter.getMatchedWords("9.");
        Set<String> expected = new HashSet<String>();
        expected.add("x");
        Assert.assertEquals(expected, actual);
    }

}
