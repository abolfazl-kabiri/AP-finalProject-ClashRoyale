package cards;

import sample.Target;

public class InfernoTower extends Building{
    public InfernoTower(int damage, int hp) {
        super(0.4, Target.AIRandGROUND, 6, 40, 5,
                ".\\photos\\cardsImage\\inferno_00000.png",
                damage, hp);
    }
}
