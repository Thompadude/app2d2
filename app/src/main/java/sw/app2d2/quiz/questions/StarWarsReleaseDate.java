package sw.app2d2.quiz.questions;

public class StarWarsReleaseDate extends Question {

    public StarWarsReleaseDate() {
        super(new String[]{"1999", "1969", "1977", "1980"}, "Pick the correct release date for Star Wars Episode IV - A New Hope.");
        setCorrectAnswer("Luke");
    }

}