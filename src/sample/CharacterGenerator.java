package sample;

import cards.*;

/**
 * The type Character generator.
 */
public class CharacterGenerator {
    /**
     * Card factory card.
     *
     * @param prompt the prompt
     * @return the card
     */
    public static Card cardFactory(String prompt){
        if (prompt.equals("archer"))
            return new Archer(0, 0);
        else if(prompt.equals("arrows"))
            return new Arrows(0);
        else if (prompt.equals("baby dragon"))
            return new BabyDragon(0, 0);
        else if (prompt.equals("barbarian"))
            return new Barbarian(0, 0);
        else if (prompt.equals("cannon"))
            return new Cannon(0 , 0);
        else if (prompt.equals("fireball"))
            return new FireBall(0);
        else if (prompt.equals("giant"))
            return new Giant(0, 0);
        else if (prompt.equals("inferno tower"))
            return new InfernoTower(0, 0);
        else if (prompt.equals("mini pekka"))
            return new MiniPekka(0, 0);
        else if (prompt.equals("rage"))
            return new Rage(0);
        else if (prompt.equals("valkyrie"))
            return new Valkyrie(0, 0);
        else if (prompt.equals("wizard"))
            return new Wizard(0, 0);
        return null;
    }
}
