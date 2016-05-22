package com.example.saarang.saarang;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Keerthi Suresh on 17-05-2016.
 */
public class RockShow extends Fragment {
    final Context context =getActivity();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_rockshow, container,false);
        return v;
    }
}

