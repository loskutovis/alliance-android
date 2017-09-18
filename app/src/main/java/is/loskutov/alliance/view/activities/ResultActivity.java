package is.loskutov.alliance.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import is.loskutov.alliance.R;

public class ResultActivity extends AppCompatActivity {

    Button startAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setView();
    }

    public void setView() {
        startAgain = (Button) findViewById(R.id.start_again_button);

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
