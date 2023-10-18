/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.waregame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author semje
 */




public class Cellule extends JPanel implements ActionListener{
    
    public static int hauteur = 93 ;
    public static int largeur = 99 ;
    public static int largeur_transversale = 62 ;
    public static int decallage = 18 ;
    public static int mi_hauteur = 46 ;
    
    public static int longeur_jauge_sante_max = 62 ; // correspond a 38 point de vie (max dans le jeu)
    public static int longeur_jauge_defense_max = 56 ; // correspond a 10 point de defense (max dans le jeu)
    
    public static int x[] = {0, 18, 80, 98, 80, 18};
    public static int y[] = {46, 1, 1, 46, 92, 92};
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public int id_cellule;
    public boolean touche ;
    public Terrain terrain ;
    public Image img ;
    public Unite unite;
    public Polygon polygon ;
    public Color couleurPolygon ;
    private Timer timer;
    private boolean enAnimation;
    
    //****************** attributs utilises en animation
    
    public int x_cellule_dans_fentre ;
    public int y_cellule_dans_fentre ;
    public static int nombre_de_mouvement_max = 20 ;
    public int nombre_de_mouvement_courant ;
    
    
    //////////////////////////////////////////////////// sante
    public int x_jauge_sante ;
    public int y_jauge_sante ;
    public int y_jauge_sante_courant ;
    
    public int longueur_jauge_vie ;
    public int longueur_jauge_vie_courant ;
    
    public int decalage_jauge ;
    public int decalage_jauge_courant ;
    
    //////////////////////////////////////////////////// defense
    public int x_jauge_defense ;
    public int y_jauge_defense ;
    public int y_jauge_defense_courant ;
    
    public int longueur_jauge_defense ;
    public int longueur_jauge_defense_courant ;
    
