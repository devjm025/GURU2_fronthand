package com.android.appdev

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var Title_btn_Startbtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Title_btn_Startbtn=findViewById<Button>(R.id.Title_btn_Startbtn)

        Title_btn_Startbtn.setOnClickListener {
//            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
//                    var dlg = AlertDialog.Builder(this)
//                    dlg.setTitle("권한이 필요한 이유")
//                    dlg.setMessage("사진 정보를 얻기 위해서는 외부 저장소 권한이 필수로 필요합니다.")
//                    dlg.setPositiveButton("확인"){dialog,which->
//                        ActivityCompat.requestPermissions(this@MainActivity,
//                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
//                        1000)}
//                    dlg.setNegativeButton("취소",null)
//                    dlg.show()
//                }else{
//                    ActivityCompat.requestPermissions(this,
//                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1000)
//                }
//            }

            var intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}