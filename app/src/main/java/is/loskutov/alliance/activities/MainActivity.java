package is.loskutov.alliance.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import is.loskutov.alliance.R;

public class MainActivity extends AppCompatActivity {
    public static final int CATEGORIES = 0, THEMES = 1;
    protected Button byCategories, byThemes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    protected void setViews() {
        byCategories = (Button) findViewById(R.id.by_categories);
        byThemes = (Button) findViewById(R.id.by_themes);

        setOnClickCategories();
        setOnClickThemes();
    }

    protected void setOnClickCategories() {
        byCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(CATEGORIES);
            }
        });
    }

    protected void setOnClickThemes() {
        byThemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIntent(THEMES);
            }
        });
    }

    private void setIntent(int type) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);

        intent.putExtra("type", type);
        startActivity(intent);
    }
}
