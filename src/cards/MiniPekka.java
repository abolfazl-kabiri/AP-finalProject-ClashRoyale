package cards;

import sample.Speed;
import sample.Target;

public class MiniPekka extends Soldier{
    public MiniPekka(int damage, int hp){
        super(4, 1.8,
                Speed.FAST, Target.GROUND,
                "melee", false,
                1,
                ".\\photos\\cardsImage\\mini pekka_00000.png", damage, hp);
    }
}
