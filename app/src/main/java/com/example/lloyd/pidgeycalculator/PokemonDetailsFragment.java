package com.example.lloyd.pidgeycalculator;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class that stores pokedex data as a fragment for access by
 * a screenslider pager adapter
 */

public class PokemonDetailsFragment extends Fragment {

    /** Stores data for swipe right */
    Pokemon previousPokemon;

    /** Store data for current page */
    Pokemon foundPokemon;

    /** Datea for swipe left */
    Pokemon nextPokemon;

    /** Holds all available pokemon data */
    PokemonData pokeDex;

    /** Stats of current pokemon */
    MoveStats moveStats;

    List<AttackMove> pokemonAttacks;
    List<Pokemon> pokemonList;

    TextView pname;
    TextView type;
    TextView weight;
    TextView maxCP;
    TextView candyToEvolve;
    TextView tempTextView;

    String name;
    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static PokemonDetailsFragment create(int pageNumber) {
        PokemonDetailsFragment fragment = new PokemonDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /** Mod 151 so that the pages can 'loop' and appear to be infinite */
        mPageNumber = getArguments().getInt(ARG_PAGE) % 151;

        name = getActivity().getIntent().getExtras().getString("pokemonName");
        pokemonList = new ArrayList<>();
        pokemonAttacks = new ArrayList<>();
        pokeDex = new PokemonData();
        moveStats = new MoveStats();

        this.pokemonList = pokeDex.getPokemonList();
        Iterator<Pokemon> itr = this.pokemonList.iterator();
        foundPokemon = itr.next();

        int i = 0;
        while (i != mPageNumber) {
            foundPokemon = itr.next();
            i++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.activity_pokemon_details_fragment, container, false);

        setPokemonAttacks(rootView);

        return rootView;
    }

    void setPokemonAttacks(View v) {
        /**
         *  Get info about attacks:
         *  myAttacks [0-1] are primary attacks
         *  myAttacks[2-4] are special attacks
         */

        AttackMove tempAttack;
        pokemonAttacks = moveStats.getMoveList();
        Iterator<AttackMove> aItr = pokemonAttacks.iterator();
        tempAttack = aItr.next();
        while (aItr.hasNext()) {
            if (tempAttack.moveName.equals(foundPokemon.mainAttack1)) {

                tempTextView = (TextView) v.findViewById(R.id.AttackName1);
                tempTextView.setText(foundPokemon.mainAttack1);

                tempTextView = (TextView) v.findViewById(R.id.AttackType1);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) v.findViewById(R.id.DPS1);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) v.findViewById(R.id.Damage1);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            } else if (tempAttack.moveName.equals(foundPokemon.mainAttack2)) {

                tempTextView = (TextView) v.findViewById(R.id.AttackName2);
                tempTextView.setText(foundPokemon.mainAttack2);

                tempTextView = (TextView) v.findViewById(R.id.AttackType2);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) v.findViewById(R.id.DPS2);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) v.findViewById(R.id.Damage2);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            } else if (tempAttack.moveName.equals(foundPokemon.subAttack1)) {
                tempTextView = (TextView) v.findViewById(R.id.SpecialName1);
                tempTextView.setText(foundPokemon.subAttack1);

                tempTextView = (TextView) v.findViewById(R.id.SpecialType1);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) v.findViewById(R.id.SpecialDPS1);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) v.findViewById(R.id.SpecialDamage1);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            } else if (tempAttack.moveName.equals(foundPokemon.subAttack2)) {

                tempTextView = (TextView) v.findViewById(R.id.SpecialName2);
                tempTextView.setText(foundPokemon.subAttack2);

                tempTextView = (TextView) v.findViewById(R.id.SpecialType2);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) v.findViewById(R.id.SpecialDPS2);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) v.findViewById(R.id.SpecialDamage2);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            } else if (tempAttack.moveName.equals(foundPokemon.subAttack3)) {
                tempTextView = (TextView) v.findViewById(R.id.SpecialName3);
                tempTextView.setText(foundPokemon.subAttack3);

                tempTextView = (TextView) v.findViewById(R.id.SpecialType3);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) v.findViewById(R.id.SpecialDPS3);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) v.findViewById(R.id.SpecialDamage3);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            }
            tempAttack = aItr.next();
        }

        /** set text fields for pokemon info */
        pname = (TextView) v.findViewById(R.id.PokemonName);
        pname.setText(foundPokemon.name);

        type = (TextView) v.findViewById(R.id.PokemonType);
        type.setText("Type(s): " + foundPokemon.type);
        if (!foundPokemon.subtype.equals("")) {
            type.setText(type.getText() + " / " + foundPokemon.subtype);
        }

        weight = (TextView) v.findViewById(R.id.PokemonWeight);
        weight.setText("Weight: " + foundPokemon.weight);

        maxCP = (TextView) v.findViewById(R.id.PokemonMaxCP);
        maxCP.setText("Max CP: " + foundPokemon.maxCP);

        candyToEvolve = (TextView) v.findViewById(R.id.PokemonEvoCost);
        candyToEvolve.setText("Evo Cost: " + foundPokemon.candyToEvolve);
    }

    public int getPageNumber() {
        return mPageNumber;
    }
}


