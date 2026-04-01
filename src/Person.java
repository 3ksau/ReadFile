import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String name;
    private int cityId;

    public Person(int id, String name, int cityId) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public int getCityId() {return cityId;}

    @Override
    public String toString() {
        return "ID: " + id + " | Имя: " + name + " | CityID: " + cityId;
    }
}
