package org.crazyemperor.entity;

import org.crazyemperor.connections.DBConnection.ConnectionToDBCar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private final ConnectionToDBCar CONNECT;
    private static final String SELECT_ALL = "SELECT * FROM cars";
    private static final String NUMBER_OF_CAR_FROM_COUNTRY = "SELECT country, count(*) FROM cars GROUP BY country";



    public CarDAO(ConnectionToDBCar connection) {
        this.CONNECT = connection;
    }



    public List<Car> getAllCars() {

        Connection connection = CONNECT.getConnect();

        List<Car> cars = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_ALL);

            while (resultSet.next()){
                Car car = new Car(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("country"));

                cars.add(car);
            }
        } catch (SQLException e) {
            throw new RuntimeException("It's not possible to select all car of them", e);
        }

        return cars;
    }


    public List<String> getNumberCarFromCountry() {

        Connection connection = CONNECT.getConnect();

        List<String> numberCarFromCountry = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(NUMBER_OF_CAR_FROM_COUNTRY);

            while (resultSet.next()){
                String country = resultSet.getString(1);
                int count = resultSet.getInt(2);

                String text = String.valueOf(count) + " " + country;

                numberCarFromCountry.add(text);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Some error for  the number of car from countries", e);
        }

        return numberCarFromCountry;
    }


    public void insertCars(Car car) {

        Connection connection = CONNECT.getConnect();

        final String INSERT_CAR = "INSERT INTO cars VALUES (%d, '%s', '%s')".formatted(car.getID(), car.getName(), car.getCountry());

        try(Statement statement = connection.createStatement()) {

            statement.execute(INSERT_CAR);

        } catch (SQLException e) {
            throw new RuntimeException("It's not possible to insert car of them to data base", e);
        }
    }


    public void deleteCar(int id) {

        Connection connection = CONNECT.getConnect();

        final String DELETE_CAR = "DELETE FROM cars WHERE id = %d".formatted(id);

        try(Statement statement = connection.createStatement()) {

            statement.executeUpdate(DELETE_CAR);

        } catch (SQLException e) {
            throw new RuntimeException("Delete didn't occur", e);
        }
    }


    public void update(int id, Car car) {

        Connection connection = CONNECT.getConnect();

        final String UPDATE_CAR = "UPDATE cars SET name = '%s', country = '%s' WHERE id = %d".formatted(car.getName(), car.getCountry(), id);

        try(Statement statement = connection.createStatement()) {

            statement.executeUpdate(UPDATE_CAR);

        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }


    public Car getCar(int id) {

        Connection connection = CONNECT.getConnect();

        Car car = null;

        final String SELECT_CAR = "SELECT * FROM cars WHERE id = %d".formatted(id);

        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_CAR);

            while (resultSet.next()){
                if (resultSet.getInt("id") == id) {
                    car = new Car(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("country"));
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("It's not possible to select this car of them", e);
        }

        return car;
    }
}
