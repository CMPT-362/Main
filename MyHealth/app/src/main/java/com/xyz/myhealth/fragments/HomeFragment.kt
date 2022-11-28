package com.xyz.myhealth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.xyz.myhealth.R
import com.xyz.myhealth.activities.UserProfileActivity

/**
 * This fragment contains information of the Users today's data
 * UserIcon - Starts UserProfileActivity
 * HistoryIcon - Starts HomeHistoryActivity
 * InformationIcon - Starts InformationActivity
 */
class HomeFragment : Fragment() {
    private lateinit var userProfileButton : ImageView
    private lateinit var allHistoryButton : ImageView
    private lateinit var informationButton : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        userProfileButton = view.findViewById(R.id.homeUserProfile)
        userProfileButton.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this.context, "You clicked on UserProfile", Toast.LENGTH_SHORT).show()
            val intent : Intent = Intent(context, UserProfileActivity::class.java)
            startActivity(intent)
        })

        allHistoryButton = view.findViewById(R.id.homeAllHistory)
        allHistoryButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context, "You clicked on AllHistoryActivity", Toast.LENGTH_SHORT).show()
        })

        informationButton = view.findViewById(R.id.homeInformation)
        informationButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this.context, "You clicked on InformationActivity", Toast.LENGTH_SHORT).show()
        })

        return view;
    }
}