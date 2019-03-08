package com;

import java.util.*;

public class Main {

    private static final List<Student> studentList = new ArrayList<>();
    private static List<Cards> cardList = new ArrayList<>();
    private static List<Menus> menus = new LinkedList<>(); //We want to retain the order
    Scanner scanner = new Scanner( System.in );

    static{
        //Initialize menu items once
        new Main().initMenuItems();
    }

    public static void main(String[] args) {
        while( true ) {
            new Main().displayMainMenu();
        }
    }

    /**
     * Display main menu and prompt input
     */
    private void displayMainMenu() {
        int menuOption = 0;
        System.out.println("    Elimu School Program     ");
        System.out.println("*******************************");
        System.out.println("Select Option");
        System.out.println("1. Enroll Students");
        System.out.println("2. Issue Cards");
        System.out.println("3. Load Cards");
        System.out.println("4. Check card balance");
        System.out.println("5. Pay Meals");
        System.out.println("6. Generate Report");
        System.out.println("0. Exit");

        System.out.print("\nEnter option : ");
        try {
            menuOption = scanner.nextInt();

            //Execution will reach here of there is no exception
            if (menuOption < 0 || menuOption > 6) {
                scanner.reset();
                invalidInputMessage();
            }
            else menuHandler( menuOption );

        } catch (InputMismatchException e) {
            scanner.reset();
            invalidInputMessage();
        }
    }

    /**
     * Delegate menu option to corresponding services
     *
     * @param menuOption
     */
    private void menuHandler( int menuOption ){
        switch( menuOption ){
            case 1://Student Enrollment
                enrollStudent();
                return;
            case 2:
                issueCard();
                return;
            case 3:
                loadCard();
                return;
            case 4:
                checkCardBalance();
                return;
            case 5:
                payMeals();
                return;
            case 6:
                generateReport();
                return;
            case 0:
                System.exit( 0 );
                break;
        }
    }

    private void enrollStudent(){

        scanner.reset();
        System.out.println("New Student Enrollment");
        System.out.print("--------------------------------");

        System.out.print("\nEnter number of students: ");
        int noOfStudents = scanner.nextInt();

        for( int i= 0; i < noOfStudents; i++) {

            System.out.print("\nEnter first name: ");
            String firstName = scanner.next();

            System.out.print("Enter middle name: ");
            String middleName = scanner.next();

            System.out.print("Enter surname: ");
            String surname = scanner.next();

            System.out.print("Enter registration number: ");
            String registration = scanner.next();

            //Create a new student object
            Student student = new Student(firstName, middleName, surname, registration);
            studentList.add(student);
        }

        System.out.println("Student(s) enrolled successfully. \n");
        return;
    }

    private void issueCard(){
        scanner.reset();
        System.out.println("Issue Card");
        System.out.print("--------------------------------");

        System.out.print("\nEnter student registration: ");
        String registration = scanner.next();

        //Look for record with such details
        Student currentStudent = null;
        for (Student student: studentList ) {
            if( student.getRegistration().equals( registration ) ){
                currentStudent = student;
                break;
            }
        }

        if( currentStudent == null ){
            System.err.println("Invalid student registration");
            System.out.println();
            return;
        }

        //Create a new card
        String cardNumber = String.format( "%03d", cardList.size() + 1);
        Cards card = new Cards( cardNumber );
        cardList.add( card );

        //Link student with card
        currentStudent.getCards().add( card );

        //Print Receipt
        printCardIssueReceipt( currentStudent, card );
    }

    private void loadCard(){
        scanner.reset();
        System.out.println("Load Card");
        System.out.print("--------------------------------");

        System.out.println();
        Cards currentCard = getCard();
        if( currentCard == null ) return;

        //Prompt for amount
        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        //Compute card balance
        double balance = currentCard.getBalance() + amount;
        currentCard.setBalance( balance );

        printCardLoadReceipt( currentCard );
    }

    private void checkCardBalance(){
        scanner.reset();
        System.out.println("Check Balance");
        System.out.print("--------------------------------");

        System.out.print("\nEnter card number: ");
        String cardNumber = scanner.next();

        //Look for record with such details
        Cards currentCard = null;
        for (Cards card: cardList ) {
            if( card.getCardNumber().equals( cardNumber ) ){
                currentCard = card;
                break;
            }
        }

        if( currentCard == null ){
            System.err.println("Invalid card number");
            System.out.println();
            return;
        }

        //Print Receipt
        printCardLoadReceipt( currentCard );
    }

