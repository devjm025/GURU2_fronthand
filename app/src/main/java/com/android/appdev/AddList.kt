package com.android.appdev

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class AddList : AppCompatActivity() {

    private var adapter : ListAdapter? = null
    private var db: BucketListDataBase?=null

    private val OPEN_GALLERY = 1
    lateinit var dbManager: DBManager

    lateinit var image : ImageView
    lateinit var imagebtn : Button

    lateinit var RGBcategory: RadioGroup

    lateinit var seekbar : SeekBar
    lateinit var tvprogress : TextView
    lateinit var radioGroupdate : RadioGroup
    lateinit var datetextView: TextView
    lateinit var editTextlist : EditText
    lateinit var editTextinfo : EditText

    lateinit var saveButton : Button

    var str_image : String = ""
    var str_date : String ="0"
    var int_progress : Int = 0

    //checkbox listener 등록
    lateinit var category : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_list)

        image = findViewById(R.id.AddList_imageView_upload)
        imagebtn = findViewById(R.id.AddList_button_upload)

        RGBcategory = findViewById(R.id.AddList_RG_category)

        seekbar = findViewById(R.id.AddList_SeekBar_progress)
        tvprogress = findViewById(R.id.AddList_textView_progress)
        radioGroupdate = findViewById(R.id.AddList_radioGroup_date)
        datetextView = findViewById(R.id.AddList_textView_date)
        editTextlist = findViewById(R.id.AddList_editText_list)
        editTextinfo = findViewById(R.id.AddList_editText_description)
        saveButton = findViewById(R.id.AddList_button_save)

        dbManager =  DBManager(this, "bucketlistDB",null,1)

        var dateString =""

        //저장할 이미지 불러오기
        imagebtn.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    var dlg = AlertDialog.Builder(this)
                    dlg.setTitle("권한이 필요한 이유")
                    dlg.setMessage("사진 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다.")
                    dlg.setPositiveButton("확인"){dialog,which->ActivityCompat.requestPermissions(this@AddList,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        1000)}
                    dlg.setNegativeButton("취소",null)
                    dlg.show()
                }else{
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1000)
                }
            }
            else{
                openGalley()
            }
        }

        //D-Day 정하기
        radioGroupdate.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.AddList_radioButton_yes ->  {
                    datetextView.visibility = View.VISIBLE
                    val cal = Calendar.getInstance()
                    val dataSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                        datetextView.text = dateString
                        str_date = dateString
                    }
                    DatePickerDialog(this, dataSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
                }

                R.id.AddList_radioButton_no ->  datetextView.visibility = View.INVISIBLE
            }
        }

        //category
        RGBcategory.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.AddList_Rb_book ->  category = "book"
                R.id.AddList_Rb_money ->  category = "money"
                R.id.AddList_Rb_travel ->  category = "travel"
            }
        }

        //진행상황
        seekbar.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvprogress.text = progress.toString() + "%"
                int_progress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        db= BucketListDataBase.getInstance(this)
        //저장하기
        saveButton.setOnClickListener {
            var str_title : String = editTextlist.text.toString()
            var str_info : String = editTextinfo.text.toString()

            db!!.listDao().insert(BucketList(null,str_image,category,int_progress, str_date,str_title,str_info))


            val intent = Intent(this@AddList,ListActivity::class.java)
            ContextCompat.startActivity(this@AddList,intent,null)
        }

    }

    //갤러리에 접근하여 이미지 가져오는 함수
    private fun openGalley(){
        val intent : Intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLERY)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){

                var currentImageUrl : Uri? = data?.data
                str_image = currentImageUrl.toString()

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