package cards;

import sample.Speed;
import sample.Target;


public class Archer extends Soldier{
    public Archer(int damage, int hp){
        super(3, 1.2,
                Speed.MEDIUM, Target.AIRandGROUND,
                "5", false, 2,
                ".\\photos\\cardsImage\\archer_00000.png", damage, hp);
    }
}
