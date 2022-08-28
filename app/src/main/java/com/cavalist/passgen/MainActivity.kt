package com.cavalist.passgen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.NumberPicker
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import java.security.DigestException
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {
    val hashConverter = HashConverter()
    val checkboxAnalyzer = CheckboxAnalyzer()
    val selectedCharManager = SelectedCharManager()

    var isUserSetting = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 32
        numberPicker.value = 16

        val textInput_specialCustom = findViewById<TextInputEditText>(R.id.textInput_specialCustom)
        textInput_specialCustom.isEnabled = false

        val specialCustomInput = findViewById<TextInputEditText>(R.id.textInput_specialCustom)
        specialCustomInput.doOnTextChanged { text, start, before, count ->
            val checkBox_specialCustom = findViewById<CheckBox>(R.id.checkBox_specialCustom)
            val checkBox_number = findViewById<CheckBox>(R.id.checkBox_number)
            val checkBox_small = findViewById<CheckBox>(R.id.checkBox_small)
            val checkBox_large = findViewById<CheckBox>(R.id.checkBox_large)

            if (isUserSetting){
                val resString = text.toString().toCharArray().distinct().joinToString("")
                isUserSetting = false
                specialCustomInput.setText(resString)
            }else{
                selectedCharManager.clear()

                writeCheckboxesToManager(
                    checkBox_number,
                    checkBox_small,
                    checkBox_large,
                    checkBox_specialCustom
                )

                syncInputToSpecialCharString()
                syncTextView_selectedCharsToManager()
                isUserSetting = true
            }

        }
    }

    fun onCheckboxClicked(view: View) {
        val textInput_specialCustom = findViewById<TextInputEditText>(R.id.textInput_specialCustom)
        val checkBox_specialCustom = findViewById<CheckBox>(R.id.checkBox_specialCustom)
        val checkBox_number = findViewById<CheckBox>(R.id.checkBox_number)
        val checkBox_small = findViewById<CheckBox>(R.id.checkBox_small)
        val checkBox_large = findViewById<CheckBox>(R.id.checkBox_large)
        val checkBox_special1 = findViewById<CheckBox>(R.id.checkBox_special1)
        val checkBox_special2 = findViewById<CheckBox>(R.id.checkBox_special2)

        selectedCharManager.clear()

        if (checkBox_specialCustom.isChecked){
            checkBox_special1.isEnabled = false
            checkBox_special2.isEnabled = false
            textInput_specialCustom.isEnabled = true

            writeCheckboxesToManager(
                checkBox_number,
                checkBox_small,
                checkBox_large,
                checkBox_specialCustom,
            )
            syncInputToSpecialCharString()
        }else{
            checkBox_special1.isEnabled = true
            checkBox_special2.isEnabled = true
            textInput_specialCustom.isEnabled = false

            writeCheckboxesToManager(
                checkBox_number,
                checkBox_small,
                checkBox_large,
                checkBox_special1,
                checkBox_special2
            )
        }
        syncTextView_selectedCharsToManager()

    }

    private fun syncInputToSpecialCharString(){
        val specialInput = findViewById<TextInputEditText>(R.id.textInput_specialCustom)
        val specialCustomChars = selectedCharManager.getType("SpecialCustomChars")
        specialCustomChars.setCharString(specialInput.text.toString())
    }
    private fun writeCheckboxesToManager(vararg checkBoxes: CheckBox){
        for (checkBox in checkBoxes){
            val charType = checkboxAnalyzer.getCharType(checkBox.id)
            if (checkBox.isChecked){
                selectedCharManager.addCharType(charType)
            }
        }
    }

    private fun syncTextView_selectedCharsToManager() {
        val textView_selectedChars = findViewById<TextView>(R.id.textView_selectedChars)

        textView_selectedChars?.text = selectedCharManager.getCharsetsViewString()
    }

    fun onButtonGenerateClicked(view: View){
        val textInput_input = findViewById<TextInputEditText>(R.id.textInput_input)
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        try {
            val inputString = textInput_input?.text
            if (inputString == null || inputString.length == 0){
                return
            }
            val bytes = inputString.toString().toByteArray()

            val md = MessageDigest.getInstance("SHA-256")
            val hash = md.digest(bytes)

            val charset =selectedCharManager.getCharset()
            if (charset.size == 0){
                return
            }

            hashConverter.setCharset(charset)
            var resString = hashConverter.bytesToString(hash)
            resString = resString.substring(0, numberPicker.value)

            updateTextView_result(resString)
        } catch (e: CloneNotSupportedException){
            throw DigestException("digest 만들기 실패")
        }
    }

    fun onButtonClearClicked(view: View){
        updateTextView_result("")
    }

    private fun updateTextView_result(str: String) {
        val textView_result = findViewById<TextView>(R.id.textView_result)

        textView_result?.text = str
    }

}