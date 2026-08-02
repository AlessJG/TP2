import java.time.*;
import java.util.*;

public class Calendrier {
	//format fichier calendrierXX.CSV -- où XX est le numero du mois
	//Date, heure1-duree(en min), heure2-duree, heure3-duree, ...
	//1, 12:00-60, 14:00-90, ...
	
	private int mois; //la valeur du mois courant (en nombre)
	private int jour; //la valeur de la date d'aujourd'hui (en nombre)
	private int nbJours; //le nombre de jours du mois courant
	private int nbJoursRestants; //le nombre de jours restants entre aujourd'hui et la fin du mois
	private ArrayList<Date> dates; //une liste des dates (objets Date) du mois
	
	
	/**
	 * Constructeur de la classe, initialise les variables globales
	 */
	public Calendrier() {
		this.dates = new ArrayList<Date>();
		this.jour = LocalDate.now().getDayOfMonth();
		this.mois = LocalDate.now().getMonthValue();
		this.nbJours = LocalDate.now().getMonth().length(LocalDate.now().isLeapYear());
		this.nbJoursRestants = this.nbJours - LocalDate.now().getDayOfMonth() + 1;
		
		String nomFichier = ("./CSV/calendriers/calendrier" + 
				 			this.mois + ".csv");
		
		ArrayList<String[]> calendrier = GestionFichiers.lireCSV(nomFichier);
		
		if(calendrier == null || calendrier.isEmpty()) {
			nouveauCalendrier(nomFichier);
		}
		else {
			for(int i = 0; i<this.nbJoursRestants; i++) {
				
				Date date = new Date(i + this.jour);
				String[] ligne = calendrier.get(i);
				
				for(int j = 1; j<ligne.length; j++) {
					try {
						String[] s = ligne[j ].split("-");
						LocalTime heureDebut = LocalTime.parse(s[0].trim());
						Duration duree = Duration.ofMinutes(Long.parseLong(s[1].trim()));
						date.ajouterIndispo(heureDebut, duree);;
					}
					catch(Exception e) {
						break;
					}
				}
				
				this.dates.add(date);
			}
		}
		
	}
	
	/**
	 * Fonction qui gère l'absence de fichier calendrierXX.CSV, elle crée un nouveau fichier, le 
	 * rempli des dates (objets Date) restantes au mois et rempli aussi this.dates avec ces dates   
	 * @param nomFichier, le path vers le fichier calendrierXX.CSV de ce mois
	 */
	public void nouveauCalendrier(String nomFichier) {
		String calendrierString = "Date, heure1-duree1, heure2-duree2, heure3-duree3, ...\n";
		for (int x = this.jour; x<=this.nbJours; x++) {
			calendrierString += x + ",\n";
			Date date = new Date(x);
			this.dates.add(date);
		}
		GestionFichiers.ecrire(nomFichier, calendrierString);
	}
	
	/**
	 * Fonction qui sert à obtenir la liste des dates (objets Date) disponibles de ce mois-ci
	 * @return dispo, la liste
	 */
	public ArrayList<Date> getDisponibilites() {

	    ArrayList<Date> dispo = new ArrayList<>();

	    for (Date date : this.dates) {

	        if (!date.getCreneauDispo().isEmpty()) {
	            dispo.add(date);
	        }
	    }

	    return dispo;
	}
	
	/**
	 * Fonction qui sert à afficher le calendrier de ce mois-ci avec les semaines du mois numérotées
	 * @return semaines, une liste des dates par chaque semaine
	 */
	public Date[][] getSemainesMois() {

	    Date[][] semaines = new Date[6][7];

	    int semaine = 0;
	    int colonne = getColonneDebut(this.dates.get(0));

	    for (Date d : this.dates) {

	        if (!d.getCreneauDispo().isEmpty()) {
	            semaines[semaine][colonne] = d;
	        }

	        colonne++;

	        if (colonne == 7) {
	            colonne = 0;
	            semaine++;
	        }
	    }

	    return semaines;
	}
    
    /**
     * Fonction qui sert à afficher l'entête d'une semaine. Celui-ci contient les abbréviations des
     * noms des 7 jours de la semaine formattées, ainsi que le nombre d'espace à inclure avant la 
     * date de début
     * @param start, la date (objet Date) de début, l'affichage commence à partir de celle-ci
     * @return espaces, le nombre d'espace
     */
	private int getColonneDebut(Date start) {

	    int diff = start.getJour() - this.jour;

	    DayOfWeek jourSemaine = LocalDate.now()
	            .plusDays(diff)
	            .getDayOfWeek();

	    return jourSemaine.getValue() % 7;
	}
    
    /**
     * Fonction qui sert à afficher le calendrier de la semaine choisie avec les créneaux de 
     * disponibilités de chaque journée.
     * @param semaine, la liste des dates (objets Date) de la semaine à afficher
     */
	public Date[] getSemaine(int index) {
	    return getSemainesMois()[index];
	}
    
    /**
     * Accesseur pour la liste de dates (objets Date) du calendrier
     * @return this.dates
     */
    public ArrayList<Date> getDates(){
    	return this.dates;
    }
}