public class Vehicle implements Movable {
    private String registrationNumber;
    private String model;
    private double speed = 0.0;
    private static int numberOfVehicles;

    public Vehicle(String number, String carModel) {
        registrationNumber = number;
        model = carModel;
        numberOfVehicles++;
    }

    public void setSpeed(double newSpeed){
        speed = newSpeed;
    }
    public double getSpeed() {
        return speed;
    }

    public static void printSpeed(Vehicle v) {
        System.out.println("The speed is " + v.getSpeed() + " km/h.");
    }

    public static int getNumberOfVehicles() {
        return numberOfVehicles;
    }
}