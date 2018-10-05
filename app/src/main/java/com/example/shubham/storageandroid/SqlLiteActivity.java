package com.example.shubham.storageandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SqlLiteActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    Button button1, button2, button3;

    MyHandler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);

        editText1 = findViewById(R.id.et1_sqllitename);
        editText2 = findViewById(R.id.et2_sqlliteage);
        editText3 = findViewById(R.id.et3_sqlliteaddress);
        editText4 = findViewById(R.id.et4_sqllitesearch);

        button1 = findViewById(R.id.btn1_sqllite);
        button2 = findViewById(R.id.btn2_sqllite);
        button3 = findViewById(R.id.btn3_sqllite);

        myHandler = new MyHandler(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                String Age = editText2.getText().toString();
                String address = editText3.getText().toString();
                if (name.isEmpty() || address.isEmpty() || Age.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Fields can not empty", Toast.LENGTH_LONG).show();
                    //editText1.setError("not empty");
                } else {
                    int age = Integer.parseInt(String.valueOf(editText2.getText()));
                    long id = myHandler.addPerson(new Person(name, age, address));
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    Toast.makeText(getBaseContext(), "Data successfully inserted ID-" + id, Toast.LENGTH_LONG).show();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = editText4.getText().toString();
                if (searchName.isEmpty()) {
                    Toast.makeText(getBaseContext(), "Name can not empty", Toast.LENGTH_LONG).show();
                } else {
                    Person person = myHandler.getPerson(searchName);
                    if (person != null) {
                        String id = String.valueOf(person.get_id());
                        String name = person.get_name();
                        String age = String.valueOf(person.get_age());
                        String address = person.get_address();
                        editText1.setText(name);
                        editText2.setText(age);
                        editText3.setText(address);
                        Log.e("data", "" + id);
                    } else {
                        Toast.makeText(getBaseContext(), "No data found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deleteName = editText4.getText().toString();
                long id = myHandler.deletePerson(deleteName);
                if (id > 0) {
                    Toast.makeText(getBaseContext(), "Data deleted successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), "No data found", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
