package Exercices;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static javax.swing.SwingConstants.CENTER;

public class IhmOption implements ActionListener, WindowListener {

    //Création de la fenêtre en global pour y avoir accès dans toute la classe
    private JFrame frame = null;

    //Création des boutons en global pour y avoir accès dans toute la classe
    private final JButton SecondeMoins = new JButton("-");
    private final JButton MinuteMoins = new JButton("-");
    private final JButton HeureMoins = new JButton("-");
    private final JButton JourMoins = new JButton("-");
    private final JButton MoisMoins = new JButton("-");
    private final JButton AnneeMoins = new JButton("-");

    private final JButton SecondePlus = new JButton("+");
    private final JButton MinutePlus = new JButton("+");
    private final JButton HeurePlus = new JButton("+");
    private final JButton JourPlus = new JButton("+");
    private final JButton MoisPlus = new JButton("+");
    private final JButton AnneePlus = new JButton("+");

    private final JButton AppliquerOption = new JButton("Appliquer les options");
    private final JButton Quitter = new JButton("Quitter");
    private final JButton ReinitialiserOption = new JButton("Réinitialiser les options");

    //Création des texte à afficher en global pour y avoir accès dans toute la classe
    private JLabel NbSeconde = null;
    private JLabel NbMinute = null;
    private JLabel NbHeure = null;
    private JLabel NbJour = null;
    private JLabel NbMois = null;
    private JLabel NbAnnee = null;

    private boolean OPresser;
    private boolean OptionEnregistrer = false;
    private int NombreSeconde;
    private int NombreMinute;
    private int NombreHeure;
    private int NombreJour;
    private int NombreMois;
    private int NombreAnnee;

    //Variable temporaire pour éviter l'enregistrement direct des options
    private int TempNombreSeconde;
    private int TempNombreMinute;
    private int TempNombreHeure;
    private int TempNombreJour;
    private int TempNombreMois;
    private int TempNombreAnnee;

    //Constructeur par défaut
    public IhmOption(){
        this.OPresser = false;

        this.NombreSeconde = 1;
        this.NombreMinute = 1;
        this.NombreHeure = 1;
        this.NombreJour = 1;
        this.NombreMois = 1;
        this.NombreAnnee = 1;

        this.frame = new JFrame("Option");
    }

    //Constructuer plus modulable
    public IhmOption(String TitreFen, int nombreSeconde, int nombreMinute, int nombreHeure, int nombreJour, int nombreMois, int nombreAnnee) {
        this.OPresser = false;

        if((nombreSeconde > 0) && (nombreMinute > 0) && (nombreHeure > 0) && (nombreJour > 0) && (nombreMois > 0) && (nombreAnnee > 0)){
            this.NombreSeconde = nombreSeconde;
            this.NombreMinute = nombreMinute;
            this.NombreHeure = nombreHeure;
            this.NombreJour = nombreJour;
            this.NombreMois = nombreMois;
            this.NombreAnnee = nombreAnnee;
        }else{
            this.NombreSeconde = 1;
            this.NombreMinute = 1;
            this.NombreHeure = 1;
            this.NombreJour = 1;
            this.NombreMois = 1;
            this.NombreAnnee = 1;
        }

        this.frame = new JFrame(TitreFen);
    }

