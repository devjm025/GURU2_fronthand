package com.android.appdev

import android.Manifest.permission_group.STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import android.Manifest
import androidx.fragment.app.Fragment
import kotlin.concurrent.timer

class AccountActivity : AppCompatActivity() {

    lateinit var account_btn_LogIn: Button
    lateinit var account_btn_SignUp:Button
    //배너 변수
    private val REQUEST_READ_EXTERNAL_STORAGE =1000
    lateinit var viewPager : ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        viewPager = findViewById(R.id.viewPager)

        account_btn_LogIn=findViewById<Button>(R.id.account_btn_LogIn)
        account_btn_SignUp=findViewById<Button>(R.id.account_btn_SignUp)

        account_btn_LogIn.setOnClickListener{
            var intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }


        account_btn_SignUp.setOnClickListener{
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        //getAllPhotos()
        //권한이 부여되었는지 확인
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        //권한이 허용되지 않음
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                //이전에 거부한 적이 있으면 설명(경고)
                var dlg = AlertDialog.Builder(this)
                dlg.setTitle("권한이 필요한 이유")
                dlg.setMessage("사진 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다.")
                dlg.setPositiveButton("확인"){dialog, which ->
                    ActivityCompat.requestPermissions(this@AccountActivity,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)}

                dlg.setNegativeButton("취소",null)
                dlg.show()
            }else{
                //처음 권한 요청청
                ActivityCompat.requestPermissions(this@AccountActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
            }else{
            //권한이 이미 허용됨
            getAllPhotos()
        }

    }
    private fun getAllPhotos(){
        //모든 사진 정보 가져오기
        val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null, //가져올 항목 배열
            null, //조건
            null, //조건
            MediaStore.Images.ImageColumns.DATE_TAKEN+" DESC") //활영 최신 날짜순

        val fragments = ArrayList<Fragment>()

        if(cursor != null){
            while(cursor.moveToNext()){
                //사진 경로 uri가져오기
                val uri =
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                Log.d("HomeActivity",uri)
                fragments.add(PhotoFragment.newInstance(uri))
            }
            cursor.close()
        }

        val adapter = MyPagerAdapter(supportFragmentManager)
        adapter.updateFragments(fragments)
        viewPager.adapter = adapter

        //3초마다 자동으로 슬라이드
        timer(period = 3000){
            runOnUiThread {
                if(viewPager.currentItem < adapter.count-1){
                    viewPager.currentItem++
                }else{
                    viewPager.currentItem=0
                }
            }
        }
    }


}