package com.example.localadmin.g11_ajh265_kex496.uiobjects;

import android.widget.ImageView;

import com.example.localadmin.g11_ajh265_kex496.R;
import com.example.localadmin.g11_ajh265_kex496.constraint.LogicTank;
import com.example.localadmin.g11_ajh265_kex496.util.TankInfoWrapper;

import java.util.Observer;

/**
 *
 * tank object
 * Created by localadmin on 10/31/15.
 */
public class Tank extends CellObject {

    public long getId() {
        return id;
    }

    long id;
    long life;
    long direction;
    long myTankId;




    static LogicTank myLogicTank = new LogicTank();


    /**
     *
     * constructor
     * @param x id of the tank being created
     */
    public Tank(long x){

        direction = x % 10;
        life = ( (x % 10000) - direction ) / 10;
        id = ((x % 10000000) - ( x % 10000 ) ) / 10000;

    }


    public void setMyTanksId( long x ){
        myTankId = x;

    }


    public static LogicTank getMyLogicTank(){

        return myLogicTank;
    }


    /**
     * returns id of image to display the object
     * @return the display id
     * @param paramid tank id passed in
     */


    public int display(  long paramid  ){


        int resource = 0;

        if( paramid == id ) {

            this.deleteObservers();
            this.addObserver( myLogicTank );

            switch((int)direction){
                case 0: resource = R.drawable.tankup;
                    break;
                case 2: resource = R.drawable.tankright;
                    break;
                case 4: resource = R.drawable.tankdown;
                    break;
                case 6: resource = R.drawable.tankleft;
                    break;
            }

            TankInfoWrapper myWrapper = new TankInfoWrapper();
            myWrapper.setDirection( direction );
            myWrapper.setId( id );
            myWrapper.setLife( life );

            setChanged();
            notifyObservers( myWrapper );

            return resource;
        }else{

            switch((int)direction){
                case 0: resource = R.drawable.enemyup;
                    break;
                case 2: resource = R.drawable.enemyright;
                    break;
                case 4: resource = R.drawable.enemydown;
                    break;
                case 6: resource = R.drawable.enemyleft;
                    break;
            }


            return resource;
        }
    }


    public int updateDisplay( long paramid  ){

       return -1;

    }
}
