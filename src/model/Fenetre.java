/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Alexandre
 */
public class Fenetre {
    JFrame frame;
    JTabbedPane tabbedPane;
    JPanel menu_princip;
    JPanel donnee;
    JPanel quitter;
    JButton rep_questionnaire;
    JButton rep_test;
    Utilisateur utilisateur;
    Questionnaire questionnaire;
    Test test;
    
    public Fenetre(){
        frame = new JFrame();
        questionnaire = new Questionnaire();
        test = new Test();
        tabbedPane = new JTabbedPane();
        menu_princip = new JPanel();
        quitter = new JPanel();
        donnee = new JPanel();
        utilisateur = new Utilisateur();
        
        rep_questionnaire = new JButton("Répondre au questionnaire");
        rep_questionnaire.addActionListener((ActionEvent ae) -> {
            questionnaire();
        });
        rep_test = new JButton("Faire le test");
        rep_test.addActionListener((ActionEvent ae) -> {
            test();
        });
        
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("PPE");
        menu_princip.add(rep_questionnaire);
        menu_princip.add(rep_test);
        tabbedPane.addTab("Menu principal",menu_princip);
        tabbedPane.addTab("Voir les données",donnee);
        tabbedPane.addTab("Quitter",quitter);
        tabbedPane.addChangeListener((ChangeEvent ce) -> {
            if(tabbedPane.getSelectedIndex()==2)
               System.exit(0);
        });
        frame.add(tabbedPane);
        frame.pack();
    }
    
    private void questionnaire(){
        questionnaire.setVisible(true);
        questionnaire.End.addActionListener((ActionEvent ae) -> {
            utilisateur.repondre_questionnaire(questionnaire.getRepQuestionnaire());
            questionnaire.dispose();
        });
    }
    
    private void test(){
        if(utilisateur.RepQuestionnaire.isEmpty())
            return;
        int temp=Calendar.getInstance().get(Calendar.SECOND);
        while(temp!=0){
            if(temp!=Calendar.getInstance().get(Calendar.SECOND)){
              temp=Calendar.getInstance().get(Calendar.SECOND);
              System.out.println("Veuillez attendre " + String.valueOf(60-temp) + " secondes");
            }
        }
        test.setVisible(true);
        test.passerTest(utilisateur);
    }
}
