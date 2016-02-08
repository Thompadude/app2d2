package sw.app2d2.quiz.questions;

public class FirstWord extends Question {

    public FirstWord() {
        super(new String[]{"Darth Vader", "Luke", "Death Star", "The"}, "What is the first word of the opening crawl of Star Wars: Return of the Jedi?");
    }

    @Override
    public boolean isCorrect(String answer) {
        if (answer.equals("Luke")) {
            return true;
        }
        return false;
    }

}