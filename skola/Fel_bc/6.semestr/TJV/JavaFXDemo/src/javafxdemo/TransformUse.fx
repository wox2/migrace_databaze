package javafxdemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;

Stage {
    title: "Transform Use"
    width: 400
    height: 400
    scene: Scene {
        content: ImageView {
            x: 200
            y: 100
            image: Image {
                url: "{__DIR__}images/java_duke_mascot.jpg"
            }
            transforms: [
                Rotate {
                    pivotX: 150
                    pivotY: 150
                    angle: 30
                },
                Scale {
                    x: 0.5
                    y: 1.0
                }
            ]
        }
    }
}
