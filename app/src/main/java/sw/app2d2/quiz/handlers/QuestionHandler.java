package sw.app2d2.quiz.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sw.app2d2.quiz.questions.FirstWord;
import sw.app2d2.quiz.questions.Question;
import sw.app2d2.quiz.questions.StarWarsReleaseDate;
import sw.app2d2.quiz.questions.StormTrooperWeapon;

/**
 * Handles the questions classes.
 */
public class QuestionHandler implements Serializable {

    private List<Question> questions;

    public QuestionHandler() {
        this.questions = new ArrayList<>();
        Question firstWord = new FirstWord();
        Question trooperWeapon = new StormTrooperWeapon();
        Question releaseDate = new StarWarsReleaseDate();

        this.questions.add(firstWord);
        this.questions.add(trooperWeapon);
        this.questions.add(releaseDate);
    }

    public List<Question> getQuestions() {
        return questions;
    }

}