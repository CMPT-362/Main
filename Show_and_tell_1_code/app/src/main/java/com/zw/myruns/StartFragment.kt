package com.zw.myruns

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class StartFragment : Fragment() {

    private lateinit var inputTypeSpinner : Spinner
    private lateinit var inputTypes :  Array<String>
    private lateinit var inputTypeAdapter : ArrayAdapter<String>

    private lateinit var activityTypeSpinner : Spinner
    private lateinit var activityTypes :  Array<String>
    private lateinit var activityTypeAdapter : ArrayAdapter<String>

    private lateinit var startButton : Button

    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inputTypes = resources.getStringArray(R.array.inputTypes)
        activityTypes = resources.getStringArray(R.array.activityTypes)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // test
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_start, container, false)

        inputTypeSpinner = view.findViewById(R.id.inputTypeSpinner)
        inputTypeAdapter = ArrayAdapter<String>(requireContext() , android.R.layout.simple_spinner_item, inputTypes)
        inputTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        inputTypeSpinner.adapter = inputTypeAdapter

        activityTypeSpinner = view.findViewById(R.id.activityTypeSpinner)
        activityTypeAdapter = ArrayAdapter<String>(requireContext() , android.R.layout.simple_spinner_item, activityTypes)
        activityTypeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        activityTypeSpinner.adapter = activityTypeAdapter

        startButton = view.findViewById(R.id.startButton)

        startButton.setOnClickListener(View.OnClickListener {

            val inputType = inputTypeSpinner.getSelectedItem().toString()
            val activityType = activityTypeSpinner.getSelectedItem().toString()
            val intent : Intent

            when(inputType){
                "Manual Entry" -> {
                    intent = Intent(context, ManualActivity::class.java)
                }
                "Exercise Entry" ->{
                    intent = Intent(context, ExerciseActivity::class.java)
                }
                "Automatic" -> {
                    intent = Intent(context, MapActivity::class.java)
                }
                else -> {
                    println("not an input type")
                    intent = Intent(context, ManualActivity::class.java)
                }
            }

            intent.putExtra("activity_type", activityType)
            intent.putExtra("input_type", inputType)
            resultLauncher.launch(intent)
        })

        return view
    }

}