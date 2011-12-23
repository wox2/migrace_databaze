package javafxdemo;

import javafx.scene.effect.light.DistantLight;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

Stage {
    title: "Lighting"
    width: 400
    height: 400
    scene: Scene {
        content: [
            Circle {
                centerX: 100
                centerY: 100
                radius: 50
                fill: Color.CORAL
            }
            Circle {
                centerX: 250
                centerY: 100
                radius: 50
                fill: Color.CORAL
                effect: Lighting {
                    light: DistantLight {
                        azimuth: 45.0
                        elevation: 45.0
                    }
                }
            }
        ]
    }
}
