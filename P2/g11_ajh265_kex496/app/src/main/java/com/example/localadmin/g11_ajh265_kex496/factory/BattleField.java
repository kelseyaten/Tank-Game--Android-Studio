package com.example.localadmin.g11_ajh265_kex496.factory;

import com.example.localadmin.g11_ajh265_kex496.uiobjects.CellObject;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

/**
 * Created by localadmin on 10/31/15.
 * represents all the cell objects on the field
 *
 *
 */
public class BattleField {


    EntityFactory[][] myArray = new EntityFactory[16][16];
    CellObject[][] objectArray = new CellObject[16][16];


    /**
     *
     *
     * @return returns the requested cellobject array
     */
    public  CellObject[][] getObjArray(){

        return objectArray;
    }


    /**
     * constructor for battlefield. initializes entity factory array
     * @param m gridwrapper
     */

    public BattleField( GridWrapper m ){

        for( int x = 0 ; x < 16; x ++ ){
            for( int y = 0; y < 16; y ++ ) {
                myArray[x][y] = new EntityFactory();
            }
        }

    }

    /**
     *
     * sets views at specified location in object array
     * @param x the x pos
     * @param y the y pos
     * @param val the id to set that pos to
     * @param tankId the tank id of the user
     * @return the R.id value of that new cellobject
     */


    public int setView( int x, int y, long val, long tankId ){

        EntityFactory myFactory = new EntityFactory();
        objectArray[x][y] = myFactory.createEntity((int)val, tankId);
        if( objectArray[x][y] instanceof Tank){

            ((Tank)objectArray[x][y]).setMyTanksId(tankId);

            return objectArray[x][y].display( tankId );
        }else{

            return objectArray[x][y].display( val );

        }

    }



}
