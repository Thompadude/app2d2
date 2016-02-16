package sw.app2d2.quiz.questions;

public class FirstWordReturnOfTheJedi extends Question {

    public FirstWordReturnOfTheJedi() {
        super(new String[]{"Darth Vader", "Luke", "Death Star", "The"}, "What is the first word of the opening crawl of Star Wars: Return of the Jedi?");
        setCorrectAnswer("Luke");
    }

}