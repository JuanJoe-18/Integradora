package model;

public class Goal {
    private String playerName;
    private String assistPlayerName;


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