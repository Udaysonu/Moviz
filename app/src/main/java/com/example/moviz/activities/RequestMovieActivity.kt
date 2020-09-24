package com.example.moviz.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.moviz.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_request_movie.*

class RequestMovieActivity : AppCompatActivity() {
    val db by lazy{
        FirebaseDatabase.getInstance().getReference()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_movie)
        supportActionBar?.title="CustomerCare"
        request_btn.setOnClickListener {
            Toast.makeText(this,"Request Sent.. We will Upload soon.....",Toast.LENGTH_SHORT)
            request_btn.isEnabled=false
            db.child("movie_requests").push().setValue(request_movie_name_text.text.toString()).addOnCompleteListener {  finish(); }

        }
    }
}