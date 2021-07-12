package controller;

import cards.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.User;

import java.io.IOException;
import java.util.*;

public class BattleDeckController {
    private String emptyCardImage = ".\\photos\\batlle deck photos\\empty card_00000.png";
    private Timer timer;
    private TimerTask timerTask;
    private User currentUser;
    private ArrayList<Card> userCards;
    private ArrayList<Button> emptyCards;
    private ArrayList<Card> selectedCards;
    private HashMap<Button, Card> selectedCardsObjects;
    private HashMap<Button, ImageView> selectedCardsImages;
    private HashMap<String, ImageView> emptyCardsImages;
    private Stage stage;
    private Parent root;
    @FXML private Button backButton;
    @FXML private Button backButtonHighlighted;
    @FXML private Button emptyCard1;
    @FXML private Button emptyCard2;
    @FXML private Button emptyCard3;
    @FXML private Button emptyCard4;
    @FXML private Button emptyCard5;
    @FXML private Button emptyCard6;
    @FXML private Button emptyCard7;
    @FXML private Button emptyCard8;
    @FXML private Button archerCard;
    @FXML private Button dragonCard;
    @FXML private Button barbarianCard;
    @FXML private Button giantCard;
    @FXML private Button pekkaCard;
    @FXML private Button valkyrieCard;
    @FXML private Button wizardCard;
    @FXML private Button arrowsCard;
    @FXML private Button fireBallCard;
    @FXML private Button rageCard;
    @FXML private Button cannonCard;
    @FXML private Button infernoCard;
    @FXML private ImageView emptyCard1Photo;
    @FXML private ImageView emptyCard2Photo;
    @FXML private ImageView emptyCard3Photo;
    @FXML private ImageView emptyCard4Photo;
    @FXML private ImageView emptyCard5Photo;
    @FXML private ImageView emptyCard6Photo;
    @FXML private ImageView emptyCard7Photo;
    @FXML private ImageView emptyCard8Photo;
    @FXML private ImageView archerPhoto;
    @FXML private ImageView dragonPhoto;
    @FXML private ImageView barbarianPhoto;
    @FXML private ImageView giantPhoto;
    @FXML private ImageView pekkaPhoto;
    @FXML private ImageView valkyriePhoto;
    @FXML private ImageView wizardPhoto;
    @FXML private ImageView arrowsPhoto;
    @FXML private ImageView fireBallPhoto;
    @FXML private ImageView ragePhoto;
    @FXML private ImageView cannonPhoto;
    @FXML private ImageView infernoPhoto;
    public void initialize(){
        timer = new Timer();
        emptyCards = new ArrayList<>();
//        selectedCards = new ArrayList<>();
        selectedCardsImages = new HashMap<>();
        selectedCardsObjects = new HashMap<>();
        emptyCardsImages = new HashMap<>();
        emptyCardsImages.put("emptyCard1Photo", emptyCard1Photo);
        emptyCardsImages.put("emptyCard2Photo", emptyCard2Photo);
        emptyCardsImages.put("emptyCard3Photo", emptyCard3Photo);
        emptyCardsImages.put("emptyCard4Photo", emptyCard4Photo);
        emptyCardsImages.put("emptyCard5Photo", emptyCard5Photo);
        emptyCardsImages.put("emptyCard6Photo", emptyCard6Photo);
        emptyCardsImages.put("emptyCard7Photo", emptyCard7Photo);
        emptyCardsImages.put("emptyCard8Photo", emptyCard8Photo);
        emptyCards.add(emptyCard1);
        emptyCards.add(emptyCard2);
        emptyCards.add(emptyCard3);
        emptyCards.add(emptyCard4);
        emptyCards.add(emptyCard5);
        emptyCards.add(emptyCard6);
        emptyCards.add(emptyCard7);
        emptyCards.add(emptyCard8);
        for (Button b : emptyCards) {
            b.setDisable(true);
            b.setOpacity(0.5);
        }
    }
    @FXML public void selectArcher(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Archer();
            moveImageViewToIntendedEmptyCard(archerPhoto, card, archerCard, temp);
        }
    }
    @FXML public void selectDragon(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if(temp != null){
            Card card = new BabyDragon();
            moveImageViewToIntendedEmptyCard(dragonPhoto, card, dragonCard, temp);
        }
    }
    @FXML public void selectBarbarian(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Barbarian();
            moveImageViewToIntendedEmptyCard(barbarianPhoto, card, barbarianCard, temp);
        }
    }
    @FXML public void selectGiant(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Giant();
            moveImageViewToIntendedEmptyCard(giantPhoto, card, giantCard, temp);
        }
    }
    @FXML public void selectPekka(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new MiniPekka();
            moveImageViewToIntendedEmptyCard(pekkaPhoto, card, pekkaCard, temp);
        }
    }
    @FXML public void selectValkyrie(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Valkyrie();
            moveImageViewToIntendedEmptyCard(valkyriePhoto, card, valkyrieCard, temp);
        }
    }
    @FXML public void selectWizard(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Wizard();
            moveImageViewToIntendedEmptyCard(wizardPhoto, card, wizardCard, temp);
        }
    }
    @FXML public void selectArrows(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Arrows();
            moveImageViewToIntendedEmptyCard(arrowsPhoto, card, arrowsCard, temp);
        }
    }
    @FXML public void selectFireBall(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new FireBall();
            moveImageViewToIntendedEmptyCard(fireBallPhoto, card, fireBallCard, temp);
        }
    }
    @FXML public void selectRage(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Rage();
            moveImageViewToIntendedEmptyCard(ragePhoto, card, rageCard, temp);
        }
    }
    @FXML public void selectCannon(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new Cannon();
            moveImageViewToIntendedEmptyCard(cannonPhoto, card, cannonCard, temp);
        }
    }
    @FXML public void selectInferno(ActionEvent event){
        timer.cancel();
        Button temp = findFirstDisableEmptyCard();
        if (temp != null){
            Card card = new InfernoTower();
            moveImageViewToIntendedEmptyCard(infernoPhoto, card, infernoCard, temp);
        }
    }
    private void moveImageViewToIntendedEmptyCard(ImageView imageView, Card card,
                                                  Button button, Button intendedEmptyCard){
        selectedCardsImages.put(intendedEmptyCard, imageView);
        selectedCards.add(card);
        selectedCardsObjects.put(intendedEmptyCard, card);
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(imageView);
        tt.setToX(-1 * (imageView.getLayoutX() - intendedEmptyCard.getLayoutX() - 8));
        tt.setToY(-1 * (imageView.getLayoutY() - intendedEmptyCard.getLayoutY() - 4));
        tt.setDuration(Duration.seconds(0.5));
        tt.play();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                intendedEmptyCard.setDisable(false);
                intendedEmptyCard.setOpacity(1);
                emptyCardsImages.get(intendedEmptyCard.getId() + "Photo").setImage(imageView.getImage());
                button.setDisable(true);
                button.setOpacity(0.5);
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 500);
    }
    @FXML public void unselectCard1(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard1).getId();
        /*if (id.contains("archer")){
         *//*TranslateTransition tt = new TranslateTransition();
            tt.setNode(archerPhoto);
            tt.setToX(archerPhoto.getLayoutX() - archerCard.getLayoutX() - 6);
            tt.setToY(archerPhoto.getLayoutY() - archerCard.getLayoutY() - 4);
            tt.setDuration(Duration.seconds(0.5));
            tt.play();
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Archer) {
                    selectedCards.remove(card);
                    break;
                }
            }
            selectedCardsImages.remove(emptyCard1);
            selectedCardsObjects.remove(emptyCard1);
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    emptyCard1Photo.setImage(new Image(emptyCardImage));
                    emptyCard1.setDisable(true);
                    emptyCard1.setOpacity(0.5);
                    archerCard.setDisable(false);
                    archerCard.setOpacity(1);
                }
            };
            timer.schedule(timerTask, 500);*//*
            unselectIntendedCard(archerPhoto, archerCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Archer) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if(id.contains("dragon")){
            unselectIntendedCard(dragonPhoto, dragonCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof BabyDragon) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("barbarian")){
            unselectIntendedCard(barbarianPhoto, barbarianCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Barbarian) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("giant")){
            unselectIntendedCard(giantPhoto, giantCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Giant) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("pekka")){
            unselectIntendedCard(pekkaPhoto, pekkaCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof MiniPekka) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("valkyrie")){
            unselectIntendedCard(valkyriePhoto, valkyrieCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Valkyrie) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("wizard")){
            unselectIntendedCard(wizardPhoto, wizardCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Wizard) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("arrows")){
            unselectIntendedCard(arrowsPhoto, arrowsCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Arrows) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("fireBall")){
            unselectIntendedCard(fireBallPhoto, fireBallCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof FireBall) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("rage")){
            unselectIntendedCard(ragePhoto, rageCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Rage) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("cannon")){
            unselectIntendedCard(cannonPhoto, cannonCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Cannon) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (id.contains("inferno")){
            unselectIntendedCard(infernoPhoto, infernoCard, emptyCard1, emptyCard1Photo);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof InfernoTower) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }*/
        unselectViaEmptyCard(emptyCard1, emptyCard1Photo, id);
    }
    @FXML public void unselectCard2(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard2).getId();
        unselectViaEmptyCard(emptyCard2, emptyCard2Photo, id);
    }
    @FXML public void unselectCard3(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard3).getId();
        unselectViaEmptyCard(emptyCard3, emptyCard3Photo, id);
    }
    @FXML public void unselectCard4(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard4).getId();
        unselectViaEmptyCard(emptyCard4, emptyCard4Photo, id);
    }
    @FXML public void unselectCard5(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard5).getId();
        unselectViaEmptyCard(emptyCard5, emptyCard5Photo, id);
    }
    @FXML public void unselectCard6(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard6).getId();
        unselectViaEmptyCard(emptyCard6, emptyCard6Photo, id);
    }
    @FXML public void unselectCard7(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard7).getId();
        unselectViaEmptyCard(emptyCard7, emptyCard7Photo, id);
    }
    @FXML public void unselectCard8(ActionEvent event){
        String id = selectedCardsImages.get(emptyCard8).getId();
        unselectViaEmptyCard(emptyCard8, emptyCard8Photo, id);
    }
    @FXML public void backToMenu(ActionEvent event) {
        timer.cancel();
        try {
            currentUser.setDeck(selectedCards);
            currentUser.saveUser();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainMenu.fxml"));
            stage = (Stage) backButton.getScene().getWindow();
            root = loader.load();
            MainMenuController menuController = loader.getController();
            menuController.setCurrentUser(currentUser);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("main menu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void highlightBack(MouseEvent event){
        backButton.setVisible(false);
        backButton.setDisable(true);
        backButtonHighlighted.setVisible(true);
        backButtonHighlighted.setDisable(false);
    }
    @FXML public void unHighlightedBack(MouseEvent event){
        backButton.setVisible(true);
        backButton.setDisable(false);
        backButtonHighlighted.setVisible(false);
        backButtonHighlighted.setDisable(true);
    }
    private void unselectViaEmptyCard(Button intendedEmptyCard, ImageView intendedCardPhoto,
                                      String buttonId){
        if (buttonId.contains("archer")){
            /*TranslateTransition tt = new TranslateTransition();
            tt.setNode(archerPhoto);
            tt.setToX(archerPhoto.getLayoutX() - archerCard.getLayoutX() - 6);
            tt.setToY(archerPhoto.getLayoutY() - archerCard.getLayoutY() - 4);
            tt.setDuration(Duration.seconds(0.5));
            tt.play();
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Archer) {
                    selectedCards.remove(card);
                    break;
                }
            }
            selectedCardsImages.remove(emptyCard1);
            selectedCardsObjects.remove(emptyCard1);
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    emptyCard1Photo.setImage(new Image(emptyCardImage));
                    emptyCard1.setDisable(true);
                    emptyCard1.setOpacity(0.5);
                    archerCard.setDisable(false);
                    archerCard.setOpacity(1);
                }
            };
            timer.schedule(timerTask, 500);*/
            unselectIntendedCard(archerPhoto, archerCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Archer) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if(buttonId.contains("dragon")){
            unselectIntendedCard(dragonPhoto, dragonCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof BabyDragon) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("barbarian")){
            unselectIntendedCard(barbarianPhoto, barbarianCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Barbarian) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("giant")){
            unselectIntendedCard(giantPhoto, giantCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Giant) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("pekka")){
            unselectIntendedCard(pekkaPhoto, pekkaCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof MiniPekka) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("valkyrie")){
            unselectIntendedCard(valkyriePhoto, valkyrieCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Valkyrie) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("wizard")){
            unselectIntendedCard(wizardPhoto, wizardCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Wizard) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("arrows")){
            unselectIntendedCard(arrowsPhoto, arrowsCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Arrows) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("fireBall")){
            unselectIntendedCard(fireBallPhoto, fireBallCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof FireBall) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("rage")){
            unselectIntendedCard(ragePhoto, rageCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Rage) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("cannon")){
            unselectIntendedCard(cannonPhoto, cannonCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof Cannon) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
        else if (buttonId.contains("inferno")){
            unselectIntendedCard(infernoPhoto, infernoCard, intendedEmptyCard, intendedCardPhoto);
            Iterator<Card> cardIterator = selectedCards.iterator();
            while (cardIterator.hasNext()){
                Card card = cardIterator.next();
                if (card instanceof InfernoTower) {
                    selectedCards.remove(card);
                    break;
                }
            }
        }
    }
    private void unselectIntendedCard(ImageView imageView, Button intendedCard,
                                      Button intendedEmptyCard,
                                      ImageView intendedEmptyCardImage){
        TranslateTransition tt = new TranslateTransition();
        tt.setNode(imageView);
        tt.setToX(imageView.getLayoutX() - intendedCard.getLayoutX() - 6);
        tt.setToY(imageView.getLayoutY() - intendedCard.getLayoutY() - 4);
        tt.setDuration(Duration.seconds(0.5));
        tt.play();
        selectedCardsImages.remove(intendedEmptyCard);
        selectedCardsObjects.remove(intendedEmptyCard);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                intendedEmptyCardImage.setImage(new Image(emptyCardImage));
                intendedEmptyCard.setDisable(true);
                intendedEmptyCard.setOpacity(0.5);
                intendedCard.setDisable(false);
                intendedCard.setOpacity(1);
            }
        };
        timer.schedule(timerTask, 500);

    }
    private Button findFirstDisableEmptyCard(){
        Button temp = null;
        for (int i = 0; i < emptyCards.size(); i++) {
            if (emptyCards.get(i).isDisable()){
                temp = emptyCards.get(i);
                break;
            }
        }
        return temp;
    }
    public void setCurrentUser(User user) {
        this.currentUser = user;
        userCards = user.getDeck();
        selectedCards = userCards;
        System.out.println(userCards);
    }
}
