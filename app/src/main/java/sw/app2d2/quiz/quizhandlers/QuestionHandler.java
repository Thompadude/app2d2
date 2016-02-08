package sw.app2d2.quiz.quizhandlers;

import java.util.ArrayList;
import java.util.List;

import sw.app2d2.quiz.questions.FirstWord;
import sw.app2d2.quiz.questions.Question;
import sw.app2d2.quiz.questions.StormtrooperWeapon;

/**
 * Handles the questions classes.
 */
public class QuestionHandler {

    List<Question> questions;

    public QuestionHandler() {
        this.questions = new ArrayList<>();
        this.questions.add(new FirstWord());
        this.questions.add(new StormtrooperWeapon());
    }

    public List<Question> getQuestions() {
        return questions;
    }

}