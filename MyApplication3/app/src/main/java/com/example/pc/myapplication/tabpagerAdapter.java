package com.example.pc.myapplication;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;

public class tabpagerAdapter extends FragmentStatePagerAdapter {


    String[] tabarray = new String[]{"one","two","three","four","five","six"};

    Integer tabnumber = 6;



    public tabpagerAdapter(FragmentManager fm) {

        super(fm);

    }



    @Override

    public CharSequence getPageTitle(int position) {

        return tabarray[position];

    }



    @Override

    public Fragment getItem(int position) {



        switch (position)

        {

            case 0:
                one one1 = new one();

                return one1;


            case 1:

                two two2 = new two();

                return two2;

            case 2:

                three three3 = new three();

                return three3;

            case 3:

                four four4 = new four();

                return four4;

            case 4:

                five five5 = new five();

                return five5;

            case 5:

                six six6 = new six();

                return six6;

        }





        return null;

    }



    @Override

    public int getCount() {

        return tabnumber;

    }

}