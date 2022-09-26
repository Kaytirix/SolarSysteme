package Exercices.Horloge;

import java.util.Calendar;
import java.util.Date;

public class Horloge extends Thread{

    //Attributs
    private boolean continuer;
    private Date Temps;
    private Date TempsMaintenant;
    private Calendar TempsInterressant;
    private boolean SecondeMinute,SecondeHeure,SecondeJour,SecondeMois,SecondeAnnee;
    private int conversion=1,seconde=1;

    //Constructeur de l'Horloge
    public Horloge(){
        continuer=true;
        Temps = new Date();
        TempsInterressant = Calendar.getInstance();
        SecondeMinute=false;
        SecondeHeure=false;
        SecondeJour=false;
        SecondeMois=false;
        SecondeAnnee=false;
    }

    //Setter permettant de gérer la durée d'execution du Thread contenant l'Horloge si besoin
    public void setContinuer(boolean continuer){this.continuer=continuer;}

    //Permet de rénitialiser l'échelle du temps afin de le redéfinir facilement
    public void RenitiliserEchelleTemps(){
        SecondeMinute=false;
        SecondeHeure=false;
        SecondeJour=false;
        SecondeMois=false;
        SecondeAnnee=false;
    }

    //Methode permettant de modifier l'horloge en passant un nombre de minutes
    //pendant un nombre de seconde passé en paramètres
    public void SecondeEnMinute(int Minute, int Seconde){
        SecondeMinute=true;
        conversion=Minute;
        seconde=Seconde;
    }

    //Methode permettant de modifier l'horloge en passant un nombre d'heures
    //pendant un nombre de seconde passé en paramètres
    public void SecondeEnHeure(int Heure, int Seconde){
        SecondeHeure=true;
        conversion=Heure;
        seconde=Seconde;
    }

    //Methode permettant de modifier l'horloge en passant un nombre de jours
    //pendant un nombre de seconde passé en paramètres
    public void SecondeEnJour(int Jour, int Seconde){
        SecondeJour=true;
        conversion=Jour;
        seconde=Seconde;
    }

    //Methode permettant de modifier l'horloge en passant un nombre de mois
    //pendant un nombre de seconde passé en paramètres
    public void SecondeEnMois(int Mois, int Seconde) {
        SecondeMois=true;
        conversion=Mois;
        seconde=Seconde;
    }

    //Methode permettant de modifier l'horloge en passant un nombre d'année
    //pendant un nombre de seconde passé en paramètres
    public void SecondeEnAnnee(int Annee, int Seconde){
        SecondeAnnee=true;
        conversion=Annee;
        seconde=Seconde;
    }

    //Getter permettant de recupérer le temps de l'horloge en miliseconde
    public long getTimeInMillis() {return TempsInterressant.getTimeInMillis();}

    //Getter permettant de recupérer l'année de l'horloge
    public int getYear() {return TempsInterressant.get(TempsInterressant.YEAR);}

    @Override
    public void run(){
        //Boucle gérer par le programme principale grace au setter de l'attribut continuer
        while(continuer) {

            //Declarations d'une nouvelle date permettant d'avoir toujours la date actuel
            TempsMaintenant = new Date();

            //Cette condition permet de savoir si 1 miliseconde est passé entre deux dates afin de modifier l'horloge
            //qui nous intéresses toutes les 1 milisecondes seulement
            if (Temps.getTime() != TempsMaintenant.getTime()) {

                //Affichage de la date qui nous intéresse
                //System.out.println(TempsInterressant.getTime());

                //Ajout de 1 miliseconde pour que les deux dates comparés soit de nouveau égales dans la condition si dessus
                Temps.setTime(Temps.getTime() + 1);

                //Vérification de la modification de l'horloge par l'utilisateur

                //Si l'utilisateur passe un certains nombre de minutes durant un certain nombre de seconde ceci est réalisé
                if (SecondeMinute) {
                    //Augmentation de 60 miliseconde toutes les milisecondes si Minutes=1 et seconde=1
                    TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 60) / seconde));
                } else {
                    //Si l'utilisateur passe un certains nombre d'heures durant un certain nombre de seconde ceci est réalisé
                    if (SecondeHeure) {
                        TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 3600) / seconde));
                    } else {
                        //Si l'utilisateur passe un certains nombre de jours durant un certain nombre de seconde ceci est réalisé
                        if (SecondeJour) {
                            TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 86400) / seconde));
                        } else {
                            //Verification de l'annee si elle est ou non bisextile
                            if (TempsInterressant.get(TempsInterressant.YEAR) % 4 == 0) {
                                //Si l'utilisateur passe un certains nombre de mois durant un certain nombre de seconde
                                //et que c'est une annee bisextile ceci est réalisé
                                if (SecondeMois) {
                                    TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 2635200) / seconde));
                                } else {
                                    //Si l'utilisateur passe un certains nombre d'année durant un certain nombre de seconde
                                    //et que c'est une annee bisextile ceci est réalisé
                                    if (SecondeAnnee) {
                                        TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 31622400) / seconde));
                                    }
                                    //Si l'utilisateur ne modifie pas la durée de l'horloge alors l'on passe 1 miliseconde a notre horloge
                                    //quelque soit l'année
                                    else {
                                        TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + 1);
                                    }
                                }
                            } else {
                                //Si l'utilisateur passe un certains nombre de mois durant un certain nombre de seconde
                                //et que ce n'est pas une annee bisextile ceci est réalisé
                                if (SecondeMois) {
                                    TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 2628000) / seconde));
                                } else {
                                    //Si l'utilisateur passe un certains nombre d'année durant un certain nombre de seconde
                                    //et que c'est une annee bisextile ceci est réalisé
                                    if (SecondeAnnee) {
                                        TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + ((conversion * 31536000) / seconde));
                                    }
                                    //Si l'utilisateur ne modifie pas la durée de l'horloge alors l'on passe 1 miliseconde a notre horloge
                                    //quelque soit l'année
                                    else {
                                        TempsInterressant.setTimeInMillis(TempsInterressant.getTimeInMillis() + 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
