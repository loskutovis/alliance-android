package is.loskutov.alliance.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Questions {
    private int id, number, rightAnswer, theme;
    private char category4, category5, category6;
    private String question, answer1, answer2, answer3, category;

    Questions(JSONObject question) throws JSONException {
        this.id = question.getInt("id");
        this.number = question.getInt("number");
        this.rightAnswer = question.getInt("right_answer");
        this.theme = question.getInt("theme");

        this.category4 = question.getString("category4").toCharArray()[0];
        this.category5 = question.getString("category5").toCharArray()[0];
        this.category6 = question.getString("category6").toCharArray()[0];

        this.category = question.getString("category");
        this.question = question.getString("question");
        this.answer1 = question.getString("answer1");
        this.answer2 = question.getString("answer2");
        this.answer3 = question.getString("answer3");
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getTheme() {
        return theme;
    }

    public char getCategory4() {
        return category4;
    }

    public char getCategory5() {
        return category5;
    }

    public char getCategory6() {
        return category6;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getCategory() {
        return category;
    }
}
