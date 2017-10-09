package com.studies.sandrini.agenda;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.studies.sandrini.agenda.adapter.StudentAdapter;
import com.studies.sandrini.agenda.converter.StudentConverter;
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

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.RECEIVE_SMS} , 234);
        }

        registerForContextMenu(studentsList);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.students_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.send_grades:
                SendStudentsTask task = new SendStudentsTask(this);
                task.execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    private void loadList() {
        StudentDAO dao = new StudentDAO(this);
        List<Student> students = dao.searchStudents();
        dao.close();


        StudentAdapter adapter = new StudentAdapter(this, students);
        studentsList.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Student student = (Student) studentsList.getItemAtPosition(info.position);

        final MenuItem visitSite = menu.add("Visit site");
        visitSite.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent siteIntent = new Intent(Intent.ACTION_VIEW);

                String site = student.getSite();
                if (!site.startsWith("http://")){
                    site = "http://" + site;
                }
                siteIntent.setData(Uri.parse(site));
                visitSite.setIntent(siteIntent);
                return false;
            }
        });

        final MenuItem sendSMS = menu.add("Send SMS");
        sendSMS.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setData(Uri.parse("sms:" + student.getPhone()));
                sendSMS.setIntent(smsIntent);
                return false;
            }
        });

        MenuItem viewOnMap = menu.add("View on map");
        viewOnMap.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent mapIntent = new Intent(Intent.ACTION_VIEW);
                mapIntent.setData(Uri.parse("geo:0,0?q=" + student.getAdress()));
                return false;
            }
        });

        MenuItem callStudent = menu.add("Call student");
        callStudent.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (ActivityCompat.checkSelfPermission(StudentsList.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(StudentsList.this,
                            new String[]{android.Manifest.permission.CALL_PHONE}, 123);
                }
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + student.getPhone()));

                return false;
            }
        });



        MenuItem delete = menu.add("Delete");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
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

