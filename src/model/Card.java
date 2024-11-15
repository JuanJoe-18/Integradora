package model;

public class Card {
    private String teamName;
    private String playerName;
    private CardType cardType;


    public Card(String teamName, String playerName, CardType cardType) {
        this.teamName = teamName;
        this.playerName = playerName;
        this.cardType = cardType;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardType + " card for " + playerName + " from " + teamName;
    }
















}






