package com.example.tnb_20.endavina;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.File;
import java.util.Collections;

public class FameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fame_activity);

        super.setTitle("Ranking");


        ArrayAdapter<Intentos> arrAdapter = new ArrayAdapter<Intentos>(this, R.layout.fame_activity, MainActivity.arrIntent){
            @Override
            public View getView(int pos, View convertView, ViewGroup container)
            {
                // getView ens construeix el layout i hi "pinta" els valors de l'element en la posició pos
                if( convertView==null ) {
                    // inicialitzem l'element la View amb el seu layout
                    convertView = getLayoutInflater().inflate(R.layout.item_layout, container, false);
                }
                // "Pintem" valors (també quan es refresca)
                ((TextView) convertView.findViewById(R.id.name)).setText(getItem(pos).player_name);
                ((TextView) convertView.findViewById(R.id.tries)).setText(Integer.toString(getItem(pos).intentos));
                ((ImageView) convertView.findViewById(R.id.imgView)).setImageURI(getItem(pos).photoPath);
                return convertView;
            }
        };

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(arrAdapter);




        /*ListView rank = findViewById(R.id.listView);



        ImageView img = findViewById(R.id.image);
        if(dir.listFiles().length>0) {
            File image = new File(dir, dir.listFiles()[dir.listFiles().length - 1].getName());
            if (image.exists()) {
                Uri uri = Uri.fromFile(image);
                img.setImageURI(uri);
            }
        }



        for (Intentos tries : MainActivity.arrIntent) {

            rank.add

            ImageView img = rank.setAdapter(Adapter);
            rank.addFooterView(new ImageView(ImageView. Uri.fromFile(new File(tries.photoPath))), "Nombre: "+tries.player_name+"\tIntentos: "+tries.intentos+"\n", false);
            //rank.setText(rank.getText()+"Nombre: "+tries.player_name+"       Intentos: "+tries.intentos+"\n");
        }*/
    }
}
