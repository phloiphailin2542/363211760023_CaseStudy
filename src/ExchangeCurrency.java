import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExchangeCurrency{
    public static void main(String[] args) {
       ExchangeAPI  obj = new ExchangeAPI();
        Scanner scanner =new Scanner(System.in);

        try {
            System.out.println("### Currency Exchange System ###");
            System.out.println("please, enter your current currency: ");
            String curentCurrency = scanner.nextLine();
            if (obj.getConnection(curentCurrency)==false)
                System.out.println("Sorry, we can't find this currency rate.");
            else {
                System.out.println("Amount: ");
                double amount = Double.parseDouble(scanner.nextLine());

                System.out.println("Enter new currency: ");
                String newCurrency = scanner.nextLine();

                double rate = obj.getEachRate(newCurrency);

                System.out.println("\nExchange "+curentCurrency+" to "+newCurrency);
                System.out.println("\t"+curentCurrency+": "+amount);
                System.out.println("\t"+newCurrency+": "+(amount*rate));

            }
        }catch (InputMismatchException | IOException e) {
            e.printStackTrace();
        }
    }//main
}//class
