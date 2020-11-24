package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ezpt.ui.login.SearchTrainerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.search_btn).setOnClickListener {
            // if user type이 trainer이면 ?
            val nextIntent = Intent(this, SearchTraineeActivity::class.java)
            // if user type이 trainee이면 ?
            //val nextIntent = Intent(this, SearchTrainerActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.mypage_btn).setOnClickListener {
            // if user type이 trainer이면 ?
            val nextIntent = Intent(this, MypageTrainerActivity::class.java)
            // if user type이 trainee이면 ?
            //val nextIntent = Intent(this, MypageTraineeActivity::class.java)
            startActivity(nextIntent)
        }
        findViewById<Button>(R.id.chat_btn).setOnClickListener {
            val nextIntent = Intent(this, ChatActivity::class.java)
            startActivity(nextIntent)
        }
    }
}