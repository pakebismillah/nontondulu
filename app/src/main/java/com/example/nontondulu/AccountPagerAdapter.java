package com.example.nontondulu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AccountPagerAdapter extends FragmentStateAdapter {

    public AccountPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new SavedFragment();
        } else {
            return new ResepkuFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

