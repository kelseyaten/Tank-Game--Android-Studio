package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localadmin.g11_ajh265_kex496.R;

/**
 * Created by localadmin on 10/31/15.
 */
public class Tank extends CellObject {

    long id;
    long life;
    long direction;
    long myTankId;

    public ImageView getMyView() {
        return myView;
    }

    public void setMyView(ImageView myView) {
        this.myView = myView;
    }

    ImageView myView;


    public Tank(long x){

        direction = x % 10;
        life = ( (x % 10000) - direction ) / 10;
        id = ((x % 10000000) - ( x % 10000 ) ) / 10000;

    }

    public long getDirection(){
        return direction;
    }

    public void setMyTanksId( long x ){
        myTankId = x;

    }

    public long getMyTanksId(  ){

        return myTankId;
    }


    public int display(  long paramid  ){


        int resource = 0;

        if( paramid == id ) {

            switch((int)direction){
                case 0: resource = R.drawable.tankup;
                    break;
                case 2: resource = R.drawable.tankright;
                    break;
                case 4: resource = R.drawable.tankdown;
                    break;
                case 6: resource = R.drawable.tankleft;
                    break;
            }


            return resource;
        }else{

            switch((int)direction){
                case 0: resource = R.drawable.enemyup;
                    break;
                case 2: resource = R.drawable.enemyright;
                    break;
                case 4: resource = R.drawable.enemydown;
                    break;
                case 6: resource = R.drawable.enemyleft;
                    break;
            }


            return resource;
        }
    }


    public int updateDisplay( long paramid  ){

       return -1;

    }
}
