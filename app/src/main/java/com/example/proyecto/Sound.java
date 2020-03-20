package com.example.proyecto;

import android.media.MediaPlayer;
import android.view.View;

public class Sound {

    private MediaPlayer mp;
    int posicion = 0;

    public Sound() {
    }

    public void Moneda(View view) {
        MediaPlayer mp = MediaPlayer.create(view.getContext(),R.raw.mariocoin);
        mp.start();
    }

    public void Cliente(View view) {
        MediaPlayer mp = MediaPlayer.create(view.getContext(),R.raw.nuevo);
        mp.start();
    }

    public void Producto(View view) {
        MediaPlayer mp = MediaPlayer.create(view.getContext(),R.raw.cajaregistradora);
        mp.start();
    }
}
