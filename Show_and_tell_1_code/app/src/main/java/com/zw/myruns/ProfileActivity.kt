package com.zw.myruns

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import java.io.File

class ProfileActivity : AppCompatActivity() {

    private lateinit var photoView: ImageView
    private lateinit var nameView: TextView
    private lateinit var emailView: TextView
    private lateinit var weightView: TextView
    private lateinit var calorieGoalView: TextView

    private val tempImgName = "temp_img.jpg"
    private lateinit var tempImgUri: Uri
    private val profileImgName = "profile_img.jpg"
    private lateinit var profileImgUri: Uri

    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        photoView = findViewById(R.id.profile_photo)
        nameView = findViewById(R.id.profile_name)
        emailView = findViewById(R.id.profile_email)
        weightView = findViewById(R.id.profile_email)
        calorieGoalView = findViewById(R.id.profile_calorie_goal)

        val tempImgFile = File(getExternalFilesDir(null), tempImgName)
        tempImgUri = FileProvider.getUriForFile(this, "com.zw.myruns", tempImgFile)
        val profileImgFile = File(getExternalFilesDir(null), profileImgName)
        profileImgUri = FileProvider.getUriForFile(this, "com.zw.myruns", profileImgFile)

        sharedPref = this.getSharedPreferences(
            getString(R.string.profile_preference_key),
            Context.MODE_PRIVATE
        )
        loadProfile()

        if (savedInstanceState != null) {
            setPhoto(tempImgName, tempImgUri)
        }
    }


    private fun fileExists(imageName: String):Boolean {
        return File(getExternalFilesDir(null), imageName).exists()
    }

    private fun setPhoto(imageName: String, imageUri: Uri){
        if( fileExists(imageName)) {
            photoView.setImageBitmap(Util.getBitmap(this, imageUri))
        }
    }

    private fun deleteTemp(){
        val tempImgFile = File(getExternalFilesDir(null), tempImgName)
        tempImgFile.delete()
    }

    fun onChangePhotoClicked(view: View){
        var intent : Intent
        val photoDialogOptions = arrayOf("Open Camera", "Select from Gallery")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Profile Picture")
            .setItems(photoDialogOptions)
            { dialog, which ->
                if( photoDialogOptions[which].equals("Open Camera") ) {
                    intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, tempImgUri)
                    camResultLauncher.launch(intent)
                }else{
                    intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    galleryResultLauncher.launch(intent)
                }
            }
        val photoDialog = builder.create()
        photoDialog.show()

    }

    private val camResultLauncher :  ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if(result.resultCode == Activity.RESULT_OK){
                setPhoto(tempImgName, tempImgUri)
            }
    }
    private val galleryResultLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){result: ActivityResult ->
            if(result.resultCode == Activity.RESULT_OK) {
                val intentData = result.data
                val uri = intentData?.data!!
                Util.writeBitmap(this, tempImgName, uri, 180f)
                setPhoto(tempImgName, tempImgUri)
            }
    }

    fun onSaveClicked(view: View) {
        saveProfile()
        deleteTemp()
        finish()
    }

    fun onCancelClicked(view: View) {
        deleteTemp()
        finish()
    }

    private fun loadProfile() {
        setPhoto(profileImgName, profileImgUri)

        val defaultData = ""
        nameView.text = sharedPref.getString("name_key", defaultData)
        emailView.text = sharedPref.getString("email_key", defaultData)
    }

    private fun saveProfile(){
        if(fileExists(tempImgName)) {
            Util.writeBitmap(this, profileImgName, tempImgUri, -90f)
        }

        val name : String = nameView.text.toString()
        val email : String = emailView.text.toString()

        with(sharedPref.edit()) {
            putString("name_key", name)
            putString("email_key", email)
            apply()
        }

    }


}