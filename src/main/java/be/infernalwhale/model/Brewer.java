package be.infernalwhale.model;

public class Brewer {
    private int id;
    private String name;
    private String address;
    private String zipcode;
    private String city;
    private int turnover;

    public int getId() {
        return id;
    }

    public Brewer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Brewer setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Brewer setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Brewer setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Brewer setCity(String city) {
        this.city = city;
        return this;
    }

    public int getTurnover() {
        return turnover;
    }

    public Brewer setTurnover(int turnover) {
        this.turnover = turnover;
        return this;
    }
}
