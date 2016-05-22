package com.example.saarang.saarang;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v4.view.ViewPager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends Activity{

    private ViewPager viewPager;
    private android.app.ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Events", "Register" };
    ListView listView;
    ArrayAdapter<String> listAdapter;
    String fragmentArray[] = {"LecDems","RockShow", "Workshops","Classical Night","Informals"};

MyAdapter mAdapter ;
    ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.list);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fragmentArray);
        listView.setAdapter(listAdapter);
        PhoneCallListener phoneListener = new PhoneCallListener();
        TelephonyManager telephonyManager = (TelephonyManager)
                this.getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = new LecDems();
                if (position == 0) {
                    fragment = new LecDems();

                } else if (position == 1) {
                    fragment = new RockShow();

                } else if (position == 3) {
                    fragment = new ClassicalNight();

                } else if (position == 4) {
                    fragment = new Informals();

                } else {
                    fragment = new Workshops();
                }

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.relativeLayout, fragment).addToBackStack(null).commit();


            }


        });
    }




    public void onClickCall(View v) {

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        String telno ;
        switch(v.getId()){
            case R.id.LecDemsBtn:
                telno = "tel : 9940109995";
                break;
            case R.id.WorkshopsBtn:
                telno = "tel : 9940109995";
                break;
            case R.id.RockShowBtn:
                telno = "tel : 9940109995";
                break;
            case R.id.ClassicalNightBtn:
                telno = "tel : 9940109995";
                break;
            case R.id.InformalsBtn:
                telno = "tel : 9940109995";
                break;




        }
        callIntent.setData(Uri.parse("tel:9940109995"));
        try {startActivity(callIntent); } catch (SecurityException e) { Toast.makeText(this, "Sorry , you dont have the permission to make this call.",
                Toast.LENGTH_LONG).show(); }


    }


    //monitor phone call activities
    private class PhoneCallListener extends PhoneStateListener {

        private boolean isPhoneCalling = false;

        String LOG_TAG = "LOGGING 123";

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            if (TelephonyManager.CALL_STATE_RINGING == state) {
                // phone ringing
                Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
            }

            if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
                // active
                Log.i(LOG_TAG, "OFFHOOK");

                isPhoneCalling = true;
            }

            if (TelephonyManager.CALL_STATE_IDLE == state) {
                // run when class initial and phone call ended,
                // need detect flag from CALL_STATE_OFFHOOK
                Log.i(LOG_TAG, "IDLE");

                if (isPhoneCalling) {

                    Log.i(LOG_TAG, "restart app");

                    // restart app
                    Intent i = getBaseContext().getPackageManager()
                            .getLaunchIntentForPackage(
                                    getBaseContext().getPackageName());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                    isPhoneCalling = false;
                }

            }
        }
    }

    public void onClickMap(View v){
        Intent myIntent = new Intent(v.getContext(), MapsActivity.class);
        startActivityForResult(myIntent, 0);

    }

    public void onClickRegister(View v){

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relativeLayout,new Registeration()).addToBackStack(null).commit();

    }
    public void onClickEmail(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        String email = "class_night@saarang.org";
        switch(v.getId()) {
            case R.id.ClassicalNightEmail:
                email = "class_night@saarang.org";
                break;
            case R.id.WorkshopsEmail:
                email = "workshops@saarang.org";
                break;
            case R.id.RockShowEmail:
                email = "rockshow@saarang.org";
                break;
            case R.id.LecdemsEmail:
                email = "lecdems@saarang.org";
                break;
            case R.id.InformalsEmail:
                email = "informals@saarang.org";
                break;
        }
            emailIntent.putExtra(Intent.EXTRA_EMAIL , email);
        emailIntent.setType("message/rfc822");
        startActivity(emailIntent);
    }

    /*public void onClickCalender(View v){
        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
        intent.setType("vnd.android.cursor.item/event");

        switch(v.getId()) {
            case R.id.ClassicalNightCalendar:
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,20);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,22);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, "Classical Night");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "OAT");
                break;
            case R.id.WorkshopsCalendar:
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,20);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,22);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, "WORKSHOPS");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "CRC");
                break;
            case R.id.RockShowCalendar:
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,20);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,22);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, "ROCK SHOW");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "OAT");
                break;
            case R.id.LecDemsCalendar:
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,9);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,10);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, "Lec Dems");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "CRC");
                break;
            case R.id.InformalsCalendar:
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,20);
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,22);
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.TITLE, "Informals");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "KV Grounds");
                break;


        }
        startActivity(intent);
    }*/

}
