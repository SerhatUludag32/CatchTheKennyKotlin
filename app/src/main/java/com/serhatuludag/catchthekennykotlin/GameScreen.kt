package com.serhatuludag.catchthekennykotlin

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.serhatuludag.catchthekennykotlin.databinding.ActivityGameScreenBinding

class GameScreen : AppCompatActivity() {
    private lateinit var binding: ActivityGameScreenBinding
    var score = 0
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}