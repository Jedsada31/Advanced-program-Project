package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Platform;

public class Launcher extends Application {
    private static Platform platform;
    public static Stage primaryStage;
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        platform = new Platform();
        Gameloop gameloop = new Gameloop(platform);
        DrawingLoop drawingLoop = new DrawingLoop(platform);
        Scene scene = new Scene(platform,platform.WIDTH,platform.HEIGHT);
        primaryStage.setTitle("CatVsDog but CatVsDog but Among US");
        primaryStage.setScene(scene);
        primaryStage.show();
        (new Thread(gameloop)).start();
        (new Thread(drawingLoop)).start();
    }
}
