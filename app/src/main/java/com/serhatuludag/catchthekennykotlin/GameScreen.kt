package com.serhatuludag.catchthekennykotlin

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
        startGame()
    }

    fun timer(){
        runnable = object : Runnable {
            override fun run() {
                time--

                binding.timeText.text = "Time : $time"
                if(time>0){
                    handler.postDelayed(runnable, 1000)
                }else{
                    handler.removeCallbacks(runnable)
                    restartAlert()
                }

            }
        }
        handler.post(runnable)
    }
    fun startGame(){
        time = 16
        binding.timeText.text = "Time : $time"
        score = 0
        timer()

    }

    fun restartAlert(){
        //Alert and Ask user to restart the game when time is over
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Restart")
        alert.setMessage("Dou you want to restart the game?")
        alert.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                startGame()
            }
        })
        alert.setNegativeButton("No",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                Toast.makeText(this@GameScreen,"Game Over",Toast.LENGTH_LONG).show()
            }
        })
        alert.show()

    }



}