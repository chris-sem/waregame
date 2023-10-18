/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.waregame;

import static com.mycompany.waregame.Cellule.lectureImage;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author semje
 */


public class FenetreJeu extends javax.swing.JFrame {
    
    public FenetreJeu() {
        
        
        
        Image arriereplan = null ;
        
        try {
                BufferedImage bimg = ImageIO.read(new File("./fichier/ar3.png"));
                arriereplan = bimg.getScaledInstance(922, 653, BufferedImage.SCALE_SMOOTH);
            } catch (IOException ex) {
                System.out.println("Le chargement de l'image a echoue");
                arriereplan = null ;
            }  
        
        ArrierePlanJeu ar = new ArrierePlanJeu(arriereplan);
        ar.setBounds(103, 182, 922, 653);
        
        
        int x_initial = 105 ;
        int y_initial = 180 ;
        int valeur_y_initiale = y_initial;
        
        int moitie_cellule = 46 ;
        
        int nbre_cellules_horizontales = 12 ;
        int nbre_cellules_verticale = 7 ;
        
        int nombre_total_cellule = 71;
        
        Waregame.tabCellule = new Cellule[nombre_total_cellule] ;
        
        int numcellule = 0 ;
        
        for(int i = x_initial ; i < (62 + 20)*nbre_cellules_horizontales ; i = i + (62 + 20)){
            
            for(int j = y_initial ; j < (93 * nbre_cellules_verticale) ; j = j + 93  ){
                
                Waregame.tabCellule[numcellule] = new Cellule(numcellule,getTypeTerrain(numcellule), null);
                Waregame.tabCellule[numcellule].setOpaque(false);
                Waregame.tabCellule[numcellule].x_cellule_dans_fentre = i ;
                Waregame.tabCellule[numcellule].y_cellule_dans_fentre = j+48 ;
                Waregame.tabCellule[numcellule].setBounds(i,j+48, Cellule.largeur, Cellule.hauteur);
                add(Waregame.tabCellule[numcellule]);
                
                numcellule ++ ;
            }
            
            if(y_initial == valeur_y_initiale){
                y_initial = y_initial - moitie_cellule ;
                nbre_cellules_verticale = nbre_cellules_verticale + 1 ;
            }
            else{
                y_initial = valeur_y_initiale ;
                nbre_cellules_verticale = nbre_cellules_verticale - 1 ;
            }
        }
        
        add(ar);
        
        initComponents();
        
        PanelEntete.setLayout(new BorderLayout());
        PanelEntete.removeAll();
        PanelDebut jp_gc = new PanelDebut();
        PanelEntete.add(jp_gc);
        PanelEntete.revalidate();
        
    }
    
    public static void remplirToutesLesCellules(Unite tab[]){
        
        int nbrecell = 71 ;
        
        for(int i=0 ; i < nbrecell ; i++ ){
            
            remplirCellule(Waregame.tabCellule[i], tab[i]);
            
            Waregame.tabCellule[i].repaint();
        }
    }
    
    public static void remplirToutesLesCellules(){
        
        int nbrecell = 71 ;
        
        for(int i=0 ; i < nbrecell ; i++ ){
            
            remplirCellule(Waregame.tabCellule[i], null);
            
            Waregame.tabCellule[i].repaint();
        }
    }
    
    public static void remplirCellule(Cellule cell, Unite un){
            
        if(un  != null){
            
            cell.x_jauge_sante = 18 ;
            cell.y_jauge_sante = 16 ;

            cell.x_jauge_defense = 27 ;
            cell.y_jauge_defense = 22 ;

            cell.unite = un ;

            cell.img = lectureImage(un.cheminImage);

            cell.miseajourjauges();

            cell.repaint();
            
        }
        else{
            cell.unite = null ;
        }
        
    }
    
    public static Unite [] getPlacementUniteInitial(){
        
        int nbreCellule = 71 ;
        Unite [] tab = new Unite [nbreCellule] ;
        
        for(int i=0; i<nbreCellule; i++){
            
            switch(i){
                case 6 :
                    tab[6] = new Unite(1,"archer", 5, 3, 8, 7, 33, "./fichier/archer1.png");
                    break;
                case 12 :
                    tab[12] = new Unite(2,"archer", 5, 3, 8, 7, 33, "./fichier/archer2.png");
                    break;
                    
                case 19 : 
                    tab[19] = new Unite(1,"chevalier", 8, 5, 6, 5, 38, "./fichier/chevalier1.png");
                    break;
                    
                case 25 :
                    tab[25] = new Unite(2,"chevalier", 8, 5, 6, 5, 38, "./fichier/chevalier2.png");
                    break;
                    
                case 32 :
                    tab[32] = new Unite(1,"dragon", 10, 10, 7, 6, 38, "./fichier/dragon1.png");
                    break;
                    
                case 38 :
                    tab[38] = new Unite(2,"dragon", 10, 10, 7, 6, 38, "./fichier/dragon2.png");
                    break;
                    
                case 45:
                    tab[45] = new Unite(1,"guerrier", 7, 5, 5, 3, 28, "./fichier/guerrier1.png");
                    break;
                    
                case 51:
                    tab[51] = new Unite(2,"guerrier", 7, 5, 5, 3, 28, "./fichier/guerrier2.png");
                    break;
                
                case 58 :
                    tab[58] = new Unite(1,"magicien", 8, 10, 5, 5, 30, "./fichier/magicien1.png");
                    break;
                    
                case 64:
                    tab[64] = new Unite(2,"magicien", 8, 10, 5, 5, 30, "./fichier/magicien2.png");
                    break;
                default:
                    tab[i] = null ;
            }
        }
        
        return tab ;
    }
    
