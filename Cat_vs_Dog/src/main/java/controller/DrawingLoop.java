package controller;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Bullet;
import model.Imposter;
import view.Platform;

public class DrawingLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(Platform platform) {
        this.platform = platform;
        frameRate = 60;
        interval = 1000.0f / frameRate; // 1000 ms = 1 second
        running = true;
    }

    private void chargeBar(Imposter player,Bullet bullet){
        platform.getPlayer1().setDisable(!platform.getTurn());
        platform.getPlayer2().setDisable(platform.getTurn());

        player.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for(int i = 1; i<=3; i++){
                    player.getImageView().tick_attack();
                }
                player.setCharge(true);
            }
        });
        player.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bullet.setVisible(true);
                if(platform.getTurn()){
                    bullet.moveRight(player.getPower());
                    platform.getChargeBar1().setDisable(true);
                } else {
                    bullet.moveLeft(player.getPower());
                    platform.getChargeBar2().setDisable(true);
                }
                player.getImageView().setBackStay();
                platform.setTurn();
                player.setCharge(false);
                player.setPower(0);
            }
        });
    }

    public void checkReachGameWall(Bullet bullet){
        if(bullet.getY() >= 720 || bullet.getX() >= 1280){
            bullet.respawn();
            bullet.setVisible(false);
            javafx.application.Platform.runLater(() -> {
                platform.getPlayer1().setdoubleHit(false);
                platform.getPlayer2().setdoubleHit(false);
                platform.getBullet1().setScaleX(1);
                platform.getBullet1().setScaleY(1);
                platform.getBullet2().setScaleX(-1);
                platform.getBullet2().setScaleY(1);
            });
        }
        else if(bullet.getY() >= 720 || bullet.getX() <= 0){
            bullet.respawn();
            bullet.setVisible(false);
            javafx.application.Platform.runLater(() -> {
                platform.getPlayer1().setdoubleHit(false);
                platform.getPlayer2().setdoubleHit(false);
                platform.getBullet1().setScaleX(1);
                platform.getBullet1().setScaleY(1);
                platform.getBullet2().setScaleX(-1);
                platform.getBullet2().setScaleY(1);
            });
        }
    }

    public void hit(Bullet bullet,Imposter player)  {
        if(bullet.getBoundsInParent().intersects(player.getBoundsInParent())){
            try{
                bullet.respawn();
                Thread.sleep(500);
                bullet.setVisible(false);
            }
            catch (InterruptedException e){}

            javafx.application.Platform.runLater(() -> {
                player.setHealth();
                if(platform.getTurn()){
                    platform.getPlayerBer1().setProgress(player.getHealth());
                } else {
                    platform.getPlayerBer2().setProgress(player.getHealth());
                }
                platform.getPlayer1().setdoubleHit(false);
                platform.getPlayer2().setdoubleHit(false);
                platform.getBullet1().setScaleX(1);
                platform.getBullet1().setScaleY(1);
                platform.getBullet2().setScaleX(-1);
                platform.getBullet2().setScaleY(1);
            });
        }
    }

    private void paint(Bullet bullet) {
        bullet.repaint();
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();

            chargeBar(platform.getPlayer1(),platform.getBullet1());
            chargeBar(platform.getPlayer2(),platform.getBullet2());
            paint(platform.getBullet1());
            paint(platform.getBullet2());
            checkReachGameWall(platform.getBullet1());
            checkReachGameWall(platform.getBullet2());
            hit(platform.getBullet1(), platform.getPlayer2());
            hit(platform.getBullet2(), platform.getPlayer1());

            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {

                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {

                }
            }
        }
    }
}

