package uz.gita.speedometercustomview

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myView: MyCustomView = findViewById(R.id.my_view)

//        pedal.setOnLongClickListener {
//            myView.speedListener(x).onEach {
//                delay(100)
//                x += 1
//                return@onEach
//            }.launchIn(GlobalScope)
//            true
//        }

//        pedal.setOnTouchListener(View.OnTouchListener { v, event ->
//
//            return@OnTouchListener true
//        })

//        pedal.setOnTouchListener { v, event ->
//
//            if (event?.action == MotionEvent.) {
//                myView.speedListener(1).onEach {
//                    delay(100)
//                }.launchIn(GlobalScope)
//            }
//
//            true
//        }

        findViewById<SeekBar>(R.id.seekBar).max = 220
        findViewById<SeekBar>(R.id.seekBar).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                myView.speedListener(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }
}