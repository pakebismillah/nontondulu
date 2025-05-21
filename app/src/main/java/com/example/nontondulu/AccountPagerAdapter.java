package com.example.nontondulu;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccountPagerAdapter extends FragmentStateAdapter {

    public AccountPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? new WishlistFragment() : new ResepkuFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

