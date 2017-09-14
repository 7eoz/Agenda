package com.studies.sandrini.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StudentsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        String[] students = {"Leo", "Fer", "Math", "Nick"};
        ListView studentsList = (ListView)findViewById(R.id.students_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, students);
        studentsList.setAdapter(adapter);
    }
}
