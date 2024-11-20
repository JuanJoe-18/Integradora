package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class TournamentController {

    public Group groupA;
    public Group groupB;
    private ArrayList<Referee> referees;
    private LocalDate startDate;
    private LocalDate endDate;


    /**
     * Description: The TournamentController class has a constructor that initializes
     * the referees list and the groupA and groupB objects.
     * pre: referees must be declared.
     * pre: groupA must be declared.
     * pre: groupB must be declared.
     * pos: the referees list is initialized.
     * pos: the groupA object is initialized.
     * pos: the groupB object is initialized.
     */

    public TournamentController() {
        referees = new ArrayList<Referee>(12);
        groupA = new Group("Group A");
        groupB = new Group("Group B");

    }

    /**
     * Description: The preLoadData method is responsible for preloading the players of the teams that are part of the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: the players of the teams that are part of the tournament are preloaded.
     */

    public void preLoadData() {
        if (groupA.getTeams().size() >= 4) {
            for (int i = 0; i < 4; i++) {
                groupA.getTeams().get(i).preLoadPlayers(groupA.getTeams());
            }
        }
        if (groupB.getTeams().size() >= 4) {
            for (int i = 0; i < 4; i++) {
                groupB.getTeams().get(i).preLoadPlayers(groupB.getTeams());
            }
        }
        preLoadReferees();
    }

    /**
     * Description: The registerTournament method is responsible for registering the start and end date of the tournament.
     * pre: startDate must be declared.
     * pre: endDate must be declared.
     * pos: the start date of the tournament is registered.
     * pos: the end date of the tournament is registered.
     * pos: the groups are initialized.
     * pos: the data is preloaded.
     * pos: true is returned if the tournament is registered successfully.
     * pos: false is returned if the start date is after the end date or the duration is less than one month.
     *
     * @param startDate, String with the start date of the tournament.
     * @param endDate,   String with the end date of the tournament.
     * @return boolean, true if the tournament is registered successfully, false if the start date is after the end date or the duration is less than one month.
     */

    public boolean registerTournament(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);

        if (this.startDate.isAfter(this.endDate) || this.startDate.plusMonths(1).isAfter(this.endDate)) {
            return false; // Start date is after end date or duration is less than one month
        }
        initializeGroups();
        preLoadData();
        return true;
    }

    /**
     * Description: The initializeGroups method is responsible for preloading the teams of the groups that are part of the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: the teams of the groups that are part of the tournament are preloaded.
     */

    public void initializeGroups() {
        groupA.preloadGroupA();
        groupB.preloadGroupB();
        groupA.buildFixture(startDate);
        groupB.buildFixture(startDate);
    }

    /**
     * Description: The preLoadReferees method is responsible for preloading the referees that are part of the tournament.
     * pre: referees must be initialized.
     * pos: the referees that are part of the tournament are preloaded.
     */

    public void preLoadReferees() {
        referees.add(new Referee("1", "Juan", "Colombia", "C"));
        referees.add(new Referee("2", "Pedro", "Spain", "C"));
        referees.add(new Referee("3", "Luis", "Colombia", "A"));
        referees.add(new Referee("4", "Carlos", "Spain", "A"));
        referees.add(new Referee("5", "Jose", "England", "C"));
        referees.add(new Referee("6", "Pep", "England", "C"));
        referees.add(new Referee("7", "Andrea", "Italy", "C"));
        referees.add(new Referee("8", "JuanJoe", "Colombia", "A"));
        referees.add(new Referee("9", "Juanito", "Colombia", "C"));
        referees.add(new Referee("10", "Pedrito", "Spain", "C"));
        referees.add(new Referee("11", "Luisito", "Colombia", "A"));
        referees.add(new Referee("12", "Carlitos", "Spain", "C"));
    }

    /**
     * Description: The preLoadRefereesToMatches method is responsible for preloading the referees to the matches that are part of the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pre: referees must be initialized.
     * pos: the referees are preloaded to the matches that are part of the tournament.
     */

    public void preLoadRefereesToMatches() {
        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                match.setReferees(referees);
            }
        }
        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                match.setReferees(referees);
            }
        }
    }

    /**
     * Description: The registerTeam method is responsible for registering a team in the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: true is returned if the team is registered successfully.
     * pos: false is returned if both groups are full.
     *
     * @param name,                  String with the name of the team.
     * @param country,               String with the country of the team.
     * @param nameTechnicalDirector, String with the name of the technical director of the team.
     * @return boolean, true if the team is registered successfully, false if both groups are full.
     */

    public boolean registerTeam(String name, String country, String nameTechnicalDirector) {
        if (groupA.getTeams().size() < 4) {
            return groupA.registerTeam(name, country, nameTechnicalDirector);
        } else if (groupB.getTeams().size() < 4) {
            return groupB.registerTeam(name, country, nameTechnicalDirector);
        }
        System.err.println("Error: Both groups are full");
        return false; // Both groups are full
    }

    /**
     * Description: The registerPlayer method is responsible for registering a player in a team of the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: true is returned if the player is registered successfully.
     * pos: false is returned if the team is not found.
     *
     * @param shirtNumber, int with the shirt number of the player.
     * @param teamName,    String with the name of the team.
     * @param playerName,  String with the name of the player.
     * @param country,     String with the country of the player.
     * @param position,    String with the position of the player.
     * @return boolean, true if the player is registered successfully, false if the team is not found.
     */

    public boolean registerPlayer(int shirtNumber, String teamName, String playerName, String country, String position) {
        Country countryEnum = null;
        Position positionEnum = null;

        for (Country c : Country.values()) {
            if (c.name().equalsIgnoreCase(country)) {
                countryEnum = c;
                break;
            }
        }

        for (Position p : Position.values()) {
            if (p.name().equalsIgnoreCase(position)) {
                positionEnum = p;
                break;
            }
        }

        if (countryEnum == null || positionEnum == null) {
            return false; // Invalid country or position
        }

        for (Team team : groupA.getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team.registerPlayer(shirtNumber, playerName, countryEnum, positionEnum);
            }
        }
        for (Team team : groupB.getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team.registerPlayer(shirtNumber, playerName, countryEnum, positionEnum);
            }
        }
        return false; // Team not found
    }

    /**
     * Description: The registerReferee method is responsible for registering a referee in the tournament.
     * pre: referees must be initialized.
     * pos: true is returned if the referee is registered successfully.
     * pos: false is returned if the country is not valid, the type is not valid or the referee already exists.
     *
     * @param id,      String with the id of the referee.
     * @param name,    String with the name of the referee.
     * @param country, String with the country of the referee.
     * @param type,    String with the type of the referee.
     * @return boolean, true if the referee is registered successfully, false if the country is not valid, the type is not valid or the referee already exists.
     */

    public boolean registerReferee(String id, String name, String country, String type) {
        // Check if the country is valid
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
        // Check if the type is valid
        switch (type.toUpperCase()) {
            case "C":
            case "A":
                break;
            default:
                return false; // Type isn't valid
        }
        // Check if the referee already exists
        for (Referee referee : referees) {
            if (referee.getId().equals(id)) {
                return false; // Referee already exists
            }
        }
        referees.add(new Referee(id, name, country, type));
        return true;
    }

    /**
     * Description: The showFixture method is responsible for showing the fixture of the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: the fixture of the tournament is shown.
     * pos: the referees are preloaded to the matches.
     *
     * @return String, a message with the fixture of the tournament.
     */

    public String showFixture() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------\n");
        sb.append("//////////////////////////Fixture//////////////////////////\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append("| Grupo A |\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append(groupA.showFormattedFixture());
        sb.append("-----------------------------------------------------------\n");
        sb.append("| Grupo B |\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append(groupB.showFormattedFixture());
        sb.append("-----------------------------------------------------------\n");

        preLoadRefereesToMatches();
        return sb.toString();
    }

    /**
     * Description: The assignRefereeToMatch method is responsible for assigning a referee to a match of the tournament.
     * pre: referees must be initialized.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: true is returned if the referee is assigned to the match successfully.
     * pos: false is returned if the referee is not found, the match is not found or the referee is from the same country as either team.
     *
     * @param refereeId, String with the id of the referee.
     * @param teamName1, String with the name of the first team.
     * @param teamName2, String with the name of the second team.
     * @return boolean, true if the referee is assigned to the match successfully, false if the referee is not found, the match is not found or the referee is from the same country as either team.
     */

    public boolean assignRefereeToMatch(String refereeId, String teamName1, String teamName2) {
        Referee referee = null;
        for (Referee r : referees) {
            if (r.getId().equals(refereeId)) {
                referee = r;
                break;
            }
        }
        if (referee == null) {
            System.err.println("Error: Referee not found");
            return false;
        }
        Match match = null;
        for (Day day : groupA.getDays()) {
            for (Match m : day.getMatches()) {
                if ((m.getTeam1().getName().equalsIgnoreCase(teamName1) && m.getTeam2().getName().equalsIgnoreCase(teamName2))
                        || (m.getTeam1().getName().equalsIgnoreCase(teamName2) && m.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                    match = m;
                    break;
                }
            }
        }
        if (match == null) {
            for (Day day : groupB.getDays()) {
                for (Match m : day.getMatches()) {
                    if ((m.getTeam1().getName().equalsIgnoreCase(teamName1) && m.getTeam2().getName().equalsIgnoreCase(teamName2))
                            || (m.getTeam1().getName().equalsIgnoreCase(teamName2) && m.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                        match = m;
                        break;
                    }
                }
            }
        }
        if (match == null) {
            System.err.println("Error: Match not found");
            return false;
        }
        if (referee.getCountry().equalsIgnoreCase(match.getTeam1().getCountry()) || referee.getCountry().equalsIgnoreCase(match.getTeam2().getCountry())) {
            System.err.println("Error: Referee cannot be from the same country as either team");
            return false;
        }
        match.setReferees(referees);
        return true;
    }

    /**
     * Description: The registerResult method is responsible for registering the result of a match of the tournament.
     * pre: groupA must be initialized.
     * pre: groupB must be initialized.
     * pos: true is returned if the result of the match is registered successfully.
     * pos: false is returned if the match is not found.
     *
     * @param teamName1,  String with the name of the first team.
     * @param teamName2,  String with the name of the second team.
     * @param team1Goals, int with the goals of the first team.
     * @param team2Goals, int with the goals of the second team.
     * @return boolean, true if the result of the match is registered successfully, false if the match is not found.
     */

    public boolean registerResult(String teamName1, String teamName2, int team1Goals, int team2Goals) {
        Match match = null;
        for (Day day : groupA.getDays()) {
            for (Match m : day.getMatches()) {
                if ((m.getTeam1().getName().equalsIgnoreCase(teamName1) && m.getTeam2().getName().equalsIgnoreCase(teamName2))
                        || (m.getTeam1().getName().equalsIgnoreCase(teamName2) && m.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                    match = m;
                    break;
                }
            }
        }
        if (match == null) {
            for (Day day : groupB.getDays()) {
                for (Match m : day.getMatches()) {
                    if ((m.getTeam1().getName().equalsIgnoreCase(teamName1) && m.getTeam2().getName().equalsIgnoreCase(teamName2))
                            || (m.getTeam1().getName().equalsIgnoreCase(teamName2) && m.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                        match = m;
                        break;
                    }
                }
            }
        }
        if (match == null) {
            System.err.println("Error: Match not found");
            return false;
        }
        match.setTeam1Goals(team1Goals);
        match.setTeam2Goals(team2Goals);

        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();

        team1.setPlayedMatches(team1.getPlayedMatches() + 1);
        team2.setPlayedMatches(team2.getPlayedMatches() + 1);

        team1.setGoalsFor(team1.getGoalsFor() + team1Goals);
        team1.setGoalsAgainst(team1.getGoalsAgainst() + team2Goals);

        team2.setGoalsFor(team2.getGoalsFor() + team2Goals);
        team2.setGoalsAgainst(team2.getGoalsAgainst() + team1Goals);

        if (team1Goals > team2Goals) {
            team1.setWonMatches(team1.getWonMatches() + 1);
            team2.setLostMatches(team2.getLostMatches() + 1);
            team1.setPoints(team1.getPoints() + 3);
        } else if (team1Goals < team2Goals) {
            team2.setWonMatches(team2.getWonMatches() + 1);
            team1.setLostMatches(team1.getLostMatches() + 1);
            team2.setPoints(team2.getPoints() + 3);
        } else {
            team1.setDrawnMatches(team1.getDrawnMatches() + 1);
            team2.setDrawnMatches(team2.getDrawnMatches() + 1);
            team1.setPoints(team1.getPoints() + 1);
            team2.setPoints(team2.getPoints() + 1);
        }

        return true;
    }

    public boolean registerGoalByPlayer(String team1Name, String team2Name, int shirtNumber, String playerName, String assistPlayerName) {
        Goal goal = new Goal(playerName, assistPlayerName);
        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(team1Name) && match.getTeam2().getName().equalsIgnoreCase(team2Name)) ||
                        (match.getTeam1().getName().equalsIgnoreCase(team2Name) && match.getTeam2().getName().equalsIgnoreCase(team1Name))) {

                    if (!match.getTeam1().hasPlayer(playerName) && !match.getTeam2().hasPlayer(playerName)) {
                        System.err.println("Error: Player " + playerName + " is not in the match");
                        return false;
                    }

                    match.addGoal(goal);

                    // Update team statistics
                    Team team1 = match.getTeam1();
                    Team team2 = match.getTeam2();

                    if (match.getTeam1().getName().equalsIgnoreCase(team1Name)) {
                        team1.setGoalsFor(team1.getGoalsFor() + 1);
                        team2.setGoalsAgainst(team2.getGoalsAgainst() + 1);
                    } else {
                        team2.setGoalsFor(team2.getGoalsFor() + 1);
                        team1.setGoalsAgainst(team1.getGoalsAgainst() + 1);
                    }

                    return true;
                }
            }
        }
        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(team1Name) && match.getTeam2().getName().equalsIgnoreCase(team2Name)) ||
                        (match.getTeam1().getName().equalsIgnoreCase(team2Name) && match.getTeam2().getName().equalsIgnoreCase(team1Name))) {

                    if (!match.getTeam1().hasPlayer(playerName) && !match.getTeam2().hasPlayer(playerName)) {
                        System.err.println("Error: Player " + playerName + " is not in the match");
                        return false;
                    }

                    match.addGoal(goal);

                    // Update team statistics
                    Team team1 = match.getTeam1();
                    Team team2 = match.getTeam2();

                    if (match.getTeam1().getName().equalsIgnoreCase(team1Name)) {
                        team1.setGoalsFor(team1.getGoalsFor() + 1);
                        team2.setGoalsAgainst(team2.getGoalsAgainst() + 1);
                    } else {
                        team2.setGoalsFor(team2.getGoalsFor() + 1);
                        team1.setGoalsAgainst(team1.getGoalsAgainst() + 1);
                    }

                    return true;
                }
            }
        }
        return false; // Match not found
    }

    public boolean registerCardtoPlayer(String teamName1, String teamName2, String playerName, CardType cardType, String refereeName) {
        Referee referee = null;
        for (Referee r : referees) {
            if (r.getName().equalsIgnoreCase(refereeName)) {
                referee = r;
                break;
            }
        }
        if (referee == null) {
            System.err.println("Error: Referee not found");
            return false;
        }

        if (registerCard(groupA, teamName1, teamName2, playerName, cardType, referee)) {
            return true;
        }

        if (registerCard(groupB, teamName1, teamName2, playerName, cardType, referee)) {
            return true;
        }

        System.err.println("Error: Match not found");
        return false;
    }

    private boolean registerCard(Group group, String teamName1, String teamName2, String playerName, CardType cardType, Referee referee) {
        for (Day day : group.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(teamName1) && match.getTeam2().getName().equalsIgnoreCase(teamName2)) ||
                        (match.getTeam1().getName().equalsIgnoreCase(teamName2) && match.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                    if (match.getTeam1().hasPlayer(playerName) || match.getTeam2().hasPlayer(playerName)) {
                        match.addCard(new Card(teamName1, playerName, cardType, referee));
                        return true;
                    } else {
                        System.err.println("Error: Player not found in the match teams");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public String showStandings() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------\n");
        sb.append("//////////////////////////Standings////////////////////////\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append("| Grupo A |\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append(groupA.showStandings());
        sb.append("-----------------------------------------------------------\n");
        sb.append("| Grupo B |\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append(groupB.showStandings());
        sb.append("-----------------------------------------------------------\n");
        return sb.toString();
    }

    public String showStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------------------------------------------------\n");
        sb.append("//////////////////////////Statistics////////////////////////\n");
        sb.append("-----------------------------------------------------------\n");
        sb.append("Top Scorer: ").append(calculateTopScorer()).append("\n");
        sb.append("Fair Play Team: ").append(calculateFairPlayTeam()).append("\n");
        sb.append("Team Efficiency: ").append(calculateTeamEfficiency()).append("\n");
        sb.append("Player Efficiency: ").append(calculatePlayerEfficiency()).append("\n");
        sb.append("Referee Card Index: ").append(calculateRefereeCardIndex()).append("\n");
        sb.append("-----------------------------------------------------------\n");
        return sb.toString();
    }
    private String calculateTopScorer() {
        String topScorer = "";
        int maxGoals = 0;

        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                for (Goal goal : match.getGoals()) {
                    int playerGoals = 0;
                    for (Day d : groupA.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Goal g : m.getGoals()) {
                                if (g.getPlayerName().equals(goal.getPlayerName())) {
                                    playerGoals++;
                                }
                            }
                        }
                    }
                    for (Day d : groupB.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Goal g : m.getGoals()) {
                                if (g.getPlayerName().equals(goal.getPlayerName())) {
                                    playerGoals++;
                                }
                            }
                        }
                    }
                    if (playerGoals > maxGoals) {
                        maxGoals = playerGoals;
                        topScorer = goal.getPlayerName();
                    }
                }
            }
        }

        return topScorer + " with " + maxGoals + " goals";
    }
    private String calculateFairPlayTeam() {
        String fairPlayTeam = "";
        int minCards = Integer.MAX_VALUE;

        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                for (Card card : match.getCards()) {
                    int teamCards = 0;
                    for (Day d : groupA.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Card c : m.getCards()) {
                                if (c.getTeamName().equals(card.getTeamName())) {
                                    teamCards++;
                                }
                            }
                        }
                    }
                    for (Day d : groupB.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Card c : m.getCards()) {
                                if (c.getTeamName().equals(card.getTeamName())) {
                                    teamCards++;
                                }
                            }
                        }
                    }
                    if (teamCards < minCards) {
                        minCards = teamCards;
                        fairPlayTeam = card.getTeamName();
                    }
                }
            }
        }

        return fairPlayTeam + " with " + minCards + " cards";
    }
    private String calculateTeamEfficiency() {
        String mostEfficientTeam = "";
        double maxEfficiency = 0.0;

        for (Team team : groupA.getTeams()) {
            double efficiency = (double) team.getGoalsFor() / team.getPlayedMatches();
            if (efficiency > maxEfficiency) {
                maxEfficiency = efficiency;
                mostEfficientTeam = team.getName();
            }
        }

        for (Team team : groupB.getTeams()) {
            double efficiency = (double) team.getGoalsFor() / team.getPlayedMatches();
            if (efficiency > maxEfficiency) {
                maxEfficiency = efficiency;
                mostEfficientTeam = team.getName();
            }
        }

        return mostEfficientTeam + " with an efficiency of " + maxEfficiency;
    }

    private String calculatePlayerEfficiency() {
        String mostEfficientPlayer = "";
        double maxEfficiency = 0.0;

        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                for (Goal goal : match.getGoals()) {
                    int playerGoals = 0;
                    int playerMatches = 0;
                    for (Day d : groupA.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Goal g : m.getGoals()) {
                                if (g.getPlayerName().equals(goal.getPlayerName())) {
                                    playerGoals++;
                                }
                            }
                            if (m.hasPlayer(goal.getPlayerName())) {
                                playerMatches++;
                            }
                        }
                    }
                    for (Day d : groupB.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Goal g : m.getGoals()) {
                                if (g.getPlayerName().equals(goal.getPlayerName())) {
                                    playerGoals++;
                                }
                            }
                            if (m.hasPlayer(goal.getPlayerName())) {
                                playerMatches++;
                            }
                        }
                    }
                    double efficiency = (double) playerGoals / playerMatches;
                    if (efficiency > maxEfficiency) {
                        maxEfficiency = efficiency;
                        mostEfficientPlayer = goal.getPlayerName();
                    }
                }
            }
        }

        return mostEfficientPlayer + " with an efficiency of " + maxEfficiency;
    }

    private String calculateRefereeCardIndex() {
        String refereeWithMostCards = "";
        int maxCards = 0;

        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                for (Card card : match.getCards()) {
                    int refereeCards = 0;
                    for (Day d : groupA.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Card c : m.getCards()) {
                                if (c.getReferee().getId().equals(card.getReferee().getId())) {
                                    refereeCards++;
                                }
                            }
                        }
                    }
                    for (Day d : groupB.getDays()) {
                        for (Match m : d.getMatches()) {
                            for (Card c : m.getCards()) {
                                if (c.getReferee().getId().equals(card.getReferee().getId())) {
                                    refereeCards++;
                                }
                            }
                        }
                    }
                    if (refereeCards > maxCards) {
                        maxCards = refereeCards;
                        refereeWithMostCards = card.getReferee().getId();
                    }
                }
            }
        }

        return refereeWithMostCards + " with " + maxCards + " cards";
    }










}