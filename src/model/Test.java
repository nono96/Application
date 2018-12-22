/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Alexandre
 */
public class Test extends JFrame{
    
    JPanel fenetre;
    JLabel image;
    Timer t;
    int cpt;
    String che;
    LinkedList list_img;
    
    public Test(){
        super();
        list_img = new LinkedList();
        this.setVisible(false);
        fenetre = new JPanel(new BorderLayout());
        image = new JLabel();
        image.setSize(new Dimension(2160,1440));
        fenetre.add(image);
        cpt=1;
        this.setMinimumSize(new Dimension(2160,1440));
        che ="";
        add(fenetre);
        pack();
    }
    
    //Passer le test
    public void passerTest(Utilisateur u){
        LinkedList rep = u.RepQuestionnaire;
        LinkedList select = new LinkedList();
        //Ajoute les choix de l'utilisateur à une liste
        for(int i=4;i<rep.size();i++)
            if(!rep.get(i).toString().startsWith("Not"))
                select.add(rep.get(i));
            
        t = new javax.swing.Timer(10000, (final ActionEvent e) -> {
            String ch = GetRandomImage(select);
            u.img_test.add(ch);
            changer_image(ch,u);
        });
        
        //Lance le test
        String c = GetRandomImage(select);
        changer_image(c,u);
        u.img_test.add(c);
        t.start();
    }
    
    //Renvoie le chemin d'une image aléatoire selon la liste
    public String GetRandomImage(LinkedList select){
        String temp = che;
        Random r = new Random();
        for(int i=0;i<select.size();i++)
            System.out.println(select.get(i));
        while(che.equals(temp)){ //S'assure que 2 images qui se suivenr diffèrent
            int size = select.size();
            System.out.println("Taille de la sélection :" + size);
            int r1 = r.nextInt(size);
            System.out.println("Sélection n : " + r1);
            String type_rand = String.valueOf(select.get(r1)); //Type (jeune,blond,etc..)
            System.out.println("Nom de la sélection : " + type_rand);
            File d = new File("Image"+File.separator+type_rand+File.separator);
            int t = d.listFiles().length;
            System.out.println("Nb_intensite " + t);
            int intensite_rand = 1+r.nextInt(t);
            System.out.println("Intensité : " + intensite_rand);
            che = "Image"+File.separator + type_rand + File.separator +intensite_rand + File.separator; 
        }
        //Accède au dossier
        File dossier = new File(che);
        if(!dossier.exists()){
            System.out.println("Dossier null"); 
            return null;
        }
        //Choix d'une image aléatoire danss le dossier
        int n = r.nextInt(dossier.listFiles().length);
        return (dossier.listFiles()[n].getPath());
    }
    
    //Changer d'image
    public void changer_image(String chemin,Utilisateur u){
        System.out.println("Chemin:" + chemin);
        cpt++;
        if(chemin==null)
            return;
        list_img.add(chemin);
        try {
            BufferedImage not_scaled = ImageIO.read(new File(chemin));
            BufferedImage rescaled = new BufferedImage(2160,1440,BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = rescaled.createGraphics();

            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(not_scaled, 0, 0, 2160,1440, null);
            g2.dispose();
            image.setIcon(new ImageIcon(rescaled));
        } catch (IOException ex) {
            System.out.println("Probleme changement");
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        revalidate();
        repaint();
        pack();
        
        if(cpt==10){
            //S'arrete après 5 images
            t.stop();
            this.setVisible(false);
            cpt=1;
            u.sauvegarder_donnee(this);
        }
    }
}
