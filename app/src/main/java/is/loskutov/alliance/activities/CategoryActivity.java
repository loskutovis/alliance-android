package is.loskutov.alliance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import is.loskutov.alliance.R;
import is.loskutov.alliance.models.Themes;
import is.loskutov.alliance.system.Api;
import is.loskutov.alliance.system.ApiResult;
import is.loskutov.alliance.system.CategoryAdapter;

public class CategoryActivity extends AppCompatActivity implements ApiResult {
    protected Button backButton;
    protected LinearLayout categoriesLayout;
    protected RecyclerView buttonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setViews();
    }

    protected void setViews() {
        backButton = (Button) findViewById(R.id.back_button);
        categoriesLayout = (LinearLayout) findViewById(R.id.categories_layout);
        buttonsList = (RecyclerView) findViewById(R.id.buttons_list);

        Intent intent = getIntent();

        int type = intent.getIntExtra("type", MainActivity.CATEGORIES);

        switch (type) {
            case MainActivity.CATEGORIES:
                String[] buttons = getResources().getStringArray(R.array.categories);

                setButtonsList(buttons);

                break;
            case MainActivity.THEMES:
                Api api = new Api<>(this);

                api.execute("getThemes");

                break;
        }

        setBackButtonOnClick();
    }

    protected void setBackButtonOnClick() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void processFinish(ArrayList output) {
        String[] buttons = new String[output.size()];

        for (int i = 0; i < output.size(); i++) {
            Themes theme = (Themes) output.get(i);

            buttons[i] = theme.getName();
        }

        setButtonsList(buttons);
    }

    protected void setButtonsList(String[] buttons) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        CategoryAdapter adapter = new CategoryAdapter(buttons);

        buttonsList.setLayoutManager(layoutManager);
        buttonsList.setAdapter(adapter);
    }
}