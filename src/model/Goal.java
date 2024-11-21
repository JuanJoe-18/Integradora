package model;

public class Goal {

    private String playerName;
    private String assistPlayerName;


    /**
     * Description: Constructor to initialize a Goal object
     * post: A Goal object is created
     * @param playerName The name of the player who scored the goal
     * @param assistPlayerName The name of the player who assisted the goal
     */
    public Goal(String playerName, String assistPlayerName) {
        this.playerName = playerName;
        this.assistPlayerName = assistPlayerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getAssistPlayerName() {
        return assistPlayerName;
    }


    @Override
    public String toString() {
        return "Goal by " + playerName + " assisted by " + assistPlayerName;
    }
}