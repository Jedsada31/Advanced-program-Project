package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Bullet extends Pane {
    //Logger logger = LoggerFactory.getLogger(Character.class);
    public static final int CHARACTER_WIDTH = 75;
    public static final int CHARACTER_HEIGHT = 75;
    private Image characterImg;
    private ImageView imageView;
    private int x;
    private int y;
    private int StartX;
    private int StartY;
    int xVelocity;
    int yVelocity;
    int xAcceleration = 0;
    int yAcceleration = 1;
    int xMaxVelocity;
    int yMaxVelocity;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = false;
    boolean isJumping = true;

    public Bullet(int x, int y, int velocity) {
        this.xMaxVelocity = velocity;
        this.yMaxVelocity = velocity;
        this.x = x;
        this.y = y;
        this.StartX = x;
        this.StartY = y;
        this.setTranslateX((double)x);
        this.setTranslateY((double)y);
        this.characterImg = new Image(this.getClass().getResourceAsStream("/Kuni.png"));
        this.imageView = new ImageView(characterImg);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.getChildren().add(imageView);
    }

    public void moveY() {
        this.setTranslateY((double)this.y);
        if(isMoveRight || isMoveLeft){
            if (this.isJumping) {
                this.yVelocity = this.yVelocity - this.yAcceleration;
                this.y -= this.yVelocity;
            }
        }
    }

    public void moveX() {
        this.setTranslateX((double)this.x);
        if (this.isMoveLeft) {
            this.x -= this.xVelocity;
        }

        if (this.isMoveRight) {
            this.x += this.xVelocity;
        }

    }

    public void repaint() {
        this.moveX();
        this.moveY();
    }

    public void moveLeft(double power) {
        isMoveLeft = true;
        isJumping = true;
        isFalling = false;

        yVelocity = (int) (yMaxVelocity * power);
        xVelocity = (int) (xMaxVelocity * power)-xAcceleration;
    }

    public void moveRight(double power) {
        isMoveRight = true;
        isJumping = true;
        isFalling = false;

        yVelocity = (int) (yMaxVelocity * power);
        xVelocity = (int) (xMaxVelocity * power)+xAcceleration;
    }

    public void respawn(){
        x=StartX;
        y=StartY;
        isMoveRight=false;
        isMoveLeft=false;
    }

    public void setxAcceleration(int wind) {
        this.xAcceleration = wind;
    }

    public int getY() { return y; }
    public int getX() { return x; }

//    public void trace() {
//        this.logger.info("x:{} y:{} vx:{} vy:{}", new Object[]{this.x, this.y, this.xVelocity, this.yVelocity});
//    }
}