package com.example.lloyd.pidgeycalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class PidgeyCalculatorOld extends AppCompatActivity implements View.OnClickListener{

    int pidgeys;
    int candies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pidgey_calculator);

        //Buttons
        Button pidgeyPlus = (Button) findViewById(R.id.PidgeysPlus);
        Button pidgeyMinus = (Button) findViewById(R.id.PidgeysMinus);
        Button candyMinus = (Button) findViewById(R.id.CandyMinus);
        Button candyPlus = (Button) findViewById(R.id.CandyPlus);
        Button calculate = (Button) findViewById(R.id.GO);


        //Listeners
        pidgeyPlus.setOnClickListener(this);  //register a callback when view is clicked
        pidgeyMinus.setOnClickListener(this);
        candyPlus.setOnClickListener(this);
        candyMinus.setOnClickListener(this);
        calculate.setOnClickListener(this);

        //start done key handlers
        TextView numpidgeys = (TextView) findViewById(R.id.NumPidgeys);
        numpidgeys.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(PidgeyCalculatorOld.this);
                    return true;
                }
                return false;
            }
        });

        TextView numcandies = (TextView) findViewById(R.id.CandyToEvo);
        numcandies.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // TODO Auto-generated method stub
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideKeyboard(PidgeyCalculatorOld.this);
                    return true;
                }
                return false;
            }
        });


    }



    public void onClick (View view){
        int fieldNum;

        if(view.getId() == R.id.GO)
        {
            //pass extra vars using intent extras
            Bundle calcBundle = new Bundle();

            TextView p = (TextView) findViewById(R.id.NumPidgeys);
            pidgeys = Integer.parseInt(p.getText().toString());

            p = (TextView) findViewById(R.id.CandyToEvo);
            candies = Integer.parseInt(p.getText().toString());

            calcBundle.putIntArray("pidgeyCandy", new int[]{pidgeys, candies});
            Intent myIntent = new Intent(this, PidgeyCalculator.class);
            myIntent.putExtras(calcBundle);

            PidgeyCalculatorOld.this.startActivity(myIntent);
        }


        //START PLUS MINUS HANDLERS
        //in the future could create handler methods to clean up this code
        if(view.getId() == R.id.PidgeysPlus) {
            TextView t = (TextView) findViewById(R.id.NumPidgeys);
            checkEmptyField(t);

            fieldNum = Integer.parseInt(t.getText().toString());
            if(fieldNum >= 99999){
                fieldNum = 99998;
            }
            t.setText(String.valueOf(String.valueOf(fieldNum + 1))); //increment pidgeys here

        }
        else if(view.getId() == R.id.PidgeysMinus) {
            TextView t = (TextView) findViewById(R.id.NumPidgeys);
            checkEmptyField(t);

            fieldNum = Integer.parseInt(t.getText().toString());
            if(fieldNum <= 0){
                return;
            }
            t.setText(String.valueOf(fieldNum - 1));
        }
        else if(view.getId() == R.id.CandyMinus) {
            TextView t = (TextView) findViewById(R.id.NumPidgeys);
            checkEmptyField(t);
            fieldNum = Integer.parseInt(t.getText().toString());
            if(fieldNum <= 0){
                return;
            }
            t.setText(String.valueOf(fieldNum - 1));
        }
        //END PLUS MINUS HANDLERS

        //candy +-
        else if(view.getId() == R.id.CandyPlus) {
            TextView t = (TextView) findViewById(R.id.CandyToEvo);
            checkEmptyField(t);
            fieldNum = Integer.parseInt(t.getText().toString());
            if(fieldNum >= 99999){
                fieldNum = 99998;
            }
            t.setText(String.valueOf(fieldNum + 1)); //increment candies here
        }
    }

    boolean checkEmptyField(TextView t){
        if(t.getText().toString().isEmpty()){
            t.setText("0");
        }
        return true;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent myIntent = new Intent(this, HomeScreen.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PidgeyCalculatorOld.this.startActivity(myIntent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.EvoMenu:
                myIntent = new Intent(this, EvolutionCalc.class);
                PidgeyCalculatorOld.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PDexMenu:
                myIntent = new Intent(this, PokemonListView.class);
                PidgeyCalculatorOld.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
