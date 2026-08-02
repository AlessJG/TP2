import java.io.File;
import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class VueRapport {

    private Stage stage;
    private Controleur controleur;
    private VuePrincipale vueP;


    public VueRapport(Stage stage, Controleur controleur){

        this.stage = stage;
        this.controleur = controleur;

    }


    public void setVueP(VuePrincipale vueP){
        this.vueP = vueP;
    }



    public void menuRapports(){

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);


        DatePicker debut = new DatePicker();

        DatePicker fin = new DatePicker();



        Button generer = new Button("Générer les rapports");


        generer.setOnAction(e -> {


            DirectoryChooser chooser =
                    new DirectoryChooser();


            File dossier =
                    chooser.showDialog(stage);


            if(dossier != null){

                controleur.genererRapports(
                        debut.getValue(),
                        fin.getValue(),
                        dossier.getAbsolutePath()
                );

            }


        });



        Button retour = new Button("Retour");


        retour.setOnAction(e -> {
            vueP.menuPrincipal();
        });



        root.getChildren().addAll(
                debut,
                fin,
                generer,
                retour
        );


        stage.setScene(new Scene(root,400,300));
        stage.setTitle("Rapports");
        stage.show();

    }

}
