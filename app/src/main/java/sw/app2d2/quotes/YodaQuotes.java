package sw.app2d2.quotes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class YodaQuotes {

    List<String> yodaQuotes;
    Random random;

    public YodaQuotes() {
        yodaQuotes = new ArrayList<>();
        yodaQuotes.add("\"Fear is the path to the dark side. Fear leads to anger. Anger leads to hate. Hate leads to suffering.\"");
        yodaQuotes.add("\"Powerful you have become, the dark side I sense in you.\"");
        yodaQuotes.add("\"PATIENCE YOU MUST HAVE my young padawan\"");
        yodaQuotes.add("\"If you end your training now — if you choose the quick and easy path as Vader did — you will become an agent of evil.\"");
        yodaQuotes.add("\"You must unlearn what you have learned.\"");
        yodaQuotes.add("\"When you look at the dark side, careful you must be. For the dark side looks back.\"");
        yodaQuotes.add("\"The fear of loss is a path to the Dark Side.\"");
        yodaQuotes.add("\"Always two there are, no more, no less. A master and an apprentice.\"");
        yodaQuotes.add("\"The dark side clouds everything. Impossible to see the future is.\"");
        yodaQuotes.add("\"I cannot teach him. The boy has no patience.\"");
        yodaQuotes.add("\"When nine hundred years old you reach, look as good you will not.\"");
        yodaQuotes.add("\"A Jedi uses the Force for knowledge and defense, never for attack.\"");
        yodaQuotes.add("\"Adventure. Excitement. A Jedi craves not these things.\"");
        yodaQuotes.add("\"Judge me by my size, do you?\"");
        yodaQuotes.add("\"Do. Or do not. There is no try.\"");
    }

    /**
     * Generate a new random Yoda quote and never generates the same quote twice in a row.
     *
     * @param oldQuote the old quote to compare the new quote with.
     * @return the new quote.
     */
    public String generateYodaQuote(String oldQuote) {
        String quote;
        do {
            quote = getRandomYodaQuote();
        } while (quote.equals(oldQuote));
        return quote;
    }

    /**
     * Get a random Yoda quote from the yodaQuotes List.
     *
     * @return the random yoda quote.
     */
    public String getRandomYodaQuote() {
        random = new Random();
        int randomIndex = random.nextInt(yodaQuotes.size());
        return yodaQuotes.get(randomIndex);
    }

}