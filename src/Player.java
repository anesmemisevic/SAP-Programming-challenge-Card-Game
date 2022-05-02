public class Player {
    String name;
    Cards drawPile;
    Cards discardPile;
    Cards newDeck = new Cards(); // helper deck

    public Player(String name, Cards deck) {
        this.name = name;
        drawPile = new Cards();
        discardPile = new Cards();
        addDrawPile(deck);
    }

    public String getName() {
        return this.name;
    }

    public Cards getDrawPile() {
        return this.drawPile;
    }

    public Cards getDiscardPile() {
        return this.discardPile;
    }

    private void addDrawPile(Cards draw) {
        Cards newDeck = new Cards();
        newDeck = newDeck.addDrawPile(draw);
        this.drawPile = newDeck;
    }

    public int drawAcard() {
        return this.drawFromDrawPile();
    }

    private int drawFromDrawPile() {
        newDeck = this.discardPile;
        if (this.drawPile.getSize() == 0) {
            for (int i = 0; i < newDeck.getSize(); i++) {
                this.drawPile.getDeck().add(newDeck.getDeck().get(i));
            }
            this.discardPile.getDeck().clear(); // assert empty
            return this.drawPile.getDeck().get(0);
        }
        return this.drawPile.getDeck().get(0);
    }

    public void winsRound(Player player2) {
        System.out.println(this.name + " wins this round.\n");
        this.getDiscardPile().getDeck().add(this.drawAcard());
        this.getDiscardPile().getDeck().add(player2.drawAcard());
        this.getDrawPile().getDeck().remove(0);
        player2.getDrawPile().getDeck().remove(0);
    }


    public String toStringTestFull() { // use for full testing -- to see where and how are the cards added / deleted
        // e.g.: System.out.println(player1.toStringTestFull() + " " + player1.drawAcard());

        int size = this.discardPile.getSize() + this.drawPile.getSize();
        String name = this.name + ": ";
        String str = "draw pile: " + this.drawPile.toString() + ", size:" + this.drawPile.getSize();

        str += " discard pile : " + this.discardPile.toString() + ", size: " + this.discardPile.getSize()
                + ", full size: " + size;
        return name + str;
    }

    public String toString() {
        int size = this.discardPile.getSize() + this.drawPile.getSize();
        String str = this.name + " (" + (size) + " cards):";
        return str;
    }
}
