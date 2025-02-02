package Websites;
import java.util.*;
import java.lang.*;
import java.time.*;
// 224659.95   2662.88
public class CompoundInterest {
    public static void runPayCalc() {
        double principal = getPrincipal();
        double interestRate = getInterestRate();
        double payment = getPayment();
        double interestTotal = 0;
        double newPrincipalPay = 0;
        LocalDate date = LocalDate.now().plusMonths(1);
        int counter = 1;
        System.out.println(
                "    Starting Principal: " + stringMoney(principal) +
                        "    Starting Interest Rate: " + interestRate +
                        "    Pay per month: " + stringMoney(payment)
        );
        while (principal > 0.0 || counter >= 1000) {
            //double newInterestPay = calcInterestMonthly(interestRate, principal);
            double newInterestPay = calcInterestDaily(interestRate, principal, date.lengthOfMonth());
            newPrincipalPay = calcPrincPayment(interestRate, principal, payment, date.lengthOfMonth());
            interestTotal = interestTotal + newInterestPay;
            principal = principal - newPrincipalPay;
            if (principal <= 0.0){
                newPrincipalPay = newPrincipalPay - newInterestPay + principal;
                principal =  0.0;
            }
            String output = (
                    "#" + counter +
                    "   "+date.getMonth()+" "+date.getYear() +
                    "    Principal Payment: " + stringMoney(newPrincipalPay) +
                    "    Interest Payment: " + stringMoney(newInterestPay) +
                    "    New Principal: " + stringMoney(principal)
            );
            System.out.println(output);

            date = date.plusMonths(1);
            counter++;
        }
        System.out.println("Total Paid: " + String.format("%.2f",(counter -1) * payment + newPrincipalPay) + "    Total Interest Paid: "+ String.format("%.2f",interestTotal) );
    }

    static double getPrincipal(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input Principal: ");
        return input.nextDouble();
    }

    static double getInterestRate(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input interest rate: ");
        return input.nextDouble();
    }

    static double getPayment(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input monthly payment: ");
        return input.nextDouble();
    }

    static double calcInterestMonthly(double i, double p){
        //return Math.round(i/100.0/12*p*100.0)/100.0;
        return doubleMoney(i/100.0/12*p);
    }

    static double calcInterestDaily(double i, double p, int z){
        double dailyInterest = 0.0;
        while (z > 0) {
            p += (i / 100.0 / 365.0 * p);
            dailyInterest += (i / 100.0 / 365.0 * p);
            z--;
            //calcInterestDaily(i, p, z);
        }
        return doubleMoney(dailyInterest);
    }

    static double calcPrincPayment(double i, double p, double pay, int z){
        //return pay - calcInterestMonthly(i, p);
        //return Math.round( (pay - calcInterestMonthly(i, p) ) * 100.0 )/100.0;
        //return doubleMoney(pay - calcInterestMonthly(i, p));
        return doubleMoney(pay - calcInterestDaily(i,p,z));
    }

    static double doubleMoney(double d) {
        return Math.round(d * 100.0)/100.0;
    }

    static String stringMoney(double d) {
        return String.format("%.2f",doubleMoney(d));
    }

}
