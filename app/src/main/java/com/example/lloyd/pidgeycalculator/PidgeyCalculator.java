package com.example.lloyd.pidgeycalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PidgeyCalculator extends AppCompatActivity {

    TextView numPids;
    TextView numCands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calc_results);

        Button calculate = (Button) findViewById(R.id.CalcPidgeys);
        numPids = (TextView) findViewById(R.id.NumPidgeys);
        numCands = (TextView) findViewById(R.id.NumCandies);

        numCands.setHint("Number of Candies");
        numPids.setHint("Number of Pidgeys");

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numCands.getText().toString().equals("") ||
                        numPids.getText().toString().equals("")){
                    return;
                }

                if(view == findViewById(R.id.CalcPidgeys)){

                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    //display calcs here

                    int pidgeys = Integer.parseInt(numPids.getText().toString());
                    int candies = Integer.parseInt(numCands.getText().toString());

                    int evolved = 0;
                    int tempcand = candies;
                    int tempPid = pidgeys;


                    while(tempPid > 0 && tempcand > 11){
                        evolved++;
                        tempPid--;
                        tempcand -= 12;
                    }

                    int xp = evolved * 500;
                    int xpWithEgg = xp * 2;
                    int timeSpent = evolved / 2;
                    int candyNeeded = 0;
                    int candyHave = 0;
                    int finalCalc = 0;
                    int pNeeded = 60 - evolved;
                    if(evolved < 60){
                        candyNeeded = pNeeded * 12; //how many candies I need for remaining pidgeys
                        candyHave = tempPid + tempcand; //how many candies I have including transfers
                        finalCalc = (pNeeded) //pidgeys needed
                                + ((candyNeeded - candyHave) / 4); //total candy needed minus what we already have
                        if(candyNeeded - candyHave < 0){
                            finalCalc = pNeeded;
                        }
                    }

                    //if candy is remaining find out how many pidgeys to catch to use up remaining
                    int leftoverCandy = 0;
                    if(tempcand > 12 && tempPid <= 0){
                        while(tempcand > 12){
                            leftoverCandy++;
                            tempcand -= 9;
                        }
                    }

                    TextView t;
                    if(leftoverCandy > 0){
                        t = (TextView) findViewById(R.id.UseCandy);
                        t.setText(Html.fromHtml("Catch <b>" + String.valueOf(leftoverCandy) +
                         "</b> more pidgeys to use your remaining candy for <b>" +
                         String.valueOf(leftoverCandy + evolved) + "</b> total evolutions in <b> " +
                         String.valueOf((leftoverCandy + evolved) / 2) + "</b> minutes"));
                    }

                    // calculation results displayed
                    t = (TextView) findViewById(R.id.EvolvedPidgeys);
                    t.setText(Html.fromHtml(String.valueOf(evolved)));

                    t = (TextView) findViewById(R.id.GainedXP);
                    t.setText(Html.fromHtml("XP Gained: <b>" + String.valueOf(xp) + "</b>"));

                    t = (TextView) findViewById(R.id.eggXP);
                    t.setText(Html.fromHtml("XP With Lucky Egg: <b>" + String.valueOf(xpWithEgg) + "</b>"));

                    t = (TextView) findViewById(R.id.TimeSpent);
                    t.setText(Html.fromHtml("Time Spent <b>" + String.valueOf(timeSpent) + " Minutes</b>"));

                    // recommendations
                    t = (TextView) findViewById(R.id.PNeeded30);
                    t.setText(Html.fromHtml("You need to catch <b>" + String.valueOf(finalCalc) + "</b> more pidgeys for 30 minutes of evolution time"));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.EvoMenu:
                myIntent = new Intent(this, EvolutionCalc.class);
                PidgeyCalculator.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PDexMenu:
                myIntent = new Intent(this, PokemonListView.class);
                PidgeyCalculator.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
