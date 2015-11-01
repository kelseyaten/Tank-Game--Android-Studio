package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

/**
 * Created by localadmin on 10/31/15.
 */
public class BattleField {

    GridWrapper myWrapper;
    EntityFactory[][] myArray = new EntityFactory[16][16];
    CellObject[][] objectArray = new CellObject[16][16];
    ImageView[][] viewArray = new ImageView[16][16];


    public BattleField( GridWrapper m ){

            myWrapper = m;
            for( int x = 0 ; x < 16; x ++ ){
                for( int y = 0; y < 16; y ++ ) {
                    myArray[x][y] = new EntityFactory();
                }
            }

    }

    public void CreateField( Context c, GridLayout g, int size, long id ){

        int sizer =   (int)   ((double) ( size )/ 16) ;
        for(int x = 0; x < 16; x ++ ) {
            for( int y = 0; y < 16; y ++ ){
                int num = myWrapper.getGrid()[x][y];
                CellObject myObj = myArray[x][y].createEntity( num );
                objectArray[x][y] = myObj;
                viewArray[x][y] = myObj.display( c,  g, sizer, id );
            }
        }
    }

    public void updateDisplay( long id,  GridWrapper grid ){

        myWrapper = grid;

        for(int x = 0; x < 16; x ++ ) {
            for (int y = 0; y < 16; y++) {

                int one = objectArray[x][y].getValue();
                int two = myWrapper.getGrid()[x][y];

                if( one != two ) {


                    ImageView v =  objectArray[x][y].getMyView();
                    objectArray[x][y] = myArray[x][y].createEntity(two);
                    objectArray[x][y].setMyView( v );
                    objectArray[x][y].updateDisplay(id);

                }


            }
        }


    }





    }
