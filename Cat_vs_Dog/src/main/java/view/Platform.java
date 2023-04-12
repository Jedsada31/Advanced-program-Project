package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Bullet;
import model.Imposter;

import java.util.Random;

public class Platform extends Pane {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private Image platformIng;
    private ProgressBar playerBer1 = new ProgressBar(1);
    private ProgressBar playerBer2 = new ProgressBar(1);
    private ProgressBar chargeBar1 = new ProgressBar(0);
    private ProgressBar chargeBar2 = new ProgressBar(0);
    private ProgressBar windBar = new ProgressBar(0);
    private Imposter player1;
    private Imposter player2;
    private Bullet bullet1;
    private Bullet bullet2;
    private Random rand = new Random();
    private boolean turn = true;
    private int wind;

    public Platform() {
        windBar.setTranslateX(580);
        windBar.setTranslateY(150);
        playerBer1.setScaleX(4);
        playerBer1.setScaleY(2);
        playerBer1.setTranslateX(200);
        playerBer1.setTranslateY(50);
        playerBer2.setScaleX(4);
        playerBer2.setScaleY(2);
        playerBer2.setTranslateX(1000);
        playerBer2.setTranslateY(50);

        bullet1 = new Bullet(150,420,30);
        bullet1.setVisible(false);
        player1 = new Imposter(100,420,0,0);
        chargeBar1.setTranslateX(50);
        chargeBar1.setTranslateY(520);
        chargeBar1.setRotate(-90);
        chargeBar1.setDisable(true);

        bullet2 = new Bullet(WIDTH-player2.CHARACTER_WIDTH-50,420,30);
        bullet2.setScaleX(-1);
        bullet2.setVisible(false);
        player2 = new Imposter(WIDTH-player2.CHARACTER_WIDTH-100,420,0,0);
        player2.setScaleX(-1);
        chargeBar2.setTranslateX(WIDTH-150);
        chargeBar2.setTranslateY(520);
        chargeBar2.setRotate(-90);
        chargeBar2.setDisable(true);

        ImageView doubleButton1 = new ImageView(new Image(getClass().getResourceAsStream("/double.png")));
        doubleButton1.setTranslateX(40);
        doubleButton1.setTranslateY(80);
        doubleButton1.setFitWidth(100);
        doubleButton1.setFitHeight(100);
        doubleButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player2.setdoubleHit(true);
                getChildren().remove(doubleButton1);
            }
        });
        ImageView bigButton1 = new ImageView(new Image(getClass().getResourceAsStream("/Big.png")));
        bigButton1.setTranslateX(140);
        bigButton1.setTranslateY(80);
        bigButton1.setFitWidth(100);
        bigButton1.setFitHeight(100);
        bigButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bullet1.setScaleX(3);
                bullet1.setScaleY(3);
                getChildren().remove(bigButton1);
            }
        });
        ImageView toxicButton1 = new ImageView(new Image(getClass().getResourceAsStream("/Toxic.png")));
        toxicButton1.setTranslateX(240);
        toxicButton1.setTranslateY(80);
        toxicButton1.setFitWidth(100);
        toxicButton1.setFitHeight(100);
        toxicButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player2.setdoubleHit(true);
                getChildren().remove(toxicButton1);
            }
        });

        ImageView healingButton1 = new ImageView(new Image(getClass().getResourceAsStream("/Healing.png")));
        healingButton1.setTranslateX(340);
        healingButton1.setTranslateY(80);
        healingButton1.setFitWidth(100);
        healingButton1.setFitHeight(100);
        healingButton1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player1.Healing();
                playerBer1.setProgress(player1.getHealth());
                getChildren().remove(healingButton1);
            }
        });


        ImageView doubleButton2 = new ImageView(new Image(getClass().getResourceAsStream("/double.png")));
        doubleButton2.setTranslateX(WIDTH-440);
        doubleButton2.setTranslateY(80);
        doubleButton2.setFitWidth(100);
        doubleButton2.setFitHeight(100);
        doubleButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player1.setdoubleHit(true);
                getChildren().remove(doubleButton2);
            }
        });
        ImageView bigButton2 = new ImageView(new Image(getClass().getResourceAsStream("/Big.png")));
        bigButton2.setTranslateX(WIDTH-340);
        bigButton2.setTranslateY(80);
        bigButton2.setFitWidth(100);
        bigButton2.setFitHeight(100);
        bigButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                bullet2.setScaleX(-3);
                bullet2.setScaleY(3);
                getChildren().remove(bigButton2);
            }
        });
        ImageView toxicButton2 = new ImageView(new Image(getClass().getResourceAsStream("/Toxic.png")));
        toxicButton2.setTranslateX(WIDTH-240);
        toxicButton2.setTranslateY(80);
        toxicButton2.setFitWidth(100);
        toxicButton2.setFitHeight(100);
        toxicButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player1.setdoubleHit(true);
                getChildren().remove(toxicButton2);
            }
        });
        ImageView healingButton2 = new ImageView(new Image(getClass().getResourceAsStream("/Healing.png")));
        healingButton2.setTranslateX(WIDTH-140);
        healingButton2.setTranslateY(80);
        healingButton2.setFitWidth(100);
        healingButton2.setFitHeight(100);
        healingButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                player2.Healing();
                playerBer2.setProgress(player2.getHealth());
                getChildren().remove(healingButton2);
            }
        });

        platformIng = new Image(getClass().getResourceAsStream("/background.png"));
        ImageView backgroundImg = new ImageView(platformIng);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        getChildren().add(backgroundImg);
        getChildren().addAll(bullet1,bullet2);
        getChildren().addAll(doubleButton1,bigButton1,toxicButton1,healingButton1);
        getChildren().addAll(doubleButton2,bigButton2,toxicButton2,healingButton2);
        getChildren().addAll(playerBer1,playerBer2,windBar,chargeBar1,chargeBar2,player1,player2);
    }

    public Imposter getPlayer1() {
        return player1;
    }
    public Imposter getPlayer2(){
        return player2;
    }
    public Bullet getBullet1() { return bullet1; }
    public Bullet getBullet2() { return bullet2; }
    public ProgressBar getChargeBar1() {
        return chargeBar1;
    }
    public ProgressBar getChargeBar2() {
        return chargeBar2;
    }
    public ProgressBar getPlayerBer1() {
        return playerBer1;
    }
    public ProgressBar getPlayerBer2() {
        return playerBer2;
    }
    public boolean getTurn() {
        return turn;
    }
    public void setTurn() {
        this.turn = !turn;
        wind = rand.nextInt(20) - 10;
        System.out.println(wind);
        bullet1.setxAcceleration(wind);
        bullet2.setxAcceleration(wind);

        if(wind < 0){
            windBar.setScaleX(-1);
            windBar.setProgress(wind*(-1.0)/20.0);
        } else if(wind > 0) {
            windBar.setScaleX(1);
            windBar.setProgress(wind/20.0);
        } else {
            windBar.setProgress(0);
        }
    }
}
