package com.example.jotang.MyFragments;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jotang.LoginActivity;

public class FragmentAdapter extends FragmentPagerAdapter {

    private final int PAGER_COUNT = 3;
    private IntroFragment introFragment = null;
    private TimeFragment timeFragment = null;
    private BroadFragment broadFragment = null;

    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
        introFragment = new IntroFragment();
        timeFragment = new TimeFragment();
        broadFragment = new BroadFragment();
    }
    

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case LoginActivity.PAGE_ONE:
                fragment = introFragment;
                break;
            case LoginActivity.PAGE_TWO:
                fragment = timeFragment;
                break;
            case LoginActivity.PAGE_THREE:
                fragment = broadFragment;
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }
}
