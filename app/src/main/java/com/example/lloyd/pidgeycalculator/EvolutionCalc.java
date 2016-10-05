package com.example.lloyd.pidgeycalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EvolutionCalc extends AppCompatActivity implements View.OnClickListener{

    List<Pokemon> pokemonList;
    PokemonData pokemonData;
    List<String> pNames;

    /** Do calculations button */
    Button evoButton;

    /** To autocomplete pokemon name search */
    AutoCompleteTextView evolverNames;

    /** Show evloved values */
    TextView CPtext;

    /** Stores the values of evolved pokemon */
    Pokemon evolvedTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evolution_calc);
        evolverNames = (AutoCompleteTextView) findViewById(R.id.NamesAutocomplete);

        pokemonList = new ArrayList<>();
        pNames = new ArrayList<>();
        pokemonData = new PokemonData();
        CPtext = (TextView) findViewById(R.id.CurrentCP);

        /** Populate list for autocomplete */
        pokemonList = pokemonData.getPokemonList();
        Pokemon p;
        Iterator<Pokemon> itr = pokemonList.iterator();
        while(itr.hasNext()){
            p = itr.next();
            if(p.evoCPMultiplier > 1) {
                pNames.add(p.name);
            }
        }
        ArrayAdapter<String> pAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, pNames);

        evolverNames.setAdapter(pAdapter);
        evolverNames.setHint("Search Here");
        evolverNames.setThreshold(1);
        CPtext.setHint("Current CP");

        evoButton = (Button) findViewById(R.id.EvoCalcButton);
        evoButton.setOnClickListener(this);

    }

    /** Handle keyboard always showing */
    public void onClick (View view){
        if (view.getId() == R.id.EvoCalcButton){
            hideKeyboard(this);
            String name = evolverNames.getEditableText().toString();
            Iterator<Pokemon> aItr = pokemonList.iterator();
            Pokemon tempP = aItr.next();


            while(!tempP.name.toLowerCase().equals(name.toLowerCase()) && aItr.hasNext()){
                    tempP = aItr.next();
            }
            if(tempP.name.toLowerCase().equals(name.toLowerCase())){
                if(aItr.hasNext()) {
                    evolvedTo = aItr.next();
                }
            }

            if(CPtext.getText().toString().equals("") || !tempP.name.toLowerCase().equals(name.toLowerCase())){
                return;
            }

            /** Calculations based on standard deviation on a bell curve */
            double lowerBound = tempP.evoCPMultiplier - tempP.evoSD;
            double upperBound = tempP.evoCPMultiplier + tempP.evoSD;

            TextView t = (TextView) findViewById(R.id.CurrentCP);
            double cpVal = Double.parseDouble(t.getText().toString());

            t = (TextView) findViewById(R.id.EvoChanceTitle);
            t.setText("Chance of Evolution");

            int upperResult = (int) (upperBound * cpVal);
            int lowerResult = (int) (lowerBound * cpVal);
            int result = (int) (cpVal * tempP.evoCPMultiplier);

            if(checkIfMax(result)){
                return;
            }

            /** Else update textfields with new information */
            t = (TextView) findViewById(R.id.ResultCP);
            t.setText(Html.fromHtml("<b>" + Integer.toString(result) + "</b>"));

            t = (TextView) findViewById(R.id.EvoHighRange);
            t.setText(Html.fromHtml("<b>16%</b> are greater than <b>" + Integer.toString(upperResult)));


            if(lowerBound <= 1){
                t = (TextView) findViewById(R.id.EvoMidRange);
                t.setText(Html.fromHtml("<b>84%</b> fall between <b>" + Integer.toString(lowerResult) +
                        "</b> and <b>" + Integer.toString(upperResult) + "</b>"));
            }
            else{
                t = (TextView) findViewById(R.id.EvoMidRange);
                t.setText(Html.fromHtml("<b>68%</b> fall between <b>" + Integer.toString(lowerResult) +
                        "</b> and <b>" + Integer.toString(upperResult) + "</b>"));

                t = (TextView) findViewById(R.id.EvoLowRange);
                t.setText(Html.fromHtml("<b>16%</b> are less than <b>" + Integer.toString(lowerResult)));

            }
            }

        }

    /** to handle case where keyboard was showing on maximize or activity start */
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

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
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
            EvolutionCalc.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.PDexMenu:
                myIntent = new Intent(this, PokemonListView.class);
                EvolutionCalc.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PCalcMenu:
                myIntent = new Intent(this, PidgeyCalculator.class);
                EvolutionCalc.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /** Handle case pokemon estimated over max CP */
    boolean checkIfMax(int result){
        if(result > Integer.parseInt(evolvedTo.maxCP)){

            TextView t;
            result = Integer.parseInt(evolvedTo.maxCP);

            t = (TextView) findViewById(R.id.ResultCP);
            t.setText(Html.fromHtml("<b>" + Integer.toString(result) + "</b>"));

            t = (TextView) findViewById(R.id.EvoChanceTitle);
            t.setText("Estimated to evolve to Max CP");

            t = (TextView) findViewById(R.id.EvoMidRange);
            t.setText("");

            t = (TextView) findViewById(R.id.EvoHighRange);
            t.setText("");

            t = (TextView) findViewById(R.id.EvoLowRange);
            t.setText("");

            return true;
        }
        return false;
    }
}
