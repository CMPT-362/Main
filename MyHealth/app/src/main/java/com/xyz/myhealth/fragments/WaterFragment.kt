package com.xyz.myhealth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.xyz.myhealth.R

class WaterFragment : Fragment() {
    private lateinit var drinkIcon : ImageView
    private lateinit var drinkWater : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_water, container, false)

        drinkIcon = view.findViewById(R.id.drinkIcon)
        drinkIcon.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context, "You clicked on DrinkIcon", Toast.LENGTH_SHORT).show()
        })

        drinkWater = view.findViewById(R.id.drinkWater)
        drinkWater.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context, "You clicked on DrinkWater", Toast.LENGTH_SHORT).show()
        })

        return view;
    }

}