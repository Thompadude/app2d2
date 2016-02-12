package sw.app2d2.quiz;

import java.io.Serializable;

/**
 * Handles the user (client) in the quiz section.
 */
public class User implements Serializable {

    private String userName;
    private int score;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addPoint() {
        this.score++;
    }

}