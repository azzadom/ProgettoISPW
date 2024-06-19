package com.privateevents.bean;

import com.privateevents.exception.IncorrectDataException;

public class TicketBean {

    private String typeName;

    private Double price;

    private Integer minimumAge;

    private String description;

    public void setTypeName(String typeName) throws IncorrectDataException {
        if(typeName == null || typeName.isEmpty())
            throw new IncorrectDataException("Type name cannot be empty");
        else if(typeName.length() > 45)
            throw new IncorrectDataException("Type name cannot be longer than 45 characters");
        else {
            this.typeName = typeName;
        }
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setDescription(String description) throws IncorrectDataException {
        if(description == null || description.isEmpty())
            throw new IncorrectDataException("Description cannot be empty");
        else if(description.length() > 255)
            throw new IncorrectDataException("Description cannot be longer than 25 characters");
        else{
            this.description = description;
        }
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
