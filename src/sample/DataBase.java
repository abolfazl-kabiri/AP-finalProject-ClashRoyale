package sample;

import cards.*;
import towers.KingTower;
import towers.PrincessTower;
import towers.Tower;

public class DataBase {


    public static int getUserLevel(int xp){
        int level = 0;
        if(xp < 300)
            level = 2;
        else if (xp < 300 + 500)
            level = 3;
        else if(xp < 300 + 500 + 900)
            level = 4;
        else if (xp < 300 + 500 + 900 + 1700)
            level = 5;
        else if (xp < 300 + 500 + 900 + 1700 + 2500)
            level = 6;
        return level;
    }
    public static int requiredXPForNextLevel(int level){
        if (level == 0)
            return 0;
        if (level == 1)
            return 300;
        else if (level == 2)
            return 300 + 500;
        else if (level == 3)
            return 300 + 500 + 900;
        else if (level == 4)
            return 300 + 500 + 900 + 1700;
        else
            return 300 + 500 + 900 + 1700 + 2500;
    }

    public static String getEnemyPathInBattle(Tower tower){
        String path = "";
        if (tower instanceof KingTower)
            path = ".\\photos\\inside game models\\red king building_00000.png";
        else if (tower instanceof PrincessTower)
            path = ".\\photos\\inside game models\\red queen building_00000.png";
        return path;
    }
    public static String getPathInBattle(Tower tower){
        String path = "";
        if (tower instanceof KingTower)
            path = ".\\photos\\inside game models\\blue king building_00000.png";
        else if (tower instanceof PrincessTower)
            path = ".\\photos\\inside game models\\blue queen building_00000.png";
        return path;
    }
    public static String getPathInBattlePlayer(Card card){
        String path = "";
        if(card instanceof Arrows)
            path = ".\\photos\\icon.jpg";
        else if (card instanceof Archer)
            path = ".\\photos\\inBattleGifs\\archer\\blue archer\\blue-archer-back-walk.gif";
        else if(card instanceof BabyDragon)
            path = ".\\photos\\inBattleGifs\\baby dragon\\blue baby dragon\\blue-baby-dragon-back-fly.gif";
        else if(card instanceof Barbarian)
            path = ".\\photos\\inBattleGifs\\barbarian\\blue barbarian\\blue-barbarian-back-walk.gif";
        else if(card instanceof Cannon)
            path = ".\\photos\\inBattle\\cannon.png";
        else if (card instanceof FireBall)
            path = ".\\photos\\inBattle\\fireball.png";
        else if(card instanceof Giant)
            path = ".\\photos\\inBattleGifs\\giant\\blue giant\\blue-giant-back-walk.gif";
        else if (card instanceof InfernoTower)
            path = ".\\photos\\inBattleGifs\\inferno\\building_inferno_tower_sprite_2.png";
        else if (card instanceof MiniPekka)
            path = ".\\photos\\inBattleGifs\\mini pekka\\blue mini pekka\\blue-mini-pekka-back-walk.gif";
        else if (card instanceof Rage)
            path = ".\\photos\\inBattleGifs\\purple circle.png";
        else if (card instanceof Valkyrie)
            path = ".\\photos\\inBattleGifs\\valkyrie\\blue valkyrie\\blue-valkyrie-back-walk.gif";
        else if(card instanceof Wizard)
            path = ".\\photos\\inBattleGifs\\wizard\\blue wizard\\blue-wizard-back-walk.gif";
        return path;
    }
    public static String getPathInBattleBot(Card card){
        String path = "";
        if(card instanceof Arrows)
            path = ".\\photos\\icon.jpg";
        else if (card instanceof Archer)
            path = ".\\photos\\inBattleGifs\\archer\\red archer\\red-archer-front-walk.gif";
        else if(card instanceof BabyDragon)
            path = ".\\photos\\inBattleGifs\\baby dragon\\red baby dragon\\red-baby-dragon-front-fly.gif";
        else if(card instanceof Barbarian)
            path = ".\\photos\\inBattleGifs\\barbarian\\red barbarian\\red-barbarian-front-walk.gif";
        else if(card instanceof Cannon)
            path = ".\\photos\\inBattle\\cannon.png";
        else if (card instanceof FireBall)
            path = ".\\photos\\inBattle\\fireball.png";
        else if(card instanceof Giant)
            path = ".\\photos\\inBattleGifs\\giant\\red giant\\red-giant-front-walk.gif";
        else if (card instanceof InfernoTower)
            path = ".\\photos\\inBattleGifs\\inferno\\building_inferno_tower_sprite_0.png";
        else if (card instanceof MiniPekka)
            path = ".\\photos\\inBattleGifs\\mini pekka\\red mini pekka\\red-mini-pekka-front-walk.gif";
        else if (card instanceof Rage)
            path = ".\\photos\\inBattleGifs\\purple circle.png";
        else if (card instanceof Valkyrie)
            path = ".\\photos\\inBattleGifs\\valkyrie\\red valkyrie\\red-valkyrie-front-walk.gif";
        else if(card instanceof Wizard)
            path = ".\\photos\\inBattleGifs\\wizard\\red wizard\\red-wizard-front-walk.gif";
        return path;
    }
    public static String getDamagingPathPlayer(Card card){
        String path = "";
        if(card instanceof Arrows)
            path = ".\\photos\\icon.jpg";
        else if (card instanceof Archer)
            path = ".\\photos\\inBattleGifs\\archer\\blue archer\\blue-archer-back-hit.gif";
        else if(card instanceof BabyDragon)
            path = ".\\photos\\inBattleGifs\\baby dragon\\blue baby dragon\\blue-baby-dragon-back-hit.gif";
        else if(card instanceof Barbarian)
            path = ".\\photos\\inBattleGifs\\barbarian\\blue barbarian\\blue-barbarian-back-hit.gif";
        else if(card instanceof Cannon)
            path = ".\\photos\\inBattle\\cannon.png";
        else if (card instanceof FireBall)
            path = ".\\photos\\inBattle\\fireball.png";
        else if(card instanceof Giant)
            path = ".\\photos\\inBattleGifs\\giant\\blue giant\\blue-giant-back-hit.gif";
        else if (card instanceof InfernoTower)
            path = ".\\photos\\inBattleGifs\\inferno\\building_inferno_tower_sprite_2.png";
        else if (card instanceof MiniPekka)
            path = ".\\photos\\inBattleGifs\\mini pekka\\blue mini pekka\\blue-mini-pekka-back-hit.gif";
        else if (card instanceof Rage)
            path = ".\\photos\\inBattleGifs\\purple circle.png";
        else if (card instanceof Valkyrie)
            path = ".\\photos\\inBattleGifs\\valkyrie\\blue valkyrie\\blue-valkyrie-back-hit.gif";
        else if(card instanceof Wizard)
            path = ".\\photos\\inBattleGifs\\wizard\\blue wizard\\blue-wizard-back-hit.gif";
        return path;
    }
    public static String getDamagePathBot(Card card){
        String path = "";
        if(card instanceof Arrows)
            path = ".\\photos\\icon.jpg";
        else if (card instanceof Archer)
            path = ".\\photos\\inBattleGifs\\archer\\red archer\\red-archer-front-hit.gif";
        else if(card instanceof BabyDragon)
            path = ".\\photos\\inBattleGifs\\baby dragon\\red baby dragon\\red-baby-dragon-front-hit.gif";
        else if(card instanceof Barbarian)
            path = ".\\photos\\inBattleGifs\\barbarian\\red barbarian\\red-barbarian-front-hit.gif";
        else if(card instanceof Cannon)
            path = ".\\photos\\inBattle\\cannon.png";
        else if (card instanceof FireBall)
            path = ".\\photos\\inBattle\\fireball.png";
        else if(card instanceof Giant)
            path = ".\\photos\\inBattleGifs\\giant\\red giant\\red-giant-front-hit.gif";
        else if (card instanceof InfernoTower)
            path = ".\\photos\\inBattleGifs\\inferno\\building_inferno_tower_sprite_0.png";
        else if (card instanceof MiniPekka)
            path = ".\\photos\\inBattleGifs\\mini pekka\\red mini pekka\\red-mini-pekka-front-hit.gif";
        else if (card instanceof Rage)
            path = ".\\photos\\inBattleGifs\\purple circle.png";
        else if (card instanceof Valkyrie)
            path = ".\\photos\\inBattleGifs\\valkyrie\\red valkyrie\\red-valkyrie-front-hit.gif";
        else if(card instanceof Wizard)
            path = ".\\photos\\inBattleGifs\\wizard\\red wizard\\red-wizard-front-hit.gif";
        return path;
    }
    public static String getPathInBattle(Card card, boolean isBot){
        if (isBot)
            return getPathInBattleBot(card);
        else
            return getPathInBattlePlayer(card);
    }

