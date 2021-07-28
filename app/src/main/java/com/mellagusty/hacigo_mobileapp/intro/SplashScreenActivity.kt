package com.mellagusty.hacigo_mobileapp.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.mellagusty.hacigo_mobileapp.ui.MainActivity
import com.mellagusty.hacigo_mobileapp.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, IntroSlideActivity::class.java))
            finish()
        },3000)
    }
}