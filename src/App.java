import java.util.ArrayList;

public class App {
    private static ArrayList<Integer> cardListAfterTie = new ArrayList<Integer>();

    public static void main(String[] args) throws Exception {

        /**
         * Task 1: Create a shuffled deck of cards
         */
        Cards cards = new Cards("initial");
        Player player1 = new Player("Player 1", cards);
        Player player2 = new Player("Player 2", cards);

        do {
            /**
             * Task 2: Draw cards
             */
            drawCards(player1, player2);
            /**
             * Task 3: Playing a turn
             */
            if (player1.drawAcard() > player2.drawAcard()) {
                player1.winsRound(player2);

            } else if (player1.drawAcard() < player2.drawAcard()) {
                player2.winsRound(player1);
            } else {
                System.out.println("No winner in this round.\n");
                isTieSituation(player1, player2);
            }
        } while (!gameOver(player1, player2));

        printWinner(player1, player2);
    }

    private static void drawCards(Player player1, Player player2) {
        System.out.println(player1.toString() + " " + player1.drawAcard());
        System.out.println(player2.toString() + " " + player2.drawAcard());
    }

    private static void printWinner(Player player1, Player player2) {
        if (player1.getDrawPile().getSize() == 0 && player1.getDrawPile().getSize() == 0) {
            System.out.println(player2.name + " wins the game.");
            System.exit(0);
        } else if (player2.getDrawPile().getSize() == 0 && player2.getDrawPile().getSize() == 0) {
            System.out.println(player1.name + " wins the game.");
            System.exit(0);
        } else {}
    }

    private static boolean gameOver(Player player1, Player player2) {
        return (checkCurrentPlayerSituation(player1) || checkCurrentPlayerSituation(player2));
    }

    private static boolean checkCurrentPlayerSituation(Player player1) {
        return player1.getDrawPile().getSize() == 0 && player1.getDiscardPile().getSize() == 0;
    }

    private static void isTieSituation(Player player1, Player player2) {
        try {

            if (player1.drawAcard() != player2.drawAcard()) {
                drawCards(player1, player2);
                if (player1.drawAcard() > player2.drawAcard()) {

                    player1.getDiscardPile().getDeck().add(player1.drawAcard());
                    player1.getDiscardPile().getDeck().add(player2.drawAcard());
                    player1.getDrawPile().getDeck().remove(0);
                    player2.getDrawPile().getDeck().remove(0);
                    player1.getDiscardPile().getDeck().addAll(cardListAfterTie);
                    player1.getDiscardPile().shuffleIt();
                    cardListAfterTie.clear();
                    System.out.println(player1.name + " wins this round.\n");
                    return;

                } else {
                    player2.getDiscardPile().getDeck().add(player2.drawAcard());
                    player2.getDiscardPile().getDeck().add(player1.drawAcard());
                    player1.getDrawPile().getDeck().remove(0);
                    player2.getDrawPile().getDeck().remove(0);
                    player2.getDiscardPile().getDeck().addAll(cardListAfterTie);
                    player2.getDiscardPile().shuffleIt();
                    cardListAfterTie.clear();
                    System.out.println(player2.name + " wins this round.\n");
                    return;
                }
            }

            cardListAfterTie.add(player1.getDrawPile().getDeck().remove(0));
            cardListAfterTie.add(player2.getDrawPile().getDeck().remove(0));

            isTieSituation(player1, player2);

        } catch (Exception e) {
            printWinner(player1, player2); // print winner when one of the players have only one card left and that same player
                                           // does not win but scores a tie - then, since that player's only card is 
                                           // discarded from the deck, the player loses the game.
        }
    }

}