    //Permet la création de la fenêtre et de son contenu
    public void Creation() {

        //Permet de redéfinir les valeurs si elles ont été modifier ou de définir les valeurs selon celle du constructeur
        ReinitialiserOption();

        //Creation des panels pour un bonne structure et organisation de l'interface
        JPanel panelPincipal = new JPanel(null);
        JPanel pannelButtonOption = new JPanel();
        JPanel panelToucheInfo = new JPanel();
        JPanel panelInfoSupp = new JPanel();
        JPanel panelEchelTemps = new JPanel();

        //Configuration de panel principale
        panelPincipal.setBackground(Color.white);
        panelPincipal.setBounds(0,0,505,700); //Définition de la taille du panel dans la fenêtre

        //Création de la partie concernant les touches
        InformationTouche(panelToucheInfo);

        //Création de la partie concernant les informations supplémentaires
        InformationSupplementaire(panelInfoSupp);

        //Création de la partie pour changer l'échelle du temps
        ChangementEchelleTemps(panelEchelTemps);

        //Création de la partie concernant les buttons d'enregistrement et sortie
        ButtonOption(pannelButtonOption);

        //Configuration de la fenêtre

        //Ajout de tout les panels à la fenêtre
        this.frame.add(panelToucheInfo);
        this.frame.add(panelInfoSupp);
        this.frame.add(panelEchelTemps);
        this.frame.add(pannelButtonOption);
        this.frame.add(panelPincipal);

        this.frame.pack();
        this.frame.setSize(505, 550);
        this.frame.setResizable(false);

        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Ferme la fenêtre
        this.frame.addWindowListener(this); //Si la fenêtre est demandé à être fermé un évènement lui est liée

        //Fin de configuration
    }

    //Permet la création du contenu concernant les touches
    private void InformationTouche(JPanel panelToucheInfo){

        panelToucheInfo.setBorder(new TitledBorder(new LineBorder(Color.black, 1, true), "Liste de touche"));
        panelToucheInfo.setBounds(10, 20, 230,210);

        //Création de deux panel comportant la fonction de la touche et son type
        JPanel FonctionTouche = new JPanel();
        JPanel TypeTouche = new JPanel();

        //Définition de l'organisation des panel
        FonctionTouche.setLayout(new BoxLayout(FonctionTouche, BoxLayout.Y_AXIS));
        TypeTouche.setLayout(new BoxLayout(TypeTouche, BoxLayout.Y_AXIS));

        //Ajout au panel attaché
        panelToucheInfo.add(FonctionTouche);
        panelToucheInfo.add(TypeTouche);

        //Création des textes
        JLabel TxtZoom = new JLabel("Zoom :");
        JLabel TxtDezoom = new JLabel("Dézoom :");
        JLabel TxtAcc = new JLabel("Accélération :");
        JLabel TxtDecc = new JLabel("Déccélération :");
        JLabel TxtSorti = new JLabel("Quitter :");

        JLabel TxtTypeZoom = new JLabel("flèche du haut");
        JLabel TxtTypeDezoom = new JLabel("flèche du bas");
        JLabel TxtTypeAcc = new JLabel("flèche de droite");
        JLabel TxtTypeDecc = new JLabel("flèche de gauche");
        JLabel TxtTypeSorti = new JLabel("touche x");
        //Fin

        //Ajout de tous les textes
        FonctionTouche.add(TxtZoom);
        FonctionTouche.add(TxtDezoom);
        FonctionTouche.add(TxtAcc);
        FonctionTouche.add(TxtDecc);
        FonctionTouche.add(TxtSorti);

        TypeTouche.add(TxtTypeZoom);
        TypeTouche.add(TxtTypeDezoom);
        TypeTouche.add(TxtTypeAcc);
        TypeTouche.add(TxtTypeDecc);
        TypeTouche.add(TxtTypeSorti);
        //Fin

    }

    //Permet la création du contenu concernant les informations supplémentaire
    private void InformationSupplementaire(JPanel panelInfoSupp){
        panelInfoSupp.setBorder(new TitledBorder(new LineBorder(Color.black, 1, true), "Information supplémentaire"));
        panelInfoSupp.setBounds(250, 20, 230,210); //Défininition de la taille du panel

        JPanel TxtInfoSupp = new JPanel();
        //Définition de l'organisation du panel
        TxtInfoSupp.setLayout(new BoxLayout(TxtInfoSupp, BoxLayout.Y_AXIS));

        //Création des textes
        JLabel TxtTitreDev = new JLabel("Développeur :");
        JLabel TxtDevUn = new JLabel("Rémi Ometz");
        JLabel TxtDevDeux = new JLabel("Sébastian Zitouni", CENTER);
        JLabel TxtVersion = new JLabel("Version de l'application : V1.5");

        //Ajout des texte au panel liée
        TxtInfoSupp.add(TxtTitreDev);
        TxtInfoSupp.add(TxtDevUn);
        TxtInfoSupp.add(TxtDevDeux);
        TxtInfoSupp.add(TxtVersion);

        panelInfoSupp.add(TxtInfoSupp);
    }

