import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String name;
    private String postalCode;

    public City(int id, String name, String postalCode) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Город: " + name + " | Индекс: " + postalCode;
    }
}
