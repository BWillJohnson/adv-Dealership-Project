package com.pluralsight;
import java.io.*;



public class contractDataManager {
    private static final String FILE_NAME1 = "contract.csv";
    public static void saveContract(Contract contract) {

       try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME1))) {
           if (contract instanceof SalesContract contractOfSales){
               writer.write(contract.getDate()+  " | ");
               writer.write(contract.getCustomerEmail()+  " | ");
               writer.write( contract.getCustomerName()+  " | ");
               writer.write( contract.vehicleSold.getVin()+  " | ");
               writer.write( contract.vehicleSold.getYear()+  " | ");
               writer.write( contract.vehicleSold.getMake()+  " | ");
               writer.write( contract.vehicleSold.getModel()+  " | ");
               writer.write( contract.vehicleSold.getVehicleType()+  " | ");
               writer.write( contract.vehicleSold.getColor()+  " | ");
               writer.write( contract.vehicleSold.getOdometer()+  " | ");
               writer.write( contract.vehicleSold.getPrice()+  " | ");
               writer.write( ((SalesContract) contract).getSalesTaxAmount()+  " | ");
               writer.write( ((SalesContract) contract).getRecordingFee()+  " | ");
               writer.write(((SalesContract) contract).getProcessingFee()+ " | ");
               writer.write( contract.getTotalPrice()+  " | ");
               writer.write( contract.getMonthlyPayment()+  " | ");

           } else if (contract instanceof LeaseContract) {
               writer.write(contract.getDate()+  " | ");
               writer.write(contract.getCustomerEmail()+  " | ");
               writer.write( contract.getCustomerName()+  " | ");
               writer.write( contract.vehicleSold.getVin()+  " | ");
               writer.write( contract.vehicleSold.getYear()+  " | ");
               writer.write( contract.vehicleSold.getMake()+  " | ");
               writer.write( contract.vehicleSold.getModel()+  " | ");
               writer.write( contract.vehicleSold.getVehicleType()+  " | ");
               writer.write( contract.vehicleSold.getColor()+  " | ");
               writer.write( contract.vehicleSold.getOdometer()+  " | ");
               writer.write( contract.vehicleSold.getPrice()+  " | ");
               writer.write( ((LeaseContract) contract).getExpectedEndingValue()+  " | ");
               writer.write( ((LeaseContract) contract).getLeaseFee()+  " | ");
               writer.write( contract.getTotalPrice()+  " | ");
               writer.write( contract.getMonthlyPayment()+  " | ");
           }else {
               System.err.println("I knew it was you messing it up!");
           }

       }catch (Exception e){
           System.err.println("!Notice! error writing contract to  file!");
       }

    }
}
