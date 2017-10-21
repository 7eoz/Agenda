package com.studies.sandrini.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.studies.sandrini.agenda.model.Test;

public class TestDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_details);

        Intent testIntent = getIntent();
        Test test = (Test) testIntent.getSerializableExtra("test");

        TextView testSubject = (TextView) findViewById(R.id.test_subject);
        TextView testDate = (TextView) findViewById(R.id.test_date);
        ListView testTopics = (ListView) findViewById(R.id.test_topics);

        testSubject.setText(test.getSubject());
        testDate.setText(test.getDate());
        ArrayAdapter<String> topics = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test.getTopics());
        testTopics.setAdapter(topics);

    }
}
