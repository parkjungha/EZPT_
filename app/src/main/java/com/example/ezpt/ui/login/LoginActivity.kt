package com.example.ezpt.ui.login
import android.content.Intent
import android.util.Patterns;
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ezpt.MainActivity
import com.example.ezpt.ProfileSettingActivity
import com.example.ezpt.R
import com.example.ezpt.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // SIGN UP 버튼
        findViewById<Button>(R.id.btn_signUp).setOnClickListener {
            val nextIntent = Intent(this, RegisterActivity::class.java)
            startActivity(nextIntent)
        }

        // SIGN IN 버튼
        findViewById<Button>(R.id.btn_signIn).setOnClickListener {
            signIn()
        }

        findViewById<Button>(R.id.test_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }

    }

    fun signIn() {
        var email = email_edittext.text.toString()
        var password = password_edittext.text.toString()

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful) { // 로그인 성공
                Toast.makeText(
                    this@LoginActivity,
                    R.string.login_succeeded,
                    Toast.LENGTH_SHORT
                ).show()
                moveMainPage()

            } else {
                // 로그인 실패
                Toast.makeText(
                    this@LoginActivity,
                    R.string.login_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun moveMainPage() {
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null) {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}