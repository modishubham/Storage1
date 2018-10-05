package com.example.shubham.storageandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText editText;
    Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("myPreference", MODE_PRIVATE);
        editText = findViewById(R.id.editText);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                saveData(data);
                editText.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InternalStorageActivity.class);
                startActivity(intent);
            }
        });

    }

    public void saveData(String data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data", data);
        editor.commit();
        Toast.makeText(MainActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
    }

    public void getData() {
        String data = sharedPreferences.getString("data", "NA");
        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
        editText.setText(data);
    }
}
