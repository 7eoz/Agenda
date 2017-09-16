package com.studies.sandrini.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_form_ok:
                Toast.makeText(FormActivity.this, "Student saved", Toast.LENGTH_SHORT).show();
                EditText nameField = (EditText) findViewById(R.id.form_name);
                String name = nameField.getText().toString();
                EditText adressField = (EditText) findViewById(R.id.form_adress);
                String adress = adressField.getText().toString();
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
