package is.loskutov.alliance.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import is.loskutov.alliance.R;
import is.loskutov.alliance.model.ApiMethod;
import is.loskutov.alliance.model.Category;
import is.loskutov.alliance.model.Questions;
import is.loskutov.alliance.model.Testing;
import is.loskutov.alliance.system.Api;
import is.loskutov.alliance.system.ApiResult;

public class TestingActivity extends AppCompatActivity implements ApiResult {
    int testingProgress = 1;
    TextView question, theme, numberOfQuestions;
    RadioButton answer1, answer2, answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        setView();
    }

    public void setView() {
        theme = (TextView) findViewById(R.id.theme);
        numberOfQuestions = (TextView) findViewById(R.id.number_of_questions);
        question = (TextView) findViewById(R.id.question);

        answer1 = (RadioButton) findViewById(R.id.answer1);
        answer2 = (RadioButton) findViewById(R.id.answer2);
        answer3 = (RadioButton) findViewById(R.id.answer3);

        Intent intent = getIntent();
        Category category = intent.getParcelableExtra("category");

        Api<Testing, Category> api = new Api<>(this);
        ApiMethod<Category> method = new ApiMethod<>("getTesting", category);

        api.execute(method);
    }

    @Override
    public void processFinish(ArrayList output) {
        Testing testing = (Testing) output.get(0);
        Questions[] questions = testing.getQuestions();

        theme.setText(testing.getTheme());
        numberOfQuestions.setText(String.valueOf(testingProgress) + ' ' + getResources().getString(R.string.of) + ' ' +
                String.valueOf(testing.getNumberOfQuestions()));
        question.setText(questions[0].getQuestion());
        answer1.setText(questions[0].getAnswer1());
        answer2.setText(questions[0].getAnswer2());
        answer3.setText(questions[0].getAnswer3());
    }
}