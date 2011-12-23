package javafxdemo;

import javafx.animation.transition.FadeTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

var image: ImageView = ImageView {
    x: 100
    y: 100
    image: Image {
        url: "{__DIR__}images/java_duke_mascot.jpg"
    }
    onMouseClicked: function (e: MouseEvent) {
        FadeTransition {
            fromValue: 1.0
            toValue: 0.2
            node: image
            duration: 2s
            autoReverse: true
            repeatCount: 2
        }.play();
    }
}

Stage {
    title: "Fade Transition"
    width: 400
    height: 400
    scene: Scene {
        content: image
    }
}