    public static Terrain getTypeTerrain(int id_cellule)
    {
        switch (id_cellule){
            case 0:
                return new Terrain("montagne");
            case 1:
                return new Terrain("montagne");
            case 2:
                return new Terrain("village");
            case 3:
                return new Terrain("village");
            case 4:
                return new Terrain("montagne");
            case 5:
                return new Terrain("montagne");
            case 6:
                return new Terrain("montagne");
            case 7:
                return new Terrain("montagne");
            case 8:
                return new Terrain("village");
            case 9:
                return new Terrain("village");
            case 10:
                return new Terrain("village");
            case 11:
                return new Terrain("montagne");
            case 12:
                return new Terrain("montagne");
            case 13:
                return new Terrain("montagne");
            case 14:
                return new Terrain("village");
            case 15:
                return new Terrain("village");
            case 16:
                return new Terrain("village");
            case 17:
                return new Terrain("village");
            case 18:
                return new Terrain("riviere");
            case 19:
                return new Terrain("plaine");
            case 20:
                return new Terrain("plaine");
            case 21:
                return new Terrain("village");
            case 22:
                return new Terrain("village");
            case 23:
                return new Terrain("village");
            case 24:
                return new Terrain("riviere");
            case 25:
                return new Terrain("riviere");
            case 26:
                return new Terrain("plaine");
            case 27:
                return new Terrain("plaine");
            case 28:
                return new Terrain("village");
            case 29:
                return new Terrain("village");
            case 30:
                return new Terrain("riviere");
            case 31:
                return new Terrain("plaine");
            case 32:
                return new Terrain("plaine");
            case 33:
                return new Terrain("plaine");
            case 34:
                return new Terrain("riviere");
            case 35:
                return new Terrain("riviere");
            case 36:
                return new Terrain("riviere");
            case 37:
                return new Terrain("plaine");
            case 38:
                return new Terrain("plaine");
            case 39:
                return new Terrain("plaine");
            case 40:
                return new Terrain("riviere");
            case 41:
                return new Terrain("riviere");
            case 42:
                return new Terrain("foret");
            case 43:
                return new Terrain("plaine");
            case 44:
                return new Terrain("plaine");
            case 45:
                return new Terrain("foret");
            case 46:
                return new Terrain("foret");
            case 47:
                return new Terrain("riviere");
            case 48:
                return new Terrain("foret");
            case 49:
                return new Terrain("foret");
            case 50:
                return new Terrain("foret");
            case 51:
                return new Terrain("plaine");
            case 52:
                return new Terrain("foret");
            case 53:
                return new Terrain("riviere");
            case 54:
                return new Terrain("foret");
            case 55:
                return new Terrain("foret");
            case 56:
                return new Terrain("village");
            case 57:
                return new Terrain("foret");
            case 58:
                return new Terrain("riviere");
            case 59:
                return new Terrain("riviere");
            case 60:
                return new Terrain("village");
            case 61:
                return new Terrain("village");
            case 62:
                return new Terrain("village");
            case 63:
                return new Terrain("village");
            case 64:
                return new Terrain("foret");
            case 65:
                return new Terrain("riviere");
            case 66:
                return new Terrain("village");
            case 67:
                return new Terrain("village");
            case 68:
                return new Terrain("village");
            case 69:
                return new Terrain("village");
            case 70:
                return new Terrain("village");
            default:
                return null;
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelEntete = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projet Java, par Salamata, Christopher, Franklin, Oscar");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        PanelEntete.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PanelEnteteLayout = new javax.swing.GroupLayout(PanelEntete);
        PanelEntete.setLayout(PanelEnteteLayout);
        PanelEnteteLayout.setHorizontalGroup(
            PanelEnteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1101, Short.MAX_VALUE)
        );
        PanelEnteteLayout.setVerticalGroup(
            PanelEnteteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelEntete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelEntete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        Waregame.clip.stop();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreJeu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PanelEntete;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