    public static int getHP(Tower tower, int level){
        int hp = 0;
        if(tower instanceof KingTower)
            hp = getKingTowerHP(level);
        else if(tower instanceof PrincessTower)
            hp = getPrincessTowerHP(level);
        return hp;
    }
    private static int getKingTowerHP(int level){
        switch (level){
            case 1:
                return 2400;
            case 2:
                return 2568;
            case 3:
                return 2736;
            case 4:
                return 2904;
            default:
                return 3096;
        }
    }
    private static int getPrincessTowerHP(int level){
        switch (level){
            case 1:
                return 1400;
            case 2:
                return 1512;
            case 3:
                return 1624;
            case 4:
                return 1750;
            default:
                return 1890;
        }
    }


    public static int getDamage(Tower tower, int level){
        int damage = 0;
        if(tower instanceof KingTower)
            damage = getKingTowerDamage(level);
        else if(tower instanceof PrincessTower)
            damage = getPrincessTowerDamage(level);
        return damage;
    }
    private static int getKingTowerDamage(int level) {
        switch (level){
            case 1:
                return 50;
            case 2:
                return 53;
            case 3:
                return 57;
            case 4:
                return 60;
            default:
                return 64;
        }
    }
    private static int getPrincessTowerDamage(int level) {
        switch (level){
            case 1:
                return 50;
            case 2:
                return 54;
            case 3:
                return 58;
            case 4:
                return 62;
            default:
                return 68;
        }
    }


