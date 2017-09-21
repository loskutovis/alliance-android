package is.loskutov.alliance.view.activities;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import is.loskutov.alliance.R;
import is.loskutov.alliance.model.Questions;
import is.loskutov.alliance.system.ResultAdapter;

public class ResultActivity extends AppCompatActivity {

    RecyclerView results;
    Button startAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setView();
    }

    public void setView() {
        startAgain = (Button) findViewById(R.id.start_again_button);
        results = (RecyclerView) findViewById(R.id.result_list);

        Intent intent = getIntent();
        int amount = intent.getIntExtra("amount", 0);
        int correctAnswers = intent.getIntExtra("correct_answers", 0);
        Parcelable[] parcelableQuestions = intent.getParcelableArrayExtra("questions");
        Questions[] questions = new Questions[parcelableQuestions.length];

        for (int i = 0; i < questions.length; i++) {
            questions[i] = (Questions) parcelableQuestions[i];
            questions[i].setQuestionNumber(i+1);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ResultAdapter adapter = new ResultAdapter(questions, ResultActivity.this);

        results.setLayoutManager(layoutManager);
        results.setAdapter(adapter);

        setStartAgain();
    }

    public void setStartAgain() {
        startAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }
}
