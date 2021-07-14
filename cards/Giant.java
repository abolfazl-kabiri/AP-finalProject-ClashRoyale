package cards;

import sample.Speed;
import sample.Target;

public class Giant extends Soldier{
    public Giant(int damage, int hp){
        super(5, 1.5,
                Speed.SLOW, Target.BUILDINGS,
                "melee", false,
                1,
                ".\\photos\\cardsImage\\giant_00000.png",
                damage, hp);
    }
}
