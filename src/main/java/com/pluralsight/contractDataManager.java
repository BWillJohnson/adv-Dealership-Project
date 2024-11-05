package com.pluralsight;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class contractDataManager {
    private static final String FILE_NAME1 = "contract.csv";
    public Contract getContract() {
        Contract contract1 = new SalesContract();
        Contract contract2 = new LeasingContract();
        List<Contract> newContract = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME1))){
            String fileLine;
            int fileLineNum = 0;
            while ((fileLine = reader.readLine()) != null){
                String[] points = fileLine.split("\\|");
                if (fileLineNum == 0){
                    String contractType = points[0];
                    String date = points[1];
                    String customerName = points[2];
                    String customerEmail = points[3];
                    int vehicleVin = Integer.parseInt(points[4]);
                    int vehicleYear = Integer.parseInt(points[5]);
                    String vehicleMake = points[6];
                    String vehicleModel = points[7];
                    String vehicleType = points[8];
                    String color = points[9];
                    int odometer = Integer.parseInt(points[10]);
                    double price = Double.parseDouble(points[11]);
                    double salesTaxAmount = Double.parseDouble(points[12]);


                    String vehicleSold = points[];
                    double totalPrice = Double.parseDouble(points[]);
                    double monthlyPayment = Double.parseDouble(points[]);
                   newContract

                }

            }

        }catch (Exception e){

        }
        return null;
    }
}
