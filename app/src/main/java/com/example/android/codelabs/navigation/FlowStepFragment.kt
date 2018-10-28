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

import android.os.Bundle
import androidx.transition.*
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

/**
 * Presents how multiple steps flow could be implemented.
 */
class FlowStepFragment : androidx.fragment.app.Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enterTransition = TransitionInflater.from(context).inflateTransition(R.transition.enter)
//        exitTransition = TransitionInflater.from(context).inflateTransition(R.transition.exit)

        var enterTr = TransitionSet().apply {
            addTransition(Slide())
            addTransition(AlphaTransition())
//            addTransition(Fade(Visibility.MODE_IN))
//            addTransition(MyTransition())
        }

        //enterTr.propagation = CircularPropagation()
        enterTr.duration = 1000;
        enterTransition = enterTr

        var exitTr = TransitionSet().apply {
            addTransition(Slide())
            addTransition(AlphaTransition())
//            addTransition(Fade(Visibility.MODE_OUT))
//            addTransition(MyTransition())
        }

        //exitTr.propagation = CircularPropagation()
        exitTr.duration = 1000;
        exitTransition = exitTr
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        //val step = arguments?.getInt("step")

        // TODO STEP 9 - Use type-safe arguments - remove previous line!
        val step = arguments?.let {
            val safeArgs = FlowStepFragmentArgs.fromBundle(it)
            safeArgs.step;
        }
        // TODO ENDSTEP 9

        return when (step) {
            2 -> inflater.inflate(R.layout.flow_step_two_fragment, container, false)
            else -> inflater.inflate(R.layout.flow_step_one_fragment, container, false)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.next_button).setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.next_action)
        )
    }
}
