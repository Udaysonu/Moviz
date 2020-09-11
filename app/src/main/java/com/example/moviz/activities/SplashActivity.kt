package com.example.moviz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviz.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
       lv_note.alpha=0f
        lv_note.animate().setDuration(3000).alpha(1f).withEndAction {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)

        }
    }
}