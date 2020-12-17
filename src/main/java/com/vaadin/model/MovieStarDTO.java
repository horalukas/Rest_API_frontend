package com.vaadin.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Component
public class MovieStarDTO {

    private int id;
    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;

    public MovieStarDTO(){

    }

    public MovieStarDTO(int id, String firstName, String secondName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public int getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieStarDTO)) return false;
        MovieStarDTO that = (MovieStarDTO) o;
        return id == that.id &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
