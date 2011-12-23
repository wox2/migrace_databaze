package javafxdemo;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

var mediaPlayer = MediaPlayer {
    autoPlay: false
    media: Media {
        source: "file:/C:/Users/woxie/Desktop/JavaFXDemo/big.flv"
    }
}

Stage {
    title: "Video"
    width: 600
    height: 400
    scene: Scene {
        content: MediaView {
            mediaPlayer: mediaPlayer
            clip: Circle {
                centerX: 200
                centerY: 200
                radius: 200
            }
        }
    }
}

mediaPlayer.play();
