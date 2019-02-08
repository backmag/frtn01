
public class hockey {

    public static void hockey(String[] args) {
        Car car1;
        Car car2;
        car1 = new Car("ABC111", "Volvo V70");
        car2 = new Car("DEF222", "Saab 9-5");
        car1.setSpeed(100);
        car2.setSpeed(200);
        System.out.println("The speed of car1 is " + car1.getSpeed());
        System.out.println("There are " + Vehicle.getNumberOfVehicles() + " cars.");
        Vehicle.printSpeed(car2);

        MarathonRunner runner = new MarathonRunner();
        runner.setSpeed(10);
        printSpeed(runner);
    }

    public static void printSpeed(Movable m) {
        System.out.println("Speed = " + m.getSpeed());
    }
}