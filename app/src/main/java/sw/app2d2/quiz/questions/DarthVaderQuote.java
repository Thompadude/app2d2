package sw.app2d2.quiz.questions;

public class DarthVaderQuote extends Question {

    public DarthVaderQuote() {
        super(new String[]{"I’ve been waiting for you, Obi-Wan", "Luke, I am your father", "Together we can rule the galaxy", "I find your lack of faith disturbing"}, "Which of these Darth Vader quotes is wrong?");
        setCorrectAnswer("Luke, I am your father");
    }
}
