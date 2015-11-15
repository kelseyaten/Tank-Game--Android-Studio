package com.example.localadmin.g11_ajh265_kex496;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void start( View v ){

        Intent myIntent = new Intent(getBaseContext(), TankClientActivity_.class);
        startActivity(myIntent);

    }
}
