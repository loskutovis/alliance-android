package is.loskutov.alliance.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import is.loskutov.alliance.R;
import is.loskutov.alliance.system.Api;

public class CategoryActivity extends AppCompatActivity {
    protected Button backButton;
    protected LinearLayout categoriesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setViews();
    }

    protected void setViews() {
        backButton = (Button) findViewById(R.id.back_button);
        categoriesLayout = (LinearLayout) findViewById(R.id.categories_layout);

        Intent intent = getIntent();

        int type = intent.getIntExtra("type", MainActivity.CATEGORIES);

        switch (type) {
            case MainActivity.CATEGORIES:
                Button button4 = new Button(this);
                Button button5 = new Button(this);
                Button button6 = new Button(this);

                setButtonStyle(button4, R.string.category_6);
                setButtonStyle(button5, R.string.category_5);
                setButtonStyle(button6, R.string.category_4);

                break;
            case MainActivity.THEMES:
                Api api = new Api<Integer, String>();

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

    private void setButtonStyle(Button button, int text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)getResources().getDimension(
                R.dimen.button_width), ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, (int) getResources().getDimension(
                R.dimen.button_margin_bottom));

        int padding = (int) getResources().getDimension(
                R.dimen.button_padding);

        button.setText(text);
        button.setPadding(padding, padding, padding, padding);
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));
        button.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));

        categoriesLayout.addView(button, 0, params);
    }
}
