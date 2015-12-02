package com.example.localadmin.g11_ajh265_kex496.UI;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.localadmin.g11_ajh265_kex496.R;

import com.example.localadmin.g11_ajh265_kex496.factory.BattleField;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.CellObject;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

import java.util.Observable;

/**
 *
 *
 * adapter class that deals with adapting a gamegrid to the gridview
 * Created by localadmin on 11/2/15.
 */

@EBean
public class GridAdapter extends BaseAdapter {

    private final Object monitor = new Object();

    @SystemService
    protected LayoutInflater inflater;


    private int[][] mEntities = new int[16][16];
    private long tankId;
    private BattleField myField;

    public static boolean islocated = false;

    public  CellObject[][] getObjArray(){
      return myField.getObjArray();
    }


    /**
     *
     * sets the tankId fo the users tank
     * @param x the tankid
     */

    public void setTankID( long x ){

        tankId = x;
    }


    /**
     *
     * updates the gridwrapper which it is reading from
     *
     * @param myGrid
     */
    public void updateList(GridWrapper myGrid ) {

        myField = new BattleField( myGrid );

        islocated = false;
        for( int x = 0; x < 16; x ++ ){
            for( int y = 0; y < 16; y ++ ){


                try {
                    long co = myGrid.getGrid()[x][y];
                    long comp = ((co % 10000000) - (co % 10000)) / 10000;


                    if( comp == Tank.getMyLogicTank().getId() ){
                        islocated = true;

                    }

                }catch(Exception e){

                }



                }

            }



        synchronized (monitor) {
            this.mEntities = myGrid.getGrid();
            this.notifyDataSetChanged();
        }

        if( islocated == false ){

            //restart activity here



        }

    }

    @Override
    public int getCount() {
        return 16 * 16;
    }

    @Override
    public Object getItem(int position) {
        return mEntities[(int) position / 16][position % 16];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.field_item, null);
        }

        int row = position / 16;
        int col = position % 16;

        long val = mEntities[row] [col];

        if (convertView instanceof ImageView) {
            synchronized (monitor) {

                ((ImageView) convertView).setImageResource(myField.setView(row, col, val, tankId));


            }
        }

        return convertView;
    }
}