    //Permet la création du contenu concernant le changement d'échelle de temps
    private void ChangementEchelleTemps(JPanel panelEchelTemps){
        panelEchelTemps.setBorder(new TitledBorder(new LineBorder(Color.black, 1, true), "Echelle de temps"));
        panelEchelTemps.setBounds(10,240,470,210); //Défininission de la taille du panel

        JPanel ChangementEchelTemps = new JPanel();

        //Défininission de l'organisation du panel
        ChangementEchelTemps.setLayout(new GridLayout(3,3));

        //Création de tout les panel
        CreationPanelEchelleTemps(ChangementEchelTemps);

        //Création du texte d'explication de fonctionnement du changement d'échelle
        JPanel ExplicationChangementEchelTemps = new JPanel();
        JLabel TxtExplicationFonctionnement = new JLabel("Pour l'horloge, X seconde vaut Y minute ou Y heure ou Yjour...");
        ExplicationChangementEchelTemps.add(TxtExplicationFonctionnement);

        //Ajout des panel au panel liée
        panelEchelTemps.add(ChangementEchelTemps);
        panelEchelTemps.add(ExplicationChangementEchelTemps);
    }

    //Permet la création de tout les panel concernant la modification de l'échelle du temps
    private void CreationPanelEchelleTemps(JPanel ChangementEchelTemps){
        JPanel PanelSeconde = new JPanel();
        PanelSeconde(PanelSeconde); //Création du contenu du panel des secondes

        JPanel PanelMinute = new JPanel();
        PanelMinute(PanelMinute); //Création du contenu du panel des minutes

        JPanel PanelHeure = new JPanel();
        PanelHeure(PanelHeure); //Création du contenu du panel des heures

        JPanel PanelJour = new JPanel();
        PanelJour(PanelJour); //Création du contenu du panel des jours

        JPanel PanelMois = new JPanel();
        PanelMois(PanelMois); //Création du contenu du panel des mois

        JPanel PanelAnnee = new JPanel();
        PanelAnnee(PanelAnnee); //Création du contenu du panel des années

        //Ajout de tout les panel
        ChangementEchelTemps.add(PanelSeconde);
        ChangementEchelTemps.add(PanelMinute);
        ChangementEchelTemps.add(PanelHeure);
        ChangementEchelTemps.add(PanelJour);
        ChangementEchelTemps.add(PanelMois);
        ChangementEchelTemps.add(PanelAnnee);
    }

    //Permet la création du panel des secondes
    private void PanelSeconde(JPanel PanelSeconde){
        //Définition de l'organisation du panel
        PanelSeconde.setLayout(new BoxLayout(PanelSeconde,BoxLayout.Y_AXIS));

        //Création du texte informatif
        JLabel Seconde = new JLabel("Nombre de seconde : ",CENTER);

        JPanel PanelAjoutSeconde = new JPanel();

        //Création du texte comportant la valeur
        NbSeconde = new JLabel(String.valueOf(TempNombreSeconde),CENTER);

        //Création du contenu pour changer et afficher la valeur des secondes
        ChangementValeur(PanelAjoutSeconde, SecondeMoins, NbSeconde, SecondePlus);

        //Ajout au panel liée
        PanelSeconde.add(Seconde);
        PanelSeconde.add(PanelAjoutSeconde);
    }

    //Permet la création du panel des minutes
    private void PanelMinute(JPanel PanelMinute){
        //Définition de l'organisation du panel
        PanelMinute.setLayout(new BoxLayout(PanelMinute,BoxLayout.Y_AXIS));

        //Création du texte informatif
        JLabel Minute = new JLabel("Nombre de minute : ",CENTER);

        JPanel PanelAjoutMinute = new JPanel();

        //Création du texte comportant la valeur
        NbMinute = new JLabel(String.valueOf(NombreMinute),CENTER);

        //Création du contenu pour changer et afficher la valeur des minutes
        ChangementValeur(PanelAjoutMinute, MinuteMoins, NbMinute, MinutePlus);

        //Ajout au panel liée
        PanelMinute.add(Minute);
        PanelMinute.add(PanelAjoutMinute);
    }

