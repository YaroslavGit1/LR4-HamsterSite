package com.example.hamstersite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    EditText login, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginEditText2);
        password = findViewById(R.id.passwordEditText2);

        ImageView imageView = findViewById(R.id.imageView2);

        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri imageUri = intent.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClickEnter(View view) {
        Intent intent = new Intent(this, SiteActivity.class);
        String loginText = login.getText().toString();
        String passwordText = password.getText().toString();
        if (!loginText.isEmpty()) {
            intent.putExtra("login", loginText);
            intent.putExtra("password", passwordText);
            startActivityForResult(intent, 1);
        }
        else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 1) {
            long dateInMillis = data.getLongExtra("date", 0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dateInMillis);

            String date = calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                    (calendar.get(Calendar.MONTH) + 1) + "/" +
                    calendar.get(Calendar.YEAR);

            Toast.makeText(this, " Ваш день рождения " + date, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickOpenPlayMarket(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=com.example.app"));
        startActivity(intent);
    }
}