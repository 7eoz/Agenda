package com.studies.sandrini.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import com.studies.sandrini.agenda.model.Student;

public class FormHelper {
    private final EditText nameField;
    private final EditText adressField;
    private final EditText phoneField;
    private final EditText siteField;
    private final RatingBar gradeField;
    private Student student;


    public FormHelper(FormActivity form) {
        nameField = (EditText) form.findViewById(R.id.form_name);
        adressField = (EditText) form.findViewById(R.id.form_adress);
        phoneField = (EditText) form.findViewById(R.id.form_phone);
        siteField = (EditText) form.findViewById(R.id.form_site);
        gradeField = (RatingBar) form.findViewById(R.id.form_grade);
        student = new Student();
    }

    public Student getStudent() {
        student.setName(nameField.getText().toString());
        student.setAdress(adressField.getText().toString());
        student.setPhone(phoneField.getText().toString());
        student.setSite(siteField.getText().toString());
        student.setGrade(Double.valueOf(gradeField.getProgress()));
        return student;
    }

    public void fillForm(Student student) {
        nameField.setText(student.getName());
        adressField.setText(student.getAdress());
        phoneField.setText(student.getPhone());
        siteField.setText(student.getSite());
        gradeField.setProgress(student.getGrade().intValue());
        this.student = student;
    }
}
