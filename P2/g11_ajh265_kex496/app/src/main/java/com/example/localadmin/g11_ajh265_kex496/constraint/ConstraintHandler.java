package com.example.localadmin.g11_ajh265_kex496.constraint;


import com.example.localadmin.g11_ajh265_kex496.rest.UIUpdate;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Bullet;
import com.example.localadmin.g11_ajh265_kex496.uiobjects.Tank;

import org.androidannotations.annotations.EBean;

/**
 *
 * handles constraint checking for the users tank
 * Created by localadmin on 11/14/15.
 */
@EBean
public class ConstraintHandler {


    private LogicTank myLogicTank;
    private BulletObserver myBulletObserver;


    /**
     * default constructor for the class
     *
     */
    public ConstraintHandler(){

        myLogicTank = Tank.getMyLogicTank();
        myBulletObserver = UIUpdate.myBulletObserver;

    }


    /**
     *
     * @return returns the users tankId
     */
    public long getTankId(){


        return myLogicTank.getId();
    }


    /**
     *
     * @return a boolean saying if the tank can be fired at that time or not
     */
    public boolean canFire(  ){


        return myBulletObserver.canFire();

    }


    /**
     *
     * returns a bool saying if the tank can move or not
     *
     * @param id id of the tank that is requesting constraint checks
     * @return bool saying if it can move
     */
    public boolean move( int id ){


        int direction = (int)myLogicTank.getDirection();


        if( ( direction == 0  ||  direction == 4 ) && ( id == 0  ||  id == 4 ) )
            return true;
        if( ( direction == 2  ||  direction == 6 ) && ( id == 2  ||  id == 6 ) )
            return true;
        return false;

    }


    /**
     *
     * @return returns the int of the direction that the calling tank is able to move to when turn
     * right is called
     */

    public int turnRight(){


        int direction = (int)myLogicTank.getDirection();
        action myAction = null;

        switch( direction ){

            case 2:
                myAction = action.DOWN;
                break;
            case 4:
                myAction = action.LEFT;
                break;
            case 6:
                myAction = action.UP;
                break;
            case 0:
                myAction = action.RIGHT;
                break;

        }

        return getEnumValue( myAction );

    }

    /**
     *
     * @return returns the int of the direction that the calling tank is able to move to when turn
     * left is called
     */

    public int turnLeft( ){


        int direction = (int)myLogicTank.getDirection();
        action myAction = null;
        switch( direction ){

            case 2:
                myAction = action.UP;
                break;
            case 0:
                myAction = action.LEFT;
                break;
            case 6:
                myAction = action.DOWN;
                break;
            case 4:
                myAction = action.RIGHT;
                break;

        }
        return getEnumValue( myAction );

    }



    public enum action {
        UP, DOWN, LEFT, RIGHT
    }


    /**
     *
     * @param m the enum passed in
     * @return the direction value of the enum passed in
     */
    public int getEnumValue( action m ){

        if( m == action.UP ){

            return 0;
        }else if( m == action.DOWN ){

            return 4;
        }else if( m == action.LEFT ){

            return 6;
        }else if( m == action.RIGHT ){
            return 2;
        }

        return -1;
    }


}
