package bean;

public class TicketBean {

    private String typeName;

    private Double price;

    private Integer minimumAge;

    private String description;

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeName() {
        return typeName;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public String getDescription() {
        return description;
    }

}
