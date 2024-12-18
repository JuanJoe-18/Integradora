package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Group {

    private String name;
    private ArrayList<Team> teams;
    private ArrayList<Day> days;

    /**
     * Description: Constructor to initialize a Group object
     * @param name The name of the group
     */

    public Group(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
        this.days = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    /**
     * Description: This method preloads the group A with 4 teams
     * Pre: The group A is empty
     * Post: The group A has 4 teams
     * */
    public void preloadGroupA() {
        teams.add(new Team("America", "Colombia", "Juan"));
        teams.add(new Team("Nacional", "Colombia", "Pedro"));
        teams.add(new Team("Real Madrid", "Spain", "Luis"));
        teams.add(new Team("Barcelona", "Spain", "Carlos"));
    }

    /**
     * Description: This method preloads the group B with 4 teams
     * Pre: The group B is empty
     * Post: The group B has 4 teams
     * */
    public void preloadGroupB() {
        teams.add(new Team("Manchester United", "England", "Jose"));
        teams.add(new Team("Manchester City", "England", "Pep"));
        teams.add(new Team("Juventus", "Italy", "Andrea"));
        teams.add(new Team("Juniors Icesi", "Colombia", "JuanJoe"));
    }

    /**
     * Description: This method registers a team in the group
     * Pre: The team is not registered in the group
     * Post: The team is registered in the group
     * @param name The name of the team
     * @param country The country of the team
     * @param nameTechnicalDirector The name of the technical director of the team
     * @return boolean True if the team was registered successfully, false otherwise
     * */
    public boolean registerTeam(String name, String country, String nameTechnicalDirector) {
        for (Team team : teams) {
            if (team.getName().equals(name)) {
                return false; // Team already exists
            }
        }
        boolean validCountry = false;
        for (Country c : Country.values()) {
            if (c.name().equalsIgnoreCase(country)) {
                validCountry = true;
                break;
            }
        }
        if (!validCountry) {
            return false; // Country is not valid
        }
        Team team = new Team(name, country, nameTechnicalDirector);
        teams.add(team);
        return true;
    }


    /**
     * Description: This method builds the fixture for the group by generating all possible matches
     * between the teams, shuffling them, and distributing them across three match days.
     * Pre: The group has at least two teams.
     * Pos: The group has a fixture with three match days, each containing two matches.
     * @param startDate The start date for the first match day.
     */

    public void buildFixture(LocalDate startDate) {
        if (teams.size() < 4) {
            System.err.println("Each group must have exactly 4 teams.");
        }

        ArrayList<Match> matches = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                matches.add(new Match(teams.get(i), teams.get(j)));
            }
        }
        Collections.shuffle(matches);
        for (int i = 0; i < 3; i++) {
            Day day = new Day("Match Day " + (i + 1), startDate.plusDays(i * 10));
            days.add(day);
        }
        int matchIndex = 0;
        for (Day day : days) {
            for (int j = 0; j < 2; j++) {
                Match match = matches.get(matchIndex++);
                match.setDateTime(day.getDate().plusDays(j * 2).atStartOfDay()); // Ensure each match has a unique date
                day.addMatch(match);
            }
        }
    }

    /**
     * Description: This method shows the fixture of the group
     * pre: The group has a fixture
     * post: The fixture of the group is shown
     * @return string with the fixture of the group
     * */
    public String showFormattedFixture() {
        StringBuilder sb = new StringBuilder();
        for (Day day : days) {
            sb.append(day.getName()).append(" (").append(day.getDate()).append(")\n");
            for (Match match : day.getMatches()) {
                sb.append(match).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Description: This method calculates the standings of the group
     * pre: The group has played all matches
     * post: The teams in the group are sorted by points, goal difference, goals for, and goals against
     * */
    public void calculateStandings() {
        teams.sort(Comparator.comparingInt(Team::getPoints).reversed()
                .thenComparingInt(Team::getGoalDifference).reversed()
                .thenComparingInt(Team::getGoalsFor).reversed()
                .thenComparingInt(Team::getGoalsAgainst));
    }

    /**
     * Description: This method shows the standings of the group
     * pre: The group has played all matches
     * post: The standings of the group are shown
     * @return string with the standings of the group
     * */

    public String showStandings() {
        calculateStandings();
        StringBuilder sb = new StringBuilder();
        sb.append("Grupo ").append(name).append("\n");
        sb.append(String.format("%-20s %2s %2s %2s %2s %3s %3s %3s %4s\n", "CLUB", "PJ", "G", "E", "P", "GF", "GC", "DG", "Pts"));
        int position = 1;
        for (Team team : teams) {
            sb.append(String.format("%d. %-17s %2d %2d %2d %2d %3d %3d %3d %4d\n",
                    position++, team.getName(), team.getPlayedMatches(), team.getWonMatches(),
                    team.getDrawnMatches(), team.getLostMatches(), team.getGoalsFor(),
                    team.getGoalsAgainst(), team.getGoalDifference(), team.getPoints()));
        }
        return sb.toString();
    }

    /**
     * Description: This method returns the information of the group
     * @return  string with the information of the group
     * */
    @Override
    public String toString() {
        return "Group: " + name;
    }
}
