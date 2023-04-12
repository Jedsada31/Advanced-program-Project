package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class AnimatedSprite extends ImageView {

    int count, columns, offsetX, offsetY, width, height, curXIndex=0, curYIndex=0;

    public AnimatedSprite(Image image, int count, int columns, int offsetX, int offsetY, int width, int height){
        this.setImage(image);
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void tick_attack(){
        curXIndex = (3)%columns;
        curYIndex = (3)/columns;
        interpolate();
    }

    public void tick_hit(){
        curXIndex = (2)%columns;
        curYIndex = (2)/columns;
        interpolate();
    }

    protected void interpolate() {
        final int x = curXIndex*width+offsetX;
        final int y = curYIndex*height+offsetY;
        this.setViewport(new Rectangle2D(x, y, width, height));
    }

    public void setBackStay(){
        this.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    public void setBackStay_injure(){
        curXIndex = (4)%columns;
        curYIndex = (4)/columns;
        interpolate();
    }
}
