package Scenes;

import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.util.*;

import javax.sound.sampled.*;
import java.io.*;


public class WinScene extends Scene {

    private Clip clip;
    private VBox winMenu;
    private Label winLabel;
    private String winImageUrl = "resources\\images\\Win.gif";
    private LevelScene levelScene;
    private Button repeatButton;
    private Button backButton;
    private Timeline timeline;

    public WinScene(GridPane pane, int FRAME_WIDTH, int FRAME_HEIGHT, LevelScene levelScene) throws IOException {

        super(pane, FRAME_WIDTH, FRAME_HEIGHT);
        this.levelScene = levelScene;

        winLabel = new Label("Перемога за Вами!");
        winLabel.setFont(Font.font("Candara", FontWeight.BOLD, 60));
        winLabel.setTextFill(Paint.valueOf("WHITE"));

        InputStream stream = new FileInputStream(winImageUrl);
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(250);

        repeatButton = new Button("Повторити");
        repeatButton.setPrefWidth(250);
        repeatButton.setPrefHeight(90);
        repeatButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center");
        repeatButton.setFont(Font.font("Candara", FontWeight.BOLD, 25));
        repeatButton.setOnMouseEntered(mouseEvent -> repeatButton.setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-text-alignment: center"));
        repeatButton.setOnMouseExited(mouseEvent -> repeatButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center"));
        repeatButton.setOnMouseClicked(mouseEvent -> {
            getClip().stop();
            levelScene.getLevelButton().runLevel();
        });

        backButton = new Button("До головного меню");
        backButton.setPrefWidth(250);
        backButton.setPrefHeight(90);
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center");
        backButton.setFont(Font.font("Candara", FontWeight.BOLD, 20));
        backButton.setOnMouseEntered(mouseEvent -> backButton.setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-text-alignment: center"));
        backButton.setOnMouseExited(mouseEvent -> backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center"));
        backButton.setOnMouseClicked(mouseEvent -> {
            getClip().stop();
            StartMenuScene.getStage().setScene(StartMenuScene.getLoadingScene());
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                StartMenuScene.getStage().setScene(StartMenuScene.getStartMenuScene());
                StartMenuScene.getStartMenuScene().getClip().setMicrosecondPosition(0);
                StartMenuScene.getStartMenuScene().getClip().start();
            }));
            timeline.play();
        });

        winMenu = new VBox(winLabel,imageView,repeatButton,backButton);
        winMenu.setAlignment(Pos.CENTER);
        winMenu.setSpacing(15);

        InputStream backStream = new FileInputStream("resources\\images\\Back.gif");
        // these strings sets up size of background appropriate to size of window
        ImageView background = new ImageView(new Image(backStream));
        background.setFitWidth(FRAME_WIDTH);
        background.setFitHeight(FRAME_HEIGHT);

        GridPane winGroup = new GridPane();
        winGroup.getChildren().add(winMenu);
        winGroup.setAlignment(Pos.CENTER);

        pane.setPrefWidth(FRAME_WIDTH);
        pane.setPrefHeight(FRAME_HEIGHT);
        pane.getChildren().add(background);
        pane.getChildren().add(winGroup);
//        pane.setBackground(new Background(new BackgroundFill
//                (Paint.valueOf("DARKCYAN"), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setAlignment(Pos.CENTER);

        playBackMusic();
    }

    private void playBackMusic() {
        try {
            File soundFile = new File("resources/music/Win.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (UnsupportedAudioFileException exc) {
            exc.printStackTrace();
        } catch (LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }

    private Clip getClip(){
        return clip;
    }
}
