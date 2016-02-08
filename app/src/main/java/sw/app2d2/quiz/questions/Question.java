package sw.app2d2.quiz.questions;

public abstract class Question implements CorrectAnswer {

    private String[] alternatives;
    private String question;

    public Question(String[] alternatives, String question) {
        this.alternatives = alternatives;
        this.question = question;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public String getQuestion() {
        return question;
    }

}