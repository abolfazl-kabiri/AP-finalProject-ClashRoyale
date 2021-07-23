package sample;

import cards.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The type User.
 */
public class User implements Serializable {

    private String username;
    private String password;
    private int level;
    private int xp;
    private File userFile;
    private ArrayList<String> battleHistory;
    private ArrayList<Card> deck;
    transient private Player player;


    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        level = 1;
        xp = 0;
        battleHistory = new ArrayList<>();
        deck = new ArrayList<>();
        userFile = new File(createFilePath());
        if(!userFile.exists())
            saveUser();

    }
    private String createFilePath(){
        return ".\\src\\users\\" + username + ".bin";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    /**
     * Save user.
     */
    public void saveUser(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(userFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets battle history.
     *
     * @return the battle history
     */
    public ArrayList<String> getBattleHistory() {
        return battleHistory;
    }

    /**
     * Gets deck.
     *
     * @return the deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Sets deck.
     *
     * @param deck the deck
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets player.
     *
     * @param player the player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets xp.
     *
     * @return the xp
     */
    public int getXp() {
        return xp;
    }

    /**
     * Sets xp.
     *
     * @param xp the xp
     */
    public void setXp(int xp) {
        this.xp = xp;
    }

    /**
     * Set level.
     *
     * @param level the level
     */
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * Add to history.
     *
     * @param history the history
     */
    public void addToHistory(String history){
        battleHistory.add(history);
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets battle history.
     *
     * @param battleHistory the battle history
     */
    public void setBattleHistory(ArrayList<String> battleHistory) {
        this.battleHistory = battleHistory;
    }
}
