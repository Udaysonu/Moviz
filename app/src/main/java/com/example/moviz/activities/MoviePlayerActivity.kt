package com.example.moviz.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.moviz.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_movie_player.*

class MoviePlayerActivity : AppCompatActivity() {
    lateinit var simpleExoPlayer:SimpleExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        //setting full screen view
        setFullScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_player)
        supportActionBar?.hide()
        val streamlink=intent.getStringExtra("MOV_LINK")
        initExoPlayer(streamlink);

    }

    private fun setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    private fun initExoPlayer(streamlink:String) {

        // available in exoplayer documenentation
        simpleExoPlayer=ExoPlayerFactory.newSimpleInstance(this);
        movie_exo_player.player=simpleExoPlayer
        val dataSourceFactory=DefaultDataSourceFactory(this,
            Util.getUserAgent(this,"appname"))
        val videosoure=ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(streamlink))
        simpleExoPlayer.prepare(videosoure)
        simpleExoPlayer.playWhenReady=true
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }
}