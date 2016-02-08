package sw.app2d2.quiz.quizhandlers;

/**
 * Handles the user (client) in the quiz section.
 */
public class UserHandler {

    private String userName;
    private int score;

    public UserHandler(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public void addPoint() {
        this.score++;
    }

}