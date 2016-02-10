package sw.app2d2.quiz.questions;

import java.io.Serializable;

public abstract class Question implements Serializable {

    private boolean isAnsweredCorrect;
    private String[] alternatives;
    private String question;
    private String correctAnswer;

    /**
     * @param alternatives is the answer alternatives the user has to choose from.
     * @param question     is the actual question the user is asked to answer.
     */
    public Question(String[] alternatives, String question) {
        this.alternatives = alternatives;
        this.question = question;
    }

    public boolean isCorrect(String answer) {
        if (answer.equals(getCorrectAnswer())) {
            setIsAnsweredCorrect(true);
            return true;
        }
        return false;
    }

    public boolean isAnsweredCorrect() {
        return isAnsweredCorrect;
    }

    public void setIsAnsweredCorrect(boolean isAnsweredCorrect) {
        this.isAnsweredCorrect = isAnsweredCorrect;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}