public class Car extends Vehicle {
    private int numberOfPassengers;

    public Car(String number, String carModel) {
        super(number,carModel);
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public void setNumberOfPassengers(int passengers) {
        numberOfPassengers = passengers;
    }
}