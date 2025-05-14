package com.example.obligatorisk;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

import static com.example.obligatorisk.TypeFigur.musepeker;

/**<p> Klasse for applikasjonen tegneprogram
 *</p>
 * @author Kaisa Lien
 */

public class TegneProgram extends Application {
    /**
     * oppretter text for informasjon om figur
     */
    public static Text txt_figur = new Text("");
    /**
     * oppretter text for informasjon om farge
     */
    public static Text txt_farge = new Text("");
    /**
     * oppretter text for informasjon om linjefarge
     */
    public static Text txt_linjefarge = new Text("");
    /**
     * oppretter text for informasjon om lengde
     */
    public static Text txt_lengde = new Text("");
    /**
     * oppretter text for informasjon om areal
     */
    public static Text txt_areal = new Text("");
    /**
     * oppretter boolean verdi for å dra objekt (musepeker)
     */
    public static boolean dra = false;
    /**
     * oppretter colorpicker for å velge en farge
     */
    public static ColorPicker farge = new ColorPicker();
    /**
     * oppretter colorpicker for å velge en linjefarge
     */
    public static ColorPicker linjeFarge = new ColorPicker();
    /**
     * oppretter en slider til å endre tekststørrelse
     */
    public static Slider slider;
    /**
     * oppretter et textfield for input av bruker
     */
    public static TextField tekst = new TextField();
    /**
     * oppretter en combobox av enum liste TypeFigur
     */
    public static ComboBox<TypeFigur> velgFigur = new ComboBox<>();
    /**
     * oppretter en tom figur for å holde på objektene tegnet
     */
    public static Shape valgtFigur = null;
    /**
     * oppretter en liste med figurer
     */
    public static ArrayList<Shape> figur = new ArrayList<>();


