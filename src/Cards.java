import java.util.ArrayList;
import java.util.Collections;

public class Cards {
    ArrayList<Integer> cardDeck;

    private static final int initialNumbers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    public ArrayList<Integer> getDeck() {
        return cardDeck;
    }

    private void setDeck(ArrayList<Integer> setThisDeck) {
        this.cardDeck = setThisDeck;
    }

    public int getSize() {
        return this.getDeck().size();
    }

    public Cards(String s) {
        if (s.equals("initial")) {
            cardDeck = new ArrayList<Integer>();
            initialFillCards(cardDeck);
        }
        this.shuffleIt();
    }

    public Cards() {
        cardDeck = new ArrayList<Integer>();
    }

    public void initialFillCards(ArrayList<Integer> cards) {
        int count = 0;
        while (cards.size() != 40) {
            for (int i = 0; i < 4; i++) {
                cards.add(initialNumbers[count]);
            }
            count++;
        }
    }

    public void shuffleIt() { // Fisher-Yates Shuffle Algorithm from Java Collections
        Collections.shuffle(this.getDeck());
    }

    public Cards addDrawPile(Cards draw) {
        ArrayList<Integer> deck = new ArrayList<Integer>();
        Cards newDeck = new Cards();
        for (int i = 0; i < 20; i++) {
            deck.add(draw.getDeck().get(i));
        }
        newDeck.setDeck(deck);
        newDeck.shuffleIt();
        return newDeck;
    }

    public int drawAcard() {
        return this.cardDeck.get(0);
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < cardDeck.size(); i++)
            str += cardDeck.get(i) + " ";
        return str;
    }

}
