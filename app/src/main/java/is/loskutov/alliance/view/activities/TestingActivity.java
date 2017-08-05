package is.loskutov.alliance.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import is.loskutov.alliance.R;
import is.loskutov.alliance.model.Category;
import is.loskutov.alliance.system.Api;
import is.loskutov.alliance.system.ApiResult;

public class TestingActivity extends AppCompatActivity implements ApiResult {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Intent intent = getIntent();
        Category category = intent.getParcelableExtra("category");

        Api api = new Api<>(this);

        api.execute("getTesting");
    }

    @Override
    public void processFinish(ArrayList output) {

    }
}