    public static int getDamage(Card card, int level){
        int damage = 0;
        if(card instanceof Barbarian)
            damage = getBarbarianDamage(level);
        else if (card instanceof Archer)
            damage = getArcherDamage(level);
        else if (card instanceof BabyDragon)
            damage = getBabyDragonDamage(level);
        else if (card instanceof Wizard)
            damage = getWizardDamage(level);
        else if (card instanceof MiniPekka)
            damage = getMiniPekkaDamage(level);
        else if (card instanceof Giant)
            damage = getGiantDamage(level);
        else if (card instanceof Valkyrie)
            damage = getValkyrieDamage(level);
        else if(card instanceof FireBall)
            damage = getFireBallDamage(level);
        else if(card instanceof Arrows)
            damage = getArrowsDamage(level);
        else if(card instanceof Cannon)
            damage = getCanonDamage(level);
        else if(card instanceof InfernoTower)
            damage = getInfernoTowerDamage(level);

        return damage;
    }
    private static int getCanonDamage(int level) {
        switch (level){
            case 1:
                return 60;
            case 2:
                return 66;
            case 3:
                return 72;
            case 4:
                return 79;
            default:
                return 87;
        }
    }
    private static int getArrowsDamage(int level) {
        switch (level){
            case 1:
                return 144;
            case 2:
                return 156;
            case 3:
                return 174;
            case 4:
                return 189;
            default:
                return 210;
        }
    }
    private static int getFireBallDamage(int level) {
        switch (level){
            case 1:
                return 325;
            case 2:
                return 357;
            case 3:
                return 393;
            case 4:
                return 432;
            default:
                return 474;
        }
    }
    private static int getBarbarianDamage(int level){
        switch (level){
            case 1:
                return 75;
            case 2:
                return 82;
            case 3:
                return 90;
            case 4:
                return 99;
            default:
                return 109;
        }
    }
    private static int getArcherDamage(int level){
        switch (level){
            case 1:
                return 33;
            case 2:
                return 44;
            case 3:
                return 48;
            case 4:
                return 53;
            default:
                return 58;
        }
    }
    private static int getBabyDragonDamage(int level){
        switch (level){
            case 1:
                return 100;
            case 2:
                return 110;
            case 3:
                return 121;
            case 4:
                return 133;
            default:
                return 146;
        }
    }
    private static int getWizardDamage(int level){
        switch (level){
            case 1:
                return 130;
            case 2:
                return 143;
            case 3:
                return 157;
            case 4:
                return 172;
            default:
                return 189;
        }
    }
    private static int getMiniPekkaDamage(int level){
        switch (level){
            case 1:
                return 325;
            case 2:
                return 357;
            case 3:
                return 393;
            case 4:
                return 432;
            default:
                return 474;
        }
    }
    private static int getGiantDamage(int level){
        switch (level){
            case 1:
                return 126;
            case 2:
                return 138;
            case 3:
                return 152;
            case 4:
                return 167;
            default:
                return 183;
        }
    }
    private static int getValkyrieDamage(int level){
        switch (level){
            case 1:
                return 120;
            case 2:
                return 132;
            case 3:
                return 145;
            case 4:
                return 159;
            default:
                return 175;
        }
    }
    private static int getInfernoTowerDamage(int level) {
        switch (level){
            case 1:
                return 20;
            case 2:
                return 22;
            case 3:
                return 24;
            case 4:
                return 26;
            default:
                return 29;
        }
    }


