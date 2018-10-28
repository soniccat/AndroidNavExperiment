package com.example.android.codelabs.navigation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class MyTransition extends android.transition.Slide {
    public MyTransition() {
        super();
    }

    public MyTransition(int slideEdge) {
        super(slideEdge);
    }

    public MyTransition(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator a = super.onAppear(sceneRoot, view, startValues, endValues);
        Animator show = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(a.getInterpolator());
        set.setDuration(a.getDuration());
        set.play(a).with(show);

        return set;
    }


    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        Animator a = super.onDisappear(sceneRoot, view, startValues, endValues);
        Animator hide = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(a.getInterpolator());
        set.setDuration(a.getDuration());
        set.play(a).with(hide);

        return set;
    }
}
