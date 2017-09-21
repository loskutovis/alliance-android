package is.loskutov.alliance.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    int testingProgress = 0, amount = 0, correctAnswers = 0;

    Questions[] questions;
    Category category;
    Testing testing;

    TextView question, theme, numberOfQuestions;
    RadioButton answer1, answer2, answer3;
    Button nextButton;

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

        nextButton = (Button) findViewById(R.id.next_button);

        Intent intent = getIntent();
        category = intent.getParcelableExtra("category");

        Api<Testing, Category> api = new Api<>(this);
        ApiMethod<Category> method = new ApiMethod<>("getTesting", category);

        api.execute(method);

        getNextQuestion();
    }

    public void getNextQuestion() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questions[testingProgress].setUserAnswer(getUserAnswer(testingProgress));
                testingProgress++;

                if (testingProgress < questions.length) {
                        answer1.setChecked(true);
                        setQuestionFields(testingProgress);
                }
                else {
                    Intent intent = new Intent(TestingActivity.this, ResultActivity.class);

                    intent.putExtra("questions", (Parcelable[]) questions);
                    intent.putExtra("theme", testing.getTheme());
                    intent.putExtra("number_of_questions", amount);
                    intent.putExtra("correct_answers", correctAnswers);

                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void processFinish(ArrayList output) {
        testing = (Testing) output.get(0);
        amount = testing.getNumberOfQuestions();
        questions = testing.getQuestions();

        theme.setText(testing.getTheme());
        setQuestionFields(testingProgress);
    }

    public void setQuestionFields(int number) {
        numberOfQuestions.setText(String.valueOf(testingProgress + 1) + ' ' + getResources().getString(R.string.of) + ' ' +
                String.valueOf(amount));
        question.setText(questions[number].getQuestion());
        answer1.setText(questions[number].getAnswer1());
        answer2.setText(questions[number].getAnswer2());
        answer3.setText(questions[number].getAnswer3());
    }

    public String getUserAnswer(int number) {
        int answerOrder = 0;
        String userAnswer = "";
        RadioButton checkedAnswer = null;

        if (answer1.isChecked()) {
            checkedAnswer = answer1;
            answerOrder = 1;
        }
        else if (answer2.isChecked()) {
            checkedAnswer = answer2;
            answerOrder = 2;
        }
        else if (answer3.isChecked()) {
            checkedAnswer = answer3;
            answerOrder = 3;
        }

        if (checkedAnswer != null) {
            if (questions[number].getRightAnswer() == answerOrder) {
                correctAnswers++;
            }

            userAnswer = (String) checkedAnswer.getText();
        }

        return userAnswer;
    }
}