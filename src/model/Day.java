package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Day {

    private String name;
    private LocalDate date;
    private ArrayList<Match> matches;

    /**
     * Description: This constructor initializes a Day object with the given parameters.
     * @param date the date of the day.
     * @param name the name of the day.
     */

    public Day(String name, LocalDate date ) {
        this.date = date;
        this.matches = new ArrayList<>();
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }
    public void addMatch(Match match) {
        matches.add(match);
    }



    /**
     * Description: This method returns a string representation of the Day object.
     * @return string with the information of the day, including its name, date, and matches.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Day{name=").append(name);
        sb.append(", date=").append(date);
        sb.append(", matches=").append(matches);
        sb.append('}');
        return sb.toString();
    }

}
