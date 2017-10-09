package com.studies.sandrini.agenda.converter;

import com.studies.sandrini.agenda.model.Student;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by Sandrini on 09/10/2017.
 */

public class StudentConverter {

    public String convertToJSON(List<Student> students) {
        JSONStringer js = new JSONStringer();

        try {
            js.object().key("list").array().object().key("aluno").array();
            for (Student student : students){
                js.object();
                js.key("nome").value(student.getName());
                js.key("grade").value(student.getGrade());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return js.toString();
    }
}
