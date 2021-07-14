package cards;

import sample.Speed;
import sample.Target;

public class BabyDragon extends Soldier{
    public BabyDragon(){
        super(4, 1.8,
                Speed.FAST, Target.AIRandGROUND,
                "3", true,
                1, 1,
                ".\\photos\\cardsImage\\baby dragon_00000.png");
    }
}