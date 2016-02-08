package sw.app2d2.quiz.quizhandlers;

import java.util.ArrayList;
import java.util.List;

import sw.app2d2.quiz.questions.FirstWord;
import sw.app2d2.quiz.questions.Question;
import sw.app2d2.quiz.questions.StormtrooperWeapon;

public class QuestionHandler {

    List<Question> questions;
    int questionsSize;

    public QuestionHandler() {
        this.questions = new ArrayList<>();
        this.questions.add(new FirstWord());
        this.questions.add(new StormtrooperWeapon());
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getQuestionsSize() {
        return questions.size();
    }

}