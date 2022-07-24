package com.android.appdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class AddList : AppCompatActivity() {
    lateinit var image : ImageButton
    lateinit var checkmoney : CheckBox
    lateinit var checkbook : CheckBox
    lateinit var checktravel : CheckBox

    lateinit var progress : ProgressBar
    lateinit var radioGroupdate : RadioGroup
    lateinit var editTextlist : EditText
    lateinit var editTextdesc : EditText

    lateinit var saveButton : Button

    lateinit var title : String
    lateinit var desc :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        image = findViewById(R.id.AddList_imageButton_upload)
        checkmoney = findViewById(R.id.AddList_checkBox_money)
        checkbook = findViewById(R.id.AddList_checkBox_book)
        checktravel = findViewById(R.id.AddList_checkBox_travel)
        progress = findViewById(R.id.AddList_progressBar_progress)
        radioGroupdate = findViewById(R.id.AddList_radioGroup_date)
        editTextlist = findViewById(R.id.AddList_editText_list)
        editTextdesc = findViewById(R.id.AddList_editText_description)
        saveButton = findViewById(R.id.AddList_button_save)

        var category : Int

        var listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                when (buttonView.id) {
                    R.id.AddList_checkBox_money -> category = 0
                    R.id.AddList_checkBox_book -> category = 1
                    R.id.AddList_checkBox_travel -> category = 2
                }
            }
        }

        radioGroupdate.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.AddList_radioButton_yes ->  Toast.makeText(applicationContext, "사과", Toast.LENGTH_SHORT).show()
                R.id.AddList_radioButton_no ->  Toast.makeText(applicationContext, "사과", Toast.LENGTH_SHORT).show()
            }
        }

        saveButton.setOnClickListener {
            title = editTextlist.text.toString()
            desc = editTextlist.text.toString()
        }

    }
}