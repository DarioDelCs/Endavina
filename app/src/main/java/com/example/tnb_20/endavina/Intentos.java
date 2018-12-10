package com.example.tnb_20.endavina;

import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

public class Intentos implements Comparable<Intentos>{

    public int intentos;
    public String player_name;
    public Uri photoPath;

    public Intentos(int intento, String nombre, Uri photoPath){
        this.intentos=intento;
        this.player_name=nombre;
        this.photoPath=photoPath;
    }

    @Override
    public int compareTo(Intentos intentos){
        return this.intentos-intentos.intentos;
    }

    @Override
    public String toString() {
        return "Intentos [inentos=" + intentos + ", player_name=" + player_name + "]";
    }

}
