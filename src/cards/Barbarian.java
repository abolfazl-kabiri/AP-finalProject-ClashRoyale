package cards;

import sample.Speed;
import sample.Target;

public class Barbarian extends Soldier{
    public Barbarian(){
        super(5, 1.5,
                Speed.MEDIUM, Target.GROUND, "melee",
                false, 4, 1,
                ".\\photos\\cardsImage\\barbarian_00000.png");
    }
}