    /**<p> kjører programmet TegneProgram
     *</p>
     * @param args det programmet vil kjøre
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        Pane tegneOmr = new Pane();
        pane.setCenter(tegneOmr);

        // * oppretter knapper med metodekall for funksjonalitet
        Button endreFarge = new Button("Bytt farge");
        knappeStil(endreFarge);
        endreFarge.setOnAction(e -> byttFarge(farge.getValue()));

        Button endreLinjeFarge = new Button("Bytt linjefarge");
        knappeStil(endreLinjeFarge);
        endreLinjeFarge.setOnAction(e -> byttLinjeFarge(linjeFarge.getValue()));

        Button oppBtn = new Button("Flytt frem");
        knappeStil(oppBtn);
        oppBtn.setOnAction(e -> flyttFrem(tegneOmr));

        Button nedBtn = new Button("Flytt bak");
        knappeStil(nedBtn);
        nedBtn.setOnAction(e -> flyttBak(tegneOmr));

        Button fremstBtn = new Button("Flytt fremst");
        knappeStil(fremstBtn);
        fremstBtn.setOnAction(e -> flyttFremst(tegneOmr));

        Button bakerstBtn = new Button("Flytt bakerst");
        knappeStil(bakerstBtn);
        bakerstBtn.setOnAction(e -> flyttBakerst(tegneOmr));

        Button slett = new Button("Slett");
        knappeStil(slett);
        slett.setOnAction(e -> slettValgtFigur(tegneOmr));

        // * setter default verdi av fargene i colorpicker
        farge.setValue(Color.BLACK);
        linjeFarge.setValue(Color.GRAY);

        // * oppretter tomme klasseobjekter
        Rektangel rektangel = new Rektangel(0,0,0,0);
        Sirkel sirkel = new Sirkel(0,0,0);
        Linje linje = new Linje(0,0,0, 0);
        Tekst txt = new Tekst();

        // * verdier fra lister i combobox
        velgFigur.getItems().setAll(TypeFigur.values());
        velgFigur.setValue(musepeker);

        //* oppretter en slider, setter default, min og max verdi, markører og indikator på sliderlinjen
        slider = new Slider();
        slider.setMin(1);
        slider.setMax(100);
        slider.setValue(25);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        //* oppretter labels og metodekall for stil
        Label tekstStrl = new Label("Endre størrelsen på teksten");
        labelStil(tekstStrl, pane);

        Label tekstInput = new Label("Skriv ønsket tekst: ");
        labelStil(tekstInput, pane);

        Label figurFarge = new Label("Velg farge");
        labelStil(figurFarge, pane);

        Label figurValg = new Label("Velg figur, tekst eller peker");
        labelStil(figurValg, pane);

        Label figurValgtInfo = new Label("Figur: ");
        labelStil(figurValgtInfo, pane);

        Label fargeValgtInfo = new Label("Farge: ");
        labelStil(fargeValgtInfo, pane);

        Label figurLengdeInfo = new Label("Lengde: ");
        labelStil(figurLengdeInfo, pane);

        Label figurArealInfo = new Label("Areal: ");
        labelStil(figurArealInfo, pane);

        Label fargeLn = new Label("Velg linjefarge");
        labelStil(fargeLn, pane);

        Label fargeLinjeInfo = new Label("Linjefarge: ");
        labelStil(fargeLinjeInfo, pane);

        //* oppretter vbox'er. begge paneler på høyre og venstre, samt vbox innenfor disse
        VBox sliderBox = new VBox(tekstStrl, slider);
        sliderBox.setSpacing(7);

        VBox tekstBox = new VBox(tekstInput, tekst);
        tekstBox.setSpacing(7);

        VBox fargeBox = new VBox(figurFarge, farge, fargeLn, linjeFarge);
        fargeBox.setSpacing(10);

        VBox figurBox = new VBox(figurValg, velgFigur);
        figurBox.setSpacing(7);

        VBox infoPanelInnhold = new VBox(figurValgtInfo, txt_figur, fargeValgtInfo, txt_farge, fargeLinjeInfo, txt_linjefarge, figurLengdeInfo, txt_lengde, figurArealInfo, txt_areal, oppBtn, nedBtn, fremstBtn, bakerstBtn, endreFarge, endreLinjeFarge, slett);
        infoPanelInnhold .setSpacing(15);

        VBox infoPanel = new VBox(infoPanelInnhold );
        infoPanel.setSpacing(30);
        pane.setRight(infoPanel);
        infoPanel.setBackground(new Background(new BackgroundFill(Color.rgb(222, 165, 164), new CornerRadii(3), Insets.EMPTY)));
        infoPanel.setPadding((new Insets(10, 15, 15, 15)));
        infoPanel.setMaxWidth(150);
        infoPanel.setMinWidth(150);

        VBox valgPanel = new VBox(figurBox, fargeBox, tekstBox, sliderBox);
        valgPanel.setSpacing(30);
        pane.setLeft(valgPanel);
        valgPanel.setBackground(new Background(new BackgroundFill(Color.rgb(222, 165, 164), new CornerRadii(3), Insets.EMPTY)));
        valgPanel.setPadding(new Insets(10, 15, 15, 15));

        //* oppretter selve scenen programmet kjører på
        Scene scene = new Scene(pane, 1400, 800);
        primaryStage.setTitle("Tegneprogram");
        primaryStage.setScene(scene);
        primaryStage.show();

        //* henter tekst fra textfield. setOnKeyTyped slik at man ikke trenger enter
        tekst.setOnKeyTyped(e -> txt.setTekst(tekst));

        /*
          <p> endrer fargen på selektert objekt switch case som oppretter figurobjekter når bruker har valgt fra combobox. musepeker setOnMouseDragged(null); slik at den ikke endrer objekter
          </p>
         */
        tegneOmr.setOnMousePressed(e -> {
            dra = (velgFigur.getValue() == musepeker);
            switch (velgFigur.getValue()) {
                case rektangel -> rektangel.lagRektangel(e, tegneOmr, farge, linjeFarge);
                case sirkel -> sirkel.lagSirkel(e, tegneOmr, farge, linjeFarge);
                case linje -> linje.lagLinje(e, tegneOmr, farge, linjeFarge);
                case tekst -> txt.lagTekst(e, tegneOmr, farge, linjeFarge, slider);
                case musepeker -> tegneOmr.setOnMouseDragged(null);
            }

        });

    }

    /**
     * <p> metode som setter informasjon om figurene i infopanel
     * </p>
     * @param figurType hvilken type figur som blir selektert
     * @param farge hvilken farge som blir selektert
     * @param linjeFarge hvilken linjefarge som blir selektert
     * @param areal informasjon om areal av figur
     * @param lengde informasjon om lengden av figur
     */
    public static void settInfo(String figurType, String farge, String linjeFarge, double areal, double lengde) {
        txt_figur.setText(figurType);
        txt_farge.setText(farge);
        txt_linjefarge.setText(linjeFarge);
        txt_areal.setText("" + areal);
        txt_lengde.setText("" + lengde);
    }

    /**
     * <p> flytter selektert objekt en indeks bakover
     * </p>
     * @param pane hvilket panel handling skal skje på
     */
    public void flyttBak(Pane pane) {
        if (valgtFigur != null) {
            ObservableList<Node> children = pane.getChildren();
            int index = children.indexOf(valgtFigur);
            if (index > 0) {
                children.remove(valgtFigur);
                children.add(index - 1, valgtFigur);
            }
        }
    }

    /**
     * <p> flytter selektert objekt helt bak
     * </p>
     * @param pane hvilket panel handling skal skje på
     */
    public void flyttBakerst(Pane pane) {
        if (valgtFigur != null) {
            ObservableList<Node> children = pane.getChildren();
            children.remove(valgtFigur);
            children.add(0, valgtFigur);
        }
    }

    /**
     * <p> flytter selektert objekt en indeks fremover
     * </p>
     * @param pane hvilket panel handling skal skje på
     */
    public void flyttFrem(Pane pane) {
        if (valgtFigur != null) {
            ObservableList<Node> children = pane.getChildren();
            int index = children.indexOf(valgtFigur);
            if (index < children.size() - 1) {
                children.remove(valgtFigur);
                children.add(index + 1, valgtFigur);
            }
        }
    }

    /**
     * <p> flytter selektert objekt helt frem
     * </p>
     * @param pane hvilket panel handling skal skje på
     */
    public void flyttFremst(Pane pane) {
        if (valgtFigur != null) {
            ObservableList<Node> children = pane.getChildren();
            children.remove(valgtFigur);
            children.add(valgtFigur);
        }
    }

    /**
     * <p> sletter selektert objekt
     * </p>
     * @param pane hvilket panel handling skal skje på
     */
    public void slettValgtFigur(Pane pane) {
        if (valgtFigur != null) {
            pane.getChildren().remove(valgtFigur);
            valgtFigur = null;
        }
    }

    /**
     * <p> endrer fargen på selektert objekt
     * </p>
     * @param nyFarge den nye fargen du bytter til
     */
    public void byttFarge(Color nyFarge) {
        if (valgtFigur != null) {
            Shape figur = valgtFigur;
            figur.setFill(nyFarge);
        }
    }

    /**
     * <p> endrer linjefargen på selektert objekt
     * </p>
     * @param nyFarge den nye linjefargen du bytter til
     */
    public void byttLinjeFarge(Color nyFarge) {
        if (valgtFigur != null) {
            Shape figur = valgtFigur;
            figur.setStroke(nyFarge);
        }
    }

    /**
     * <p> setter en stil på alle labels
     * </p>
     * @param lb label du vil endre på
     * @param pane område den endres på
     */
    private void labelStil(Label lb, Pane pane) {
        lb.setTextFill(Color.web("3a3a3a"));
        lb.setFont(Font.font("Calibri", FontWeight.BOLD, 14));
        pane.getChildren().addAll(lb);
    }

    /**
     * <p> setter en stil på alle knapper
     * </p>
     * @param btn knapp du vil endre på
     */
    private void knappeStil(Button btn) {
        btn.setMinWidth(120);
        btn.setMinHeight(20);
        btn.setStyle("-fx-background-radius: 10;");
    }
}

