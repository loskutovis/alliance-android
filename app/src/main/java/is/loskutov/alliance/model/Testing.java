package is.loskutov.alliance.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Testing {
    private String theme;
    private int numberOfQuestions;
    private Questions[] questions;

    public Testing(JSONObject testing) throws JSONException {
        this.theme = testing.getString("theme");
        this.numberOfQuestions = testing.getInt("number_of_questions");
        this.questions = new Questions[this.numberOfQuestions];

        JSONArray jsonQuestions = testing.getJSONArray("questions");

        Log.d("Length", String.valueOf(jsonQuestions.length()));

        for (int i = 0; i < jsonQuestions.length(); i++) {
            this.questions[i] = new Questions(jsonQuestions.getJSONObject(i));
        }
    }

    public String getTheme() {
        return theme;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public Questions[] getQuestions() {
        return questions;
    }
}
