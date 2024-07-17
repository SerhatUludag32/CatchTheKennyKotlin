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
    var time = 16
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timer()
    }

    fun timer(){
        runnable = object : Runnable {
            override fun run() {
                time--
                binding.timeText.text = "Time : $time"
                handler.postDelayed(runnable, 1000)
            }
        }
        handler.post(runnable)
    }

}