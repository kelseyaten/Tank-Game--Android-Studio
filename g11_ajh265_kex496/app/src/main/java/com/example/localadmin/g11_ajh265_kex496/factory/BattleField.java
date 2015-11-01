package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;

import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

/**
 * Created by localadmin on 10/31/15.
 */
public class BattleField {

    GridWrapper myWrapper;
    EntityFactory[][] myArray;

    public BattleField( GridWrapper m ){
            myWrapper = m;

            for( int x = 0 ; x < 16; x ++ ){
                for( int y = 0; y < 16; y ++ ) {
                    myArray[x][y] = new EntityFactory();
                }
            }

    }

    public void CreateField( Context c, GridLayout g, int size, long id ){

        int sizer =   (int)   ((double) ( size )/ 15) ;
        for(int x =0; x < 16; x ++ ) {
            for( int y = 0; y < 16; y ++ ){

                int num = myWrapper.getGrid()[x][y];
                CellObject myObj = myArray[x][y].createEntity( num );
                myObj.display( c,  g, sizer, id );

            }
        }


    }


}
