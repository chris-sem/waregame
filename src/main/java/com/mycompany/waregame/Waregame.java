/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.waregame;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.sound.sampled.*;

/**
 *
 * @author semje
 */
public class Waregame {

    public static Cellule tabCellule[] ;
    public static InfosClick infosClick ;
    public static Color couleurDeBaseCellule ;
    
    public static int ptsDplacement_montage ;
    public static int ptsDplacement_foret ;
    public static int ptsDplacement_village ;
    public static int ptsDplacement_plaine ;
    public static int ptsDplacement_riviere ;
    
    public static int nombreMaxTourAjouer;
    public static int id_joueur_courant ;
    
    public static String nom_joueur1;
    public static int num_tour_joueur1;
    
    public static String nom_joueur2;
    public static int num_tour_joueur2;
    
    public static Sauvegarde[] tableauDesauvegarde ;
    public static Sauvegarde partieSauvegarderAJoueur ;
    
    public static FenetreJeu fj ;
    public static Clip clip;
    
    public static Clip clipClick;
    
    public static Clip clipAttaque;
    //public static PanelJeuEnCours p_jeu_encours ;
    
    
    
    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        
        infosClick = null ;
        nombreMaxTourAjouer = 50 ;
        num_tour_joueur1 = nombreMaxTourAjouer ;
        num_tour_joueur2 = nombreMaxTourAjouer ;
        id_joueur_courant = 1 ;
        couleurDeBaseCellule = new Color(192, 192,192);
       
        tableauDesauvegarde = null ;
        
        ptsDplacement_montage = 3 ;
        ptsDplacement_foret = 3;
        ptsDplacement_village = 2;
        ptsDplacement_plaine = 1;
        ptsDplacement_riviere= 1 ;
        
        File file = new File("./fichier/warAudio.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        clip.start();
        
            
        fj = new FenetreJeu();
        fj.setVisible(true);
        fj.setLocationRelativeTo(null);
      

    }
    

        
    
    
    public static int nombreUniteRestant(int id){
        
        int n = 0 ;
        
        for(int i=0 ; i < 71 ; i++){
            
            if( (tabCellule[i].unite != null ) && ( tabCellule[i].unite.idjoueur == id)){
                n ++ ;
            }
        }
        
        return n ;
    }
    
    public static int genererNombrealeatoire(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
