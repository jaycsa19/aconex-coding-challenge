import com.aconex.util.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UtilsTest {

    @Test
    public void testReturnNullIfStringIsNullRemovePunctuationTest() {
        String s = Utils.removePunctuation(null);
        Assert.assertEquals(null, s);
    }

    @Test
    public void testRemovePunctuationAndWhiteSpaceTest() {
        String s = Utils.removePunctuation("flow er,pot^");
        Assert.assertEquals("flowerpot", s);
    }

    @Test
    public void testReturnNullIfStringIsEmptyRemovePunctuationTest() {
        String s = Utils.removePunctuation("");
        Assert.assertEquals(null, s);
    }

    @Test
    public void testReturnNullIfStringHasAllPunctuationRemovePunctuationTest() {
        String s = Utils.removePunctuation("$...,*");
        Assert.assertEquals(null, s);
    }

    @Test
    public void testReturnNullIfStringIsNullRemoveUnwantedCharsFromPhoneTest() {
        String s = Utils.removeUnwantedCharsFromPhone(null);
        Assert.assertEquals(null, s);
    }

    @Test
    public void testRemoveUnwantedCharsFromPhoneTest() {
        String s = Utils.removeUnwantedCharsFromPhone("123.:^45. 4");
        Assert.assertEquals("123.45.4", s);
    }

    @Test
    public void testReturnNullIfStringIsEmptyRemoveUnwantedCharsFromPhoneTest() {
        String s = Utils.removeUnwantedCharsFromPhone("");
        Assert.assertEquals(null, s);
    }

    @Test
    public void testReturnNullIfStringHasAllPunctuationRemoveUnwantedCharsFromPhoneTest() {
        String s = Utils.removeUnwantedCharsFromPhone("$---   ,*");
        Assert.assertEquals(null, s);
    }

    @Test
    public void testReturnHyphenSeparatedStringsCombinePhrasesTest() {
        List<List<String>> input = new ArrayList<List<String>>();
        List<String> phrase1 = new ArrayList<String>();
        phrase1.add("mul");
        phrase1.add("add");
        phrase1.add("sub");
        List<String> digit = new ArrayList<String>();
        digit.add("1");
        List<String> phrase2 = new ArrayList<String>();
        phrase2.add("ab");
        phrase2.add("cd");
        input.add(phrase1);
        input.add(digit);
        input.add(phrase2);
        Set<String> actual = Utils.combinePhrases(input);
        Set<String> expected = new HashSet<String>();
        expected.add("add-1-ab");
        expected.add("mul-1-ab");
        expected.add("sub-1-ab");
        expected.add("mul-1-cd");
        expected.add("add-1-cd");
        expected.add("sub-1-cd");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReturnNULLIfNullListCombinePhrasesTest() {
        Set<String> actual = Utils.combinePhrases(null);
        Assert.assertEquals(null, actual);
    }

    @Test
    public void testReturnSetIfListOfSizeOneCombinePhrasesTest() {
        List<List<String>> input = new ArrayList<List<String>>();
        List<String> phrase1 = new ArrayList<String>();
        phrase1.add("abc");
        input.add(phrase1);
        Set<String> expected = new HashSet<String>();
        expected.add("abc");
        Set<String> actual = Utils.combinePhrases(input);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testReturnNULLIfEmptyListCombinePhrasesTest() {
        List<List<String>> input = new ArrayList<List<String>>();
        Set<String> actual = Utils.combinePhrases(input);
        Assert.assertEquals(null, actual);
    }

}
