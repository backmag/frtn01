public class Car {
    private String registrationNumber = "";
    private String model = "";
    private double speed = 0.0;

    public Car(String number, String carModel) {
        registrationNumber = number;
        model = carModel;
    }

    public void setSpeed(double newSpeed){
        speed = newSpeed;
    }
    public double getSpeed() {
        return speed;
    }

}