    public int decalage_jauge_defense ;
    public int decalage_jauge_courant_defense ;
    
    
    public Cellule(int id_cel, Terrain ter, Unite u){
        
        //this.setLayout(null);
        
        this.id_cellule = id_cel;
        this.terrain = ter ;
        this.couleurPolygon = Waregame.couleurDeBaseCellule ;
        this.unite = u ;
        
        this.x_cellule_dans_fentre = 0;
        this.y_cellule_dans_fentre = 0;
        this.nombre_de_mouvement_courant = 0 ;
        
        this.timer = new Timer(30, this);
        this.enAnimation = false ;
        
        if(unite != null){
            this.x_jauge_sante = 18 ;
            this.y_jauge_sante = 16 ;

            this.x_jauge_defense = 27 ;
            this.y_jauge_defense = 22 ;

            this.img = lectureImage(unite.cheminImage);

            this.miseajourjauges();
            
        }
        
        
        this.polygon = new Polygon(x, y, 6);
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(polygon.contains(e.getX(), e.getY())){
                    
                    
                    if(Waregame.tabCellule[id_cellule].unite != null){
                        
                        if(Waregame.tabCellule[id_cellule].unite.idjoueur == Waregame.id_joueur_courant){
                            
                            
                            
                            
                            touche = true ;
                            
                            if(Waregame.infosClick == null){
                                //je clique pour la premiere fois sur une de mes cellules
                                couleurPolygon = Color.GREEN ;
                                repaint();
                                Waregame.infosClick = new InfosClick(id_cellule, unite) ;
                                
                                Waregame.infosClick.deplacement_courant = Waregame.infosClick.unite_click.deplacement ;
                                
                                afficherInforUniteVersEntete();
                                miseAjourDeplacement();
                                
                            }
                            else{
                                //j'essaie d'attaquer ma propre cellule :
                                
                                Waregame.infosClick.unite_click = null ;
                                Waregame.infosClick = null ;

                                remiseAgris();
                                    
                                //On remets tout a zero, mais on ne passe pas la main
                                //passerLaMain(Waregame.id_joueur_courant);
                                
                            }
                        }
                        
                        else{
                            
                            if(Waregame.infosClick != null){
                                
                                if(Waregame.infosClick.unite_click.vision != 0){
                                    
                                    //C'est une attaque confirméeee
                                    //annimation d'attaque si possible
                                    
                                    //enAnimation = true ;
                                    
                                    timer.start();
                                    enAnimation = true ;
                                    
                                    
                                    attaqueSurCellule(Waregame.infosClick.unite_click.attaque);
                                    
                                    Waregame.infosClick.unite_click = null ;
                                    Waregame.infosClick = null ;

                                    remiseAgris();
                                    passerLaMain(Waregame.id_joueur_courant);
                                    
                                }
                            }
                        }
                        
                        
                    }
                    
                    
                    else{
                        
                        if(Waregame.infosClick != null){
                            
                            miseajourcellule(Waregame.infosClick.unite_click);
                            
                            Waregame.tabCellule[Waregame.infosClick.id_cellule].unite = null ;
                            Waregame.tabCellule[Waregame.infosClick.id_cellule].repaint();
                            
                            Waregame.infosClick.unite_click = null ;
                            Waregame.infosClick = null ;
                            
                            remiseAgris();
                            passerLaMain(Waregame.id_joueur_courant);
                        
                        }
                    }
                    
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
                if(Waregame.infosClick != null){
                    
                    if(touche == false){
                        touche = true ;
                        
                        if( (Waregame.infosClick.deplacement_courant - terrain.pointDeplacement) < 0){
                            
                            Waregame.infosClick = null ;
                        
                            remiseAgris();
                        }
                        
                        else{
                            
                            if(unite != null){
                                
                                if( unite.idjoueur != Waregame.id_joueur_courant && Waregame.infosClick.unite_click.vision > (Waregame.infosClick.unite_click.deplacement - Waregame.infosClick.deplacement_courant)){
                                    couleurPolygon = Color.RED ;
                                
                                    repaint();

                                    Waregame.infosClick.deplacement_courant = -1 ;
                                }
                                
                                else{
                                    
                                    Waregame.infosClick = null ;
                        
                                    remiseAgris();
                                    
                                }
                                
                            }
                            
                            else{
                                surbrillerHexagone();
                        
                                Waregame.infosClick.deplacement_courant = Waregame.infosClick.deplacement_courant - terrain.pointDeplacement ;

                                miseAjourDeplacement();
                            }
                            
                        }
                   
                    }
                    
                    else{
                        
                        Waregame.infosClick = null ;
                        
                        remiseAgris();
                    }
                    // la cellle n'est pas marqééé
                        

                        //mise ajour des point de deplacement

                        //si les points deviennent negatives ou on tente de passer sur un ennemi

                        //Remise a zero

                        //
                        /*Waregame.infosClick.unite_click = null ;
                                Waregame.infosClick = null ;

                                remiseAgris();
                                */

                        // si  non

                        //on marque la cellule
                    
                    //si non on remets toutes les cellules a gris
                    
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
    }
    
    
    public static Image lectureImage(String chemin){
        
        Image img ;
        try {
            BufferedImage bimg = ImageIO.read(new File(chemin));
            img = bimg.getScaledInstance(40, 70, BufferedImage.SCALE_SMOOTH);
        } catch (IOException ex) {
            System.out.println("Le chargement de l'image a echoue");
            img = null ;
        }  
        
        return img ;
        
    }
    
    public void passerLaMain(int id_joueur1){
        
        if( (Waregame.num_tour_joueur1 == 0 && Waregame.num_tour_joueur2 == 0)  || (Waregame.nombreUniteRestant(1) == 0) || (Waregame.nombreUniteRestant(2) == 0) ){
            
            FinDeJeu fj = new FinDeJeu();
            
            if(Waregame.num_tour_joueur1 == 0 && Waregame.num_tour_joueur2 == 0){
                
                if(Waregame.nombreUniteRestant(1) > Waregame.nombreUniteRestant(2)){
                    
                    fj.id_joueur_gagnant = 1 ; // 1 pour joueur 1 gagant
                    fj.indice_victoire = 1 ; // 1 pour plus d'unite que l'autre
                }
                else if(Waregame.nombreUniteRestant(1) < Waregame.nombreUniteRestant(2)){
                    fj.id_joueur_gagnant = 2 ;
                    fj.indice_victoire = 1 ;
                }
                
                else{
                    fj.id_joueur_gagnant = 0 ;
                }
            }
            
            else if(Waregame.nombreUniteRestant(1) == 0){
                
                fj.id_joueur_gagnant = 2 ;
                fj.indice_victoire = 2 ; //2 pour extermination de l'unité adverse
            }
            
            else{
                
                fj.id_joueur_gagnant = 1 ;
                fj.indice_victoire = 2 ;
            }
            
            fj.afficherResultat();
            
            fj.setVisible(true);
            fj.setLocationRelativeTo(null);
            
            Waregame.id_joueur_courant = 0 ;
            Waregame.num_tour_joueur1 = 0 ;
            Waregame.num_tour_joueur2 = 0 ;
        }
        
        else{
            
            if(id_joueur1 == 1){
           
                Waregame.id_joueur_courant = 2 ;
                PanelJeuEnCours.nom_joueur_courant.setText(Waregame.nom_joueur2);

                Waregame.num_tour_joueur1 -- ;
                PanelJeuEnCours.num_tour_j1.setText("Tour joueur 1 restant : " + Waregame.num_tour_joueur1);

            }
            else{
                Waregame.id_joueur_courant = 1 ;
                PanelJeuEnCours.nom_joueur_courant.setText(Waregame.nom_joueur1);

                Waregame.num_tour_joueur2 -- ;
                PanelJeuEnCours.num_tour_j2.setText("Tour joueur 2 restant : " + Waregame.num_tour_joueur2);
            }
            
        }
        
        if( (Waregame.num_tour_joueur1 == 0 && Waregame.num_tour_joueur2 == 0)  || (Waregame.nombreUniteRestant(1) == 0) || (Waregame.nombreUniteRestant(2) == 0) ){
            
            FinDeJeu fj = new FinDeJeu();
            
            if(Waregame.num_tour_joueur1 == 0 && Waregame.num_tour_joueur2 == 0){
                
                if(Waregame.nombreUniteRestant(1) > Waregame.nombreUniteRestant(2)){
                    
                    fj.id_joueur_gagnant = 1 ; // 1 pour joueur 1 gagant
                    fj.indice_victoire = 1 ; // 1 pour plus d'unite que l'autre
                }
                else if(Waregame.nombreUniteRestant(1) < Waregame.nombreUniteRestant(2)){
                    fj.id_joueur_gagnant = 2 ;
                    fj.indice_victoire = 1 ;
                }
                
                else{
                    fj.id_joueur_gagnant = 0 ;
                }
            }
            
            else if(Waregame.nombreUniteRestant(1) == 0){
                
                fj.id_joueur_gagnant = 2 ;
                fj.indice_victoire = 2 ; //2 pour extermination de l'unité adverse
            }
            
            else{
                
                fj.id_joueur_gagnant = 1 ;
                fj.indice_victoire = 2 ;
            }
            
            fj.afficherResultat();
            
            fj.setVisible(true);
            fj.setLocationRelativeTo(null);
            
            Waregame.id_joueur_courant = 0 ;
            Waregame.num_tour_joueur1 = 0 ;
            Waregame.num_tour_joueur2 = 0 ;
        }
    }
    
    
    public void miseajourjauges(){
        
        this.x_jauge_sante = 18 ;
        this.y_jauge_sante = 16 ;

        this.x_jauge_defense = 27 ;
        this.y_jauge_defense = 22 ;
        
        this.longueur_jauge_vie = (int)((longeur_jauge_sante_max * this.unite.vie_initiale)/38);
        this.decalage_jauge = longeur_jauge_sante_max - this.longueur_jauge_vie ;
        this.y_jauge_sante = this.y_jauge_sante + this.decalage_jauge ;

        this.longueur_jauge_defense = (int)((longeur_jauge_defense_max * this.unite.defense_initiale)/10);
        this.decalage_jauge_defense = longeur_jauge_defense_max - this.longueur_jauge_defense ;
        this.y_jauge_defense = this.y_jauge_defense + this.decalage_jauge_defense ;

        this.y_jauge_sante_courant = this.y_jauge_sante ;
        this.longueur_jauge_vie_courant = (int)((this.longueur_jauge_vie * this.unite.vie_courant)/this.unite.vie_initiale);
        this.decalage_jauge_courant = this.longueur_jauge_vie - this.longueur_jauge_vie_courant ;
        this.y_jauge_sante_courant = this.y_jauge_sante_courant + this.decalage_jauge_courant ;

        this.y_jauge_defense_courant = this.y_jauge_defense ;
        this.longueur_jauge_defense_courant = (int)((this.longueur_jauge_defense * this.unite.defense_courant)/this.unite.defense_initiale);
        this.decalage_jauge_courant_defense = this.longueur_jauge_defense - this.longueur_jauge_defense_courant ;
        this.y_jauge_defense_courant = this.y_jauge_defense_courant + this.decalage_jauge_courant_defense ;
          
    }
    
    public void miseajourcellule(Unite uniteDeMiseAjour){
        this.x_jauge_sante = 18 ;
        this.y_jauge_sante = 16 ;

        this.x_jauge_defense = 27 ;
        this.y_jauge_defense = 22 ;

        this.unite = uniteDeMiseAjour ;

        this.img = lectureImage(unite.cheminImage);

        this.miseajourjauges();

        this.repaint();
    }
    
    public void afficherInforUniteVersEntete(){
        
        if(this.unite != null){
            PanelJeuEnCours.nom_unite.setText(this.unite.type.toUpperCase());
            
            if(this.unite.type.equals("magicien")){
                PanelJeuEnCours.affinite_unite.setText("| Affinité : montagne - plaine - village - foret");
            }
            
            else if(this.unite.type.equals("chevalier")){
                PanelJeuEnCours.affinite_unite.setText("| Affinité : plaine - village");
            }
            
            else if(this.unite.type.equals("dragon")){
                PanelJeuEnCours.affinite_unite.setText("| Affinité : montagne - plaine");
            }
            
            else if(this.unite.type.equals("guerrier")){
                PanelJeuEnCours.affinite_unite.setText("| Affinité : plaine - village");
            }
            
            else if(this.unite.type.equals("archer")){
                PanelJeuEnCours.affinite_unite.setText("| Affinité : montagne - foret");
            }
            else{}
            
            PanelJeuEnCours.attaque_defense_unite.setText("| Attaque : " + this.unite.attaque + ", Defénse : " + this.unite.defense_initiale + ", Déplacement : " + this.unite.deplacement + ", Vision : " + this.unite.vision);
        }
 
    }
    
    public void miseAjourDeplacement(){
        
        if(Waregame.infosClick != null){
            
            PanelJeuEnCours.deplacement_courant.setText( ""+Waregame.infosClick.deplacement_courant ) ;
        }
        
        
    }
    
    public static int relatifAleatoireMoitieIntervallePotentielDefense(double reel){
        
        long nombre = Math.round(reel);
        Random random = new Random();
        return (int) (random.nextInt((int) ((2 * nombre) + 1)) - nombre);
        
    }
    
    public void attaqueSurCellule(int puissance){
        
        long potentielDefense;
        if (unite.type.equals("magicien")){
            if (terrain.typeTerrain.equals("montagne")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("village")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("plaine")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("foret")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else {
                potentielDefense = this.unite.defense_courant;
            }
        }
        else if (unite.type.equals("chevalier")){

            if (terrain.typeTerrain.equals("village")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("plaine")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else {
                potentielDefense = this.unite.defense_courant;
            }
        }
        else if (this.unite.type.equals("dragon")){
            if (terrain.typeTerrain.equals("montagne")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("plaine")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else {
                potentielDefense = this.unite.defense_courant;
            }
        }
        else if (unite.type.equals("guerrier")){
            if (terrain.typeTerrain.equals("village")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("plaine")) {
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else {
                potentielDefense = this.unite.defense_courant;
            }
        }
        else if (unite.type.equals("archer")){
            if (terrain.typeTerrain.equals("montagne")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else if (terrain.typeTerrain.equals("foret")){
                potentielDefense = Math.round(this.unite.defense_courant + this.unite.defense_courant * terrain.bonus);
            }
            else {
                potentielDefense = this.unite.defense_courant;
            }

        }
        else {
            potentielDefense = this.unite.defense_courant;
        }


        potentielDefense = potentielDefense + relatifAleatoireMoitieIntervallePotentielDefense(potentielDefense * 0.5);
        

        if (potentielDefense < puissance) {
            int ecart = puissance - (int) (potentielDefense);

            if (this.unite.vie_courant <= ecart){
                this.unite.vie_courant = 0;
            }
            else {
                this.unite.vie_courant = this.unite.vie_courant - ecart;
                this.unite.defense_courant = this.unite.defense_initiale;
            }
        }
        else {
            this.unite.defense_courant = (int) (potentielDefense) - puissance;
        }
        this.miseajourjauges();
        if (unite.vie_courant == 0) {
            this.unite = null;
        }
        this.repaint();
        
        /*this.unite.defense_courant = this.unite.defense_courant - puissance ;
        
        this.miseajourjauges();
        this.repaint();*/
    }
    
    
    public void surbrillerHexagone(){
        
        this.couleurPolygon = Color.CYAN ;
        this.repaint();
        
    }
    
    public void remiseAgris(){
        
        for(Cellule c : Waregame.tabCellule){
            c.couleurPolygon = Waregame.couleurDeBaseCellule ;
            c.touche = false ;
            c.repaint();
        }
    }

    
    @Override
    public void paint(Graphics g2d){
        
        super.paint(g2d);

        Graphics2D g = (Graphics2D)g2d ;

        g.setColor(this.couleurPolygon);

        g.drawPolygon(this.polygon);

        if(this.unite != null){
            g.drawImage(img,38, 8, null);

            if(unite.idjoueur == 1){
                g.setColor(new Color(255, 215, 150)); //couleur jauge joueur 1
            }
            else{
                g.setColor(new Color(135, 206, 250)); // //couleur jauge joueur 2
            }

            g.drawRect(this.x_jauge_sante, this.y_jauge_sante, 5, this.longueur_jauge_vie);
            g.fillRect(this.x_jauge_sante, this.y_jauge_sante_courant, 5, this.longueur_jauge_vie_courant);

            if(unite.idjoueur == 1){
                g.setColor(Color.PINK); //couleur jauge joueur 1
            }
            else{
                g.setColor(Color.PINK); // //couleur jauge joueur 2
            }

            g.drawRect(this.x_jauge_defense, this.y_jauge_defense, 3, this.longueur_jauge_defense);
            g.fillRect(this.x_jauge_defense, this.y_jauge_defense_courant, 3, this.longueur_jauge_defense_courant);

            
            g.setColor(new Color(104, 190, 165)); //couleur jauge joueur 1
            
            if (terrain.typeTerrain.equals("montagne")) {
                if (unite.type.equals("magicien")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("dragon")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("archer")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else {}
            }
            else if (terrain.typeTerrain.equals("village")) {
                if (unite.type.equals("magicien")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("chevalier")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("guerrier")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else {}
            }
            else if (terrain.typeTerrain.equals("plaine")) {
                if (unite.type.equals("magicien")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("dragon")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("chevalier")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("guerrier")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else {}
            }
            else if (terrain.typeTerrain.equals("foret")) {
                if (unite.type.equals("magicien")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else if (unite.type.equals("archer")) {
                    g.fillOval(3, 41, 12, 12);
                }
                else {}
            }

        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(enAnimation){
            
            //System.out.println(x_cellule_dans_fentre + "" + y_cellule_dans_fentre);
            
            int x = Waregame.genererNombrealeatoire(x_cellule_dans_fentre -5, x_cellule_dans_fentre + 5);
            int y = Waregame.genererNombrealeatoire(y_cellule_dans_fentre -5, y_cellule_dans_fentre + 5);
            
            setBounds(x, y, largeur, hauteur);
            
            couleurPolygon = Color.RED ;
            repaint();
            
            this.nombre_de_mouvement_courant = this.nombre_de_mouvement_courant + 1 ;
            
            if(this.nombre_de_mouvement_courant == this.nombre_de_mouvement_max){
                enAnimation = false ;
                this.nombre_de_mouvement_courant = 0 ;
                setBounds(x_cellule_dans_fentre, y_cellule_dans_fentre, largeur, hauteur);
                
                couleurPolygon = Waregame.couleurDeBaseCellule ;
                repaint();
                timer.stop();
            }
        }
        
        
    }
    
}
