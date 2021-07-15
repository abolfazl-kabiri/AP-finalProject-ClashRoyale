package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

public class GameElement implements Serializable {

    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected String pathInBattle;
    protected ImageView imageView;

    public GameElement(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void createPicture(Pane pane){
        imageView = new ImageView();
        Image image = new Image(pathInBattle, (double)width, (double)height, false,false);
        imageView.setImage(image);
        imageView.setX(x);
        imageView.setY(y);
        pane.getChildren().add(imageView);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getPathInBattle() {
        return pathInBattle;
    }

    public void setPathInBattle(String pathInBattle) {
        this.pathInBattle = pathInBattle;
    }
}
