package com.example.dcaballero.socialapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dcaballero.socialapp.R;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }

    public void ejecutar_ayuda_newpost(View view){
        Intent i = new Intent(this, AyudaNewPost.class);
        startActivity(i);
    }
    public void ejecutar_ayuda_usuarios(View view){
        Intent i = new Intent(this, AyudaUsuarios.class);
        startActivity(i);
    }
    public void ejecutar_ayuda_posts(View view){
        Intent i = new Intent(this, AyudaPost.class);
        startActivity(i);
    }
    public void ejecutar_ayuda_like(View view){
        Intent i = new Intent(this, AyudaLike.class);
        startActivity(i);
    }
}
