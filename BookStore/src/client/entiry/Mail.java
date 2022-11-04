package client.entiry;

public class Mail {

    private String id;
    private String name;
    private Double price;
    private int number;
    private String address;
    public Mail() {

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Mail(String id, String name, Double price, int number, String address) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.number = number;
        this.address = address;
    }
}
