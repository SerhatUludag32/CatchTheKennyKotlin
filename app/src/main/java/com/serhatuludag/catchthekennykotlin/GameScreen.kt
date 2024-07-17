package com.serhatuludag.catchthekennykotlin

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.serhatuludag.catchthekennykotlin.databinding.ActivityGameScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameScreen : AppCompatActivity() {
    private lateinit var binding: ActivityGameScreenBinding
    var score = 0
    var time = 16
    private var job: Job? = null
    var runnable : Runnable = Runnable {  }
    var handler : Handler = Handler(Looper.getMainLooper())
    private lateinit var ballArray: ArrayList<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startGame()

        ballArray = ArrayList(binding.gridLayout.childCount)
        for(i in 0 until binding.gridLayout.childCount){
            ballArray.add(binding.gridLayout.getChildAt(i) as ImageView)
        }
        hideImages(ballArray)
    }

    private fun hideImages(ballArray: ArrayList<ImageView>) {
        ballArray.forEach { it.visibility = View.INVISIBLE }
        val randomIndex = (0..8).random()
        val imageView = ballArray[randomIndex]
        imageView.visibility = View.VISIBLE
    }

    fun click(view: View){
        score++
        binding.scoreText.text = "Score : $score"
    }

    private fun startBallChanging() {
        job = CoroutineScope(Dispatchers.Main).launch {
            while (time > 0) {
                hideImages(ballArray)
                delay(750) // Change ball every 0.75 second
            }
        }
    }

    internal fun timer(){
        startBallChanging()
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
    internal fun startGame(){
        time = 16
        binding.timeText.text = "Time : $time"
        score = 0
        timer()
    }

    private fun restartAlert(){
        //Alert and Ask user to restart the game when time is over
        val alert = AlertDialog.Builder(this)
        alert.setCancelable(false)
        alert.setTitle("Game Over")
        alert.setMessage("Your Score: $score\nDo you want to restart the game?")
        alert.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                score=0
                binding.scoreText.text = "Score : $score"
                startGame()
            }
        })
        alert.setNegativeButton("No",object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                finish()
            }
        })
        alert.show()

    }


}