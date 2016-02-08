package sw.app2d2.quiz.questions;

public abstract class Question implements CorrectAnswer {

    private String[] alternatives;
    private String question;

    /**
     * @param alternatives is the answer alternatives the user has to choose from.
     * @param question is the actual question the user is asked to answer.
     */
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