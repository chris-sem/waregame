/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.waregame;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLabel;

/**
 *
 * @author semje
 */
public class ArrierePlanJeu extends JLabel {
    
    private Image backgroundImage;

    public ArrierePlanJeu(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dessine l'image en arrière-plan
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
