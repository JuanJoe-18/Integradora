package model;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Match {

    private Team team1;
    private Team team2;
    private ArrayList<Referee> referees;
    private int team1Goals;
    private int team2Goals;
    private LocalDateTime dateTime;
    private ArrayList<Goal> goals;
    private ArrayList<Card> cards;

    /**
     * Description: Constructor to initialize a Match object
     * @param team1 The first team
     * @param team2 The second team
     * @param team1Goals The number of goals scored by the first team
     * @param team2Goals The number of goals scored by the second team
     * @param dateTime The date of the match
     */
    public Match(Team team1, Team team2, int team1Goals, int team2Goals, LocalDateTime dateTime) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Goals = team1Goals;
        this.team2Goals = team2Goals;
        this.referees = new ArrayList<>();
        this.dateTime = dateTime;
        this.goals = new ArrayList<>();
        this.cards = new ArrayList<>();
    }

    public Match() {
        cards = new ArrayList<>();
    }

    public Match(Team team1, Team team2) {
        this(team1, team2, 0, 0, LocalDateTime.now());
    }

    public void addGoal(Goal goal) {
        goals.add(goal);
    }


    public ArrayList<Referee> getReferees() {
        return referees;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getTeam1Goals() {
        return team1Goals;
    }

    public int getTeam2Goals() {
        return team2Goals;
    }


    public void setReferees(ArrayList<Referee> referees) {
        this.referees = referees;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Goal> getGoals() {
        return goals;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public boolean hasPlayer(String playerName) {
        return team1.hasPlayer(playerName) || team2.hasPlayer(playerName);
    }

    /**
     * Description: This method returns a string representation of the Match object.
     * @return string with the names of the two teams in the match.
     */
    @Override
    public String toString() {
        return team1.getName() + " vs " + team2.getName() + " - " + dateTime.toLocalDate();
    }
}