    private void payMeals(){

        //Display menu
        while( true ){
            int i =1;
            System.out.println("       MENU                    ");
            System.out.println("*****************************");
            for(Menus menu : menus ){
                System.out.println( String.format("%d. %s", i, menu.getMenu() ));

                i++;
            }
            i = 0;

            System.out.println();
            System.out.print("Select option: ");
            int option = scanner.nextInt();

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("         MEAL PAYMENT       ");
            System.out.println("*****************************");

            System.out.print("Enter no of units : ");
            int units = scanner.nextInt();
 
            Cards card = getCard();
            if( card == null ) return;

            Menus selectedMenu = menus.get( option - 1 );
            double total = selectedMenu.getPrice() * units;
            double runningTotal = total + selectedMenu.getTotal();
            int totalUnits = selectedMenu.getUnits() + units;
            selectedMenu.setTotal( runningTotal );
            selectedMenu.setUnits( totalUnits );
           

            //Compute card balance
            double cardBalance = card.getBalance() - total;
            card.setBalance( cardBalance );

            printMealReceipt( selectedMenu, units, total);
            break;
        }
    }

    private void generateReport(){
        System.out.println();
        System.out.println();
        System.out.println("           SALES REPORT         ");
        System.out.println("**********************************");
        System.out.println("   Meal              Units       Amount");

        int i=1;
        for( Menus menu: menus ){

            String formatter = "%d. %s%"+(20 - menu.getMenu().length() )+"d           %.2f";
            System.out.println(String.format(formatter, i,
                    menu.getMenu(),  menu.getUnits(), menu.getTotal() ) );
            i++;
        }
        System.out.println();
        System.out.println();
    }

//    a. Tea - KES 50
//    b. Coffee - KES 50
//    c. Indian Pancakes - KES 30
//    d. Omellette - KES 40
//    e. Fried Eggs - KES 60
//    f. Biscuits - KES 100

    private void initMenuItems(){
        Menus menu1 = new Menus("Tea", 50.00);
        menus.add( menu1 );

        Menus menu2 = new Menus("Coffee", 50.00);
        menus.add( menu2 );

        Menus menu3 = new Menus("Indian Pancakes", 30.00);
        menus.add( menu3 );

        Menus menu4 = new Menus("Omellette", 40.00);
        menus.add( menu4 );

        Menus menu5 = new Menus("Fried Eggs", 60.00);
        menus.add( menu5 );

        Menus menu6 = new Menus("Biscuits", 100.00);
        menus.add( menu6 );
    }

    private Cards getCard(){

        Cards currentCard = null;
        while( true ) {
            System.out.print("Enter card number : ");
            String cardNumber = scanner.next();

            for (Cards card : cardList) {
                if (card.getCardNumber().equals(cardNumber)) {
                    currentCard = card;
                    break;
                }
            }

            if (currentCard == null) {
                System.out.println("Invalid card number");
                System.out.println("1. Try again");
                System.out.println("0. Exit ");
                System.out.println();
                System.out.print("Enter option:");
                int option = scanner.nextInt();

                if( option == 1 ) continue;
                else break;
            }
            else break;
        }

        return currentCard;
    }


    private void printCardIssueReceipt( Student student, Cards card){
        System.out.println();
        System.out.println();
        System.out.println("      CARD ISSUANCE RECEIPT      ");
        System.out.println("**********************************");
        System.out.println(String.format("Student :%s %s", student.getFirstName(), student.getSurname() ) );
        System.out.println(String.format("Card No. :%s", card.getCardNumber() ) );
        System.out.println(String.format("Balance. :%.2f", card.getBalance() ) );
        System.out.println();
        System.out.println();
    }

    private void printCardLoadReceipt( Cards card){
        System.out.println();
        System.out.println();
        System.out.println("              RECEIPT            ");
        System.out.println("**********************************");
        System.out.println(String.format("Card No. :%s", card.getCardNumber() ) );
        System.out.println(String.format("Balance. :%.2f", card.getBalance() ) );
        System.out.println();
        System.out.println();
    }

    private void printMealReceipt( Menus selectedMenu, int units, double amount ){
        System.out.println();
        System.out.println();
        System.out.println("              RECEIPT            ");
        System.out.println("**********************************");
        System.out.println("Meal              Units       Amount");
        System.out.println(String.format("%s                %d          %.2f", selectedMenu.getMenu(), units, amount ) );
        System.out.println();
        System.out.println();
    }

    private void invalidInputMessage() {
        System.err.println("Invalid input supplied; try again");
        System.out.println("");
    }
}
