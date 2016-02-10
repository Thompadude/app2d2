package sw.app2d2.quiz.handlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sw.app2d2.quiz.questions.FirstWord;
import sw.app2d2.quiz.questions.Leia;
import sw.app2d2.quiz.questions.Question;
import sw.app2d2.quiz.questions.ReturnOfTheJedi;
import sw.app2d2.quiz.questions.StarWarsReleaseDate;
import sw.app2d2.quiz.questions.StormTrooperWeapon;

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