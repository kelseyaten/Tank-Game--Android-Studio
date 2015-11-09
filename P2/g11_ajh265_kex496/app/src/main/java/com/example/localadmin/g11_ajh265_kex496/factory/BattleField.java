package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

/**
 * Created by localadmin on 10/31/15.
 */
public class BattleField {


    EntityFactory[][] myArray = new EntityFactory[16][16];
    CellObject[][] objectArray = new CellObject[16][16];



    public BattleField( GridWrapper m ){

        for( int x = 0 ; x < 16; x ++ ){
            for( int y = 0; y < 16; y ++ ) {
                myArray[x][y] = new EntityFactory();
            }
        }

    }




    public int setView( int x, int y, long val, long tankId ){

        EntityFactory myFactory = new EntityFactory();
        objectArray[x][y] = myFactory.createEntity((int)val, tankId);
        if( objectArray[x][y] instanceof Tank ){

            ((Tank)objectArray[x][y]).setMyTanksId(tankId);


            return objectArray[x][y].display( tankId );
        }else{

            return objectArray[x][y].display( val );

        }





    }



}
