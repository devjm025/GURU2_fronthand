package com.android.appdev

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
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
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class ShowActivity : AppCompatActivity() {
    private val OPEN_GALLERY = 1

    private var db : BucketListDataBase ?= null
    private var blist : List<BucketList> ?= null

    lateinit var image : ImageView
    lateinit var imagebtn : Button
    lateinit var RGBcategory : RadioGroup
    lateinit var money : RadioButton
    lateinit var book : RadioButton
    lateinit var travel : RadioButton

    lateinit var seekbar : SeekBar
    lateinit var tvprogress : TextView
    lateinit var radioGroupdate : RadioGroup
    lateinit var radioButton_no : RadioButton
    lateinit var radioButton_yes: RadioButton
    lateinit var datetextView: TextView
    lateinit var TextViewlist : TextView
    lateinit var editTextinfo : EditText

    lateinit var saveButton : Button

    var id : Long = 0L
    var str_title : String =""
    var str_info : String = ""
    var str_image : String = ""
    var str_date : String = ""
    var int_progress : Int = 0
    lateinit var category : String
    var dateString : String =""

    lateinit var btnsave : Button
    lateinit var btndelete : Button

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
        //
        image = findViewById(R.id.Show_imageView_upload)
        imagebtn = findViewById(R.id.Show_button_upload)

        RGBcategory = findViewById(R.id.Show_RG_category)
        money = findViewById(R.id.Show_Rb_money)
        book = findViewById(R.id.Show_Rb_book)
        travel = findViewById(R.id.Show_Rb_travel)

        seekbar = findViewById(R.id.Show_SeekBar_progress)
        tvprogress = findViewById(R.id.Show_textView_progress)
        radioGroupdate = findViewById(R.id.Show_radioGroup_date)
        radioButton_yes = findViewById(R.id.Show_radioButton_yes)
        radioButton_no = findViewById(R.id.Show_radioButton_no)

        datetextView = findViewById(R.id.Show_textView_date)
        TextViewlist = findViewById(R.id.Show_TextView_list)
        editTextinfo = findViewById(R.id.Show_editText_description)
        saveButton = findViewById(R.id.Show_button_save)

        // 버튼
        btnsave = findViewById(R.id.Show_button_save)
        btndelete = findViewById(R.id.Show_button_delete)

        // 데이터 가져오기
        val intent = intent
        str_title= intent.getStringExtra("title").toString()

        db = BucketListDataBase.getInstance(this)
        blist = db!!.listDao().select(str_title)

        Glide.with(image).load(Uri.parse(blist!![0].image)).into(image) //이미지
        str_image = blist!![0].image.toString()
        TextViewlist.text = blist!![0].title // title
        str_title = blist!![0].title.toString()
        editTextinfo.hint = blist!![0].info //info
        str_info = blist!![0].info.toString()

        tvprogress.text = blist!![0].progress.toString() + "%" //progress
        seekbar.progress = blist!![0].progress!!
        int_progress = blist!![0].progress!!.toInt()

        //category
        if(blist!![0].category.equals("book")){
            book.setChecked(true)
        }else if(blist!![0].category.equals("money")){
            money.setChecked(true)
        }else if (blist!![0].category.equals("travel")){
            travel.setChecked(true)
        }

        category = blist!![0].category.toString()

        RGBcategory.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.Show_Rb_book ->  category = "book"
                R.id.Show_Rb_money ->  category = "money"
                R.id.Show_Rb_travel ->  category = "travel"
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

        //날짜
        str_date = blist!![0].Dday.toString()
        datetextView.setText(str_date)
        if(blist!![0].Dday.equals("0")){
            radioButton_no.setChecked(true)
            datetextView.visibility = View.INVISIBLE
        }else{
            radioButton_yes.setChecked(true)
            datetextView.visibility = View.VISIBLE
        }


        radioGroupdate.setOnCheckedChangeListener { radioGroup, checkedId ->
            when(checkedId){
                R.id.Show_radioButton_yes ->  {
                    datetextView.visibility = View.VISIBLE
                    val cal = Calendar.getInstance()
                    val dataSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        dateString = "${year}년 ${month+1}월 ${dayOfMonth}일"
                        datetextView.text = dateString
                        str_date = dateString
                    }
                    DatePickerDialog(this, dataSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(
                        Calendar.DAY_OF_MONTH)).show()
                }

                R.id.Show_radioButton_no -> {
                    datetextView.visibility = View.INVISIBLE
                    str_date = "0"
                }
            }
        }

        //이미지
        imagebtn.setOnClickListener {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    var dlg = AlertDialog.Builder(this)
                    dlg.setTitle("권한이 필요한 이유")
                    dlg.setMessage("사진 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다.")
                    dlg.setPositiveButton("확인"){dialog,which->
                        ActivityCompat.requestPermissions(this@ShowActivity,
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

        //수정사항 저장하기
        btnsave.setOnClickListener {
            db!!.listDao().updateList(str_title,str_image, category,int_progress,str_date,str_info )

            Toast.makeText(this,"수정하였습니다.",Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ShowActivity,ListActivity::class.java)
            ContextCompat.startActivity(this@ShowActivity,intent,null)
        }

        //삭제하기
        btndelete.setOnClickListener {
            db!!.listDao().deleteBucketListById(str_title)

            Toast.makeText(this,"삭제하였습니다.",Toast.LENGTH_SHORT).show()

            val intent = Intent(this@ShowActivity,ListActivity::class.java)
            ContextCompat.startActivity(this@ShowActivity,intent,null)
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