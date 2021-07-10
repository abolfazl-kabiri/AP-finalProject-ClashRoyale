package cards;

import sample.Speed;
import sample.Target;

public class Valkyrie extends Soldier{
    public Valkyrie(){
        super(4, 1.5,
                Speed.FAST, Target.GROUND,
                "melee", true,
                1, 1,
                ".\\photos\\cardsImage\\valkyrie_00000.png");
    }
}