    //Permet la création du panel des heures
    private void PanelHeure(JPanel PanelHeure){
        //Définition de l'organisation du panel
        PanelHeure.setLayout(new BoxLayout(PanelHeure,BoxLayout.Y_AXIS));

        //Création du texte informatif
        JLabel Heure = new JLabel("Nombre de heure : ",CENTER);

        JPanel PanelAjoutHeure = new JPanel();

        //Création du texte comportant la valeur
        NbHeure = new JLabel(String.valueOf(NombreHeure),CENTER);

        //Création du contenu pour changer et afficher la valeur des heures
        ChangementValeur(PanelAjoutHeure, HeureMoins, NbHeure, HeurePlus);

        //Ajout au panel liée
        PanelHeure.add(Heure);
        PanelHeure.add(PanelAjoutHeure);
    }

    //Permet la création du panel des jours
    private void PanelJour(JPanel PanelJour){
        //Définition de l'organisation du panel
        PanelJour.setLayout(new BoxLayout(PanelJour,BoxLayout.Y_AXIS));

        //Création du texte informatif
        JLabel Jour = new JLabel("Nombre de jour : ",CENTER);

        JPanel PanelAjoutJour = new JPanel();

        //Création du texte comportant la valeur
        NbJour = new JLabel(String.valueOf(NombreJour),CENTER);

        //Création du contenu pour changer et afficher la valeur des jours
        ChangementValeur(PanelAjoutJour, JourMoins, NbJour, JourPlus);

        //Ajout au panel liée
        PanelJour.add(Jour);
        PanelJour.add(PanelAjoutJour);
    }

    //Permet la création du panel des mois
    private void PanelMois(JPanel PanelMois){
        //Définition de l'organisation du panel
        PanelMois.setLayout(new BoxLayout(PanelMois,BoxLayout.Y_AXIS));

        //Création du texte informatif
        JLabel Mois = new JLabel("Nombre de mois : ",CENTER);

        JPanel PanelAjoutMois = new JPanel();

        //Création du texte comportant la valeur
        NbMois = new JLabel(String.valueOf(NombreMois),CENTER);

        //Création du contenu pour changer et afficher la valeur des mois
        ChangementValeur(PanelAjoutMois, MoisMoins, NbMois, MoisPlus);

        //Ajout au panel liée
        PanelMois.add(Mois);
        PanelMois.add(PanelAjoutMois);
    }

    //Permet la création du panel des années
    private void PanelAnnee(JPanel PanelAnnee){
        //Définition de l'organisation du panel
        PanelAnnee.setLayout(new BoxLayout(PanelAnnee,BoxLayout.Y_AXIS));

        //Création du texte informatif
        JLabel Annee = new JLabel("Nombre de année : ",CENTER);

        JPanel PanelAjoutAnnee = new JPanel();

        //Création du texte comportant la valeur
        NbAnnee = new JLabel(String.valueOf(NombreAnnee),CENTER);

        //Création du contenu pour changer et afficher la valeur des années
        ChangementValeur(PanelAjoutAnnee, AnneeMoins, NbAnnee, AnneePlus);

        //Ajout au panel liée
        PanelAnnee.add(Annee);
        PanelAnnee.add(PanelAjoutAnnee);
    }

    //Permet la création des liens entre les buttons et évènement et l'ajout des buttons et du texte au panel liée
    private void ChangementValeur(JPanel nomPanel, JButton moins, JLabel texte ,JButton plus){

        //Ajout des liens
        moins.addActionListener(this);
        plus.addActionListener(this);

        //Ajout au panel liée
        nomPanel.add(moins);
        nomPanel.add(texte);
        nomPanel.add(plus);
    }

