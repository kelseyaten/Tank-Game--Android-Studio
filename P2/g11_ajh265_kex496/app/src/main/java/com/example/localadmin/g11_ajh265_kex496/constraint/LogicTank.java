package com.example.localadmin.g11_ajh265_kex496.constraint;


import com.example.localadmin.g11_ajh265_kex496.util.TankInfoWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 *
 *
 * tank that watches users tank ( its an observer). allows for constraint checking.
 * Created by localadmin on 11/10/15.
 */
public class LogicTank extends Observable implements Observer  {



    private long direction;
    private long id;

    public long getLife() {
        return life;
    }

    private long life;

    private long prevlife;

    public long getDirection() {
        return direction;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    /**
     *
     * default constructor of tank
     */

    public LogicTank (){

        direction = 0;
        id = 0;
        life = 0;
        prevlife = life;



    }

    @Override
    public void update(Observable observable, Object data) {

        TankInfoWrapper myWrapper = (TankInfoWrapper) data;
        direction = ((TankInfoWrapper) data).getDirection();
        id = ((TankInfoWrapper) data).getId();
        life = ((TankInfoWrapper) data).getLife();
        if( prevlife == 0 ){

            prevlife = life;
        }else{

            if( prevlife != life ){

                prevlife = life;
                setChanged();
                notifyObservers();
            }

        }


    }
}
