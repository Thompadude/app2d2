package sw.app2d2.quotes;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

// JUnit4
public class YodaQuotesTest {

    YodaQuotes yodaQuotes;

    @Before
    public void setUp() throws Exception {
        yodaQuotes = new YodaQuotes();
    }

    @Test
    public void testGenerateYodaQuote() throws Exception {
        String quote;

        quote = yodaQuotes.getRandomYodaQuote();
        Assert.assertNotNull(yodaQuotes.generateYodaQuote(quote));

        // Make sure same yoda quote do not display twice in a row.
        for (int i = 0; i < 1000; i++) {
            quote = yodaQuotes.getRandomYodaQuote();
            Assert.assertNotSame(quote, yodaQuotes.generateYodaQuote(quote));
        }
    }

    @Test
    public void testGetRandomYodaQuote() throws Exception {
        Assert.assertNotNull(yodaQuotes.getRandomYodaQuote());
    }

}