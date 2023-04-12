package model;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Imposter extends Pane {
    public static final int CHARACTER_WIDTH = 160;
    public static final int CHARACTER_HEIGHT = 200;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private int offsetX;
    private int offsetY;
    private double Health = 1.0;
    private double power = 0;
    private boolean charge = false;
    private boolean doubleHit = false;

    public Imposter(int x, int y, int offsetX, int offsetY) {
        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(getClass().getResourceAsStream("/ImposterAT.png"));
        this.imageView = new AnimatedSprite(characterImg, 4, 4, offsetX, offsetY, 500, 500);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.getChildren().addAll(imageView);
    }

     public void isCharge() {
        double weight = 0.05;
        if (this.charge && power < 1){
            power += weight;
            System.out.println(power);
        }
     }

     public AnimatedSprite getImageView() {
        return this.imageView;
     }
     public double getPower(){
        return power;
    }
     public void setImageView(AnimatedSprite imageView){
        this.imageView = imageView;
     }
     public void setdoubleHit(boolean hit) { this.doubleHit = hit;}
     public void setPower(int power) { this.power = power;}
     public void setCharge(boolean charge) {
         this.charge = charge;
     }
     public double getHealth(){
        return Health;
     }
     public void setHealth() {
        this.Health -= 0.2;
        if(doubleHit) { Health -= 0.2; }
        if(Health < 0.1) {Health = 0;}
     }
     public void Healing(){
        this.Health += 0.5;
        if(Health > 1.0) { Health = 1.0; }
     }
}
