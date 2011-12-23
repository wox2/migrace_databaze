package javafxdemo;

import javafx.animation.transition.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

var image: ImageView = ImageView {
    x: 50
    y: 100
    image: Image {
        url: "{__DIR__}images/java_duke_mascot.jpg"
    }
    onMouseClicked: function (e: MouseEvent) {
        TranslateTransition {
            node: image
            byX: 30
            duration: 1s
        }.play();
    }
}

Stage {
    title: "Translate Transition"
    width: 400
    height: 400
    scene: Scene {
        content: image
    }
}
