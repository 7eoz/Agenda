package com.studies.sandrini.agenda;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.studies.sandrini.agenda.model.Test;


public class TestDetailsFragment extends Fragment {

    TextView subjectField;
    TextView dateField;
    ListView topicsField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test_details, container, false);

        subjectField = (TextView) view.findViewById(R.id.test_subject);
        dateField = (TextView) view.findViewById(R.id.test_date);
        topicsField = (ListView) view.findViewById(R.id.test_topics);

        Bundle params = getArguments();
        if(params != null) {
            Test test = (Test) params.getSerializable("test");
            populateFieldsWith(test);
        }



        return view;
    }

    public void populateFieldsWith(Test test) {
        subjectField.setText(test.getSubject());
        dateField.setText(test.getDate());

        ArrayAdapter<String> topicsAdapter =
                new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, test.getTopics());
        topicsField.setAdapter(topicsAdapter);
    }
}
