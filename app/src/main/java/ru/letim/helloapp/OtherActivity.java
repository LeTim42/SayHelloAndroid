package ru.letim.helloapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {
    public static final String WELCOME_TEXT = "welcome_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        TextView textView = findViewById(R.id.textViewWelcome);
        Button button = findViewById(R.id.buttonName);
        EditText editText = findViewById(R.id.editTextName);

        Intent intent = getIntent();
        String welcomeText = intent.getStringExtra(WELCOME_TEXT);
        textView.setText(welcomeText);

        button.setOnClickListener(view -> {
            Intent result = new Intent();
            result.putExtra(WELCOME_TEXT, welcomeText);
            result.putExtra(MainActivity.NAME, editText.getText().toString());
            setResult(MainActivity.RESULT_OK, result);
            finish();
        });
    }
}