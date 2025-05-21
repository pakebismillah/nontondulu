package com.example.nontondulu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SavedFragment extends Fragment {

    public SavedFragment() {
        // Konstruktor kosong tetap dibutuhkan
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout fragment_wishlist.xml
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }
}
