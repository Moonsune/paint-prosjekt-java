package com.example.obligatorisk;

import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * <p> Klasse for linje
 * </p>
 * @author Kaisa Lien
 */


public class Linje extends Line {

    /**
     * variabel for farge på linje
     */
    Color fargeFyll;
    /**
     * variabel for linjefarge på linje
     */
    Color fargeRundt;

    /**<p> konstrukøren for klassen linje
     *</p>
     *@param startX posisjon for start x i linje
     *@param startY posisjon for start y i linje
     *@param sluttX posisjon for slutt x i linje
     *@param sluttY posisjon for slutt y i linje
     */
    public Linje(double startX, double startY, double sluttX, double sluttY) {
        super(startX, startY, sluttX, sluttY);

        setOnMousePressed(e -> {
            TegneProgram.valgtFigur = this;
            if (TegneProgram.dra) {
                double a = (getEndY() - getStartY()) * (getEndY() - getStartY());
                double b = (getEndX() - getStartX()) * (getEndX() - getStartX());
                double lengde = Math.sqrt(a + b);
            }
            TegneProgram.settInfo("Linje", this.getFill().toString(), this.getStroke().toString(), 0, Math.sqrt(Math.pow(getEndX() - getStartX(), 2) + Math.pow(getEndY() - getStartY(), 2)));
        });

        setOnMouseDragged(e -> {
            if (TegneProgram.dra) {
                double bredde = getEndX() - getStartX();
                double høyde = getEndY() - getStartY();
                setStartX(e.getX() - bredde / 2);
                setStartY(e.getY() - høyde / 2);
                setEndX(getStartX() + bredde);
                setEndY(getStartY() + høyde);
            }
        });
    }

    /**
     * <p> metode som oppretter en ny linje
     * </p>
     * @param e event metoden skal skje på
     * @param tegneOmr område handling skjer på
     * @param farge fargen på objekt
     * @param linjeFarge linjefarge på objekt
     *
     */
    protected void lagLinje(MouseEvent e, Pane tegneOmr, ColorPicker farge, ColorPicker linjeFarge) {
        this.fargeFyll = farge.getValue();
        this.fargeRundt = linjeFarge.getValue();

        Linje linje = new Linje(e.getX(), e.getY(),e.getX(),e.getY());
        linje.setStartX(e.getX());
        linje.setEndX(e.getY());
        linje.setFill(fargeFyll);
        linje.setStroke(fargeRundt);
        linje.setStrokeWidth(5);
        tegneOmr.setOnMouseDragged(event -> {
            linje.setEndX(event.getX());
            linje.setEndY(event.getY());
        } );
        tegneOmr.getChildren().add(linje);
        TegneProgram.figur.add(linje);
    }
}
