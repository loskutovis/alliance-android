package is.loskutov.alliance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import is.loskutov.alliance.R;
import is.loskutov.alliance.system.Api;
import is.loskutov.alliance.system.ApiResult;

public class CategoryActivity extends AppCompatActivity implements ApiResult {
    protected Button backButton;
    protected LinearLayout categoriesLayout;
    protected ListView buttonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setViews();
    }

    protected void setViews() {
        backButton = (Button) findViewById(R.id.back_button);
        categoriesLayout = (LinearLayout) findViewById(R.id.categories_layout);
        buttonsList = (ListView) findViewById(R.id.buttons_list);

        Intent intent = getIntent();

        int type = intent.getIntExtra("type", MainActivity.CATEGORIES);

        switch (type) {
            case MainActivity.CATEGORIES:
                String[] buttons = getResources().getStringArray(R.array.categories);

                setButtonsList(buttons);

                break;
            case MainActivity.THEMES:
                Api api = new Api<Integer, String>(this);

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
    public void processFinish(ArrayMap output) {
        String[] buttons = new String[output.size()];

        for (int i = 0; i < output.size(); i++) {
            buttons[i] = output.valueAt(i).toString();
        }

        setButtonsList(buttons);
    }

    protected void setButtonsList(String[] buttons) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.buttons_list_item, buttons);

        buttonsList.setAdapter(adapter);
    }
}