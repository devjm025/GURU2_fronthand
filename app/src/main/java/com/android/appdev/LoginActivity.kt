package com.android.appdev


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {


    private var auth: FirebaseAuth? = null
    private var googleSignInclient: GoogleSignInClient? = null
    private var GOOGLE_LOGIN_CODE = 9001
    var signupButton: Button = findViewById(R.id.login_btn_SignUp)
    var loginButton: Button = findViewById((R.id.login_btn_LogIn))
    var googleButton: Button = findViewById(R.id.signInButton)

    //var Emailtext: EditText = findViewById(R.id.login_edittext_Email)
    //var Passwordtext: EditText = findViewById(R.id.login_edittext_Password)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        //회원가입
        signupButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        //로그인
       loginButton.setOnClickListener {
           googleLogin()
            //signIn(Emailtext.text.toString(), Passwordtext.text.toString())
        }

        //구글 로그인버튼
        googleButton.setOnClickListener { googleLogin() }

        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInclient = GoogleSignIn.getClient(this, gso)
        //구글 로그인



    }
    fun googleLogin() {
        val signInIntent = googleSignInclient?.signInIntent

        startActivityForResult(signInIntent, GOOGLE_LOGIN_CODE)
    }
    //로그아웃 안하면 자동으로 로그인, 회원가입시 바로 로그인
    override fun onStart() {
        super.onStart()
        moveMainPage(auth?.currentUser)
    }

    //로그인
    private fun signIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            baseContext, "로그인 성공하였습니다!",
                            Toast.LENGTH_SHORT
                        ).show()
                        moveMainPage(auth?.currentUser)
                    } else {
                        Toast.makeText(
                            baseContext, "로그인에 실패하였습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }



    fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        var credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth?.signInWithCredential(credential)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    moveMainPage(task.result?.user)
                } else {
                    //틀렸을 때
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_LOGIN_CODE) {
            var result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }!!
            //구글Api 가 넘겨주는 값 받아옴

            if (result.isSuccess) {
                var accout = result.signInAccount
                firebaseAuthWithGoogle(accout)
                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그인 실패!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //유저정보 넘겨주고 메인 액티비티 호출
    fun moveMainPage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}