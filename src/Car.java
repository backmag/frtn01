public class Car {
    private String registrationNumber;
    private String model;
    private double speed = 0.0;
    private static int numberOfCars;

    public Car(String number, String carModel) {
        registrationNumber = number;
        model = carModel;
        numberOfCars++;
    }

    public void setSpeed(double newSpeed){
        speed = newSpeed;
    }
    public double getSpeed() {
        return speed;
    }
    public static int getNumberOfCars() {
        return numberOfCars;
    }
}