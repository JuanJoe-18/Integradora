package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Team {

    private String name;
    private String country;
    private String nameTechnicalDirector;
    private ArrayList<Player> players;

    /**
     * Description: Constructor to initialize a Team object
     * @param name The name of the team
     * @param country The country of the team
     * @param nameTechnicalDirector The name of the technical director of the team
     */
    public Team(String name, String country, String nameTechnicalDirector) {
        this.name = name;
        this.country = country;
        this.nameTechnicalDirector = nameTechnicalDirector;
        this.players = new ArrayList<Player>(20);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNameTechnicalDirector() {
        return nameTechnicalDirector;
    }

    public void setNameTechnicalDirector(String nameTechnicalDirector) {
        this.nameTechnicalDirector = nameTechnicalDirector;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Description: This method preloads the players of the teams with a random name, country and position
     * Pre: The teams are not empty
     * Post: The teams have 20 players each
     * @param teams The list of teams
     * */

    public void preLoadPlayers(List<Team> teams) {
        List<String> names = Arrays.asList("Juan", "Pedro", "Luis", "Carlos", "Jose", "Pep", "Andrea", "JuanJoe", "Mario", "Sergio", "Miguel", "Fernando", "Roberto", "Alberto", "Ricardo", "Javier", "Hector", "Pablo", "Diego", "Manuel");
        List<Country> countries = Arrays.asList(Country.values());
        List<Position> positions = Arrays.asList(Position.values());

        Random random = new Random();

        for (Team team : teams) {
            for (int i = 1; i <= 20; i++) {
                String name = names.get(random.nextInt(names.size()));
                Country country = countries.get(random.nextInt(countries.size()));
                Position position = positions.get(random.nextInt(positions.size()));
                team.getPlayers().add(new Player(i, name, country, position));
            }
        }
    }

    /**
     * Description: This method registers a player in the team
     * Pre: The player is not registered in the team
     * Post: The player is registered in the team
     * @param shirtNumber The shirt number of the player
     * @param name The name of the player
     * @param country The country of the player
     * @param position The position of the player
     * @return A boolean that indicates if the player was registered successfully
     * */

    public boolean registerPlayer(int shirtNumber, String name, Country country, Position position) {
        for (Player player : players) {
            if (player.getShirtNumber() == shirtNumber) {
                System.err.println("Error: Player with shirt number " + shirtNumber + " already exists");
                return false; // Player with the same shirt number already exists
            }
        }
        boolean validCountry = false;
        for (Country c : Country.values()) {
            if (c.name().equalsIgnoreCase(country.name())) {
                validCountry = true;
                break;
            }
        }
        if (!validCountry) {
            System.err.println("Error: Country " + country + " is not valid");
            return false; // Country is not valid
        }

        boolean validPosition = false;
        for (Position p : Position.values()) {
            if (p.name().equalsIgnoreCase(position.name())) {
                validPosition = true;
                break;
            }
        }
        if (!validPosition) {
            System.err.println("Error: Position " + position + " is not valid");
            return false; // Position is not valid
        }
        Player player = new Player(shirtNumber, name, country, position);
        players.add(player);
        return true;
    }

    /**
     * Description: This method returns the information of the team
     * @return  string with the information of the team
     * */
    public String toString() {
        return "Team: " + name + " - Country: " + country + " - Technical Director: " + nameTechnicalDirector;
    }

}
