package cards;

import sample.Speed;
import sample.Target;

public class Wizard extends Soldier{
    public Wizard(){
        super(5, 1.7,
                Speed.FAST, Target.AIRandGROUND,
                "5", true,
                1, 1,
                ".\\photos\\cardsImage\\wizard_00000.png");
    }
}
