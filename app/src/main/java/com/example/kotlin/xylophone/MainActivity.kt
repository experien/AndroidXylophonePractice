package com.example.kotlin.xylophone

import android.content.pm.ActivityInfo
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class MainActivity : AppCompatActivity() {

//        미디어 플레이어 사용 시
//        val mediaPlayer = MediaPlayer.create(this, R.raw.do1)
//        mediaPlayer.start()
//        mediaPlayer.release()

    private val soundPool = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        SoundPool.Builder().setMaxStreams(8).build()
    } else {
        SoundPool(8, AudioManager.STREAM_MUSIC, 0)
    }

    private val sounds = listOf(
        Pair(R.id.do1, R.raw.do1),
        Pair(R.id.re, R.raw.re),
        Pair(R.id.mi, R.raw.mi),
        Pair(R.id.fa, R.raw.fa),
        Pair(R.id.sol, R.raw.sol),
        Pair(R.id.la, R.raw.la),
        Pair(R.id.si, R.raw.si),
        Pair(R.id.do2, R.raw.do2)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE -> manifest
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sounds.forEach { tune(it) }
    }


    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }


    private fun tune(pitch: Pair<Int, Int>) {
        findViewById<TextView>(pitch.first).setOnClickListener {
            val soundId = soundPool.load(this, pitch.second, 1)
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
}
