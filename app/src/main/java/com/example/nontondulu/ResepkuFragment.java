package com.example.nontondulu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ResepkuFragment extends Fragment {

    public ResepkuFragment() {
        // Konstruktor kosong wajib ada
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout fragment_resepku.xml
        return inflater.inflate(R.layout.fragment_resepku, container, false);
    }
}
