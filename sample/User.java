package sample;

import cards.Card;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {

    private String username;
    private String password;
    private int level;
    private int hp;
    private File userFile;
    private ArrayList<String> battleHistory;
    private ArrayList<Card> deck;
    private Player player;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        level = 1;
        hp = 0;
        battleHistory = new ArrayList<>();
//        battleHistory.add("hello this is test");
//        battleHistory.add("test 2");
//        battleHistory.add("test3");
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
        return level == user.level && hp == user.hp && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, level, hp);
    }

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

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<String> getBattleHistory() {
        return battleHistory;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        saveUser();
    }
}
