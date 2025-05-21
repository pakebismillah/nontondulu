package com.example.nontondulu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        super(R.layout.fragment_akun); // Ini langsung inflate XML
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi View
        TextView tvName = view.findViewById(R.id.userName);
        TextView tvEmail = view.findViewById(R.id.tvEmail);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager);

        // Set data pengguna (dummy)
        SessionManager sessionManager = new SessionManager(requireContext());
        tvName.setText(sessionManager.getName());
        tvEmail.setText(sessionManager.getEmail());


        // Setup ViewPager dengan Adapter
        AccountPagerAdapter adapter = new AccountPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Hubungkan TabLayout dengan ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Wishlist");
                    } else {
                        tab.setText("Resepku");
                    }
                }
        ).attach();

        // Tombol Logout
        btnLogout.setOnClickListener(v -> logout());
    }

    private void logout() {
        SessionManager sessionManager = new SessionManager(requireContext());
        sessionManager.logout();

        // Kembali ke Login
        startActivity(new Intent(requireContext(), LoginActivity.class));
        requireActivity().finish();
    }

}
