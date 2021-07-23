package sample;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.Serializable;

/**
 * The type Game element.
 */
public class GameElement implements Serializable {

    /**
     * The X.
     */
    protected double x;
    /**
     * The Y.
     */
    protected double y;
    /**
     * The Width.
     */
    protected int width;
    /**
     * The Height.
     */
    protected int height;
    /**
     * The Path in battle.
     */
    protected String pathInBattle;
    /**
     * The Image view.
     */
    protected transient ImageView imageView;
    /**
     * The Image.
     */
    protected transient Image image;

    /**
     * Instantiates a new Game element.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public GameElement(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Create picture.
     *
     * @param pane the pane
     */
    public void createPicture(Pane pane){
        imageView = new ImageView();
        this.image = new Image(pathInBattle, (double)width, (double)height, false,false);
        imageView.setImage(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        pane.getChildren().add(imageView);
    }

    /**
     * Create picture.
     *
     * @param pane the pane
     * @param x    the x
     * @param y    the y
     */
    public void createPicture(Pane pane, double x, double y){

        this.x  = x;
        this.y = y;
        imageView = new ImageView();
        image = new Image(pathInBattle, (double)width, (double)height, false,false);
        imageView.setImage(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        pane.getChildren().add(imageView);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
        imageView.setLayoutX(this.x);
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
        imageView.setLayoutY(this.y);
    }

    /**
     * Gets image view.
     *
     * @return the image view
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Gets path in battle.
     *
     * @return the path in battle
     */
    public String getPathInBattle() {
        return pathInBattle;
    }

    /**
     * Sets path in battle.
     *
     * @param pathInBattle the path in battle
     */
    public void setPathInBattle(String pathInBattle) {
        this.pathInBattle = pathInBattle;
    }
}
