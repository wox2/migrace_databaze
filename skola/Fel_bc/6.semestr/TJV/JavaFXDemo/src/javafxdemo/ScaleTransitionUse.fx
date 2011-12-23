package javafxdemo;

import javafx.animation.transition.ScaleTransition;
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
        ScaleTransition {
            node: image
            byX: 1.0
            byY: 1.0
            duration: 2s
            repeatCount: 2
            autoReverse: true
        }.play();
    }
}

Stage {
    title: "Scale Transition"
    width: 450
    height: 450
    scene: Scene {
        content: image
    }
}
