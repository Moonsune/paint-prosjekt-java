/**<p> pakke for prosjektet obligatorisk
 *</p>
 */
module com.example.obligatorisk {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.obligatorisk to javafx.fxml;
    exports com.example.obligatorisk;
}