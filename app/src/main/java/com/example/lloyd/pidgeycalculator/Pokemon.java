package com.example.lloyd.pidgeycalculator;

/**
 * Created by lloyd on 7/26/2016.
 */
public class Pokemon{
    String name;
    String type;
    String subtype;
    String weight;
    String height;
    String maxCP;

    String mainAttack1;
    String mainAttack2;

    String subAttack1;
    String subAttack2;
    String subAttack3;

    double evoCPMultiplier;
    double evoSD;

    String candyToEvolve;
    String imagePath;

    Pokemon(String n, String t, String sub, String maxC, String w, String m1, String m2,
            String s1, String s2, String s3, String evo, double evoMultiplier, double evoSD){
        name = n;  //pokemon name
        type = t;  //pokemon type
        subtype = sub;
        weight = w;
        maxCP = maxC;
        mainAttack1 = m1;
        mainAttack2 = m2;
        subAttack1 = s1;
        subAttack2 = s2;
        subAttack3 = s3;
        candyToEvolve = evo;
        evoCPMultiplier = evoMultiplier;
        this.evoSD = evoSD;
    }
}