package controller;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Popup;
import model.Imposter;
import view.Platform;

public class Gameloop implements Runnable{
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public Gameloop(Platform platform) {
        this.platform = platform;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }

    private void charging(Imposter player, ProgressBar chargeBar) {
        if(platform.getTurn()){
            platform.getChargeBar1().setDisable(false);
        } else {
            platform.getChargeBar2().setDisable(false);
        }
        javafx.application.Platform.runLater(() -> {
            chargeBar.setProgress(player.getPower());
        });
        player.isCharge();
    }

    public void endGame() {
        if(platform.getPlayer1().getHealth() == 0){
            javafx.application.Platform.runLater(() -> {
                Label label = new Label("Imposter 2 Win!!");
                javafx.stage.Popup popup = new Popup();
                label.setStyle(" -fx-background-color: white;");
                popup.getContent().add(label);
                label.setMinWidth(15);
                label.setMinHeight(15);
                popup.show(Launcher.primaryStage);
                running=false;
            });
        } else if(platform.getPlayer2().getHealth() == 0) {
            javafx.application.Platform.runLater(() -> {
                Label label = new Label("Imposter 1 Win!!");
                javafx.stage.Popup popup = new Popup();
                label.setStyle(" -fx-background-color: white;");
                popup.getContent().add(label);
                label.setMinWidth(15);
                label.setMinHeight(15);
                popup.show(Launcher.primaryStage);
                running=false;
            });
        }
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            charging(platform.getPlayer1(),platform.getChargeBar1());
            charging(platform.getPlayer2(), platform.getChargeBar2());
            endGame();

            time = System.currentTimeMillis() - time;

            if(time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException ignore) {
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}
