package com.studies.sandrini.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.studies.sandrini.agenda.dao.StudentDAO;
import com.studies.sandrini.agenda.model.Student;

import java.io.File;

public class FormActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 567;
    private FormHelper helper;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helper = new FormHelper(this);
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if(student != null) {
            helper.fillForm(student);
        }

        Button imageButton = (Button) findViewById(R.id.form_image_button);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imagePath = getExternalFilesDir(null) + "/" + System.currentTimeMillis();
                File imageFile = new File(imagePath);
                imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                startActivityForResult(imageIntent, CAMERA_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == CAMERA_REQUEST) {
                ImageView imageView = (ImageView) findViewById(R.id.form_image);
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                Bitmap minimizedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                imageView.setImageBitmap(minimizedBitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_form_ok:
                Student student = helper.getStudent();
                StudentDAO dao = new StudentDAO(this);
                if(student.getId() != null){
                    dao.updateStudent(student);
                }else{
                    dao.setStudent(student);
                }
                dao.close();
                Toast.makeText(FormActivity.this, "Student " + student.getName() + " saved", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
