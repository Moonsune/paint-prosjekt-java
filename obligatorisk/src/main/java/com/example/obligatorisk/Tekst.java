package com.example.obligatorisk;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * <p> Klasse for tekst
 * </p>
 * @author Kaisa Lien
 */


public class Tekst extends Text {
    /**
     * variabel for farge på tekst
     */
    Color fargeFyll;
    /**
     * variabel for linjefarge på tekst
     */
    Color fargeRundt;
    /**
     * variabel for tekst
     */
    String txt;

    /**<p> konstrukøren for klassen tekst
     *</p>
     */
    public Tekst() {
        super();

        setOnMousePressed(e -> {
            TegneProgram.valgtFigur = this;
            TegneProgram.settInfo("Tekst", this.getFill().toString(), this.getStroke().toString(), 0,0 );
        });

        setOnMouseDragged(e -> {
            if (TegneProgram.dra) {
                setX(e.getX());
                setY(e.getY());
            }
        });
    }

    /**
     * <p> gjør om input i textfield til text
     * </p>
     * @param tf textfield som blir omgjort
     *
     */
    protected void setTekst(TextField tf) {
        this.txt = tf.getText();
    }

    /**
     * <p> metode som oppretter en ny tekst
     * </p>
     * @param e event metoden skal skje på
     * @param tegneOmr område handling skjer på
     * @param farge fargen på objekt
     * @param linjeFarge linjefarge på objekt
     * @param slider slider for å endre tekststørrelse
     */
    protected void lagTekst(MouseEvent e, Pane tegneOmr, ColorPicker farge, ColorPicker linjeFarge, Slider slider) {
        this.fargeFyll = farge.getValue();
        this.fargeRundt = linjeFarge.getValue();

        Tekst tekst = new Tekst();
        tekst.setFont(Font.font("Areal", FontWeight.BOLD, slider.getValue()));
        tekst.setFill(fargeFyll);
        tekst.setStroke(fargeRundt);
            tekst.setText(txt);
            tekst.setX(e.getX());
            tekst.setY(e.getY());
            tekst.setFill(fargeFyll);
            tekst.setStroke(fargeRundt);
        tegneOmr.getChildren().add(tekst);
        TegneProgram.figur.add(tekst);
    }
}
