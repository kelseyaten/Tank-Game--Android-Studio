package com.example.localadmin.g11_ajh265_kex496.constraint;

import android.content.Intent;
import android.util.Log;

import com.example.localadmin.g11_ajh265_kex496.TankClientActivity_;
import com.example.localadmin.g11_ajh265_kex496.util.TankInfoWrapper;

import java.util.Observable;
import java.util.Observer;

/**
 *
 *
 * tank that watches users tank ( its an observer). allows for constraint checking.
 * Created by localadmin on 11/10/15.
 */
public class LogicTank  implements Observer  {



    private long direction;
    private long id;
    private long life;

    public long getDirection() {
        return direction;
    }

    public void setDirection(long direction) {
        this.direction = direction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLife() {
        return life;
    }

    public void setLife(long life) {
        this.life = life;
    }

    /**
     *
     * default constructor of tank
     */

    public LogicTank (){

        direction = 0;
        id = 0;
        life = 0;

    }

    @Override
    public void update(Observable observable, Object data) {

        TankInfoWrapper myWrapper = (TankInfoWrapper) data;
        direction = ((TankInfoWrapper) data).getDirection();
        id = ((TankInfoWrapper) data).getId();
        life = ((TankInfoWrapper) data).getLife();



    }
}
