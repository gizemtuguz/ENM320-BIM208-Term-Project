import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        System.out.print("Enter unit cost (c): ");
        double c = scanner.nextDouble();
        
        System.out.print("Enter interest rate (i): ");
        double i = scanner.nextDouble();
        
        System.out.print("Enter penalty cost (p): ");
        double p = scanner.nextDouble();
        
        System.out.print("Enter ordering cost (K): ");
        double K = scanner.nextDouble();
        
        System.out.print("Enter lead time (T): ");
        double T = scanner.nextDouble();
        
        System.out.print("Enter mean demand (mean): ");
        double mean = scanner.nextDouble();
        
        
        System.out.print("Enter standard deviation of demand (dev): ");
        double dev = scanner.nextDouble();
        
        scanner.close(); 

        String[] values = null;
        double annual_demand = mean * (12/T);


        double Order_Quantity_0 = Math.sqrt((2 * K * annual_demand) / (i * c));
        double F_Value_0 = 1 - (Order_Quantity_0 * (i * c)) / (p * annual_demand);
        System.out.println("Initial F Value: " + F_Value_0);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/excel.csv"));
            String line;
            ArrayList<Double> F_values = new ArrayList<>();
            ArrayList<Double> z_values = new ArrayList<>();
            ArrayList<Double> L_values = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                values = line.split(",");
                double f_value = Double.parseDouble(values[0].replace("\"", ""));
                double z_value = Double.parseDouble(values[1]);
                double L_value = Double.parseDouble(values[2]);

                if (f_value > F_Value_0) {
                    System.out.println("Found F value: " + f_value);
                    break;
                }

                F_values.add(f_value);
                z_values.add(z_value);
                L_values.add(L_value);
            }

            reader.close();

            int index = 0;
            for (int j = 0; j < F_values.size(); j++) {
                if (F_values.get(j) > F_Value_0) {
                    index = j;
                    break;
                }
            }

            double z_value = z_values.get(index);
            double L_value = L_values.get(index);
            double R_Value_0 = mean + dev * z_value;
            double nR = dev * L_value;

            double order_quantity = 0;
            double R_Value = 0;
            Double previous_order_quantity = null;
            Double previous_R_Value = null;
            double tolerance = 0.00001;
            int iter = 1;

            while (true) {
                order_quantity = Math.sqrt((2 * annual_demand * (K + p * nR)) / (i * c));
                double F_Value = 1 - (order_quantity * (i * c)) / (p * annual_demand);

                for (int j = 0; j < F_values.size(); j++) {
                    if (F_values.get(j) > F_Value) {
                        index = j;
                        break;
                    }
                }

                z_value = z_values.get(index);
                L_value = L_values.get(index);
                R_Value = mean + dev * z_value;
                nR = dev * L_value;

                if (previous_order_quantity != null && Math.abs(order_quantity - previous_order_quantity) < tolerance &&
                        previous_R_Value != null && Math.abs(R_Value - previous_R_Value) < tolerance) {
                    break;
                }

                iter++;
                previous_order_quantity = order_quantity;
                previous_R_Value = R_Value;
            }

            double safety_stock = R_Value - mean;
            double holding_cost = (i * c * (order_quantity / 2 + R_Value - mean));
            double penalty_cost = (p * annual_demand * nR) / order_quantity;
            double setup_cost = K * (annual_demand / order_quantity);
            double average_time_between_orders = order_quantity / annual_demand;

            System.out.println("Final R_Value: " + R_Value);
            System.out.println("Final order_quantity: " + order_quantity);
            System.out.println("Iteration value: " + iter);
            System.out.println("Safety stock: " + safety_stock);
            System.out.println("Holding cost: " + holding_cost);
            System.out.println("Setup cost: " + setup_cost);
            System.out.println("Penalty cost: " + penalty_cost);
            System.out.println("Average time between orders: " + average_time_between_orders + " years");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
