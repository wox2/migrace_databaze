package javafxdemo;

import javafx.animation.transition.AnimationPath;
import javafx.animation.transition.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.transition.OrientationType;

var path = Path {
    elements: [
        MoveTo {
            x: 50
            y: 50
        },
        QuadCurveTo {
            x: 200
            y: 200
            controlX: 140
            controlY: 70
        }
    ]
}

var rect = Rectangle {
    x: 50
    y: 50
    width: 20
    height: 10
    arcWidth: 5
    arcHeight: 5
    fill: Color.BLUE
}

var pathTransition = PathTransition {
    node: rect
    duration: 6s
    path: AnimationPath.createFromPath(path)
    orientation: OrientationType.ORTHOGONAL_TO_TANGENT
}

Stage {
    title: "Path Transition"
    width: 400
    height: 400
    scene: Scene {
        fill: Color.LIGHTGRAY
        content: [
            path,
            rect
        ]
    }
}

pathTransition.play();
