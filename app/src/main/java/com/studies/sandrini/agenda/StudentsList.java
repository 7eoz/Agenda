package com.studies.sandrini.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.studies.sandrini.agenda.dao.StudentDAO;
import com.studies.sandrini.agenda.model.Student;

import java.util.List;

public class StudentsList extends AppCompatActivity {

    private ListView studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
        studentsList = (ListView)findViewById(R.id.students_list);
        Button newStudent = (Button) findViewById(R.id.new_student);
        newStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForm = new Intent(StudentsList.this, FormActivity.class);
                startActivity(intentForm);
            }
        });

        studentsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View item, int position, long id) {
                Student student = (Student) studentsList.getItemAtPosition(position);
                Intent goForm = new Intent(StudentsList.this, FormActivity.class);
                goForm.putExtra("student", student);
                startActivity(goForm);
                //Toast.makeText(getApplicationContext(), "Student " + student.getName() + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(studentsList);
        }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        StudentDAO dao = new StudentDAO(this);
        List<Student> students = dao.getStudents();
        dao.close();

       
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        studentsList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Delete");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) studentsList.getItemAtPosition(info.position);
                StudentDAO dao = new StudentDAO(StudentsList.this);
                dao.deleteStudent(student);
                dao.close();
                Toast.makeText(getApplicationContext(),"Student " + student.getName() + " deleted", Toast.LENGTH_SHORT).show();
                loadList();
                return false;
            }
        });
    }
}

