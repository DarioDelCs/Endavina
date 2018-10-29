package com.example.tnb_20.endavina;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int number;
    int intentos=0;

    String name="";
    static ArrayList<Intentos> arrIntent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNumber((int)(Math.random()*99)+1);

        final EditText editText = findViewById(R.id.editText_num);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    putNumber(editText);
                    return true;
                }
                return false;
            }
        });
        final Button button = findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                putNumber(editText);
            }
        });
        final Button bRanking = findViewById(R.id.ranking);
        final Intent intent = new Intent();
        bRanking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FameActivity.class);
                startActivity(intent);
            }
        });

    }

    public void putNumber(EditText editText){
        Context context = getApplicationContext();
        CharSequence text=null;
        if (Integer.parseInt(editText.getText().toString())>getNumber()){
            text = "El numero es mas pequeño"+getNumber();
            intentos++;
        }else if(Integer.parseInt(editText.getText().toString())<getNumber()){
            text = "El numero es mas grande";
            intentos++;
        }else if(Integer.parseInt(editText.getText().toString())==getNumber()){
            text = "Has acertado";

            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialag);
            dialog.setTitle("Title");

            Button button = dialog.findViewById(R.id.ok);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    while (name=="") {
                        EditText edit = dialog.findViewById(R.id.txt_name);
                        name = edit.getText().toString();
                    }
                    dialog.dismiss();
                    arrIntent.add(new Intentos(intentos,name));
                    name="";
                    intentos=0;
                }
            });

            dialog.show();

            setNumber((int)(Math.random()*101)+1);
        }

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int num){ this.number=num; }
}
