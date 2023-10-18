/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.waregame;

/**
 *
 * @author semje
 */
public class Unite {
    
    public int idjoueur;
    public String type;
    public int attaque;
    public int defense_initiale;
    public int defense_courant;
    public int deplacement;
    public int vision;
    public int vie_initiale;
    public int vie_courant;
    
    public String cheminImage;
    //public String affinite;
    
    public Unite(int idjoueur, String type, int attaque, int defense_initiale, int deplacement, int vision, int vie_initiale, String cheminImage){
        this.idjoueur = idjoueur;
        this.type = type ;
        this.attaque = attaque;
        this.defense_initiale = defense_initiale;
        this.defense_courant = defense_initiale;
        this.deplacement = deplacement;
        this.vision = vision;
        this.vie_initiale = vie_initiale;
        this.vie_courant = vie_initiale;
        this.cheminImage = cheminImage;
    }
    
    public Unite(int idjoueur, String type, int attaque, int defense_initiale,int defense_courant, int deplacement, int vision, int vie_initiale,int vie_courant, String cheminImage){
        this.idjoueur = idjoueur;
        this.type = type ;
        this.attaque = attaque;
        this.defense_initiale = defense_initiale;
        this.defense_courant = defense_courant;
        this.deplacement = deplacement;
        this.vision = vision;
        this.vie_initiale = vie_initiale;
        this.vie_courant = vie_courant;
        this.cheminImage = cheminImage;
    }
}
