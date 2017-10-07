package com.studies.sandrini.agenda;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.studies.sandrini.agenda.model.Student;

public class FormHelper {
    private final EditText nameField;
    private final EditText adressField;
    private final EditText phoneField;
    private final EditText siteField;
    private final RatingBar gradeField;
    private Student student;
    private final ImageView imageField;


    public FormHelper(FormActivity form) {
        nameField = (EditText) form.findViewById(R.id.form_name);
        adressField = (EditText) form.findViewById(R.id.form_adress);
        phoneField = (EditText) form.findViewById(R.id.form_phone);
        siteField = (EditText) form.findViewById(R.id.form_site);
        gradeField = (RatingBar) form.findViewById(R.id.form_grade);
        imageField = (ImageView) form.findViewById(R.id.form_image);
        student = new Student();
    }

    public Student getStudent() {
        student.setName(nameField.getText().toString());
        student.setAdress(adressField.getText().toString());
        student.setPhone(phoneField.getText().toString());
        student.setSite(siteField.getText().toString());
        student.setGrade(Double.valueOf(gradeField.getProgress()));
        student.setImagePath((String) imageField.getTag());
        return student;
    }

    public void fillForm(Student student) {
        nameField.setText(student.getName());
        adressField.setText(student.getAdress());
        phoneField.setText(student.getPhone());
        siteField.setText(student.getSite());
        gradeField.setProgress(student.getGrade().intValue());
        loadImage(student.getImagePath());
        this.student = student;
    }

    public void loadImage(String imagePath) {
        if(imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            Bitmap minimizedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            imageField.setImageBitmap(minimizedBitmap);
            imageField.setScaleType(ImageView.ScaleType.FIT_XY);
            imageField.setTag(imagePath);
        }

    }
}
