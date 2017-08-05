package is.loskutov.alliance.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Testing {
    private String theme;
    private int numberOfQuestions;
    private Questions[] questions;

    public Testing(JSONObject testing) throws JSONException {
        this.theme = testing.getString("theme");
        this.numberOfQuestions = testing.getInt("number_of_questions");
    }

    public String getTheme() {
        return theme;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }
}
