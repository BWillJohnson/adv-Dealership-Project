package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private final Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void display() {
        init();
        boolean quit = false;
        while (!quit) {
            System.out.println("---------- CarWorld Enterprise Menu ----------");
            System.out.println("1. Get vehicles by price");
            System.out.println("2. Get vehicles by make and model");
            System.out.println("3. Get vehicles by year");
            System.out.println("4. Get vehicles by color");
            System.out.println("5. Get vehicles by mileage");
            System.out.println("6. Get vehicles by type");
            System.out.println("7. Get all vehicles");
            System.out.println("8. Sales/Leasing a carWorldVehicle");
            System.out.println("9. Add vehicle");
            System.out.println("10. Remove vehicle");
            System.out.println("0. Quit");

            System.out.print("Enter option from menu!: ");
            String response = scanner.nextLine();

            switch (response) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "8" -> processSalesOrLeasingRequest();
                case "10" -> processAddVehicleRequest();
                case "11" -> processRemoveVehicleRequest();
                case "0" -> quit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        System.out.print("Enter vehicle vin: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();

        System.out.print("Enter vehicle mileage: ");
        int mileage = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter vehicle type (Car, Truck, SUV, Motorcycle): ");
        String type = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);

        dealership.addVehicle(vehicle);
        System.out.println("Vehicle added successfully!");
        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);


    }



    private void processSalesOrLeasingRequest() {
        System.out.println("What is the VIN of the vehicle you would like to buy");
        int vin = scanner.nextInt();
        scanner.nextLine();

        contractDataManager contractDataManager = new contractDataManager();
        Vehicle vehicleToFind = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vin == vehicle.getVin()) {
                vehicleToFind = vehicle;
                break;

            }

        }
        System.out.println("What is the date?");
        String date = scanner.nextLine();
        System.out.println("What is your name?");
        String name = scanner.nextLine();
        System.out.println("What is your email?");
        String email = scanner.nextLine();
        System.out.println("Would you like to lease or buy?");
        String leaseOrBuy = scanner.nextLine();

        boolean finance;
        if (leaseOrBuy.equalsIgnoreCase("buy")) {
            System.out.println("Would you like to finance the vehicle?");
            finance = scanner.nextLine().equalsIgnoreCase("yes");
            SalesContract salesContract = new SalesContract(date, name, email, vehicleToFind, finance);
            System.out.println("Total Price: $ " + salesContract.getTotalPrice());

            if (finance) {
                System.out.println("Monthly payment: $ " + salesContract.getMonthlyPayment());
            }
            contractDataManager.saveContract(salesContract);//writes contract to contract csv
            dealership.removeVehicle(vehicleToFind);
        } else if (leaseOrBuy.equalsIgnoreCase("Lease")) {
            LeaseContract leaseContract = new LeaseContract(date, name, email, vehicleToFind);
            System.out.println("Total price: $" + leaseContract.getTotalPrice());
            System.out.println("Monthly payment: $" + leaseContract.getMonthlyPayment() + " for 36 months!");
            contractDataManager.saveContract(leaseContract);
            dealership.removeVehicle(vehicleToFind);

        } else {
            System.out.println("Invalid try again!");
        }
    }
    public void processRemoveVehicleRequest() {
        System.out.print("Enter the VIN of the vehicle you wish to remove: ");
        int vin = scanner.nextInt();

        boolean vehicleRemoved = false;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed successfully!");
                vehicleRemoved = true;
                break;
            }
        }

        if (!vehicleRemoved) {
            System.out.println("Vehicle not found. Please try again.");
            return;
        }

        DealershipFileManager manager = new DealershipFileManager();
        manager.saveDealership(dealership);


    }

    private void init() {
        DealershipFileManager manager = new DealershipFileManager();
        dealership = manager.getDealership();
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }

    }
}

