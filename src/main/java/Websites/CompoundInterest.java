package Websites;

import java.util.Scanner;
import java.lang.Math;

public class CompoundInterest {
    public static void runPayCalc() {
        double principal = getPrincipal();
        double interestRate = getInterestRate();
        double payment = getPayment();
        double interestTotal = 0;
        double newPrincipalPay = 0;
        int counter = 1;
        System.out.println(
                "    Starting Principal: " + principal +
                        "    Starting Interest Rate: " + interestRate +
                        "    Pay per month: " + payment
        );
        while (principal > 0.0 || counter >= 361) {
            double newInterestPay = calcInterestMonthly(interestRate, principal);
            newPrincipalPay = calcPrincPayment(interestRate, principal, payment);
            interestTotal = interestTotal + newInterestPay;
            principal = principal - newPrincipalPay;
            if (principal <= 0.0){
                newPrincipalPay = newPrincipalPay - newInterestPay + principal;
                principal =  0.0;
            }
            String output = ("#" + counter +
                    "    Principal Payment: " + String.format("%.2f",newPrincipalPay) +
                    "    Interest Payment: " + String.format("%.2f",newInterestPay) +
                    "    New Principal: " + String.format("%.2f",principal)
            );
            System.out.println(output);
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
        return Math.round(i/100.0/12*p*100.0)/100.0;
    }

    static double calcPrincPayment(double i, double p, double pay){
        //return pay - calcInterestMonthly(i, p);
        return Math.round( (pay - calcInterestMonthly(i, p) ) * 100.0 )/100.0;
    }
}
