package com.example.ezpt.ui.login
import android.util.Patterns;
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ezpt.R
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {

    // 비밀번호 정규식
    private val PASSWORD_PATTERN: Pattern = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$")

    // 파이어베이스 인증 객체 생성
    private var firebaseAuth: FirebaseAuth? = null

    // 이메일과 비밀번호
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null

    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
    }

    fun singUp(view: View?) {
        email = editTextEmail!!.text.toString()
        password = editTextPassword!!.text.toString()
        if (isValidEmail() && isValidPasswd()) {
            createUser(email, password)
        }
    }

    fun signIn(view: View?) {
        email = editTextEmail!!.text.toString()
        password = editTextPassword!!.text.toString()
        if (isValidEmail() && isValidPasswd()) {
            loginUser(email, password)
        }
    }

    // 이메일 유효성 검사
    private fun isValidEmail(): Boolean {
        return if (email.isEmpty()) {
            // 이메일 공백
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            false
        } else {
            true
        }
    }

    // 비밀번호 유효성 검사
    private fun isValidPasswd(): Boolean {
        return if (password.isEmpty()) {
            // 비밀번호 공백
            false
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            false
        } else {
            true
        }
    }

    // 회원가입
    private fun createUser(email: String, password: String) {
        firebaseAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // 회원가입 성공
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.register_succeeded,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // 회원가입 실패
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.register_failed,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    // 로그인
    private fun loginUser(email: String, password: String) {
        firebaseAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공
                    Toast.makeText(
                        this@LoginActivity,
                        R.string.login_succeeded,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // 로그인 실패
                    Toast.makeText(this@LoginActivity,
                        R.string.login_failed,
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}