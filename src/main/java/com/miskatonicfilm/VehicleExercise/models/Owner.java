package com.miskatonicfilm.VehicleExercise.models;

/**
 * Model representing the Owner of a vehicle, from the Owner table.
 */
public class Owner {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Long id;
    private String name;
    private String email;


}
