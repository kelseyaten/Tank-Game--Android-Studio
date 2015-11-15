package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import android.widget.ImageView;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.CellObject;

/**
 * Created by localadmin on 10/31/15.
 */
public class EmptyCell extends CellObject {

    public ImageView getMyView() {
        return myView;
    }

    public void setMyView(ImageView myView) {
        this.myView = myView;
    }

    ImageView myView;

    public EmptyCell(){


    }


    public int display( long id ){

        return R.drawable.grass;

    }

    public int updateDisplay( long paramid  ){

        myView.setImageResource(R.drawable.grass );
        return R.drawable.grass;

    }

}
