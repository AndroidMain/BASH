package ru.proglive.android1.lesson4.alyoshin.bash_reader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuoteActivity extends Activity {

    private TextView quoteView;
    private Button nextButton;
    private Button prevButton;
    private int currentId = 0;
    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;
    private long timestamp;

    private String[] quotes;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote);

        quoteView = (TextView) findViewById(R.id.textView);
        nextButton = (Button) findViewById(R.id.buttonNext);
        prevButton = (Button) findViewById(R.id.buttonPrev);

        prefs = getPreferences(MODE_PRIVATE);
        prefsEditor = prefs.edit();

        quotes = getResources().getStringArray(R.array.quotes);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            //noinspection ConstantConditions
            currentId = intent.getExtras().getInt("id");
            timestamp = intent.getExtras().getLong("timestamp");
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        long time = prefs.getLong("timestamp", 0);
        if (timestamp < time) {
            currentId = prefs.getInt("id", 0);
            timestamp = time;
        }

        quoteView.setText(quotes[currentId]);

        checkButtonsState();
    }

    @Override
    protected void onPause() {
        super.onPause();

        timestamp = System.currentTimeMillis();

        prefsEditor.putInt("id", currentId);
        prefsEditor.putLong("timestamp", timestamp);
        prefsEditor.commit();
    }

    private void checkButtonsState() {
        if (currentId == 0) {
            prevButton.setEnabled(false);
        } else if (currentId == quotes.length - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
            prevButton.setEnabled(true);
        }
    }

    public void onNextButtonClicked(View v) {
        currentId++;
        quoteView.setText(quotes[currentId]);
        checkButtonsState();
    }

    public void onPrevButtonClicked(View v) {
        currentId--;
        quoteView.setText(quotes[currentId]);
        checkButtonsState();
    }
}