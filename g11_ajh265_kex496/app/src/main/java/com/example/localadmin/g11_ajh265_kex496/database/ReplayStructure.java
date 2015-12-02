package com.example.localadmin.g11_ajh265_kex496.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

/**
 * Created by localadmin on 12/1/15.
 */

@EBean
public class ReplayStructure{

    private ArrayList<GridWrapper> myGridWrappers;


    public ReplayStructure(){
        myGridWrappers = new ArrayList<GridWrapper>();
    }

    public void insert( GridWrapper g ){

        myGridWrappers.add(g);
    }

    public GridWrapper getIndex( int x ){

        return myGridWrappers.get(x);
    }

    public int getSize(){


        return myGridWrappers.size();
    }

    public String toString(){
        String returner = "";


        for( GridWrapper grid: myGridWrappers ){

            for( int x = 0; x < 16; x ++ ){
                for( int y = 0; y < 16; y ++ ){

                    returner += grid.getGrid()[x][y];
                }

            }


        }
        return returner;
    }



}
