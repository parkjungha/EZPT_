package com.example.ezpt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezpt.data.Users
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_search_trainee.*

class SearchTraineeActivity : AppCompatActivity() {
    var firestore : FirebaseFirestore? = null
    var usernameList: MutableList<String>  = ArrayList<String>()
    var sexList: MutableList<String>  = ArrayList<String>()
    var regionList: MutableList<String>  = ArrayList<String>()
    var descriptionList: MutableList<String>  = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_trainee)

        firestore = FirebaseFirestore.getInstance()

        readQueryWhereEqualToData()

        findViewById<Button>(R.id.back_btn).setOnClickListener {
            val nextIntent = Intent(this, MainActivity::class.java)
            startActivity(nextIntent)
        }
    }

    fun readQueryWhereEqualToData() {
        firestore?.collection("Users")?.whereEqualTo("userType","Trainer")?.get()?.
                addOnCompleteListener{
                    task ->
                    if(task.isSuccessful){
                        for (dc in task.result!!.documents){
                            var user = dc.toObject(Users::class.java)
                            usernameList.add(user!!.username.toString())
                            sexList.add(user!!.sex.toString())
                            regionList.add(user!!.region.toString())
                            descriptionList.add(user!!.description.toString())
                            Log.d("SearchTraineeActivity",user.toString())
                            Log.e("SearchTraineeActivity",usernameList.toString())
                            Log.e("SearchTraineeActivity",sexList.toString())
                            Log.e("SearchTraineeActivity",regionList.toString())
                            Log.e("SearchTraineeActivity",descriptionList.toString())
                        }
                        setText()
                    }
                }
    }

    fun setText() {
        username_textview1.text = usernameList[0]
        sex_textview1.text = sexList[0]
        region_textview1.text = regionList[0]
        description_textview1.text = descriptionList[0]
        username_textview2.text = usernameList[1]
        sex_textview2.text = sexList[1]
        region_textview2.text = regionList[1]
        description_textview2.text = descriptionList[1]
    }


}