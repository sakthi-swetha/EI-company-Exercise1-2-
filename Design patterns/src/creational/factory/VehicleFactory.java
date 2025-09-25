package creational.factory;

public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        return switch(type.toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            default -> throw new IllegalArgumentException("Unknown vehicle type");
        };
    }
}
