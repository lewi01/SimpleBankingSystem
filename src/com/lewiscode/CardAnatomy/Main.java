package com.lewiscode.CardAnatomy;

import java.util.*;
public class Main {
    public static  Random random = new Random();
    public  static HashMap<String, String> account = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String customerNum;
        String customerPin;
        boolean quite = false;
        while (!quite){
            printMenu();
            int choice = scanner.nextInt();
            switch (choice){
                case 0:
                    System.out.println("Bye!");
                    quite = true;
                    break;
                case 1:
                    System.out.println("Your card has been created");
                    customerNum = customerNumberGenerator();
                    customerPin =customerPinGenerator();
                    System.out.println("Your card number:" + "\n" + customerNum);
                    System.out.println("Your card PIN:" + "\n" + customerPin);
                    account.put(customerNum,customerPin);
                    break;
                case 2:
                    System.out.println("Enter your card number:");
                    long num = scanner.nextLong();
                    String number = String.valueOf(num);
                    System.out.println("Enter your PIN:");
                    int pin = scanner.nextInt();
                    String pins = String.valueOf(pin);
                    if (account.containsKey(number) && account.get(number).equals(pins)){
                            System.out.println("You have successfully logged in!");
                            int balance =0;
                            int print;
                            while (true){
                                successfulLogInMenu();
                                 print =scanner.nextInt();
                                if(print == 0) {
                                    System.out.println("Bye!");
                                    break;
                                }else if(print == 1) {
                                    System.out.println("Balance: " + balance);
                                }else if(print == 2){
                                        System.out.println("You have successfully logged out!");
                                        break;

                                }
                            }

                    }else {
                        System.out.println("Wrong card number or PIN!");
                    }

                break;
            }
        }
    }
    public static void printMenu() {
        System.out.println("1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");
    }
    public  static  void successfulLogInMenu(){
        System.out.println("1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");
    }
    public static  String customerNumberGenerator(){
        long num = random.nextInt((999999999 - 100000000) + 1) + 100000000;
        String format = String.format("%d",num);
        int checkDigit = random.nextInt((9-1) + 1) + 1;
        String formatted = String.format("%d",checkDigit);
        String MII = "400000";
        String luhn = MII + format;
        String luhnAlgo ;
//      ...................implementing luhn algorithm in building digit checker
        int sum = 0;
        int[] arrayNum = new int[luhn.length()];
        for (int i = luhn.length()-1; i >= 0; i--) {
            arrayNum[i] = Integer.parseInt(luhn.substring(i, i + 1));
            if (i % 2 == 0) {
                arrayNum[i] *= 2;
            }
            sum += arrayNum[i] > 9 ? arrayNum[i] - 9 : arrayNum[i];
        }
        if((sum + checkDigit) % 10 == 0){
            luhnAlgo = luhn + formatted;
        }else {
           int lastDigitChecker = ((10 - (sum%10))%10);
          luhnAlgo = luhn + lastDigitChecker;
       }
        return luhnAlgo;
    }
    public static  String customerPinGenerator(){
        int pin = random.nextInt((9999 - 1000) + 1) + 1000;
        return String.format("%d",pin);
    }
}
