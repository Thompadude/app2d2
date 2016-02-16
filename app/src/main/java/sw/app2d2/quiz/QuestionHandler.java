package sw.app2d2.quiz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sw.app2d2.quiz.questions.ChewbaccaInsult;
import sw.app2d2.quiz.questions.DarthVaderQuote;
import sw.app2d2.quiz.questions.FirstWordReturnOfTheJedi;
import sw.app2d2.quiz.questions.ForceAwakesBattleship;
import sw.app2d2.quiz.questions.LeiasHomePlanet;
import sw.app2d2.quiz.questions.Question;
import sw.app2d2.quiz.questions.ReturnOfTheJediWorkingTitle;
import sw.app2d2.quiz.questions.StarWarsReleaseDate;
import sw.app2d2.quiz.questions.StormTrooperWeapon;
import sw.app2d2.quiz.questions.TieFighterSound;
import sw.app2d2.quiz.questions.YodasFirstQuestion;

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
        this.questions.add(new ChewbaccaInsult());
        this.questions.add(new ForceAwakesBattleship());
        this.questions.add(new YodasFirstQuestion());
    }

    public List<Question> getQuestions() {
        return questions;
    }

}