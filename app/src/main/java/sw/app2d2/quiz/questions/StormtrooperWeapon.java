package sw.app2d2.quiz.questions;

public class StormtrooperWeapon extends Question {


    public StormtrooperWeapon() {
        super(new String[]{"Glock 9", "DH-17", "E-11", "DL-44"}, "What blaster do all Stormtroopers use?");
    }

    @Override
    public boolean isCorrect(String answer) {
        if (answer.equals("E-11")) {
            return true;
        }
        return false;
    }

}