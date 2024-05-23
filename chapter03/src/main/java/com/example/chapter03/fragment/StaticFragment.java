package com.example.chapter03.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter03.R;
import com.example.chapter03.databinding.FragmentStaticBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaticFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentStaticBinding v = FragmentStaticBinding.inflate(inflater,container,false);
        return v.getRoot();
    }
}