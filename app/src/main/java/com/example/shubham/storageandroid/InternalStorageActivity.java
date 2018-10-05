package com.example.shubham.storageandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class InternalStorageActivity extends AppCompatActivity {

    EditText editText;
    Button button1,button2,button3;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        button1 = findViewById(R.id.button1IS);
        button2 = findViewById(R.id.button2IS);
        button3 = findViewById(R.id.button3IS);
        editText = findViewById(R.id.editTextIS);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                saveDataInInternalStorage(data);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatafromInternalStorage();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InternalStorageActivity.this,ExternalStorageActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveDataInInternalStorage(String data) {
        try {
            fileOutputStream = openFileOutput("myInternalStorageFile.txt",MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            editText.setText("");
            Toast.makeText(getBaseContext(),"data inserted successfully",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getDatafromInternalStorage() {
        try {
            fileInputStream = openFileInput("myInternalStorageFile.txt");
            int c;
            String temp = "";
            while ((c=fileInputStream.read())!=-1) {
                temp = temp+Character.toString((char)c);
            }
            fileInputStream.close();
            editText.setText(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
