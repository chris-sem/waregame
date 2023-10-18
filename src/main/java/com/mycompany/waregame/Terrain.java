/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.waregame;


/**
 *
 * @author semje
 */
public class Terrain {
 
    public String typeTerrain;
    public double bonus;
    public int pointDeplacement;

    public Terrain(String typeTerrain){
        
        this.typeTerrain = typeTerrain;

        if (typeTerrain.equals("montagne")){
            this.bonus = 0.60;
            this.pointDeplacement = 3;
        } else if (typeTerrain.equals("foret")) {
            this.bonus = 0.70;
            this.pointDeplacement = 3;
        } else if (typeTerrain.equals("village")) {
            this.bonus = 0.40;
            this.pointDeplacement = 2;
        } else if (typeTerrain.equals("plaine")) {
            this.bonus = 0.20;
            this.pointDeplacement = 1;
        } else if (typeTerrain.equals("riviere")) {
            this.bonus = 0;
            this.pointDeplacement = 1;
        }
        else {}
    }
        
}
