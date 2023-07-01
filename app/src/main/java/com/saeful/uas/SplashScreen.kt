package com.saeful.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.saeful.uas.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        Handler().postDelayed({
            val moveMain = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(moveMain)
        }, 2000)
    }
}