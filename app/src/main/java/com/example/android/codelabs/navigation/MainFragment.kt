/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.navigation

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AccelerateInterpolator
import androidx.transition.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

/**
 * Fragment used to show how to navigate to another destination
 */
class MainFragment : androidx.fragment.app.Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enterTransition = TransitionInflater.from(context).inflateTransition(R.transition.enter)
//        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition.exit)

        var enterTr = TransitionSet().apply {
//            addTransition(Slide())
//            addTransition(AlphaTransition())
//            addTransition(Fade(Visibility.MODE_IN))
            addTransition(MyTransition())
        }

        //enterTr.propagation = CircularPropagation()
        enterTr.duration = 1000;
        enterTransition = enterTr

        var exitTr = TransitionSet().apply {
//            addTransition(Slide())
//            addTransition(AlphaTransition())
//            addTransition(Fade(Visibility.MODE_OUT))
            addTransition(MyTransition())
        }

        //exitTr.propagation = CircularPropagation()
        exitTr.duration = 1000;
        exitTransition = exitTr
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO STEP 5 - Set an OnClickListener, using Navigation.createNavigateOnClickListener()
        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.flow_step_one, null)
        )
        //TODO ENDSTEP 5

        //TODO STEP 6 - Set NavOptions

        val options = NavOptions.Builder()
//            .setEnterAnim(R.anim.slide_in_right)
//            .setExitAnim(R.anim.slide_out_left)
//            .setPopEnterAnim(R.anim.slide_in_left)
//            .setPopExitAnim(R.anim.slide_out_right)
            .build()

        view.findViewById<Button>(R.id.navigate_dest_bt)?.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.flow_step_one, null, options)
        }
        //TODO ENDSTEP 6

        //TODO STEP 7 - Update the OnClickListener to navigate using an action
        view.findViewById<Button>(R.id.navigate_action_bt)?.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.next_action, null)
        )

        //TODO ENDSTEP 7

        val textView = view.findViewById<TextView>(R.id.text)

        view.findViewById<Button>(R.id.navigate_action_bt2)?.setOnClickListener {
            val view = View(context)
            view.layoutParams = FrameLayout.LayoutParams(400, 400)
            view.background = ColorDrawable(Color.RED)

            (getView() as ViewGroup).overlay.add(view)
            val widthSpec = View.MeasureSpec.makeMeasureSpec(400, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(400, View.MeasureSpec.EXACTLY)
            view.measure(widthSpec, heightSpec)
            view.layout(0, 0, 400, 400)

            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f)
            val translateAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                    PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, 0.0f, 500.0f));
            translateAnimator.interpolator = AccelerateInterpolator()

            alphaAnimator.setDuration(500)
            translateAnimator.setDuration(500)

            alphaAnimator.start();
            translateAnimator.start();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
    }
}
