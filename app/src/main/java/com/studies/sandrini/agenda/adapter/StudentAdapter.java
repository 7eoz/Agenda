package com.studies.sandrini.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        View view = inflater.inflate(R.layout.list_item, null);

        return view;
    }
}
