package com.xyz.myhealth.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.xyz.myhealth.R

/**
 * This activity is for creating or editing
 * information about User Profile
 */
class UserProfileActivity : AppCompatActivity() {

    private lateinit var nameView: TextView
    private lateinit var emailView: TextView
    private lateinit var phoneView: TextView
    private lateinit var calorieGoalView: TextView
    private lateinit var currentCalorieView: TextView

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        //find views
        nameView = findViewById(R.id.profile_name)
        emailView = findViewById(R.id.profile_email)
        phoneView = findViewById(R.id.profile_phone_number)
        calorieGoalView = findViewById(R.id.profile_calorie_goal)
        currentCalorieView = findViewById(R.id.profile_current_weight)

        //load saved profile from sharedPref
        sharedPref = this.getSharedPreferences(
            getString(R.string.profile_preference_key),
            Context.MODE_PRIVATE
        )

        // load profile
        loadProfile()
    }

    //loads saved profile data. if profile section empty, uses empty string
    private fun loadProfile() {
        val defaultData = ""
        nameView.text = sharedPref.getString("name_key", defaultData)
        emailView.text = sharedPref.getString("email_key", defaultData)
        phoneView.text = sharedPref.getString("phone_key", defaultData)
        calorieGoalView.text = sharedPref.getString("calorie_goal_key", defaultData)
        currentCalorieView.text = sharedPref.getString("current_weight_key", defaultData)
    }

    //saves the profile in SharedPreference object
    private fun saveProfile(){
        val name : String = nameView.text.toString()
        val email : String = emailView.text.toString()
        val phoneNumber : String = phoneView.text.toString()
        val calorieGoal : String = calorieGoalView.text.toString()
        val currentWeight : String = currentCalorieView.text.toString()

        with(sharedPref.edit()) {
            putString("name_key", name)
            putString("email_key", email)
            putString("phone_key", phoneNumber)
            putString("calorie_goal_key", calorieGoal)
            putString("current_weight_key", currentWeight)
            apply()
        }
    }

    fun onUserProfileSaveClicked(view: View) {
        saveProfile()
        Toast.makeText(this, "UserProfile Saved", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun onUserProfileCancelClicked(view: View) {
        Toast.makeText(this, "UserProfile Cancelled", Toast.LENGTH_SHORT).show()
        finish()
    }
}