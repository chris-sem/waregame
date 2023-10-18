/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.waregame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author semje
 */
public class Sauvegarde {

    public String nom_joueur1;
    public int num_tour_joueur1;

    public String nom_joueur2;
    public int num_tour_joueur2;
    
    public String date_sauvegarde ;

    public ElementSauvegarde tab[];
    
    public Sauvegarde(String nom_joueur1,int num_tour_joueur1, String nom_joueur2, int num_tour_joueur2 ,ElementSauvegarde tab[]){
        
        this.nom_joueur1 = nom_joueur1;
        this.num_tour_joueur1 = num_tour_joueur1;
        this.nom_joueur2 = nom_joueur2;
        this.num_tour_joueur2 = num_tour_joueur2;
        
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        this.date_sauvegarde = dateFormat.format(date);
        
        this.tab = tab ;
        
    }
    
    public void afficherSauvegarde(){
        System.out.println(this.nom_joueur1);
        System.out.println(this.num_tour_joueur1);
        System.out.println(this.nom_joueur2);
        System.out.println(this.num_tour_joueur2);
        System.out.println(this.date_sauvegarde);
        
        for(int i=0; i<this.tab.length ; i++){
            System.out.println(this.tab[i].toString());
        }
    }
    
    public Sauvegarde(String nom_joueur1,int num_tour_joueur1, String nom_joueur2, int num_tour_joueur2 ,String date, ElementSauvegarde tab[]){
        
        this.nom_joueur1 = nom_joueur1;
        this.num_tour_joueur1 = num_tour_joueur1;
        this.nom_joueur2 = nom_joueur2;
        this.num_tour_joueur2 = num_tour_joueur2;
        
        this.date_sauvegarde = date ;
        
        this.tab = tab ;
        
    }

    public Sauvegarde(String nom_joueur1,int num_tour_joueur1, String nom_joueur2, int num_tour_joueur2 ,Cellule tabCellule[])
    {
        //System.err.println("dans souvegarde");
        this.nom_joueur1 = nom_joueur1;
        this.num_tour_joueur1 = num_tour_joueur1;
        this.nom_joueur2 = nom_joueur2;
        this.num_tour_joueur2 = num_tour_joueur2;
        
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        this.date_sauvegarde = dateFormat.format(date);

        this.tab = new ElementSauvegarde[10];

        int j = 0;
        for (int i = 0 ; i < 71; i++ )
        {
            if (tabCellule[i].unite != null)
            {
                tab[j] = new ElementSauvegarde(
                    tabCellule[i].id_cellule 
                    + "_" + tabCellule[i].unite.idjoueur 
                    + "_" + tabCellule[i].unite.type 
                    + "_" + tabCellule[i].unite.attaque
                    + "_" + tabCellule[i].unite.defense_initiale 
                    + "_" + tabCellule[i].unite.defense_courant 
                    + "_"+ tabCellule[i].unite.deplacement 
                    + "_" + tabCellule[i].unite.vision 
                    + "_" + tabCellule[i].unite.vie_initiale 
                    + "_"+ tabCellule[i].unite.vie_courant 
                    + "_" + tabCellule[i].unite.cheminImage);
                j++;
            }

        }

        for ( ;j < 10; j++)
        {
            tab[j] = null;
        }

    }
    
    public Sauvegarde(){
        this.nom_joueur1 = "";
        this.num_tour_joueur1 = 0 ;
        this.nom_joueur2 ="";
        this.num_tour_joueur2 = 0 ;
        
        this.date_sauvegarde ="";
        
        this.tab = new ElementSauvegarde[10];
        
        for(int i=0 ; i<10 ; i++){
            this.tab[i] = null ;
        }
        
    }

    public void SauvegardeFichier()
    {
        
        try {
            File fichier = new File("./fichier/sauvegarde.txt");

            // Vérifier si le fichier n'existe pas, alors le créer
            if (!fichier.exists()) {
                fichier.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(fichier, true); // true pour permettre l'ajout de contenu au fichier existant
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            
            bufferedWriter.write(this.nom_joueur1+ "\n");
            bufferedWriter.write(this.num_tour_joueur1 + "\n");
            bufferedWriter.write(this.nom_joueur2 + "\n");
            bufferedWriter.write(this.num_tour_joueur2 + "\n");
            bufferedWriter.write(this.date_sauvegarde+ "\n");

            // Écrire les éléments dans le fichier
            for (ElementSauvegarde element : this.tab) {
                if (element != null)
                {
                    bufferedWriter.write(element.toString() + "\n");
                }
                else
                {
                    bufferedWriter.write("null\n");
                }
            }

            // Fermeture des flux
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Éléments sauvegardés avec succès dans le fichier");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des éléments : " + e.getMessage());
        }
        
        
    }
    
    public static int getNombreSauvegarde(){
        
        int n = 0 ;
        
        try {
            BufferedReader lecteur = new BufferedReader(new FileReader( "./fichier/sauvegarde.txt") );
            String ligne;

            while ((ligne = lecteur.readLine()) != null)
            {
                n++ ;
            }

            lecteur.close();
        }catch (IOException ex)
        {
            ex.printStackTrace();
            return 0 ;
        }

            return (int)(n / 14) ;
    }
    
    public static Sauvegarde[] getToutesLesSauvegardes(){
        
        //System.out.println("Il ya : "+ getNombreSauvegarde()+" sauvegardes ");
        Sauvegarde[] tab = new Sauvegarde[getNombreSauvegarde()];
        
        try {
            BufferedReader lecteur = new BufferedReader(new FileReader( "./fichier/sauvegarde.txt") );
            String ligne;
            int i = 0 ;
            int j = 0 ;
            int k = 0 ;
            
            String nom_joueur1 ="";
            int num_tour_joueur1 = 0;

            String nom_joueur2 ="";
            int num_tour_joueur2 = 0;

            String date_sauvegarde ="" ;

            ElementSauvegarde tableau[] = null;

            while ((ligne = lecteur.readLine()) != null)
            {
                if(i == 0)
                {
                    tableau = new ElementSauvegarde[10] ;
                    nom_joueur1 = ligne ;
                    i++;
                }
                else if (i == 1)
                {
                    num_tour_joueur1 = Integer.parseInt(ligne);
                    i++;
                }
                else if (i == 2)
                {
                    nom_joueur2 = ligne;
                    i++;
                }
                else if (i == 3)
                {
                    num_tour_joueur2 = Integer.parseInt(ligne);
                    i++;
                }
                else if (i == 4)
                {
                    
                    date_sauvegarde = ligne ;
                    i++;
                }
                else if (i >= 5)
                {
                    
                    
                    if(ligne.equals("null")){
                        tableau[j] = null;
                    }
                    else{
                        tableau[j] = new ElementSauvegarde(ligne);
                    }
                    
                    j++;
                    i++ ;
                    
                    if(i == 15){
                        
                        tab[k] = new Sauvegarde(nom_joueur1, num_tour_joueur1, nom_joueur2, num_tour_joueur2, date_sauvegarde, tableau);
                        k++ ;
                        i = 0 ;
                        j = 0 ;
                    }

                }
                else{}
                
                
            }
            lecteur.close();
            
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return tab;
    }
    
    
}

