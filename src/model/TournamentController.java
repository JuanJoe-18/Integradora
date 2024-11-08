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
     * */

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
     * */

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
     * @param startDate, String with the start date of the tournament.
     * @param endDate, String with the end date of the tournament.
     * @return boolean, true if the tournament is registered successfully, false if the start date is after the end date or the duration is less than one month.
     * */

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
     * */

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
     * */

    public void preLoadReferees(){
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
     * */

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
     * @param name, String with the name of the team.
     * @param country, String with the country of the team.
     * @param nameTechnicalDirector, String with the name of the technical director of the team.
     * @return boolean, true if the team is registered successfully, false if both groups are full.
     * */

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
     * @param shirtNumber, int with the shirt number of the player.
     * @param teamName, String with the name of the team.
     * @param playerName, String with the name of the player.
     * @param country, String with the country of the player.
     * @param position, String with the position of the player.
     * @return boolean, true if the player is registered successfully, false if the team is not found.
     * */

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
                boolean result = team.registerPlayer(shirtNumber, playerName, countryEnum, positionEnum);
                if (!result) {
                    System.err.println("Error: Player could not be registered in Group A team " + teamName);
                }
                return result;
            }
        }
        for (Team team : groupB.getTeams()) {
            if (team.getName().equalsIgnoreCase(teamName)) {
                boolean result = team.registerPlayer(shirtNumber, playerName, countryEnum, positionEnum);
                if (!result) {
                    System.err.println("Error: Player could not be registered in Group B team " + teamName);
                }
                return result;
            }
        }
        System.err.println("Error: Team " + teamName + " not found");
        return false; // Team not found
    }

    /**
     * Description: The registerReferee method is responsible for registering a referee in the tournament.
     * pre: referees must be initialized.
     * pos: true is returned if the referee is registered successfully.
     * pos: false is returned if the country is not valid, the type is not valid or the referee already exists.
     * @param id, String with the id of the referee.
     * @param name, String with the name of the referee.
     * @param country, String with the country of the referee.
     * @param type, String with the type of the referee.
     * @return boolean, true if the referee is registered successfully, false if the country is not valid, the type is not valid or the referee already exists.
     * */

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
     * @return String, a message with the fixture of the tournament.
     * */

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
     * @param refereeId, String with the id of the referee.
     * @param teamName1, String with the name of the first team.
     * @param teamName2, String with the name of the second team.
     * @return boolean, true if the referee is assigned to the match successfully, false if the referee is not found, the match is not found or the referee is from the same country as either team.
     * */

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
     * @param teamName1, String with the name of the first team.
     * @param teamName2, String with the name of the second team.
     * @param team1Goals, int with the goals of the first team.
     * @param team2Goals, int with the goals of the second team.
     * @return boolean, true if the result of the match is registered successfully, false if the match is not found.
     * */

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
        return true;
    }

    public boolean registerGoalByPlayer(String teamName, int shirtNumber, Goal goal) {
        for (Day day : groupA.getDays()) {
            for (Match match : day.getMatches()) {
                if (match.getTeam1().getName().equalsIgnoreCase(teamName) || match.getTeam2().getName().equalsIgnoreCase(teamName)) {
                    match.addGoal(goal);
                    return true;
                }
            }
        }
        for (Day day : groupB.getDays()) {
            for (Match match : day.getMatches()) {
                if (match.getTeam1().getName().equalsIgnoreCase(teamName) || match.getTeam2().getName().equalsIgnoreCase(teamName)) {
                    match.addGoal(goal);
                    return true;
                }
            }
        }
        return false;
    }
}