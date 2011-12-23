package javafxdemo;

import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

Stage {
    title: "Radial Gradient"
    width: 400
    height: 400
    scene: Scene {
        content: Rectangle {
            x: 50
            y: 50
            width: 200
            height: 100
            fill: RadialGradient {
                centerX: 0.5
                centerY: 0.5
                radius: 1
                stops: [
                    Stop {
                        offset: 0.0
                        color: Color.YELLOW
                    },
                    Stop {
                        offset: 1.0
                        color: Color.AQUA
                    }
                ]
            }
        }
    }
}
