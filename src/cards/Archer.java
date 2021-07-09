package cards;

import javafx.scene.image.Image;

import java.io.File;

public class Archer extends Soldier{
    public Archer(){
        super(3, 1.2,
                "medium", "air & ground",
                "5", true, 1, 1,
                ".\\cardsImage\\archer_00000.png");
    }
}
