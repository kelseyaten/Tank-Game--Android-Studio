package com.example.localadmin.g11_ajh265_kex496.rest;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.localadmin.g11_ajh265_kex496.MainActivity;
import com.example.localadmin.g11_ajh265_kex496.TankClientActivity;
import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.database.DBHelper;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 *
 * updates the gridview to reflect current state of the board
 * Created by localadmin on 11/14/15.
 */
@EBean
public class GridUpdater {


    @Bean
    protected GridAdapter mGridAdapter;



    public GridUpdater(){

    }


    /**
     * initilizes the gridadapter
     * @param myGridView the gridview to update to
     * @param id the tanks id
     * @param mgrid the gridwrapper with the content
     */
    public void init( View myGridView, long id, GridWrapper mgrid){

        mGridAdapter.updateList(mgrid);
        mGridAdapter.setTankID(id);
        ((GridView)myGridView).setAdapter(mGridAdapter);



    }


    /**
     *
     *
     * @param mgrid updates the gridview to this gridwrappers data
     */
    @org.androidannotations.annotations.UiThread
    public void updateDisplay( GridWrapper mgrid ){


        mGridAdapter.updateList(mgrid);
        mGridAdapter.notifyDataSetChanged();



    }






}
