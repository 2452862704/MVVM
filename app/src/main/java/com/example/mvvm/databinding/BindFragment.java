package com.example.mvvm.databinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.mvvm.R;

import org.jetbrains.annotations.NotNull;

public class BindFragment extends Fragment {

    private FragmentBindBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bind,container,false);
        binding.setContent("aaa");
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.unbind();
    }
}
