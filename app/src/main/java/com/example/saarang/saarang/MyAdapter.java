package com.example.saarang.saarang;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.saarang.saarang.LecDems;

/**
 * Created by Keerthi Suresh on 22-05-2016.
 */
public class MyAdapter extends FragmentStatePagerAdapter {

    public MyAdapter(FragmentManager fragmentManager){
        super (fragmentManager);
    }
    @Override
    public android.support.v4.app.Fragment getItem(int index){

        switch (index) {
            case 0:
           //     return  (android.support.v4.app.Fragment) new Home();
            case 1:
             //   return (android.support.v4.app.Fragment) new LecDems();

        }
        return null;
    }

    @Override
    public int getCount(){
        return 2;
    }

@Override
    public CharSequence getPageTitle(int position){
        if(position==0)return "EVENTS";
        return "REGISTER";
    }


}
