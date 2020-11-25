package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ezpt.data.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_trainee_mypage.*

class MypageTraineeActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    var auth: FirebaseAuth? = null
    var currentUserEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_mypage)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        currentUserEmail = auth?.currentUser?.email!!

        dataRW()

        findViewById<Button>(R.id.back_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
    }

    fun dataRW() {
        firestore?.collection("Users")?.whereEqualTo("email", currentUserEmail)?.get()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (dc in task.result!!.documents) {
                        var user = dc.toObject(Users::class.java)

                        profile_username.text = "User Name : "+user!!.username.toString()
                        profile_email.text = "Email : "+user!!.email.toString()
                        profile_region.text = "Region : "+user!!.region.toString()
                        profile_description.text = "Description : \n"+user!!.description.toString()
                    }
                }
            }
    }
}