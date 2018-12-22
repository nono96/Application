/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Alexandre
 */
public class Questionnaire extends JFrame {
    JPanel panel;
    
    JLabel Sexe; 
    JComboBox ChoixSex;
    
    JLabel Age;
    JTextField ChoixAge;
    
    JLabel Orientation;
    JComboBox ChoixOrientation;
    
    JLabel Desir;
    JCheckBox Femme;
    JCheckBox Homme;
    JCheckBox Couple_homo;
    JCheckBox Couple_hete;
    JCheckBox Amateur;
    JCheckBox Pornstar;
    JCheckBox Groupe;
    JCheckBox Jeunes;
    JCheckBox Vieux;
    JCheckBox Blond;
    JCheckBox Brun;
    JCheckBox Roux;
    
    JLabel FreqM;
    JTextField ChoixFreqM;
    JLabel FreqS;
    JTextField ChoixFreqS;
    
    JButton End;

    public Questionnaire(){
        super();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        this.setTitle("Questionnaire");
        this.add(panel);
        
        Sexe = new JLabel("Quel est votre sexe ?");
        panel.add(Sexe);
        ChoixSex = new JComboBox(new String[]{"Homme","Femme"});
        panel.add(ChoixSex);
        
        Age = new JLabel("Indiquez votre age :");
        panel.add(Age);
        ChoixAge = new JTextField();
        ChoixAge.setText("18");
        panel.add(ChoixAge);
        
        FreqM = new JLabel("Quel est votre fréquence de masturbation(s) par semaine ?");
        panel.add(FreqM);
        ChoixFreqM = new JTextField();
        ChoixFreqM.setText("0");
        panel.add(ChoixFreqM);
        
        FreqS = new JLabel("Quel est votre fréquence de rapport(s) par semaine ?");
        panel.add(FreqS);
        ChoixFreqS = new JTextField();
        ChoixFreqS.setText("0");
        panel.add(ChoixFreqS);
        
        Desir = new JLabel("Sélectionner vos désirs sexuels");
        panel.add(Desir);
        Femme = new JCheckBox("Femme");
        panel.add(Femme);
        Homme = new JCheckBox("Homme");
        panel.add(Homme);
        Couple_homo = new JCheckBox("Couple homosexuel");
        panel.add(Couple_homo);
        Couple_hete = new JCheckBox("Couple hétérosexuel");
        panel.add(Couple_hete);
        Amateur = new JCheckBox("Amateur");
        panel.add(Amateur);
        Pornstar = new JCheckBox("Pornstar");
        panel.add(Pornstar);
        Groupe = new JCheckBox("Groupe");
        panel.add(Groupe);
        Jeunes = new JCheckBox("Jeunes");
        panel.add(Jeunes);
        Vieux = new JCheckBox("Vieux");
        panel.add(Vieux);
        Blond = new JCheckBox("Blond");
        panel.add(Blond);
        Brun = new JCheckBox("Brun");
        panel.add(Brun);
        Roux = new JCheckBox("Roux");
        panel.add(Roux);
        
        
        End = new JButton("Finir le questionnaire");
        panel.add(End);
        this.pack();
    }
    
    //Stock les réponses au questionnaire
    public LinkedList getRepQuestionnaire(){
        LinkedList l = new LinkedList();
        l.add(ChoixSex.getSelectedItem());
        l.add(Integer.valueOf(ChoixAge.getText()));
        l.add(Float.valueOf(ChoixFreqM.getText()));
        l.add(Float.valueOf(ChoixFreqS.getText()));
        l.add(Femme.isSelected() ? "Femme" : "NotFemme");
        l.add(Homme.isSelected() ? "Homme" : "NotHomme");
        l.add(Couple_homo.isSelected() ? "Couple_homo" : "NotCouple_homo");
        l.add(Couple_hete.isSelected() ? "Couple_hete" : "NotCouple_hete");
        l.add(Amateur.isSelected() ? "Amateur" : "NotAmateur");
        l.add(Pornstar.isSelected() ? "Pornstar" : "NotPornstar");
        l.add(Groupe.isSelected() ? "Groupe" : "NotGroupe");
        l.add(Jeunes.isSelected() ? "Jeunes" : "NotJeunes");
        l.add(Vieux.isSelected() ? "Vieux" : "NotVieux");
        l.add(Blond.isSelected() ? "Blond" : "NotBlond");
        l.add(Brun.isSelected() ? "Brun" : "NotBrun");
        l.add(Roux.isSelected() ? "Roux" : "NotRoux");
        return l;
    }
    
    //Reset le questionnaire
    public void reset(){
        ChoixSex.setSelectedIndex(0);
        ChoixAge.setText("0");
        Femme.setSelected(false);
        Homme.setSelected(false);
        Couple_homo.setSelected(false);
        Couple_hete.setSelected(false);
        Pornstar.setSelected(false);
        Groupe.setSelected(false);
        Jeunes.setSelected(false);
        Vieux.setSelected(false);
        Blond.setSelected(false);
        Brun.setSelected(false);
        Roux.setSelected(false);
        ChoixFreqS.setText("0");
        ChoixFreqM.setText("0");
    }
}
