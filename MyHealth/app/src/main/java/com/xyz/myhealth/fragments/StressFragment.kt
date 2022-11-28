package com.xyz.myhealth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.slider.Slider
import com.xyz.myhealth.R

class StressFragment : Fragment() {
    private lateinit var saveButton : Button
    private lateinit var stressHistory : ImageView
    private lateinit var resetButton : Button

    //sliders
    private lateinit var slider1 : Slider
    private lateinit var slider2 : Slider
    private lateinit var slider3 : Slider
    private lateinit var slider4 : Slider
    private lateinit var slider5 : Slider
    private lateinit var slider6 : Slider
    private lateinit var slider7 : Slider
    private lateinit var slider8 : Slider
    private lateinit var slider9 : Slider
    private lateinit var slider10 : Slider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stress, container, false)

        onStressSaveClicked(view)
        onStressHistoryClicked(view)
        onStressResetClicked(view)


        //getSliderValue(slider2)
        //getSliderValue(slider3)

        return view;
    }

    private fun onStressSaveClicked(view: View){
        // all sliders
        slider1 = view.findViewById(R.id.discreteSlider_1)
        slider2 = view.findViewById(R.id.discreteSlider_2)
        slider3 = view.findViewById(R.id.discreteSlider_3)
        slider4 = view.findViewById(R.id.discreteSlider_4)
        slider5 = view.findViewById(R.id.discreteSlider_5)
        slider6 = view.findViewById(R.id.discreteSlider_6)
        slider7 = view.findViewById(R.id.discreteSlider_7)
        slider8 = view.findViewById(R.id.discreteSlider_8)
        slider9 = view.findViewById(R.id.discreteSlider_9)
        slider10 = view.findViewById(R.id.discreteSlider_10)

        saveButton = view.findViewById(R.id.stressSave)
        saveButton.setOnClickListener(View.OnClickListener {
            getSliderValue(slider1)
            getSliderValue(slider2)
            getSliderValue(slider3)
            getSliderValue(slider4)
            getSliderValue(slider5)
            getSliderValue(slider6)
            getSliderValue(slider7)
            getSliderValue(slider8)
            getSliderValue(slider9)
            getSliderValue(slider10)

            Toast.makeText(this.context, "You clicked on SaveButton", Toast.LENGTH_SHORT).show()
        })
    }

    private fun onStressHistoryClicked(view: View){
        stressHistory = view.findViewById(R.id.stressHistory)
        stressHistory.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context, "You clicked on StressHistory", Toast.LENGTH_SHORT).show()
        })
    }

    private fun onStressResetClicked(view: View){
        resetButton = view.findViewById(R.id.stressReset)
        resetButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context, "You clicked on ResetButton", Toast.LENGTH_SHORT).show()
        })
    }

    private fun getSliderValue(slider: Slider){
        val text : String = slider.value.toInt().toString()
        println("debug: $slider value is $text")
    }

}