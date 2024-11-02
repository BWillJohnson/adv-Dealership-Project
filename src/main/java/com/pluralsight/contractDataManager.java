package com.pluralsight;
import java.io.*;


public class contractDataManager {
    public Contract saveContract(Contract contract) {
        try (BufferedReader reader = new BufferedReader(new FileReader("contract.csv"))){
            String fileLine;
            int fileLineNum = 0;
            while ((fileLine = reader.readLine()) != null){
                String[] points = fileLine.split("\\|");
                if (fileLineNum == 0){ // contract data.
                    String date = points[0];
                    String customerName = points[1];
                    String customerEmail = points[2];
                    String vehicleSold = points[3];
                    double totalPrice = Double.parseDouble(points[4]);
                    double monthlyPayment = Double.parseDouble(points[5]);

                }

            }

        }
    }
}
