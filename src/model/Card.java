package model;

public class Card {
    private String teamName;
    private String playerName;
    private CardType cardType;
    private Referee referee;


    public Card(String teamName, String playerName, CardType cardType, Referee referee) {
        this.teamName = teamName;
        this.playerName = playerName;
        this.cardType = cardType;
        this.referee = referee;
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

    public Referee getReferee() {
        return referee;
    }

    @Override
    public String toString() {
        return cardType + " card for " + playerName + " from " + teamName + " by " + referee.getName();
    }
















}






