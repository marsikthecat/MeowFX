module meow.javameowfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires kotlin.stdlib;
    requires java.desktop;
    requires com.google.gson;
    requires com.github.oshi;
    requires org.bouncycastle.provider;

    opens meow.meowfx to javafx.fxml;
    exports meow.meowfx.structure;
    exports meow.meowfx to javafx.graphics;
    opens meow.meowfx.structure to javafx.fxml;
}