package com.biologi.ugm.reptilamfibijogja.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.biologi.ugm.reptilamfibijogja.TurtleFragment;
import com.biologi.ugm.reptilamfibijogja.LizardFragment;
import com.biologi.ugm.reptilamfibijogja.SnakeFragment;
import com.biologi.ugm.reptilamfibijogja.FrogFragment;

/**
 * Created by Fauziw97 on 9/12/17.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }


    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                LizardFragment tab1 = new LizardFragment();
                return tab1;
            case 1:
                FrogFragment tab2 = new FrogFragment();
                return tab2;
            case 2:
                SnakeFragment tab3 = new SnakeFragment();
                return tab3;
            case 3:
                TurtleFragment tab4 = new TurtleFragment();
                return tab4;
            default:
                return null;
        }
    }


    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
