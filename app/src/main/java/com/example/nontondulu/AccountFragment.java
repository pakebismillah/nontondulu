package com.example.nontondulu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        super(R.layout.fragment_akun);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inisialisasi
        TextView tvName = view.findViewById(R.id.userName);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        // Set data dummy
        tvName.setText("Mas Musa");
        tvEmail.setText("masmusa@example.com");

        // ViewPager + TabLayout
        AccountPagerAdapter adapter = new AccountPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Wishlist" : "Resepku")
        ).attach();

        // Logout
        btnLogout.setOnClickListener(v -> logout());
    }

    private void logout() {
        ((MainActivity) requireActivity()).logout();
    }
}