    private void ButtonOption(JPanel pannelButtonOption){
        pannelButtonOption.setBounds(10, 460, 470,38);
        ReinitialiserOption.addActionListener(this);
        AppliquerOption.addActionListener(this);
        Quitter.addActionListener(this);

        pannelButtonOption.add(ReinitialiserOption);
        pannelButtonOption.add(AppliquerOption);
        pannelButtonOption.add(Quitter);

    }

    //Permet le lien le bouton et un évènement
    @Override
    public void actionPerformed(ActionEvent e) {
        //Si le bouton est appuyé, on ajouté +1 à la valeur
        if(e.getSource() == SecondePlus){
            TempNombreSeconde += 1;
            NbSeconde.setText(String.valueOf(TempNombreSeconde));
        }
        if(e.getSource() == MinutePlus){
            TempNombreMinute += 1;
            NbMinute.setText(String.valueOf(TempNombreMinute));
        }
        if(e.getSource() == HeurePlus){
            TempNombreHeure += 1;
            NbHeure.setText(String.valueOf(TempNombreHeure));
        }
        if(e.getSource() == JourPlus){
            TempNombreJour += 1;
            NbJour.setText(String.valueOf(TempNombreJour));
        }
        if(e.getSource() == MoisPlus){
            TempNombreMois += 1;
            NbMois.setText(String.valueOf(TempNombreMois));
        }
        if(e.getSource() == AnneePlus){
            TempNombreAnnee += 1;
            NbAnnee.setText(String.valueOf(TempNombreAnnee));
        }

        //Si le bouton est appuyé, on ajouté -1 à la valeur
        if(e.getSource() == SecondeMoins && TempNombreSeconde > 1){
            TempNombreSeconde -= 1;
            NbSeconde.setText(String.valueOf(TempNombreSeconde));
        }
        if(e.getSource() == MinuteMoins && TempNombreMinute > 1){
            TempNombreMinute -= 1;
            NbMinute.setText(String.valueOf(TempNombreMinute));
        }
        if(e.getSource() == HeureMoins && TempNombreHeure > 1){
            TempNombreHeure -= 1;
            NbHeure.setText(String.valueOf(TempNombreHeure));
        }
        if(e.getSource() == JourMoins && TempNombreJour > 1){
            TempNombreJour -= 1;
            NbJour.setText(String.valueOf(TempNombreJour));
        }
        if(e.getSource() == MoisMoins && TempNombreMois > 1){
            TempNombreMois -= 1;
            NbMois.setText(String.valueOf(TempNombreMois));
        }
        if(e.getSource() == AnneeMoins && TempNombreAnnee > 1){
            TempNombreAnnee -= 1;
            NbAnnee.setText(String.valueOf(TempNombreAnnee));
        }

        //Si le bouton est appuyé, l'enregistrement des valeurs se fait
        if(e.getSource() == AppliquerOption){
            OptionEnregistrer = true;
            Enregistrement();
        }

        //Si le bouton est appuyé, on ferme l'application
        if (e.getSource() == Quitter){
            //Si aucun enregistrement n'est fait, on le signale et demande confirmation
            if(!OptionEnregistrer){
                int reponse = JOptionPane.showConfirmDialog(this.frame,
                        "Les paramètres ne sont pas enregistrés, êtes-vous sûr de quitter ?",
                        "Attention !",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (reponse== JOptionPane.YES_OPTION){
                    ReinitialiserOption();
                    ReinitialiserAffichageOption();
                    Fermeture();
                }
            }else{
                //Sinon on ferme directement
                Fermeture();
            }
        }

        //Si la réinitialisation des valeurs d'usine est démandé, on affiche un message et demande confirmation
        if(e.getSource() == ReinitialiserOption){
            int reponse = JOptionPane.showConfirmDialog(this.frame,
                    "Les paramètres seront rénitialiser par les paramètres d'usine, êtes-vous sûr ?",
                    "Attention !",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            //Si oui on réinitialise tout
            if (reponse== JOptionPane.YES_OPTION){
                ReinitialisationOptionParDef();
                ReinitialiserAffichageOptionParDef();
                Fermeture();
            }else{
                //Sinon on fait rien
                this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }

    //Permet d'afficher la fenêtre. Cela évite une création de la page
    public void Affichage(){
        OptionEnregistrer = false; //Remise à false car lors de l'affichage aucune option est enregistré
        this.frame.setVisible(true); //On rend visible la fenêtre
    }

    //Permet l'enregistrement des données
    private void Enregistrement(){
        //Les valeurs modifier temporairement deviennent les vrais valeurs
        this.NombreSeconde = TempNombreSeconde;
        this.NombreMinute = TempNombreMinute;
        this.NombreHeure = TempNombreHeure;
        this.NombreJour = TempNombreJour;
        this.NombreMois = TempNombreMois;
        this.NombreAnnee = TempNombreAnnee;
    }

    //Permet la réinitialisation des options selon l'utilisateur
    private void ReinitialiserOption(){
        //Les valeurs temporaires prennent les vrais valeurs
        this.TempNombreSeconde = this.NombreSeconde;
        this.TempNombreMinute = this.NombreMinute;
        this.TempNombreHeure = this.NombreHeure;
        this.TempNombreJour = this.NombreJour;
        this.TempNombreMois = this.NombreMois;
        this.TempNombreAnnee = this.NombreAnnee;
    }

    //Permet la réinisialisation des options par défaut du créateur
    private void ReinitialisationOptionParDef(){
        this.NombreSeconde = 1;
        this.NombreMinute = 1;
        this.NombreHeure = 1;
        this.NombreJour = 1;
        this.NombreMois = 1;
        this.NombreAnnee = 1;
    }

    //Permet la réinitialisation des valeurs affichées dans la fenêtre selon l'utilisateur
    private void ReinitialiserAffichageOptionParDef(){
        NbAnnee.setText(String.valueOf(this.NombreSeconde));
        NbMois.setText(String.valueOf(this.NombreMinute));
        NbJour.setText(String.valueOf(this.NombreHeure));
        NbHeure.setText(String.valueOf(this.NombreJour));
        NbMinute.setText(String.valueOf(this.NombreMois));
        NbSeconde.setText(String.valueOf(this.NombreAnnee));
    }

    //Permet la réinitialisation des valeurs affichées dans la fenêtre selon l'utilisateur
    private void ReinitialiserAffichageOption(){
        NbAnnee.setText(String.valueOf(TempNombreAnnee));
        NbMois.setText(String.valueOf(TempNombreMois));
        NbJour.setText(String.valueOf(TempNombreJour));
        NbHeure.setText(String.valueOf(TempNombreHeure));
        NbMinute.setText(String.valueOf(TempNombreMinute));
        NbSeconde.setText(String.valueOf(TempNombreSeconde));
    }

    //Permet un fermeture de la fenêtre
    private void Fermeture(){
        this.frame.dispose();
        this.OPresser = false;
    }

    //Evènement liée lors de la fermeture de la fenêtre
    public void windowClosing(WindowEvent e){
        int reponse;

        //Création et affichage d'une fenêtre de mise en garde si le bouton est appuyé et fermeture de cette manière
        reponse = JOptionPane.showConfirmDialog(this.frame,
                "En quittant la fenêtre de cette manière, aucun paramètre est enregistré, êtes-vous sûr de quitter ?", "Attention !",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        //Si oui
        if (reponse == JOptionPane.YES_OPTION){
            ReinitialiserOption();
            ReinitialiserAffichageOption();
            Fermeture();
        }else{
            this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    public boolean isOPresser() {
        return this.OPresser;
    }

    public void setOPresser(boolean OPresser) {
        this.OPresser = OPresser;
    }

    public int getNombreSeconde() {
        return NombreSeconde;
    }

    public int getNombreMinute() {
        return NombreMinute;
    }

    public int getNombreHeure() {
        return NombreHeure;
    }

    public int getNombreJour() {
        return NombreJour;
    }

    public int getNombreMois() {
        return NombreMois;
    }

    public int getNombreAnnee() {
        return NombreAnnee;
    }


    //Méthode non utilisé mais doit être ajouté à cause du type de la classe WindowsListener

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}