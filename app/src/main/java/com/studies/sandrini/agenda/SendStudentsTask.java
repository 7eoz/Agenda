package com.studies.sandrini.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.studies.sandrini.agenda.converter.StudentConverter;
import com.studies.sandrini.agenda.dao.StudentDAO;
import com.studies.sandrini.agenda.model.Student;

import java.util.List;

/**
 * Created by Sandrini on 09/10/2017.
 */

public class SendStudentsTask extends AsyncTask<Void, Void, String>{
    private Context context;
    private ProgressDialog progress;

    public SendStudentsTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Please wait", "sending students data...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        StudentDAO dao = new StudentDAO(context);
        List<Student> students = dao.searchStudents();
        dao.close();

        StudentConverter converter = new StudentConverter();
        String json = converter.convertToJSON(students);

        WebClient client = new WebClient();
        String answer = client.post(json);
        return answer;
    }

    @Override
    protected void onPostExecute(String answer) {
        progress.dismiss();
        Toast.makeText(context, answer, Toast.LENGTH_LONG).show();
    }
}
