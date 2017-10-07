package com.studies.sandrini.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.studies.sandrini.agenda.dao.StudentDAO;
import com.studies.sandrini.agenda.model.Student;


/**
 * Created by Sandrini on 07/10/2017.
 */

public class SMSReceiver extends BroadcastReceiver {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = {intent.getSerializableExtra("pdus")};
        byte[] pdu = (byte[]) pdus[0];
        StudentDAO dao = new StudentDAO(context);

        String format = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, format);
        String phone = sms.getDisplayOriginatingAddress();

        if(dao.isStudent(phone)){
            Toast.makeText(context, "You received a message!", Toast.LENGTH_SHORT);
        }
    }
}
