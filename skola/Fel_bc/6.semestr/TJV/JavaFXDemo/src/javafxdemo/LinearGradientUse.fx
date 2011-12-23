package javafxdemo;

import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

Stage {
    title: "Linear Gradient"
    width: 400
    height: 400
    scene: Scene {
        content: Rectangle {
            x: 50
            y: 50
            width: 200
            height: 100
            fill: LinearGradient {
                startX: 0
                startY: 0
                endX: 1
                endY: 0
                stops: [
                    Stop {
                        offset: 0.0
                        color: Color.RED
                    },
                    Stop {
                        offset: 0.5
                        color: Color.GREEN
                    },
                    Stop {
                        offset: 1.0
                        color: Color.YELLOW
                    }
                ]
            }
        }
    }
}
