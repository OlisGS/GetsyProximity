package olis.getsyproximity.samples;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import olis.getsyproximity.library.GetsyProximityClient;
import olis.getsyproximity.library.Response.BasicResponse;
import olis.getsyproximity.library.Response.SyncUserResponse;

/**
 * Created by GTO on 03.08.2014.
 */
public class Dialog extends android.app.Dialog{
    private EditText mFirstName;
    private EditText mLastName;
    private DatePicker mDatePicker;
    private RadioGroup mGender;
    public String mFirstNameString;
    public String mLastNameString;
    public String mBirthdayString;
    public String mGenderString;
    private GetsyProximityClient mGetsyProximityClient;
    private Dialog thisDialog;
    private Context mContext;

    public Dialog(Context context) {
        super(context);
        mContext = context;
        thisDialog = this;
        mGetsyProximityClient = GetsyProximityClient.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog);
        setTitle("set your data");
        Button mOK = (Button) findViewById(R.id.ok_dialog);
        mFirstName = (EditText) findViewById(R.id.first_name);
        mLastName = (EditText) findViewById(R.id.last_name);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mGender = (RadioGroup) findViewById(R.id.gender);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirstNameString = String.valueOf(mFirstName.getText());
                mLastNameString = String.valueOf(mLastName.getText());
                Calendar c = Calendar.getInstance();
                c.set(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                mBirthdayString = df.format(c.getTime());
                switch (mGender.getCheckedRadioButtonId()){
                    case R.id.male:
                        mGenderString = "m";
                        break;
                    case R.id.female:
                        mGenderString = "f";
                        break;
                    default:
                        mGenderString = "m";
                        break;
                }
                thisDialog.dismiss();
                BasicResponse basicResponse = mGetsyProximityClient.syncUser(mFirstNameString, mLastNameString, mGenderString, mBirthdayString, Main.sInstanceToken);
                if(basicResponse.isOK()) {
                    Toast.makeText(mContext, ((SyncUserResponse) basicResponse).getStatus(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, ((SyncUserResponse)basicResponse).getStatus(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
