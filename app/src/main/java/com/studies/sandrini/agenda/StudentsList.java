package com.studies.sandrini.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.studies.sandrini.agenda.dao.StudentDAO;
import com.studies.sandrini.agenda.model.Student;

import java.util.List;

public class StudentsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        StudentDAO dao = new StudentDAO(this);
        List<Student> students = dao.getStudents();
        dao.close();

        ListView studentsList = (ListView)findViewById(R.id.students_list);
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        studentsList.setAdapter(adapter);

        Button newStudent = (Button) findViewById(R.id.new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForm = new Intent(StudentsList.this, FormActivity.class);
                startActivity(intentForm);
            }
        });
        }
    }

