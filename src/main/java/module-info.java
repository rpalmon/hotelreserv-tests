module org.hotel.hotelreservationtest {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.kordamp.bootstrapfx.core;

    opens org.hotel.hotelreservationtest to javafx.fxml;
    exports org.hotel.hotelreservationtest;
}