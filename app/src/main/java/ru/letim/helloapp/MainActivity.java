package ru.letim.helloapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textViewFullWelcome);
        Button button = findViewById(R.id.buttonWelcome);
        EditText editText = findViewById(R.id.editTextWelcome);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        String welcomeText = data.getStringExtra(OtherActivity.WELCOME_TEXT);
                        String name = data.getStringExtra(NAME);
                        String welcomeFull = welcomeText + ", " + name;
                        textView.setText(welcomeFull);
                    }
                }
        );

        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
            intent.putExtra(OtherActivity.WELCOME_TEXT, editText.getText().toString());
            activityResultLauncher.launch(intent);
        });
    }
}