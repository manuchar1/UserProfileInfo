package com.example.userprofileinfo

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.userprofileinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    var eMailField: EditText? = null
    var isAllFieldsChecked = false
    var validEmail = "NO"
    var validUsername = "NOT_VALID"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eMailField = binding.etEmail

        binding.buttonSave.setOnClickListener(this)
        binding.buttonClear.setOnLongClickListener {
            binding.buttonClear
            clearData()
            true
        }

        validateUserName()

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.buttonSave -> {
                    isAllFieldsChecked = confirmation()
                    return

                }

            }
        }
    }

    // this function cleans all input data
    private fun clearData() {
        binding.etEmail.text = null
        binding.etUsername.text = null
        binding.etFirstName.text = null
        binding.etLastName.text = null
        binding.etAge.text = null
        binding.etUsername.error = null
    }

    // Function checks all the fields
    // are filled or not by the user.
    // also Here are the results
    // from validateUserName() and
    // validateEmailAddress() functions

    private fun confirmation(): Boolean {
        validateEmailAddress(eMailField)
        if (validEmail != "YES") {
            return false
        }

        if (validUsername != "VALID") {
            binding.etUsername.error = "Enter valid username"
            return false
        }

        if (binding.etFirstName.length() == 0) {
            binding.etFirstName.error = "This field is required"

        }

        if (binding.etLastName.length() == 0) {
            binding.etLastName.error = "This field is required"

        }

        if (binding.etAge.length() == 0) {
            binding.etAge.error = "This field is required"

        } else {
            Toast.makeText(this,
                    "You have successfully saved the data",
                    Toast.LENGTH_SHORT).show()

        }
        return true
    }


    // Data from the EditText field needs to be compared with.
    // In this case the the entered data needs to compared with
    // the Email address, which is implemented below
    private fun validateEmailAddress(eMailField: EditText?) {

        val emailToText = eMailField!!.text.toString()


        if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            validEmail = "YES"
            binding.etUsername.error = null
            //Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show()
        } else {
            validEmail = "NO"
            binding.etEmail.error = "Enter valid Email address"
            Toast.makeText(this, "Email is not valid!", Toast.LENGTH_SHORT).show()

        }
    }

    // This function validates Username fields during the typing
    // using doOnTextChanged method, if entered number of characters is less than 10
    // user know in advance before pressing the SAVE button

    private fun validateUserName() {

        binding.etUsername.doOnTextChanged { text, start, before, count ->

            if (text!!.length < 10) {
                validUsername = "NOT_VALID"
                binding.etUsername.error = "Username must be minimum 10 characters"
            } else if (text.length > 10) {
                validUsername = "VALID"
                binding.etUsername.error = null


            }
        }

    }

}


