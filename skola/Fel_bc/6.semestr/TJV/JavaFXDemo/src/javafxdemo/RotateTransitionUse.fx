package javafxdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.transition.RotateTransition;

var image: ImageView = ImageView {
    x: 100
    y: 100
    image: Image {
        url: "{__DIR__}images/java_duke_mascot.jpg"
    }
    onMouseClicked: function (e: MouseEvent) {
        RotateTransition {
            node: image
            byAngle: 45
            duration: 1.5s
        }.play();
    }
}

Stage {
    title: "Rotate Transition"
    width: 400
    height: 400
    scene: Scene {
        content: image
    }
}
