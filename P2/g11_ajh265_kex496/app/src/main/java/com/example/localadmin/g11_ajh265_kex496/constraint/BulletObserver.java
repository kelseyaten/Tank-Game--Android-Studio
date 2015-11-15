package com.example.localadmin.g11_ajh265_kex496.constraint;


import android.util.Log;

import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by localadmin on 11/15/15.
 * The BulletObserver class observes the UIUpdate Class for changes in the grid
 * when changes are detected, the BulletObserver counts the number of bullets on
 * the grid and sets a variable accordingly.
 *
 */

@EBean
public class BulletObserver implements Observer {


    int numBullets;


    /**
     * default constructor for the BulletObserver Class
     */

    public BulletObserver(){

        numBullets = 0;
    }


    /**
     * @return boolean representing if the users tank can fire
     */
    public boolean canFire(){

        if( numBullets >= 2 ){

            return false;
        }

        return true;
    }

    /**
     * searches field and counts bullets
     * @param m the gridwrapper containing the field to be searched
     *
     */

    @Background
    public void searchField( GridWrapper m ){

        int [][]field = m.getGrid();

        int returner = 0;
        for( int x = 0; x < 16; x ++) {
            for( int y = 0; y < 16; y ++ ){

                if( field[x][y] > 2000000 && field[x][y] < 3000000){

                    long comp = ((field[x][y] % 1000000) - field[x][y]%1000)/1000;
                    if( comp == Tank.getMyLogicTank().getId() ){

                        returner ++;
                    }


                }
            }
        }

        numBullets = returner;

    }


    /**
     *

     * @param observable
     * @param data
     */
    @Override
    public void update(Observable observable, Object data) {


        GridWrapper myGrid = (GridWrapper) data;
        searchField( myGrid );


    }
}
