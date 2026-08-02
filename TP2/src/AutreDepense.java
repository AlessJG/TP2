import java.time.LocalDate;

// Classe représentant les autres dépenses de l'auto-école
public class AutreDepense {

    private int idDepense;
    private LocalDate date;
    private Categorie categorie;
    private String description;
    private double montant;

    // Catégories :
    // P = Publicité
    // B = Bureau
    // T = Téléphone
    // I = Internet
    // A = Autre
    public enum Categorie {
        P,
        B,
        T,
        I,
        A
    }

    public AutreDepense(int idDepense,
                         LocalDate date,
                         Categorie categorie,
                         String description,
                         double montant) {

        this.idDepense = idDepense;
        this.date = date;
        this.categorie = categorie;
        this.description = description;
        this.montant = montant;
    }

    // Getters

    public int getIdDepense() {
        return idDepense;
    }

    public LocalDate getDate() {
        return date;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public double getMontant() {
        return montant;
    }

    // Setters

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
