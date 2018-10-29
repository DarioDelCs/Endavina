package com.example.tnb_20.endavina;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);

        super.setTitle("Ranking");
        TextView rank = findViewById(R.id.txtrank);

        ArrayList<Intentos> arrRank=MainActivity.arrIntent;
        int aux;
        /*for (int i=0; i<arrRank.size(); i++){
            for (int y=0; i<arrRank.size(); y++){
                aux=arrRank.get(y).intentos;
            }
        }*/

        for (Intentos tries : MainActivity.arrIntent) {
            rank.setText(rank.getText()+"Nombre: "+tries.player_name+"       Intentos: "+tries.intentos+"\n");
        }
    }
}
