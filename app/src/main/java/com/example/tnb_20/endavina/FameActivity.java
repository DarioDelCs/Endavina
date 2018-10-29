package com.example.tnb_20.endavina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);

        super.setTitle("Ranking");
        TextView rank = findViewById(R.id.txtrank);

        for (Intentos tries : MainActivity.arrIntent) {
            rank.setText(rank.getText()+"Nombre: "+tries.player_name+"       Intentos: "+tries.intentos+"\n");
        }
    }
}
