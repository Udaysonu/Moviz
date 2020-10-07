package com.example.moviz.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.moviz.R
import com.google.android.exoplayer2.ExoPlayer
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

        //hide the support action bar to make screen more natural
        supportActionBar?.hide()

        val streamlink=intent.getStringExtra("MOV_LINK")

        //initiializes the exo-player
        initExoPlayer(streamlink);

    }

    //this function sets the full feature screen
    private fun setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }


    // this opens the exoplayer
    private fun initExoPlayer(streamlink:String) {

        // available in exoplayer documenentation
        simpleExoPlayer=ExoPlayerFactory.newSimpleInstance(this);
        // add exoplayer listener it is triggered when ever the stateof the exoplayer is changed
        simpleExoPlayer.addListener(object:ExoPlayer.EventListener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)
                if(playbackState==ExoPlayer.STATE_BUFFERING)
                {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                    video_loading.visibility= View.VISIBLE
                }
                else
                {
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                    video_loading.visibility=View.INVISIBLE
                }
            }


        })
        movie_exo_player.player=simpleExoPlayer
        val dataSourceFactory=DefaultDataSourceFactory(this,
            Util.getUserAgent(this,"appname"))
        val videosoure=ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(streamlink))
        simpleExoPlayer.prepare(videosoure)

        simpleExoPlayer.playWhenReady=true
    }

    // ondestroying release the resources of exoplayer
     override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }
}