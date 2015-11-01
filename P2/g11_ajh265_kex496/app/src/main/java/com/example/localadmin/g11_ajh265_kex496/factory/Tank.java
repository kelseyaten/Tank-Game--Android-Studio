package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class Tank extends CellObject {

    long id;
    int life;
    int direction;

    public ImageView getMyView() {
        return myView;
    }

    public void setMyView(ImageView myView) {
        this.myView = myView;
    }

    ImageView myView;


    public Tank(int x){

        direction = x % 10;
        life = ( (x % 10000) - direction ) / 10;
        id = ((x % 10000000) - ( x % 10000 ) ) / 10000;

    }





    public ImageView display( Context c, GridLayout g, int size, long paramid ){

        myView = new ImageView( c );
        if( paramid == id ) {
            myView.setImageResource(R.drawable.mytank);
        }else{
            myView.setImageResource(R.drawable.tank);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size, size);
        myView.setLayoutParams(layoutParams);
        g.addView(myView);

        return myView;
    }


    public int updateDisplay( long paramid  ){

        if( paramid == id ) {

            myView.setImageResource(R.drawable.mytank);

           return R.drawable.mytank;
        }else{

            myView.setImageResource(R.drawable.tank);
           return R.drawable.tank;
        }

    }
}
