package model;

public class Referee {

    private String id;
    private String name;
    private String country;
    private String type;

    /**
     * Description: Constructor to initialize a Referee object
     * post: A Referee object is created with the given parameters
     * @param id The ID of the referee
     * @param name The name of the referee
     * @param country The country of the referee
     * @param type The type of the referee (e.g., Central, Assistant)
     */

    public Referee(String id, String name, String country,  String type) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    /**
     * Description: This method returns the information of the referee
     * @return string with the information of the referee
     */
    @Override
    public String toString() {
        return "Referee{" + "id=" + id + ", name=" + name + ", country=" + country + ", type=" + type + '}';
    }

}
