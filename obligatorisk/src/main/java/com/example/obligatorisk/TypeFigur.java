package com.example.obligatorisk;
/**
 * <p> liste for objekter lagt til i combobox
 * </p>
 * @author Kaisa Lien
 */
public enum TypeFigur {
    /**<p> figuren rektangel i liste
     *</p>
     */
    rektangel("Rektangel"),
    /**<p> figuren sirkel i liste
     *</p>
     */
    sirkel("Sirkel"),
    /**<p> figuren linje i liste
     *</p>
     */
    linje("Linje"),
    /**<p> figuren tekst i liste
     *</p>
     */
    tekst("Tekst"),
    /**<p> musepeker i liste
     *</p>
     */
    musepeker("Musepeker");

    private final String figurListe;

    TypeFigur(String list) {
        this.figurListe = list;
    }
    public String toString() {
        return figurListe;
    }
}
