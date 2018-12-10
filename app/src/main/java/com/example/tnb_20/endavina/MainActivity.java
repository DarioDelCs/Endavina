package com.example.tnb_20.endavina;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int number;
    int intentos=0;

    String name="";
    static List<Intentos> arrIntent = new ArrayList<Intentos>();

    static final int REQUEST_IMAGE_CAPTURE = 1;
    File dir = new File("data"+File.separator+"data"+File.separator+"com.example.tnb_20.endavina"+File.separator+"photos");
    File img;
    boolean makePhoto;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makePhoto=false;
        if (arrIntent.size()==0){
            loadInfo();
        }
        //si no esta el fichero creado creamos el directorio de las fotos
        if(!dir.exists()){
            dir.mkdir();
        }
        setContentView(R.layout.activity_main);
        setNumber(new Random().nextInt(100));

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
                if(!editText.getText().toString().equals("")){
                    intentos++;
                    putNumber(editText);
                }
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
            text = "El numero es mas pequeño "+getNumber();
        }else if(Integer.parseInt(editText.getText().toString())<getNumber()){
            text = "El numero es mas grande "+getNumber();
        }else if(Integer.parseInt(editText.getText().toString())==getNumber()){
            text = "Has acertado";

            dialog = new Dialog(MainActivity.this);//llama al dialogo de acierto
            dialog.setContentView(R.layout.dialag);
            dialog.setTitle("Title");

            //imgview
            final ImageView photo = dialog.findViewById(R.id.imageView);
            //boton para añadir la imagen
            Button btnCamera = dialog.findViewById(R.id.btnCamera);
            //boton "ok"
            Button button = dialog.findViewById(R.id.ok);

            //listener del boton de camera
            btnCamera.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });
            //listener boton OK
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    EditText edit = dialog.findViewById(R.id.txt_name);
                    name = edit.getText().toString();
                    if (!name.equals("") && makePhoto) {
                        dialog.dismiss();
                        arrIntent.add(new Intentos(intentos, name, Uri.fromFile(img)));
                        name = "";
                        makePhoto=false;
                        intentos = 0;
                        setNumber(new Random().nextInt(99)+1);
                        Collections.sort(arrIntent);
                        guardarInfo();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "No puedes dejar el nombre/foto en blanco", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });

            dialog.show();
        }

        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int num){ this.number=num; }

    private void loadInfo(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("persistence.txt")));
            String linia;
            while((linia = br.readLine())!=null){
                arrIntent.add(new Intentos(Integer.parseInt(linia.split(";")[0]),linia.split(";")[1], Uri.parse(linia.split(";")[2])));
            }
            br.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void guardarInfo(){
        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("persistence.txt",Context.MODE_PRIVATE));
            for (int i=0; i<arrIntent.size(); i++){
                osw.write(arrIntent.get(i).intentos+";"+arrIntent.get(i).player_name+";"+arrIntent.get(i).photoPath.toString());
                osw.append("\r\n");
            }
            osw.close();

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    //camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            makePhoto=true;

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView iv = dialog.findViewById(R.id.imageView);
            iv.setImageBitmap(imageBitmap);

            OutputStream os = null;
            try {
                if (dir.list().length>0){
                    img = new File(dir, (Integer.parseInt(dir.listFiles()[dir.listFiles().length-1].getName().substring(0,dir.listFiles()[dir.listFiles().length-1].getName().length()-4))+1) + ".png");
                }else{
                    img = new File(dir, "1.png");
                }
                os = new FileOutputStream(img);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
            } catch(IOException e) {
                System.out.println("ERROR al guardar la imagen");
            }
        }

    }
}
