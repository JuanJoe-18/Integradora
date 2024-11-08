package model;

public class Player {

    private int shirtNumber;
    private String name;
    private Country country;
    private Position position;

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

    /**
     * Description: This method returns a string representation of the Player object.
     * @return string with the player's shirt number, name, country, and position.
     */
    public String toString() {
        return "Shirt number: " + shirtNumber + ", Name: " + name + ", Country: " + country + ", Position: " + position;
    }

}
