package com.example.tnb_20.endavina;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);
        super.setTitle("Ranking");
        Intentos intentos = new Intentos();

        Context context = getApplicationContext();
        CharSequence text=intentos.getPlayer_name()+" intentos: "+intentos.getIntentos();

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


    }
}
