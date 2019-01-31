public class Truck extends Vehicle{
    private int currentLoad;

    public Truck(String number, String model) {
        super(number,model);
    }

    public int getCurrentLoad() {
        return currentLoad;
    }
    public void setCurrentLoad(int newLoad) {
        currentLoad = newLoad;
    }
}