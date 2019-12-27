package com.example.navigation_drawer.visitorinfo;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.navigation_drawer.R;

import java.util.ArrayList;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final ArrayList<String> TAB_TITLES=new ArrayList<>();
    private final ArrayList<Fragment> mFRAGMENTLIST=new ArrayList<>();
    Activity activity;

    public SectionsPagerAdapter(Activity activity, FragmentManager fm) {
        super(fm);
        this.activity=activity;
        mFRAGMENTLIST.add(new VisitorListFragment(activity));
        mFRAGMENTLIST.add(new CheckoutListFragment(activity));
        TAB_TITLES.add("Visitor List");
        TAB_TITLES.add("Check Out List");
    }

    @Override
    public Fragment getItem(int position) {
        return mFRAGMENTLIST.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}