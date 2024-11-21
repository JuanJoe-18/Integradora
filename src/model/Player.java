package model;

public class Player {

    private int shirtNumber;
    private String name;
    private Country country;
    private Position position;
    private int matchesPlayed;
    private int goalsScored;

    /**
     * Description: Constructor to initialize a Player object
     * @param shirtNumber The shirt number of the player
     * @param name The name of the player
     * @param country The country of the player
     * @param position The position of the player
     */
    public Player(int shirtNumber, String name, Country country, Position position) {
        this.shirtNumber = shirtNumber;
        this.name = name;
        this.country = country;
        this.position = position;
        this.matchesPlayed = 0;
        this.getGoalsScored();
    }

    public int getShirtNumber() {
        return shirtNumber;
    }

    public void setShirtNumber(int shirtNumber) {
        this.shirtNumber = shirtNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed){
        this.matchesPlayed = matchesPlayed;
    }
    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public double calculateEfficiency() {
        System.out.println("Calculating efficiency for player: " + name);
        System.out.println("Matches played: " + matchesPlayed);
        System.out.println("Goals scored: " + goalsScored);
        return matchesPlayed > 0 ? (double) goalsScored / matchesPlayed : 0;
    }

    public void incrementGoalsScored() {
        this.goalsScored++;
        System.out.println("Updated goals scored for player: " + name + " to " + goalsScored);
    }

    public void incrementMatchesPlayed() {
        this.matchesPlayed++;
        System.out.println("Updated matches played for player: " + name + " to " + matchesPlayed);
    }

    /**
     * Description: This method returns a string representation of the Player object.
     * @return string with the player's shirt number, name, country, and position.
     */
    public String toString() {
        return "Shirt number: " + shirtNumber + ", Name: " + name + ", Country: " + country + ", Position: " + position;
    }

}
