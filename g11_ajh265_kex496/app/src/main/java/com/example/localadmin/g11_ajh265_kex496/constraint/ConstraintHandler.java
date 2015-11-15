package com.example.localadmin.g11_ajh265_kex496.constraint;


import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;

import org.androidannotations.annotations.EBean;

/**
 * Created by localadmin on 11/14/15.
 */
@EBean
public class ConstraintHandler {


    private LogicTank myLogicTank;

    public ConstraintHandler(){

        myLogicTank = Tank.getMyLogicTank();

    }


    public long getTankId(){


        return myLogicTank.getId();
    }



    public boolean move( int id ){


        int direction = (int)myLogicTank.getDirection();


        if( ( direction == 0  ||  direction == 4 ) && ( id == 0  ||  id == 4 ) )
            return true;
        if( ( direction == 2  ||  direction == 6 ) && ( id == 2  ||  id == 6 ) )
            return true;
        return false;

    }



    public int turnRight(){

        int dir = 0;
        int direction = (int)myLogicTank.getDirection();
        switch( direction ){

            case 2:
                dir = 4;
                break;
            case 4:
                dir = 6;
                break;
            case 6:
                dir = 0;
                break;
            case 0:
                dir = 2;
                break;

        }

        return dir;

    }


    public int turnLeft( ){

        int dir = 0;
        int direction = (int)myLogicTank.getDirection();
        switch( direction ){

            case 2:
                dir = 0;
                break;
            case 0:
                dir = 6;
                break;
            case 6:
                dir = 4;
                break;
            case 4:
                dir = 2;
                break;

        }
        return dir;

    }


}
