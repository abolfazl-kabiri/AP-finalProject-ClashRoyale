package cards;

import sample.Target;

public class Cannon extends Building{
    public Cannon() {
        super(0.8, Target.GROUND,
                5.5, 30,
                6, 1,
                ".\\photos\\cardsImage\\canon_00000.png");
    }
}