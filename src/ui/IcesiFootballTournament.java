package ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.CardType;
import model.TournamentController;


public class IcesiFootballTournament {

    public Scanner rd;
    public TournamentController ctrl;

    public static void main(String[] args) {
        IcesiFootballTournament exe = new IcesiFootballTournament();
        exe.mainMenu();
    }

    public IcesiFootballTournament() {
        rd = new Scanner(System.in);
        ctrl = new TournamentController();
    }

    /**
     * Description: This method displays the main menu of the program Pre:
     * Scanner rd might be initialized
     *
     */
    public void mainMenu() {
        int option;
        do {
            System.out.println("""
                    Welcome to the Icesi University football tournament
                    1. Show the first menu
                    2. Show the second menu
                    0. Exit
                    """);
            option = rd.nextInt();
            rd.nextLine();

            switch (option) { // Keep the switch statement as it is for now
                case 1:
                    firstMenu();
                    break;
                case 2:
                    secondMenu();
                    break;
                case 0:
                    System.out.println("Thanks for using the program and for your participation in the Icesi University football tournament. Come back soon.");
                    rd.close();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

        } while (option != 0);

    }

    public void firstMenu (){
        int option;
        do {
            System.out.println("""
                    1. Register tournament
                    2. Register team
                    3. Register player
                    4. Register referee
                    5. Assign referee to match
                    6. Show fixture
                    7. Register result of a match
                    8. Show the second menu
                    0. Exit
                    """);
            option = rd.nextInt();
            rd.nextLine();
            switch (option) {
                case 1:
                    registerTournament();
                    break;
                case 2:
                    registerTeam();
                    break;
                case 3:
                    registerPlayer();
                    break;
                case 4:
                    registerReferee();
                    break;
                case 5:
                    assignRefereeToMatch();
                    break;
                case 6:
                    showFixture();
                    break;
                case 7:
                    registerResult();
                    break;
                case 8:
                    secondMenu();
                    break;
                case 0:
                    System.out.println("Thanks for using the program and for your participation in the Icesi University football tournament. Come back soon.");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 0);

    }


    public  void secondMenu (){
        int option;
        do {
            System.out.println("""
                    1. Register goal by player and his assist
                    2. Register cards by players
                    3. See standings
                    0. Exit
                    """);
            option = rd.nextInt();
            rd.nextLine();
            switch (option) {
                case 1:
                    registerGoalByPlayer();
                    break;
                case 2:
                    registerCardsByPlayers();
                    break;
                case 3:
                    System.out.println(ctrl.showStandings());
                    break;
                case 0:
                    System.out.println("Thanks for using the program and for your participation in the Icesi University football tournament. Come back soon.");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 0);
    }

    /**
     * Description: This method registers a tournament in the system Pre:
     * Scanner rd might be initialized
     *
     */
    public void registerTournament() {
        rd.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Enter the start date of the tournament (yyyy-MM-dd)");
        String startDate = rd.nextLine();
        System.out.println("Enter the end date of the tournament (yyyy-MM-dd)");
        String endDate = rd.nextLine();

        boolean success = ctrl.registerTournament(startDate, endDate);

        if (success) {
            System.out.println("Tournament registered successfully");
        } else {
            System.out.println("Error registering the tournament");
        }
    }

    /**
     * Description: This method registers a team in the system Pre: Scanner rd
     * might be initialized
     *
     */
    public void registerTeam() {
        System.out.println("Group A Teams: " + ctrl.groupA.getTeams());
        System.out.println("Group B Teams: " + ctrl.groupB.getTeams());

        System.out.println("Enter the name of the team");
        String name = rd.nextLine(); // This variable is used later in the method
        System.out.println("Enter the country of the team");
        String country = rd.nextLine();
        System.out.println("Enter the name of the technical director");
        String nameTechnicalDirector = rd.nextLine();

        boolean sucess = ctrl.registerTeam(name, country, nameTechnicalDirector);

        if (sucess) {
            System.out.println("Team registered successfully");
        } else {
            System.out.println("Error registering the team");
        }

    }

    /**
     * Description: This method registers a player in the system Pre: Scanner rd
     * might be initialized
     *
     */
    public void registerPlayer() {
        System.out.println("List of teams.");
        System.out.println("\nGroup A Teams: " + ctrl.groupA.getTeams());
        System.out.println("Group B Teams: " + ctrl.groupB.getTeams());
        System.out.println("\nList of players per team.");
        System.out.println("\n|Group A|:");
        System.out.println(ctrl.groupA.getTeams().getFirst().getPlayers());
        System.out.println("|Group B|:");
        System.out.println(ctrl.groupB.getTeams().getFirst().getPlayers());

        System.out.println("Enter the name of the team to which the player belongs");
        String teamName = rd.nextLine();
        System.out.println("Enter the shirt number of the player");
        int shirtNumber = rd.nextInt();
        rd.nextLine();
        System.out.println("Enter the name of the player");
        String playerName = rd.nextLine();
        System.out.println("Enter the country of the player");
        String country = rd.nextLine();
        System.out.println("Enter the position of the player");
        System.out.println("""
            GOALKEEPER
            DEFENDER
            MIDFIELDER
            FORWARD""");
        String position = rd.nextLine();
        boolean success = ctrl.registerPlayer(shirtNumber, teamName, playerName, country, position);
        if (success) {
            System.out.println("Player registered successfully");
        } else {
            System.out.println("Error registering the player");
        }
    }

    /**
     * Description: This method registers a referee in the system Pre: Scanner
     * rd might be initialized
     *
     */
    public void registerReferee() {
        System.out.println("Enter the id of the referee");
        String id = rd.nextLine();
        System.out.println("Enter the name of the referee");
        String name = rd.nextLine();
        System.out.println("Enter the country of the referee");
        String country = rd.nextLine();
        System.out.println("Enter the type of the referee");
        System.out.println("""
        C - Central
        A - Assistant""");
        String type = rd.nextLine();
        boolean success = ctrl.registerReferee(id, name, country, type);
        if (success) {
            System.out.println("Referee registered successfully");
        } else {
            System.out.println("Error registering the referee");
        }
    }

    /**
     * Description: This method shows the fixture of the tournament Pre: Scanner
     * rd might be initialized
     *
     */
    public void showFixture() {
        System.out.println(ctrl.showFixture());
    }

    /**
     * Description: This method assigns a referee to a match Pre: Scanner rd
     * might be initialized
     *
     */
    public void assignRefereeToMatch() {
        System.out.println("Enter the id of the referee");
        String refereeId = rd.nextLine();
        System.out.println("Enter the name of the team1");
        String teamName1 = rd.nextLine();
        System.out.println("Enter the name of the team2");
        String teamName2 = rd.nextLine();
        boolean success = ctrl.assignRefereeToMatch(refereeId, teamName1, teamName2);
        if (success) {
            System.out.println("Referee assigned to match successfully");
        } else {
            System.out.println("Error assigning the referee to the match");
        }
    }

    /**
     * Description: This method registers the result of a match Pre: Scanner rd
     * might be initialized
     *
     */
    public void registerResult() {
        rd.nextLine();
        System.out.println("Enter the name of team 1:");
        String teamName1 = rd.nextLine();
        System.out.println("Enter the name of team 2:");
        String teamName2 = rd.nextLine();
        System.out.println("Enter the goals scored by " + teamName1 + ":");
        int team1Goals = rd.nextInt();
        System.out.println("Enter the goals scored by " + teamName2 + ":");
        int team2Goals = rd.nextInt();
        rd.nextLine(); // Consume the newline character
        boolean success = ctrl.registerResult(teamName1, teamName2, team1Goals, team2Goals);
        if (success) {
            System.out.println("Result registered successfully");
        } else {
            System.out.println("Error registering the result");
        }
    }

    public void registerGoalByPlayer() {
        System.out.println("Enter the name of team 1");
        String team1Name = rd.nextLine();
        System.out.println("Enter the name of team 2");
        String team2Name = rd.nextLine();
        System.out.println("Enter the shirt number of the player");
        int shirtNumber = rd.nextInt();
        rd.nextLine();
        System.out.println("Enter the name of the player");
        String playerName = rd.nextLine();
        System.out.println("Enter the name of the player who assisted the goal");
        String assistPlayerName = rd.nextLine();
        boolean success = ctrl.registerGoalByPlayer(team1Name, team2Name, shirtNumber, playerName, assistPlayerName);
        if (success) {
            System.out.println("Goal registered successfully");
        } else {
            System.out.println("Error registering the goal");
        }
    }

    public void registerCardsByPlayers() {
        System.out.println("Enter the name of team 1:");
        String teamName1 = rd.nextLine();
        System.out.println("Enter the name of team 2:");
        String teamName2 = rd.nextLine();
        System.out.println("Enter the name of the player:");
        String playerName = rd.nextLine();
        System.out.println("Enter the type of card (YELLOW/RED):");
        String cardTypeInput = rd.nextLine().toUpperCase();
        CardType cardType = CardType.valueOf(cardTypeInput);

        boolean success = ctrl.registerCardtoPlayer(teamName1, teamName2, playerName, cardType);
        if (success) {
            System.out.println("Card registered successfully");
        } else {
            System.out.println("Error registering the card");
        }
    }


}
