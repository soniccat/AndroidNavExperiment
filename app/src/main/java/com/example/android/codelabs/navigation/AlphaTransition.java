package com.example.android.codelabs.navigation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.transition.TransitionValues;
import androidx.transition.Visibility;

public class AlphaTransition extends Visibility {
    public AlphaTransition() {
    }

    public AlphaTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator show = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        show.setDuration(300);

        return show;
    }


    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator hide = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        hide.setDuration(300);

        return hide;
    }
}
