package model;

import java.time.LocalDate;
import java.util.*;

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
    /*
    SIERRA_LEONE,
    SINGAPORE,
    SLOVAKIA,
    SLOVENIA,
    SOLOMON_ISLANDS,
    SOMALIA,
    SOUTH_AFRICA,
    SOUTH_SUDAN,
    SPAIN,
    SRI_LANKA,
    SUDAN,
    SURINAME,
    SWEDEN,
    SWITZERLAND,
    SYRIA,
    TAIWAN,
    TAJIKISTAN,
    TANZANIA,
    THAILAND,
    TIMOR_LESTE,
    TOGO,
    TONGA,
    TRINIDAD_AND_TOBAGO,
    TUNISIA,
    TURKEY,
    TURKMENISTAN,
    TUVALU,
    UGANDA
    *
    *
    *
    * */

    public void preLoadReferees() {
        referees.add(new Referee("1", "Juan", "Colombia", "A"));
        referees.add(new Referee("2", "Pedro", "Spain", "A"));
        referees.add(new Referee("3", "Luis", "TUVALU", "A"));
        referees.add(new Referee("4", "Carlos", "TURKEY", "C"));
        referees.add(new Referee("5", "Jose", "TANZANIA", "A"));
        referees.add(new Referee("6", "Pep", "England", "C"));
        referees.add(new Referee("7", "Andrea", "Italy", "C"));
        referees.add(new Referee("8", "JuanJoe", "SUDAN", "C"));
        referees.add(new Referee("9", "Juanito", "UGANDA", "A"));
        referees.add(new Referee("10", "Pedrito", "URUGUAY", "A"));
        referees.add(new Referee("11", "Luisito", "TAIWAN", "A"));
        referees.add(new Referee("12", "Carlitos", "SLOVENIA", "A"));
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

    // TournamentController.java
    public boolean registerResult(String teamName1, String teamName2, int team1Goals, int team2Goals) {
        Match match = findMatch(teamName1, teamName2);
        if (match == null) {
            System.err.println("Error: Match not found");
            return false;
        }

        // Obtener los goles ya registrados
        int existingTeam1Goals = match.getTeam1Goals();
        int existingTeam2Goals = match.getTeam2Goals();

        // Actualizar los goles del partido
        match.setTeam1Goals(team1Goals);
        match.setTeam2Goals(team2Goals);

        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();

        // Actualizar las estadísticas de los equipos
        team1.setPlayedMatches(team1.getPlayedMatches() + 1);
        team2.setPlayedMatches(team2.getPlayedMatches() + 1);

        team1.setGoalsFor(team1.getGoalsFor() - existingTeam1Goals + team1Goals);
        team1.setGoalsAgainst(team1.getGoalsAgainst() - existingTeam2Goals + team2Goals);

        team2.setGoalsFor(team2.getGoalsFor() - existingTeam2Goals + team2Goals);
        team2.setGoalsAgainst(team2.getGoalsAgainst() - existingTeam1Goals + team1Goals);

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

        updatePlayerMatchesPlayed(team1);
        updatePlayerMatchesPlayed(team2);

        // Actualizar los goles de los jugadores
        updatePlayerGoals(team1, team1Goals - existingTeam1Goals);
        updatePlayerGoals(team2, team2Goals - existingTeam2Goals);

        return true;
    }

    private void updatePlayerMatchesPlayed(Team team) {
        for (Player player : team.getPlayers()) {
            player.setMatchesPlayed(player.getMatchesPlayed() + 1);
        }
    }

    private void updatePlayerGoals(Team team, int goals) {
        for (Player player : team.getPlayers()) {
            player.setGoalsScored(player.getGoalsScored() + goals);
        }
    }

    private Match findMatch(String teamName1, String teamName2) {
        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(teamName1) && match.getTeam2().getName().equalsIgnoreCase(teamName2))
                        || (match.getTeam1().getName().equalsIgnoreCase(teamName2) && match.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                    return match;
                }
            }
        }
        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(teamName1) && match.getTeam2().getName().equalsIgnoreCase(teamName2))
                        || (match.getTeam1().getName().equalsIgnoreCase(teamName2) && match.getTeam2().getName().equalsIgnoreCase(teamName1))) {
                    return match;
                }
            }
        }
        return null;
    }

    public boolean registerGoalByPlayer(String team1Name, String team2Name, int shirtNumber, String playerName, String assistPlayerName) {
        Match match = findMatchByTeams(team1Name, team2Name);
        if (match != null) {
            Player player = findPlayerByNameAndNumber(playerName, shirtNumber);
            if (player != null) {
                Goal goal = new Goal(playerName, assistPlayerName);
                match.addGoal(goal);
                player.incrementGoalsScored();
                System.out.println("Updated goals scored for player: " + playerName + " to " + player.getGoalsScored());
                return true;
            }
        }
        return false;
    }
    private Match findMatchByTeams(String team1Name, String team2Name) {
        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(team1Name) && match.getTeam2().getName().equalsIgnoreCase(team2Name)) ||
                        (match.getTeam1().getName().equalsIgnoreCase(team2Name) && match.getTeam2().getName().equalsIgnoreCase(team1Name))) {
                    return match;
                }
            }
        }
        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                if ((match.getTeam1().getName().equalsIgnoreCase(team1Name) && match.getTeam2().getName().equalsIgnoreCase(team2Name)) ||
                        (match.getTeam1().getName().equalsIgnoreCase(team2Name) && match.getTeam2().getName().equalsIgnoreCase(team1Name))) {
                    return match;
                }
            }
        }
        return null; // Match not found
    }
    private Player findPlayerByNameAndNumber(String playerName, int shirtNumber) {
        for (Team team : groupA.getTeams()) {
            for (Player player : team.getPlayers()) {
                if (player.getName().equalsIgnoreCase(playerName) && player.getShirtNumber() == shirtNumber) {
                    return player;
                }
            }
        }
        for (Team team : groupB.getTeams()) {
            for (Player player : team.getPlayers()) {
                if (player.getName().equalsIgnoreCase(playerName) && player.getShirtNumber() == shirtNumber) {
                    return player;
                }
            }
        }
        return null; // Player not found
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
                        if (match.getReferees().contains(referee)) {
                            match.addCard(new Card(teamName1, playerName, cardType, referee));
                            return true;
                        } else {
                            System.err.println("Error: Referee not assigned to the match");
                            return false;
                        }
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
        sb.append("Referee Card Index: ").append(calculateRefereeCardIndex()).append("\n");
        sb.append("-----------------------------------------------------------\n");
        return sb.toString();
    }

    public String calculateTopScorer() {
        String topScorer = "";
        int maxGoals = 0;
        HashMap<String, Integer> playerGoalsMap = new HashMap<>();

        // Recorre todos los días y partidos del grupo A
        for (Day day : groupA.getDays()) {
            System.out.println("Processing day in Group A: " + day);
            for (Match match : day.getMatches()) {
                System.out.println("Processing match in Group A: " + match);
                for (Goal goal : match.getGoals()) {
                    String playerName = goal.getPlayerName();
                    playerGoalsMap.put(playerName, playerGoalsMap.getOrDefault(playerName, 0) + 1);
                    System.out.println("Group A - Player: " + playerName + ", Goals: " + playerGoalsMap.get(playerName));
                }
            }
        }

        // Recorre todos los días y partidos del grupo B
        for (Day day : groupB.getDays()) {
            System.out.println("Processing day in Group B: " + day);
            for (Match match : day.getMatches()) {
                System.out.println("Processing match in Group B: " + match);
                for (Goal goal : match.getGoals()) {
                    String playerName = goal.getPlayerName();
                    playerGoalsMap.put(playerName, playerGoalsMap.getOrDefault(playerName, 0) + 1);
                    System.out.println("Group B - Player: " + playerName + ", Goals: " + playerGoalsMap.get(playerName));
                }
            }
        }

        // Encuentra el jugador con más goles
        for (Map.Entry<String, Integer> entry : playerGoalsMap.entrySet()) {
            if (entry.getValue() > maxGoals) {
                maxGoals = entry.getValue();
                topScorer = entry.getKey();
            }
        }

        System.out.println("Top Scorer: " + topScorer + " with " + maxGoals + " goals");
        return topScorer + " with " + maxGoals + " goals";
    }

    public String calculateFairPlayTeam() {
        Team fairPlayTeam = null;
        int minCards = Integer.MAX_VALUE;

        for (Team team : getAllTeams()) {
            int totalCards = team.getYellowCards() + team.getRedCards();
            if (totalCards < minCards) {
                minCards = totalCards;
                fairPlayTeam = team;
            }
        }

        return fairPlayTeam != null ? fairPlayTeam.getName() : "No teams available";
    }

    private List<Team> getAllTeams() {
        List<Team> allTeams = new ArrayList<>();
        allTeams.addAll(groupA.getTeams());
        allTeams.addAll(groupB.getTeams());
        return allTeams;
    }

    public double getTeamEfficiency(String teamName) {
        Team team = findTeamByName(teamName);
        if (team != null) {
            return (double) team.getWonMatches() / team.getPlayedMatches();
        }
        return -1; // Indicate that the team was not found
    }

    public double getPlayerEfficiency(String teamName, int shirtNumber, String playerName) {
        Team team = findTeamByName(teamName);
        if (team != null) {
            for (Player player : team.getPlayers()) {
                if (player.getShirtNumber() == shirtNumber && player.getName().equalsIgnoreCase(playerName)) {
                    System.out.println("Player found: " + playerName);
                    System.out.println("Matches played: " + player.getMatchesPlayed());
                    System.out.println("Goals scored: " + player.getGoalsScored());
                    return player.calculateEfficiency();
                }
            }
        }
        System.out.println("Player not found: " + playerName);
        return -1; // Indicate that the player was not found
    }

    private Team findTeamByName(String teamName) {
        for (Team team : groupA.getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        for (Team team : groupB.getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        return null;
    }

    public String calculateRefereeCardIndex() {
        String refereeWithMostCards = "";
        int maxCards = 0;
        HashMap<String, Integer> refereeCardsMap = new HashMap<>();

        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                for (Card card : match.getCards()) {
                    String refereeName = card.getReferee().getName();
                    refereeCardsMap.put(refereeName, refereeCardsMap.getOrDefault(refereeName, 0) + 1);
                }
            }
        }

        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                for (Card card : match.getCards()) {
                    String refereeName = card.getReferee().getName();
                    refereeCardsMap.put(refereeName, refereeCardsMap.getOrDefault(refereeName, 0) + 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : refereeCardsMap.entrySet()) {
            if (entry.getValue() > maxCards) {
                maxCards = entry.getValue();
                refereeWithMostCards = entry.getKey();
            }
        }

        return refereeWithMostCards + " with " + maxCards + " cards";
    }
    public String getCentralRefereeForMatch(String teamName1, String teamName2) {
        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                if (match.getTeam1().getName().equals(teamName1) && match.getTeam2().getName().equals(teamName2)) {
                    for (Referee referee : match.getReferees()) {
                        if (referee.getType().equals("C")) {
                            return referee.getName();
                        }
                    }
                }
            }
        }
        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                if (match.getTeam1().getName().equals(teamName1) && match.getTeam2().getName().equals(teamName2)) {
                    for (Referee referee : match.getReferees()) {
                        if (referee.getType().equals("C")) {
                            return referee.getName();
                        }
                    }
                }
            }
        }
        return null;
    }












}