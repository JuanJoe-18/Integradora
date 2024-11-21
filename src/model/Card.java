package model;

public class Card {
    private String teamName;
    private String playerName;
    private CardType cardType;
    private Referee referee;

    /**
     * Description: Constructor to initialize a Card object
     * post: A Card object is created
     * @param teamName The name of the team
     * @param playerName The name of the player
     * @param cardType The type of card
     * @param referee The referee who gave the card
     */
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

    /**
     * Description: This method returns a string representation of the Card object
     * post: A string representation of the Card object is returned
     * @return A string representation of the Card object
     */
    @Override
    public String toString() {
        return cardType + " card for " + playerName + " from " + teamName + " by " + referee.getName();
    }
















}






