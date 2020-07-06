package be.infernalwhale.model;

public class Beer {
    private int id;
    private String name;
    private int brewerId;
    private Brewer brewer;
    private int categoryId;
    private Category category;
    private double price;
    private int stock;
    private double alcohol;
    private int version;
    // Ignoring the image blob


    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brewer=" + brewer.getName() +
                ", category=" + category.getCategoryName() +
                ", price=" + price +
                ", stock=" + stock +
                ", alcohol=" + alcohol +
                '}';
    }

    public String getSingleLine() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", alcohol=" + alcohol +
                '}';
    }

    public int getId() {
        return id;
    }

    public Beer setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Beer setName(String name) {
        this.name = name;
        return this;
    }

    public int getBrewerId() {
        return brewerId;
    }

    public Beer setBrewerId(int brewerId) {
        this.brewerId = brewerId;
        return this;
    }

    public Brewer getBrewer() {
        return brewer;
    }

    public Beer setBrewer(Brewer brewer) {
        this.brewer = brewer;
        return this;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Beer setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Beer setCategory(Category category) {
        this.category = category;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Beer setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStock() {
        return stock;
    }

    public Beer setStock(int stock) {
        this.stock = stock;
        return this;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public Beer setAlcohol(double alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Beer setVersion(int version) {
        this.version = version;
        return this;
    }
}
