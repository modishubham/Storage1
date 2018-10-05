package com.example.shubham.storageandroid;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {

    private File file;
    private File myFile;
    private EditText editText;
    private Button button1, button2, button3;
    private RadioGroup radioGroup;
    private RadioButton radioSelection;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        button1 = findViewById(R.id.button1ES);
        button2 = findViewById(R.id.button2ES);
        button3 = findViewById(R.id.button3ES);
        editText = findViewById(R.id.editTextES);
        radioGroup = findViewById(R.id.radioGroup);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = editText.getText().toString();
                int radioId = radioGroup.getCheckedRadioButtonId();
                if (radioId > 0) {
                    radioSelection = findViewById(radioId);
                    String selection = radioSelection.getText().toString();
                    if (selection.equals("Private External Storage")) {
                        saveDataPrivateInExternalStorage(data);
                    } else if (selection.equals("Public External Storage")) {
                        saveDataPublicInExternalStorage(data);
                    } else {
                        Toast.makeText(getBaseContext(), "Please select option", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please select option", Toast.LENGTH_LONG).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                if (radioId > 0) {
                    radioSelection = findViewById(radioId);
                    String selection = radioSelection.getText().toString();
                    if (selection.equals("Private External Storage")) {
                        getDataFromPrivateExternalStorage();
                    } else if (selection.equals("Public External Storage")) {
                        getDataFromPublicExternalStorage();
                    } else {
                        Toast.makeText(getBaseContext(), "Please select option", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Please select option", Toast.LENGTH_LONG).show();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExternalStorageActivity.this, SqlLiteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveDataPrivateInExternalStorage(String data) {
        String myFileName = "myExternalStorageFile.txt";
        try {
            file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), myFileName); // for creating the private external storage
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            editText.setText("");
            Toast.makeText(getBaseContext(), "private data saved successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveDataPublicInExternalStorage(String data) {
        String myPublicExternalFile = "myPublicExternalFile.txt";
        try {
            file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            myFile = new File(file, myPublicExternalFile);
            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
            editText.setText("");
            Toast.makeText(getBaseContext(), "public data saved successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getDataFromPrivateExternalStorage() {
        try {
            fileInputStream = new FileInputStream(file);
            int c;
            String temp = "";
            while ((c = fileInputStream.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            fileInputStream.close();
            editText.setText(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getDataFromPublicExternalStorage() {
        try {
            fileInputStream = new FileInputStream(myFile);
            int c;
            String temp = "";
            while ((c = fileInputStream.read()) != -1) {
                temp = temp + Character.toString((char) c);
            }
            fileInputStream.close();
            editText.setText(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
