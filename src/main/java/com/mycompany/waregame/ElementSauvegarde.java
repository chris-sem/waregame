/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.waregame;

/**
 *
 * @author semje
 */
public class ElementSauvegarde {
    
    public int id_cellule;
    public Unite unite;

    public ElementSauvegarde(String infoElementSauvegarde)
    {
        int idjoueur;
        String type;
        int attaque;
        int defense_initiale;
        int defense_courant;
        int deplacement;
        int vision;
        int vie_initiale;
        int vie_courant;
        String cheminImage;

        String tabElement[] = infoElementSauvegarde.split("_");
        this.id_cellule = Integer.parseInt(tabElement[0]);
        idjoueur = Integer.parseInt(tabElement[1]);
        type = tabElement[2];
        attaque = Integer.parseInt(tabElement[3]);
        defense_initiale = Integer.parseInt(tabElement[4]);
        defense_courant = Integer.parseInt(tabElement[5]);
        deplacement = Integer.parseInt(tabElement[6]);
        vision = Integer.parseInt(tabElement[7]);
        vie_initiale = Integer.parseInt(tabElement[8]);
        vie_courant = Integer.parseInt(tabElement[9]);
        cheminImage = tabElement[10];

        this.unite = new Unite(idjoueur,type,attaque, defense_initiale, defense_courant, deplacement, vision, vie_initiale, vie_courant,cheminImage );
    }
    
    

    @Override
    public String toString() {
            return id_cellule + "_" + unite.idjoueur + "_" + unite.type + "_" + unite.attaque
                    + "_" + unite.defense_initiale + "_" + unite.defense_courant + "_"
                    + unite.deplacement + "_" + unite.vision + "_" + unite.vie_initiale + "_"
                    + unite.vie_courant + "_" + unite.cheminImage;
    }
    
}
