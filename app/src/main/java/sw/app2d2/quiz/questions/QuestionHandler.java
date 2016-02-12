package sw.app2d2.quiz.questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the questions classes.
 */
public class QuestionHandler implements Serializable {

    private List<Question> questions;

    public QuestionHandler() {
        this.questions = new ArrayList<>();

        this.questions.add(new FirstWord());
        this.questions.add(new StormTrooperWeapon());
        this.questions.add(new StarWarsReleaseDate());
        this.questions.add(new Leia());
        this.questions.add(new ReturnOfTheJedi());
    }

    public List<Question> getQuestions() {
        return questions;
    }

}