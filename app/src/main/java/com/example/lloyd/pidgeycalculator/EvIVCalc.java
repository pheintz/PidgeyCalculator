package com.example.lloyd.pidgeycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//todo EVIV Calc

public class EvIVCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ev_ivcalc);

        /*
            BaseStamina = 2 * Hp
            BaseAttack = 2 * ROUND(Atk0.5 SpA0.5 + Spe0.5)
            BaseDefense = 2 * ROUND(Def0.5 SpD0.5 + Spe0.5)

            where HP, Atk, Def, SpA, SpD, Spe are all the base values in Gen 6. Take

            TotalCpMultiplier = CpMultiplier + AdditionalCpMultiplier
            TotalCpMultiplier is approximately 0.095 * Sqrt(PokemonLevel), where PokemonLevel increases by 1 every power up.

            Note: See this post to see how much (TotalCpMultiplier)2 increases every power up.

            After level 30 (or PokemonLevel = 30 * 2, since two power ups per level), each power up is about half as effective.

            Then take
            
            Stamina = (BaseStamina + IndividualStamina) * TotalCPMultiplier
            Attack = (BaseAttack + IndividualAttack) * TotalCpMultiplier
            Defense = (BaseDefense + IndividualDefense) * TotalCpMultiplier
            (no rounding). The IVs range from 0 to 15. Finally,
            CP = MAX(10, FLOOR(Stamina0.5 * Attack * Def0.5 / 10))
         */
    }
}
