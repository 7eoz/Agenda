package com.studies.sandrini.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studies.sandrini.agenda.R;
import com.studies.sandrini.agenda.StudentsList;
import com.studies.sandrini.agenda.model.Student;

import java.util.List;

/**
 * Created by Sandrini on 07/10/2017.
 */

public class StudentAdapter extends BaseAdapter {
    private final List<Student> students;
    private final Context context;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }


    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = students.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView nameField = (TextView) view.findViewById(R.id.student_name);
        TextView phoneField = (TextView) view.findViewById(R.id.student_phone);
        ImageView imageField = (ImageView) view.findViewById(R.id.student_image);
        TextView adressField = (TextView) view.findViewById(R.id.student_adress);
        TextView siteField = (TextView) view.findViewById(R.id.student_site);


        nameField.setText(student.getName());
        phoneField.setText(student.getPhone());
        if (adressField != null) {
            adressField.setText(student.getAdress());
        }
        if (siteField != null) {
            siteField.setText(student.getSite());
        }
        if(student.getImagePath() != null){
            Bitmap bitmap = BitmapFactory.decodeFile(student.getImagePath());
            Bitmap minimizedBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            imageField.setImageBitmap(minimizedBitmap);
            imageField.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return view;
    }
}
