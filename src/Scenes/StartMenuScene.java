package Scenes;

import com.sun.tools.javac.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

import javax.sound.sampled.*;
import java.io.*;

public class StartMenuScene extends Scene {

    private Clip clip;
    private Button startButton;
    private Button exitButton;

    public StartMenuScene(Pane startMenuPane, int MAP_LENGTH, int FRAME_LENGTH) throws Exception{

        super(startMenuPane,MAP_LENGTH,FRAME_LENGTH);

        Label firstLabel = new Label("Такого ви ще не бачили!");
        firstLabel.setFont(Font.font("Candara", FontWeight.BOLD,40));
        firstLabel.setTextFill(Paint.valueOf("ORANGE"));

        InputStream stream = new FileInputStream("C:\\Users\\lyubo\\Desktop\\JAVA - Останнє\\Game\\resources\\images\\Logo.png");
        Image image = new Image(stream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        startButton = new Button("Почати легенду");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(80);
        startButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center");
        startButton.setFont(Font.font("Candara", FontWeight.BOLD,20));
        startButton.setOnMouseEntered(mouseEvent -> startButton.setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-text-alignment: center"));
        startButton.setOnMouseExited(mouseEvent -> startButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center"));

        exitButton = new Button("Вийти");
        exitButton.setPrefWidth(200);
        exitButton.setPrefHeight(80);
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center");
        exitButton.setFont(Font.font("Candara", FontWeight.BOLD,20));
        exitButton.setOnMouseEntered(mouseEvent -> exitButton.setStyle("-fx-background-color: brown; -fx-text-fill: white; -fx-text-alignment: center"));
        exitButton.setOnMouseExited(mouseEvent -> exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-text-alignment: center"));

        VBox startMenu = new VBox(20,firstLabel,imageView,startButton,exitButton);
        startMenu.setAlignment(Pos.CENTER);
        startMenu.setPadding(new Insets(4));

        Group startGroup = new Group();
        startGroup.getChildren().add(startMenu);

        InputStream backStream = new FileInputStream("C:\\Users\\lyubo\\Desktop\\JAVA - Останнє\\Game\\resources\\images\\Back.gif");

        startMenuPane.setPrefWidth(MAP_LENGTH);
        startMenuPane.setPrefHeight(FRAME_LENGTH);
        startMenuPane.getChildren().add(new ImageView(new Image(backStream)));
        startMenuPane.getChildren().add(startGroup);

        playBackMusic();
    }

    private void playBackMusic(){
        try {
            File soundFile = new File("C:/Users/lyubo/Desktop/JAVA - Останнє/Game/resources/music/StartMenu.wav");;
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException  exc) {
            exc.printStackTrace();
        } catch (UnsupportedAudioFileException exc) {
            exc.printStackTrace();
        } catch (LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }

    public Button getStartButton(){
        return startButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Clip getClip(){
        return clip;
    }
}
