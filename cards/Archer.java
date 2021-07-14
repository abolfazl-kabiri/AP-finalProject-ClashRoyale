package cards;

import javafx.scene.image.Image;
import sample.Speed;
import sample.Target;

import java.io.File;

public class Archer extends Soldier{
    public Archer(){
        super(3, 1.2,
                Speed.MEDIUM, Target.AIRandGROUND,
                "5", true, 1, 1,
                ".\\photos\\cardsImage\\archer_00000.png");
    }
}