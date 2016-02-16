package sw.app2d2.quiz.questions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the questions classes. If more questions is to be added later,
 * don't forget to add them in the constructor :-)
 */
public class QuestionHandler implements Serializable {

    private List<Question> questions;

    public QuestionHandler() {
        this.questions = new ArrayList<>();

        this.questions.add(new FirstWordReturnOfTheJedi());
        this.questions.add(new StormTrooperWeapon());
        this.questions.add(new StarWarsReleaseDate());
        this.questions.add(new LeiasHomePlanet());
        this.questions.add(new ReturnOfTheJediWorkingTitle());
        this.questions.add(new TieFighterSound());
        this.questions.add(new DarthVaderQuote());
        this.questions.add(new ChewbaccaInslut());
        this.questions.add(new ForceAwakesBattleship());
        this.questions.add(new YodasFirstQuestion());
    }

    public List<Question> getQuestions() {
        return questions;
    }

}