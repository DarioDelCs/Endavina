package com.example.tnb_20.endavina;

public class Intentos implements Comparable<Intentos>{

    public int intentos;
    public String player_name;

    public Intentos(int intento, String nombre){
        this.intentos=intento;
        this.player_name=nombre;
    }

    @Override
    public int compareTo(Intentos intentos){
        return this.intentos-intentos.intentos;
        //return Integer.compare(this.intentos, intentos.intentos);
    }

    @Override
    public String toString() {
        return "Intentos [inentos=" + intentos + ", player_name=" + player_name + "]";
    }

}
