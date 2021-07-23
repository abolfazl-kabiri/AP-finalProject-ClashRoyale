package towers;

/**
 * The type King tower.
 */
public class KingTower extends Tower{

    /**
     * Instantiates a new King tower.
     *
     * @param hp     the hp
     * @param damage the damage
     * @param x      the x
     * @param y      the y
     */
    public KingTower(int hp, int damage, double x, double y) {
        super(hp, damage, 1.0, 7.0, false, ".\\photos\\inside game models\\blue king building_00000.png", x, y, 3);
    }
}
