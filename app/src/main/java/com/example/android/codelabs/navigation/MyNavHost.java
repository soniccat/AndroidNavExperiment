package com.example.android.codelabs.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.navigation.Navigator;
import androidx.navigation.fragment.NavHostFragment;

public class MyNavHost extends NavHostFragment {
    MyNavigator mNavController;

    public MyNavHost() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected Navigator createFragmentNavigator() {
        return new MyNavigator(requireContext(), getChildFragmentManager(), getId());
    }
}
