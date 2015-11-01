package com.example.localadmin.g11_ajh265_kex496;

import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.localadmin.g11_ajh265_kex496.factory.BattleField;
import com.example.localadmin.g11_ajh265_kex496.factory.CellObject;
import com.example.localadmin.g11_ajh265_kex496.factory.EntityFactory;
import com.example.localadmin.g11_ajh265_kex496.rest.BulletZoneRestClient;
import com.example.localadmin.g11_ajh265_kex496.util.GridWrapper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;

@EActivity
public class TankClientActivity extends AppCompatActivity {

    private static final String TAG = "TankClientActivity";

    @RestService
    BulletZoneRestClient restClient;

    private long tankId = -1;
    private GridWrapper grid;
    private boolean gameRunning = true;
    private BattleField myField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_client);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @AfterViews
    protected void afterViewInjection() {

        joinAsync();
        SystemClock.sleep(500);
        getField();
        SystemClock.sleep(500);
        GridLayout myGridLayout = (GridLayout)findViewById(R.id.gridLayout);

        myField = new BattleField( grid );
        myField.CreateField(getApplicationContext(), myGridLayout, getSize(), tankId);

        SystemClock.sleep(1000);


        int fieldSize =   (int)   ((double) ( getSize() )/ 16);
        float buttonHeight = (getDisplayHeight() - fieldSize ) / 6;



        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics());
        RelativeLayout myLinearLayout = (RelativeLayout)findViewById(R.id.RelativeLayout);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(dip, dip);
        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams1.addRule(RelativeLayout.ALIGN_LEFT);
        layoutParams1.bottomMargin = (int) buttonHeight;


        ImageButton left = new ImageButton(getApplicationContext());
        left.setTag(2);
        left.setOnClickListener(ClickListener);
        left.setLayoutParams(layoutParams1);
        left.setImageResource(R.drawable.left);
        left.setScaleType(ImageView.ScaleType.FIT_CENTER);



        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dip, dip);
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams2.leftMargin = getSize() - dip;
        layoutParams2.bottomMargin = (int) buttonHeight;

        ImageButton right = new ImageButton(getApplicationContext());
        right.setTag(1);
        right.setOnClickListener(ClickListener);
        right.setLayoutParams(layoutParams2);
        right.setImageResource(R.drawable.right);
        right.setScaleType(ImageView.ScaleType.FIT_CENTER);



        float buttonHeight2 = (float) ((getDisplayHeight() - fieldSize ) / 3.3);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(dip, dip);
        layoutParams3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams3.leftMargin = getSize()/2 - dip/2;
        layoutParams3.bottomMargin = (int) buttonHeight2;

        ImageButton up = new ImageButton(getApplicationContext());
        up.setTag(3);
        up.setOnClickListener(ClickListener);
        up.setLayoutParams(layoutParams3);
        up.setImageResource(R.drawable.up);
        up.setScaleType(ImageView.ScaleType.FIT_CENTER);


        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(dip, dip);
        layoutParams4.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams4.leftMargin = getSize()/2 - dip/2;
        layoutParams4.bottomMargin = 10;

        ImageButton down = new ImageButton(getApplicationContext());
        down.setTag(4);
        down.setOnClickListener(ClickListener);
        down.setLayoutParams(layoutParams4);
        down.setImageResource(R.drawable.down);
        down.setScaleType(ImageView.ScaleType.FIT_CENTER);



        myLinearLayout.addView(down);
        myLinearLayout.addView(up);
        myLinearLayout.addView(left);
        myLinearLayout.addView(right);




        startPoll();



    }


    private View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int selected_item = (Integer) v.getTag();

                move( selected_item );

          //  SystemClock.sleep(500);
        }
    };


    public int getSize(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public int getDisplayHeight(){

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    @Background
    void move( int dir ) {
        try {

            int i = 0;

            if( dir == 3 ){
                i = 0;
            }
            if( dir == 2 ) {
               i = 6;
            }
            if( dir == 1 ){
                i = 2;
            }
            if( dir == 4 ){
                i = 4;
            }


            byte b = (byte )i;
            restClient.move(tankId, b);

        }catch (Exception e) {


        }

    }


    @Background
    void joinAsync() {
        try {
            tankId = restClient.join().getResult();
            Log.d(TAG, "tankId is " + tankId);
        } catch (Exception e) {

            Log.d(TAG, "FAILED");
        }
    }


    @Background
    void getField() {
        try {
            grid = restClient.grid();

        } catch (Exception e) {

        }
    }


    @Background
    void startPoll() {

        while(gameRunning){

            getField();
            SystemClock.sleep(100);
            updateDisplay();

         }
    }


    @Background
    public void updateDisplay(){


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                GridLayout myGridLayout = (GridLayout) findViewById(R.id.gridLayout);
                myField.updateDisplay(tankId, grid);

            }
        });



    }

}