package cards;

import controller.TrainingCampController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.DataBase;
import sample.GameElement;
import sample.Speed;
import sample.Target;
import towers.Tower;

import java.util.Iterator;

public abstract class Soldier extends Card{
    protected double hitSpeed;
    protected Speed speed;
    protected Target target;
    protected String range;
    protected boolean isAreaSplash;
    protected boolean isBot;
    protected int count;
    protected int dx;
    protected int dy;
    protected boolean areaSplashDone;
    public Soldier(int cost, double hitSpeed,
                   Speed speed, Target target,
                   String range, boolean isAreaSplash,
                   int count, String path,
                   int damage, int hp) {
        super(cost, path, damage, hp);
        this.hitSpeed = hitSpeed;
        this.speed = speed;
        this.target = target;
        this.range = range;
        this.isAreaSplash = isAreaSplash;
        this.count = count;
        this.isBot = false;
        this.dx = 1;
        this.dy = -1;
        areaSplashDone = false;
    }

    public void setBot(boolean bot) {
        isBot = bot;
        this.dy = 1;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public void startFunctioning(){
        movingAnimation = new Timeline(new KeyFrame(Duration.seconds(speed.getSpeedValue()), e-> move()));
        movingAnimation.setCycleCount(Timeline.INDEFINITE);
        movingAnimation.play();
        TrainingCampController.animations.add(movingAnimation);
    }
    @Override
    public void move() {
        if (isBot)
            handleBotMove();
        else
            handlePlayerMove();

       attack();
    }

    private void handlePlayerMove(){
        this.dx = 1;
        if (!hasReachedBridge(getX())){
            if (x < 289){
                if (x < 183)
                    setX(getX() + dx);
                else if (x > 185)
                    setX(getX() - dx);
                if (y > 232.0)
                    setY(getY() + dy);
            } else {
                if (x < 376)
                    setX(getX() + dx);
                else if (x > 378)
                    setX(getX() - dx);
                if (y > 232.0)
                    setY(getY() + dy);
            }
        } else
            setY(getY() + dy);
    }
    private void handleBotMove(){
        if (!hasReachedBridge(getX())){
            if (x < 289){
                if (x <= 183)
                    setX(getX() + dx);
                else if (x >= 185)
                    setX(getX() - dx);
                if (y <= 232.0)
                    setY(getY() + dy);
            } else {
                if (x <= 376)
                    setX(getX() + dx);
                else if (x >= 378)
                    setX(getX() - dx);
                if (y <= 232.0)
                    setY(getY() + dy);
            }
        } else
            setY(getY() + dy);
    }

    @Override
    public void attack() {
        if (isBot)
            handleBotAttack();
        else
            handlePlayerAttack();
    }

    private void handlePlayerAttack(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator <Card> enemyIterator = TrainingCampController.enemyInGameCards.iterator();
            while (enemyIterator.hasNext()){
                Card temp = enemyIterator.next();
                if (!range.equals("melee")){
                    if (checkX(temp) && checkY(temp)
                            && DataBase.isTargetValid(temp, this)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(temp)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
                else{
                    if (checkXForMelee(temp) && checkYForMelee(temp)
                            && DataBase.isTargetValid(temp, this)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(temp)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
            }
        }
        synchronized (TrainingCampController.enemyTowers){
            Iterator<Tower> towerIterator = TrainingCampController.enemyTowers.iterator();
            while (towerIterator.hasNext()){
                Tower tower = towerIterator.next();
                if (!range.equals("melee")){
                    if (checkX(tower) && checkY(tower)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(tower)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
                else{
                    if (checkXForMelee(tower) && checkYForMelee(tower)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(tower)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
            }
        }
    }
    private void handleBotAttack(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator <Card> playerIterator = TrainingCampController.playerInGameCards.iterator();
            while (playerIterator.hasNext()){
                Card temp = playerIterator.next();
                if (!range.equals("melee")){
                    if (checkX(temp) && checkY(temp)
                            && DataBase.isTargetValid(temp, this)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(temp)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
                else{
                    if (checkXForMelee(temp) && checkYForMelee(temp)
                            && DataBase.isTargetValid(temp, this)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(temp)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
            }
        }
        synchronized (TrainingCampController.playerTowers){
            Iterator<Tower> towerIterator = TrainingCampController.playerTowers.iterator();
            while (towerIterator.hasNext()){
                Tower tower = towerIterator.next();
                if (!range.equals("melee")){
                    if (checkX(tower) && checkY(tower)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(tower)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
                else{
                    if (checkXForMelee(tower) && checkYForMelee(tower)){
                        this.dx = 0;
                        this.dy = 0;
                        hittingAnimation = new Timeline(new KeyFrame(Duration.seconds(this.hitSpeed), e->hit(tower)));
                        hittingAnimation.setCycleCount(Timeline.INDEFINITE);
                        hittingAnimation.play();
                        TrainingCampController.animations.add(hittingAnimation);
                    }
                }
            }
        }
    }

    public int getCount() {
        return count;
    }

    private void hit(Card card){
        if (isBot)
            this.getImageView().setImage(new Image(DataBase.getDamagePathBot(this), 34.0, 34.0, false, false));
        else if (! isBot)
            this.getImageView().setImage(new Image(DataBase.getDamagingPathPlayer(this), 43.0, 43.0, false, false));
        if (card instanceof Building || card instanceof Soldier){
            card.setHp(card.getHp() - this.damage);
            if (card.getHp() <= 0){
                card.setHp(0);
                card.setDamage(0);
                if (card instanceof Soldier){
                    if (((Soldier) card).isAreaSplash)
                        ((Soldier) card).damageAreaSplash();
                }
                if (isBot)
                    TrainingCampController.playerInGameCards.remove(card);
                else
                    TrainingCampController.enemyInGameCards.remove(card);
                this.getImageView().setImage(new Image(DataBase.getPathInBattle(this, isBot), 43.0, 43.0 , false, false));
                card.getImageView().setVisible(false);
                card.getImageView().setDisable(true);
                card = null;
                hittingAnimation.stop();
                if (!isBot)
                    dy = -1;
                else
                    dy = 1;
            }
        }
    }
    private void hit(Tower tower){
        tower.setHp(tower.getHp() - getDamage());
        if (tower.getHp() <= 0) {
            tower.setHp(0);
            tower.setDamage(0);
            if (isBot)
                TrainingCampController.playerTowers.remove(tower);
            else
                TrainingCampController.enemyTowers.remove(tower);
            tower.getImageView().setVisible(false);
            tower.getImageView().setDisable(true);
            hittingAnimation.stop();
            if (!isBot)
                dy = -1;
            else
                dy = 1;
            movingAnimation.play();
        }
    }
    private boolean checkX(GameElement gm){
        return this.getX() + (15 * Integer.parseInt(range)) > gm.getX() &&
                this.getX() - (15 * Integer.parseInt(range)) < gm.getX();
    }
    private boolean checkY(GameElement gm){
        return this.getY() + (15 * Integer.parseInt(range)) > gm.getY() &&
                this.getY() - (15 * Integer.parseInt(range)) < gm.getY();
    }
    private boolean checkYForMelee(GameElement gm){
        if (!isBot)
            return this.getY() - (2 * 15) < gm.getY();
        return this.getY() + (2 * 15) > gm.getY();
    }
    private boolean checkXForMelee(GameElement gm){
        return this.getX() - (2 * 15) < gm.getX() &&
                this.getX() + (2 * 15) > gm.getX();
    }
    private boolean checkSplash(GameElement gm){
        return this.getX() - 15 < gm.getX() &&
                this.getX() + 15 > gm.getX() &&
                this.getY() + 15 > gm.getY() &&
                this.getY() - 15 < gm.getY();
    }
    private boolean hasReachedBridge(double x){
        return (x > 183 && x < 185) || (x > 376 && x < 378);
    }
    public Target getTarget(){
        return this.target;
    }
    public void damageAreaSplash(){
        if (!areaSplashDone){
            if (isBot)
                handleBotSplash();
            else
                handlePlayerSplash();
        }
        areaSplashDone = true;

    }
    public void handleBotSplash(){
        synchronized (TrainingCampController.playerInGameCards){
            Iterator<Card> it = TrainingCampController.playerInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if(checkSplash(card)){
                    card.setHp(0);
                    card = null;
                }
            }
        }
    }
    public void handlePlayerSplash(){
        synchronized (TrainingCampController.enemyInGameCards){
            Iterator<Card> it = TrainingCampController.enemyInGameCards.iterator();
            while (it.hasNext()){
                Card card = it.next();
                if(checkSplash(card)){
                    card.setHp(0);
                    card = null;
                }
            }
        }
    }
    @Override
    public void rageIt(){
        this.damage *= 1.4;
        this.hitSpeed /= 1.4;
        this.isRaged = true;
    }
    @Override
    public void unRageIt(){
        this.damage /= 1.4;
        this.hitSpeed *= 1.4;
        this.isRaged = false;
    }
}
