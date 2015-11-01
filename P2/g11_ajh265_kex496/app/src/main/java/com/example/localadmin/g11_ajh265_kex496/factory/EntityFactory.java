package com.example.localadmin.g11_ajh265_kex496.factory;

import android.content.Entity;

/**
 * Created by localadmin on 10/31/15.
 */
public class EntityFactory {


    CellObject myObject;

    public EntityFactory(){


    }

    public CellObject createEntity( int n ){

        if( n == 0 ){

            myObject = new EmptyCell();

        }else if( n == 1000 ){

            myObject = new IndestructableWall();

        }else if( n > 1000 && n < 2000 ){

            myObject = new DestructableWall(  n - 1000 );

        }else if( n > 2000000 && n < 3000000 ){

            myObject = new Bullet( n );

        }else if( n > 10000000 && n < 20000000){

            myObject = new Tank( n );

        }


        return myObject;

    }




}
