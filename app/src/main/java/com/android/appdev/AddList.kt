package com.android.appdev

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import java.util.*

class AddList : AppCompatActivity() {
    private val OPEN_GALLERY = 1

    lateinit var image : ImageView
    lateinit var imagebtn : Button
    lateinit var checkmoney : CheckBox
    lateinit var checkbook : CheckBox
    lateinit var checktravel : CheckBox

    lateinit var progress : ProgressBar
    lateinit var radioGroupdate : RadioGroup
    lateinit var datetextView: TextView
    lateinit var editTextlist : EditText
    lateinit var editTextdesc : EditText

    lateinit var saveButton : Button

    lateinit var title : String
    lateinit var desc :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        image = findViewById(R.id.AddList_imageView_upload)
        imagebtn = findViewById(R.id.AddList_button_upload)

        checkmoney = findViewById(R.id.AddList_checkBox_money)
        checkbook = findViewById(R.id.AddList_checkBox_book)
        checktravel = findViewById(R.id.AddList_checkBox_travel)

        progress = findViewById(R.id.AddList_progressBar_progress)
        radioGroupdate = findViewById(R.id.AddList_radioGroup_date)
        datetextView = findViewById(R.id.AddList_textView_date)
        editTextlist = findViewById(R.id.AddList_editText_list)
        editTextdesc = findViewById(R.id.AddList_editText_description)
        saveButton = findViewById(R.id.AddList_button_save)

        var dateString =""

        imagebtn.setOnClickListener {
            openGalley()
        }

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
                R.id.AddList_radioButton_yes ->  {
                    datetextView.visibility = View.VISIBLE
                    val cal = Calendar.getInstance()
                    val dataSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                        datetextView.text = dateString
                    }
                    DatePickerDialog(this, dataSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
                }

                R.id.AddList_radioButton_no ->  Toast.makeText(applicationContext, "사과", Toast.LENGTH_SHORT).show()
            }
        }

        saveButton.setOnClickListener {
            title = editTextlist.text.toString()
            desc = editTextlist.text.toString()
        }

    }

    private fun openGalley(){
        val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){

                var currentImageUrl : Uri? = data?.data

                try{
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,currentImageUrl)
                    image.setImageBitmap(bitmap)
                }catch(e:Exception){
                    e.printStackTrace()
                }
            }
        }else{
            Log.d("ActivityResult", "can't get image from gallery")
        }
    }
}