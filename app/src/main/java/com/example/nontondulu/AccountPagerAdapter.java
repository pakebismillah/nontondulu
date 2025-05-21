package com.example.nontondulu;

import androidx.fragment.app.Fragment;

public class AccountPagerAdapter extends FragmentStateAdapter {

    public AccountPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new SavedFragment() : new ResepkuFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

