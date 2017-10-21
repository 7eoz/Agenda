package com.studies.sandrini.agenda;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.studies.sandrini.agenda.model.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Sandrini on 19/10/2017.
 */

public class TestsListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tests_list, container, false);

        List<String> englishTopics = Arrays.asList("Subject Types", "Definite Article", "Indefinite Article");
        Test EnglishTest = new Test("English", "10/25/2017", englishTopics);
        List<String> mathTopics = Arrays.asList("Second Degree Equations", "Trigonometry");
        Test MathTest = new Test("Math", "11/15/2017", mathTopics);

        List<Test> tests = Arrays.asList(EnglishTest, MathTest);
        ArrayAdapter<Test> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tests);
        ListView testsList = (ListView) view.findViewById(R.id.tests_list);
        testsList.setAdapter(adapter);

        testsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Test test = (Test) parent.getItemAtPosition(position);
                Intent openDetails = new Intent(getContext(), TestDetailsActivity.class);
                TestsActivity testsActivity = (TestsActivity) getActivity();
                testsActivity.selectTest(test);
            }
        });

        return view;
    }
}
