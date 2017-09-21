package com.studies.sandrini.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.studies.sandrini.agenda.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandrini on 17/09/2017.
 */

public class StudentDAO extends SQLiteOpenHelper{

    public StudentDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Students(id INTEGER PRIMARY KEY, name TEXT NOT NULL, adress TEXT, phone TEXT, site TEXT, grade REAL)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS  Students";
        db.execSQL(sql);
        onCreate(db);
    }

    public void setStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getContentValues(student);

        db.insert("Students", null, values);
    }

    @NonNull
    private ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("adress", student.getAdress());
        values.put("phone", student.getPhone());
        values.put("site", student.getSite());
        values.put("grade", student.getGrade());
        return values;
    }

    public List<Student> getStudents() {
        String sql = "SELECT * FROM  Students";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Student> students = new ArrayList<Student>();
        while(c.moveToNext()){
            Student student = new Student();
            student.setId(c.getLong(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setAdress(c.getString(c.getColumnIndex("adress")));
            student.setPhone(c.getString(c.getColumnIndex("phone")));
            student.setSite(c.getString(c.getColumnIndex("site")));
            student.setGrade(c.getDouble(c.getColumnIndex("grade")));
            students.add(student);
        }
        c.close();
        return students;
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {student.getId().toString()};
        db.delete("Students", "id = ?", params);
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getContentValues(student);
        String[] params = {student.getId().toString()};
        db.update("Students", values, "id = ?", params);
    }
}
