package com.example.hamstersite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SiteActivity extends AppCompatActivity {


    private DatePicker datePicker;
    private Button confirmButton;
    private TextView textView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        datePicker = findViewById(R.id.datePicker);
        confirmButton = findViewById(R.id.okButton);
        textView = findViewById(R.id.textId);

        Intent intent = getIntent();

        if (intent != null) {
            String login = intent.getStringExtra("login");
            String password = intent.getStringExtra("password");

            textView.setText(String.format(getString(R.string.data), login, password));
        }

        confirmButton.setOnClickListener(v -> {
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            long dateInMillis = calendar.getTimeInMillis();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("date", dateInMillis);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}