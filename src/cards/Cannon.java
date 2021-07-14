package cards;

import sample.Target;

public class Cannon extends Building{
    public Cannon(int damage, int hp) {
        super(0.8, Target.GROUND,
                5.5, 30,
                6,
                ".\\photos\\cardsImage\\canon_00000.png", damage, hp);
    }
}
