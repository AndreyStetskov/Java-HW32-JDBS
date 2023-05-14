package org.crazyemperor;

import org.crazyemperor.connections.DBConnection.ConnectionToDBCar;
import org.crazyemperor.entity.Car;
import org.crazyemperor.entity.CarDAO;

public class Main {
    public static void main(String[] args) {

        ConnectionToDBCar connectionToDBCar =
                new ConnectionToDBCar("jdbc:postgresql://localhost:5432/postgres", "postgres", "1mperator");

        CarDAO carDAO = new CarDAO(connectionToDBCar);

        System.out.println(carDAO.getAllCars());

        System.out.println(carDAO.getNumberCarFromCountry());

        Car car = new Car(5, "LAMBORGHINI", "ITALY");
        carDAO.insertCars(car);

        carDAO.deleteCar(5);

        carDAO.update(1, car);

        System.out.println(carDAO.getCar(3));

    }
}