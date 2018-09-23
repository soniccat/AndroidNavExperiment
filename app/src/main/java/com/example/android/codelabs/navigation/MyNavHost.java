package com.example.android.codelabs.navigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
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
    protected Navigator<? extends NavDestination> createFragmentNavigator() {
        return new MyNavigator(requireContext(), getChildFragmentManager(), getId());
    }
}
