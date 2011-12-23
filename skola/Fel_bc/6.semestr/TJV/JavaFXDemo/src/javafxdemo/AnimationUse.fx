package javafxdemo;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

var x = 50;

Stage {
    title: "Animation"
    scene: Scene {
        width: 400
        height: 300
        content: [
            Circle {
                centerX: bind x
                centerY: 100
                radius: 30
                fill: Color.RED
            }
        ]
    }
}

var timeLineX = Timeline {
    repeatCount: 2
    autoReverse: true
    keyFrames: [
//        KeyFrame {
//            time: 0s
//            values: [ x => 50 ]
//        },
        at (0s) { x => 50 },
//        KeyFrame {
//            time: 5s
//            values: [ x => 350 tween Interpolator.EASEBOTH ]
//        }
        at (5s) { x => 350 tween Interpolator.LINEAR }
    ]
}

timeLineX.play();
