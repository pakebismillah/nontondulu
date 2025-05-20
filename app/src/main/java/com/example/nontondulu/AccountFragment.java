package com.example.nontondulu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        super(R.layout.fragment_akun);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inisialisasi
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvEmail = view.findViewById(R.id.tv_email);
        Button btnLogout = view.findViewById(R.id.btn_logout);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);

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
        btnLogout.setOnClickListener(v -> {
            // Aksi logout nanti
        });
    }
}


