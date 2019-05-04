package com.example.proyecto;

import android.media.MediaPlayer;
import android.view.View;

public class SoundActivity {

    private MediaPlayer mp;
    int posicion = 0;


    public void Moneda() {
        MediaPlayer mp = MediaPlayer.create(this,R.raw.mario_coin);
        mp.start();
    }
}
