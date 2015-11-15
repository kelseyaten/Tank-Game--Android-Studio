package com.example.localadmin.g11_ajh265_kex496.rest;


import android.view.View;
import android.widget.GridView;

import com.example.localadmin.g11_ajh265_kex496.UI.GridAdapter;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created by localadmin on 11/14/15.
 */
@EBean
public class GridUpdater {


    @Bean
    protected GridAdapter mGridAdapter;


    public GridUpdater(){

    }



    public void init( View myGridView, long id, GridWrapper mgrid){


        mGridAdapter.updateList(mgrid);
        mGridAdapter.setTankID(id);
        ((GridView)myGridView).setAdapter(mGridAdapter);

    }


    @org.androidannotations.annotations.UiThread
    public void updateDisplay( GridWrapper mgrid ){

        mGridAdapter.updateList(mgrid);
        mGridAdapter.notifyDataSetChanged();

    }

}
