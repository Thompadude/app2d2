package sw.app2d2.quiz.questions;

/**
 * Makes sure that a question who implements the interface handles a correct answer.
 */
public interface CorrectAnswer {

    boolean isCorrect(String answer);

    String getCorrectAnswer();
}
