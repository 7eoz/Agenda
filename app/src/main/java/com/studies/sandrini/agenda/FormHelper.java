package com.studies.sandrini.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.studies.sandrini.agenda.model.Student;

/**
 * Created by Sandrini on 17/09/2017.
 */

public class FormHelper {
    private RatingBar gradeField;
    private EditText phoneField;
    private EditText adressField;
    private EditText nameField;

    public FormHelper(FormActivity form) {
        nameField = (EditText) form.findViewById(R.id.form_name);
        adressField = (EditText) form.findViewById(R.id.form_adress);
        phoneField = (EditText) form.findViewById(R.id.form_phone);
        gradeField = (RatingBar) form.findViewById(R.id.form_grade);
    }

    public Student getStudent() {
        Student student = new Student();
        student.setName(nameField.getText().toString());
        student.setAdress(adressField.getText().toString());
        student.setPhone(phoneField.getText().toString());
        student.setGrade(Double.valueOf(gradeField.getProgress()));
        return student;
    }
}