    public static int getHP(Card card, int level){
        int hp = 0;
        if(card instanceof Barbarian)
            hp = getBarbarianHP(level);
        else if (card instanceof Archer)
            hp = getArcherHP(level);
        else if (card instanceof BabyDragon)
            hp = getBabyDragonHP(level);
        else if (card instanceof Wizard)
            hp = getWizardHP(level);
        else if (card instanceof MiniPekka)
            hp = getMiniPekkaHP(level);
        else if (card instanceof Giant)
            hp = getGiantHP(level);
        else if (card instanceof Valkyrie)
            hp = getValkyrieHP(level);
        else if(card instanceof Cannon)
            hp = getCanonHP(level);
        else if(card instanceof InfernoTower)
            hp = getInfernoTowerHP(level);

        return hp;
    }
    private static int getInfernoTowerHP(int level) {
        switch (level){
            case 1:
                return 800;
            case 2:
                return 880;
            case 3:
                return 968;
            case 4:
                return 1064;
            default:
                return 1168;
        }
    }
    private static int getCanonHP(int level) {
        switch (level){
            case 1:
                return 380;
            case 2:
                return 418;
            case 3:
                return 459;
            case 4:
                return 505;
            default:
                return 554;
        }
    }
    private static int getBarbarianHP(int level){
        switch (level){
            case 1:
                return 300;
            case 2:
                return 330;
            case 3:
                return 363;
            case 4:
                return 438;
            default:
                return 480;
        }
    }
    private static int getArcherHP(int level){
        switch (level){
            case 1:
                return 125;
            case 2:
                return 127;
            case 3:
                return 151;
            case 4:
                return 166;
            default:
                return 182;
        }
    }
    private static int getBabyDragonHP(int level){
        switch (level){
            case 1:
                return 800;
            case 2:
                return 880;
            case 3:
                return 968;
            case 4:
                return 1064;
            default:
                return 1168;
        }
    }
    private static int getWizardHP(int level){
        switch (level){
            case 1:
                return 340;
            case 2:
                return 374;
            case 3:
                return 411;
            case 4:
                return 452;
            default:
                return 496;
        }
    }
    private static int getMiniPekkaHP(int level){
        switch (level){
            case 1:
                return 600;
            case 2:
                return 660;
            case 3:
                return 726;
            case 4:
                return 798;
            default:
                return 876;
        }
    }
    private static int getGiantHP(int level){
        switch (level){
            case 1:
                return 2000;
            case 2:
                return 2200;
            case 3:
                return 2420;
            case 4:
                return 2660;
            default:
                return 2920;
        }
    }
    private static int getValkyrieHP(int level){
        switch (level){
            case 1:
                return 880;
            case 2:
                return 968;
            case 3:
                return 1061;
            case 4:
                return 1170;
            default:
                return 1281;
        }
    }

    public static double getRageDuration(int level){
        switch (level){
            case 1:
                return 6.0;
            case 2:
                return 6.5;
            case 3:
                return 7.0;
            case 4:
                return 7.5;
            default:
                return 8.0;
        }
    }


    public static int getInfernoDPS(int level){
        switch (level){
            case 1:
                return 9;
            case 2:
                return 10;
            case 3:
                return 12;
            case 4:
                return 13;
            default:
                return 14;
        }
    }
    public static boolean isTargetValid(Card enemyCard, Soldier intendedCard){
        if (intendedCard.getTarget().equals(Target.GROUND)){
            if (enemyCard instanceof BabyDragon || enemyCard instanceof Spell)
                return false;
            return true;
        }
        else if (intendedCard.getTarget().equals(Target.AIRandGROUND)){
            if (enemyCard instanceof Spell)
                return false;
            return true;
        }
        else if (intendedCard.getTarget().equals(Target.BUILDINGS)){
            return enemyCard instanceof Building;
        }
        return false;
    }
}
