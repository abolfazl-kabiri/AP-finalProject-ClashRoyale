package towers;

/**
 * The type Princess tower.
 */
public class PrincessTower extends Tower{
    /**
     * Instantiates a new Princess tower.
     *
     * @param hp     the hp
     * @param damage the damage
     * @param x      the x
     * @param y      the y
     */
    public PrincessTower(int hp, int damage, double x, double y) {
        super(hp, damage, 0.8, 7.5, true, ".\\photos\\inside game models\\blue queen building_00000.png", x, y, 1);
    }
}
