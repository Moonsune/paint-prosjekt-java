package com.example.obligatorisk;

import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * <p> Klasse for sirkel
 * </p>
 * @author Kaisa Lien
 */


public class Sirkel extends Circle {

    /**
     * variabel for farge på sirkel
     */
    Color fargeFyll;
    /**
     * variabel for linjefarge på sirkel
     */
    Color fargeRundt;

    /**<p> konstrukøren for klassen sirkel
     *</p>
     * @param x posisjon x for sirkel
     * @param y posisjon y for sirkel
     * @param radius radius for sirkel
     */
    public Sirkel(double x, double y, double radius) {
        super(x, y, radius);

        setOnMousePressed(e -> {
            TegneProgram.valgtFigur = this;
            TegneProgram.settInfo("Sirkel", this.getFill().toString(), this.getStroke().toString(), 0,0 );
        });

        setOnMouseDragged(e -> {
            if (TegneProgram.dra) {
                setCenterX(e.getX());
                setCenterY(e.getY());
            }
        });
    }

    /**
     * <p> metode som oppretter en ny sirkel
     * </p>
     * @param e event metoden skal skje på
     * @param tegneOmr område handling skjer på
     * @param farge fargen på objekt
     * @param linjeFarge linjefarge på objekt
     *
     */
    protected void lagSirkel(MouseEvent e, Pane tegneOmr, ColorPicker farge, ColorPicker linjeFarge) {
        this.fargeFyll = farge.getValue();
        this.fargeRundt = linjeFarge.getValue();

        Sirkel sirkel = new Sirkel(0, 0 ,0);
        sirkel.setStrokeWidth(3);
        sirkel.setCenterX(e.getX());
        sirkel.setCenterY(e.getY());
        sirkel.setFill(fargeFyll);
        sirkel.setStroke(fargeRundt);
        tegneOmr.setOnMouseDragged(event -> sirkel.setRadius((event.getX() + event.getY()) - (e.getX() + e.getY())));
        tegneOmr.getChildren().add(sirkel);
        TegneProgram.figur.add(sirkel);
    }
}
