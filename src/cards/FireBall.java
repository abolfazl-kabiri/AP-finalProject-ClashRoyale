package cards;

/**
 * The type Fire ball.
 */
public class FireBall extends Spell{
    /**
     * Instantiates a new Fire ball.
     *
     * @param damage the damage
     */
    public FireBall(int damage){
        super(2.5, 4,
                ".\\photos\\cardsImage\\fireball_00000.png", damage, 1);
    }
}
