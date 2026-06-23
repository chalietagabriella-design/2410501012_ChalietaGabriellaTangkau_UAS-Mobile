package com.example.endemikdb.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.endemikdb.fragment.HewanFragment;
import com.example.endemikdb.fragment.TumbuhanFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final HewanFragment hewanFragment;
    private final TumbuhanFragment tumbuhanFragment;

    public ViewPagerAdapter(
            @NonNull FragmentActivity fragmentActivity
    ) {
        super(fragmentActivity);

        hewanFragment = new HewanFragment();
        tumbuhanFragment = new TumbuhanFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return hewanFragment;
        }

        return tumbuhanFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public HewanFragment getHewanFragment() {
        return hewanFragment;
    }

    public TumbuhanFragment getTumbuhanFragment() {
        return tumbuhanFragment;
    }
}