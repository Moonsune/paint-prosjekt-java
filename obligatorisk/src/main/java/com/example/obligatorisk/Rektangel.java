package com.example.obligatorisk;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * <p> Klasse for rektangel
 * </p>
 * @author Kaisa Lien
 */


public class Rektangel extends Rectangle {
    /**
     * variabel for farge på rektangel
     */
    Color fargeFyll;
    /**
     * variabel for linjefarge på rektangel
     */
    Color fargeRundt;

    /**<p> konstrukøren for klassen rektangel
     *</p>
     * @param startX posisjon for start x i rektangel
     * @param startY posisjon for start y i rektangel
     * @param sluttX posisjon for slutt x i rektangel
     * @param sluttY posisjon for slutt y i rektangel
     */
    public Rektangel(double startX, double startY, double sluttX, double sluttY){
        super(startX, startY, sluttX, sluttY);

        setOnMousePressed(e -> {
            TegneProgram.valgtFigur = this;
            TegneProgram.settInfo("Rektangel", this.getFill().toString(), this.getStroke().toString(), (getWidth() * getHeight()),0 );
        });

        setOnMouseDragged(e -> {
            if (TegneProgram.dra) {
                setX(e.getX());
                setY(e.getY());
            }
        });
    }

    /**
     * <p> metode som oppretter en ny rektangel
     * </p>
     * @param e event metoden skal skje på
     * @param tegneOmr område handling skjer på
     * @param farge fargen på objekt
     * @param linjeFarge linjefarge på objekt
     *
     */
    protected void lagRektangel(MouseEvent e, Pane tegneOmr, ColorPicker farge, ColorPicker linjeFarge) {
        this.fargeFyll = farge.getValue();
        this.fargeRundt = linjeFarge.getValue();

        Rektangel rektangel = new Rektangel(0,0,0,0);
        rektangel.setX(e.getX());
        rektangel.setY(e.getY());
        rektangel.setFill(fargeFyll);
        rektangel.setStroke(fargeRundt);
        rektangel.setStrokeWidth(3);
        tegneOmr.setOnMouseDragged(event -> {
            rektangel.setWidth(event.getX() - e.getX());
            rektangel.setHeight(event.getY() - e.getY());
        });
        tegneOmr.getChildren().add(rektangel);
        TegneProgram.figur.add(rektangel);
    }

}

