package com.studies.sandrini.agenda;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.studies.sandrini.agenda.model.Test;


public class TestsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = fragmentManager.beginTransaction();
        tx.replace(R.id.main_frame, new TestsListFragment());
        if(isLandscape()) {
            tx.replace(R.id.secondary_frame, new TestDetailsFragment());
        }

        tx.commit();
    }

    private boolean isLandscape() {
        return getResources().getBoolean(R.bool.landMode);
    }

    public void selectTest(Test test) {
        FragmentManager manager = getSupportFragmentManager();
        if(!isLandscape()) {
            FragmentTransaction tx = manager.beginTransaction();

            TestDetailsFragment detailsFragment = new TestDetailsFragment();
            Bundle params = new Bundle();
            params.putSerializable("test", test);
            detailsFragment.setArguments(params);

            tx.replace(R.id.main_frame, detailsFragment);
            tx.addToBackStack(null);
            tx.commit();
        }else {
            TestDetailsFragment detailsFragment =
                    (TestDetailsFragment) manager.findFragmentById(R.id.secondary_frame);
            detailsFragment.populateFieldsWith(test);
        }

    }
}
