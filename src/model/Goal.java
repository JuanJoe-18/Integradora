package model;

public class Goal {
    private String playerName;
    private String assistPlayerName;
    private  CardType cardType;


    public Goal(String playerName, String assistPlayerName) {
        this.playerName = playerName;
        this.assistPlayerName = assistPlayerName;
        this.cardType = cardType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAssistPlayerName() {
        return assistPlayerName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardType setCardType(CardType cardType) {
        return this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Goal by " + playerName + " assisted by " + assistPlayerName;
    }
}