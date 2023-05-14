package org.crazyemperor.entity;

import java.util.Objects;

public class Car {

    private final int ID;
    private String name;
    private String country;


    public Car(int ID, String name, String country) {
        this.ID = ID;
        this.name = name;
        this.country = country;
    }

    public int getID() {
        return ID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return ID == car.ID && Objects.equals(name, car.name) && Objects.equals(country, car.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, country);
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
