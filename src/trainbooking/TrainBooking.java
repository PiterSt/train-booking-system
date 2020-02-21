/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainbooking;
import java.util.*;
import java.io.*;
/**
 *
 * @author piotrstanny
 */
public class TrainBooking {

    /**
     * @param args the command line arguments
     */
    static final int SEATING_CAPACITY = 8;
    
    public static void main(String[] args) throws Exception {
        String[] Train = new String[SEATING_CAPACITY];
        initialise(Train);
        Train[1] = "Mary Drew";
        Train[5] = "John Smith";
        Train[7] = "Anna Kowalska";
        System.out.println(
                "Welcome to the Polish National Trains Booking System.\n"
                        + "This is a booking management system.");
        
        // Loading main menu:
        
        String menuChoice = menuList(); 
        
        while (!"q".equals(menuChoice)) {
            switch(menuChoice) {
                case "v":
                    System.out.println("\nList of all bookings:\n---------------------");
                    viewBookings(Train);
                    menuChoice = menuList();
                    break;
                case "a":
                    System.out.println("\nAdd customer to seat:\n---------------------");
                    addBooking(Train);
                    menuChoice = menuList();
                    break;
                case "e":
                    System.out.println("\nSee empty seats below:\n---------------------");
                    emptySeats(Train);
                    menuChoice = menuList();
                    break;
                case "d":
                    System.out.println("\nRemove customer from seat:\n---------------------");
                    deleteCustomer(Train);
                    menuChoice = menuList();
                    break;
                case "f":
                    System.out.println("\nFind a customer:\n---------------------");
                    findCustomer(Train);
                    menuChoice = menuList();
                    break;
                case "s":
                    System.out.println("\nSave program data into file:\n---------------------");
                    storeDataToFile(Train);
                    menuChoice = menuList();
                    break;
                case "l":
                    System.out.println("\nLoad data from file:\n---------------------");
                    loadFileData(Train);
                    menuChoice = menuList();
                    break;
                case "o":
                    System.out.println("\nBookings listed alphabetically:\n---------------------");
                    sortByName(Train);
                    menuChoice = menuList();
                    break;
                default:
                    System.out.println("\nInvalid input!");
                    menuChoice = menuList();
            }
        }
        // Closing the program:
        
        System.out.println("\nYour session has ended. Goodbye!");
    }
    
    // Basic Methods:
    
    private static void initialise(String[] trainSeats) {
        for (int i=0; i< trainSeats.length; i++) trainSeats[i]= "e";
        System.out.println("Loading bookings...\n");
    }
    
    static boolean isInteger(String name) {
        try {
            int integer = Integer.parseInt(name); 
            return true;
        }
        catch(NumberFormatException error) {
            return false;
        }
    }
    
    private static boolean seatNoValidationFailed(String seatNo, String[] trainSeats) {
        int seatNoAsInt;
        if (!isInteger(seatNo)) {
            System.out.print("You have to enter a number!\nChoose seat again: ");
            return true;
        }
        seatNoAsInt = Integer.parseInt(seatNo);
        if (seatNoAsInt<1 || seatNoAsInt>8) {
            System.out.println("Seat numbers are from 1 to 8!\nChoose seat again: ");
            return true;
        }
        if (!trainSeats[seatNoAsInt-1].equals("e")) {
            System.out.println("Seat number " + seatNoAsInt + " is occupied!\nChoose seat again: ");
            return true;
        }
        return false;
    }
    
    private static boolean nameValidationFailed(String name) {
        if (name.equals("")) {
            System.out.print("Name cannot be empty!\nTry again: ");
            return true;
        }
        if (isInteger(name)) {
            System.out.print("Name cannot be a number!\nTry again: ");
            return true;
        }
        return false;
    }
    
    private static boolean deleteValidationFailed(String seatNo, String[] trainSeats) {
        int seatNoAsInt;
        if (!isInteger(seatNo)) {
            System.out.print("You have to enter a number!\nChoose seat again: ");
            return true;
        }
        seatNoAsInt = Integer.parseInt(seatNo);
        if (seatNoAsInt<1 || seatNoAsInt>8) {
            System.out.println("Seat numbers are from 1 to 8!\nChoose seat again: ");
            return true;
        }
        if (trainSeats[seatNoAsInt-1].equals("e")) {
            System.out.println("Seat number " + seatNoAsInt + " is already empty!\nChoose seat again: ");
            return true;
        }
        return false;
    }
    
    private static String menuList() {
        System.out.println(
                  "\nTo continue, choose from the list of menu options:\n"
                + "---------------------------\n"
                + "Q:\t Quit program\n"
                + "V:\t View all bookings\n"
                + "A:\t Add customer to seat\n"
                + "E:\t Display empty seats\n"
                + "D:\t Delete customer from seat\n"
                + "F:\t Find a customer\n"
                + "S:\t Store program data in to file\n"
                + "L:\t Load program data from file\n"
                + "O:\t Sort bookings alphabetically by name");
        Scanner in = new Scanner(System.in);
        String menuChoice = in.nextLine().toLowerCase();
        return menuChoice;
    }
    
    // Menu Options Methods:
    
    private static void viewBookings(String[] trainSeats) {
        for (int i=0; i < trainSeats.length; i++) {
            if (trainSeats[i].equals("e")) {
                System.out.println("Seat No. " + (i+1) + " - empty.");
            } else {
                System.out.println("Seat No. " + (i+1) + " - occupied by " + trainSeats[i]);
            }
        }
    }
    
    private static void addBooking(String[] trainSeats){
        String name, seatNo;
        int seatNoAsInt;
        // Checking if empty seats available
        if (Arrays.asList(trainSeats).contains("e")) {
            Scanner in = new Scanner(System.in);
            
            // Processing name input:
            System.out.print("Enter customer's name: ");
            name = in.nextLine();
            while (nameValidationFailed(name)) {
                name = in.nextLine();
            }
            // Processing seat number input:
            System.out.print("Choose your seat number: ");
            seatNo = in.nextLine();
            while (seatNoValidationFailed(seatNo, trainSeats)) {
                seatNo = in.nextLine();
            }
            seatNoAsInt = Integer.parseInt(seatNo);
            trainSeats[seatNoAsInt-1] = name;
            System.out.println("...DONE.\nYou have booked seat No "+seatNoAsInt+" on the name "+ trainSeats[seatNoAsInt-1]);
        } else {
            System.out.println("There is no empty seats available!");
        }
    }
    
    private static void emptySeats(String[] trainSeats) {
        for (int i=0; i<trainSeats.length; i++) {
            if (trainSeats[i].equals("e")) {
            System.out.println("No. " + (i+1));
            }
        }
    }
    
    private static void deleteCustomer(String[] trainSeats) {
        String seatNo;
        int seatNoAsInt;
        for (int i=0; i<trainSeats.length; i++) {
            if (!trainSeats[i].equals("e")) {
                System.out.println("No. " + (i+1) + ": " + trainSeats[i]);
            }
        }
        System.out.print("\nChoose seat number to clear it: ");
        Scanner in = new Scanner(System.in);
        seatNo = in.nextLine();
        while (deleteValidationFailed(seatNo, trainSeats)) {
                seatNo = in.nextLine();
            }
        seatNoAsInt = Integer.parseInt(seatNo);
        trainSeats[seatNoAsInt-1] = "e";
        System.out.println("...\nSeat number " + seatNoAsInt + " is now empty.");
    }
    
    private static void findCustomer(String[] trainSeats) {
        String name;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter customer's name: ");
        name = in.nextLine();
        while (nameValidationFailed(name)) {
            name = in.nextLine();
        }
        System.out.println("\nCustomers matching request: ");
        for (int i=0; i<trainSeats.length; i++) {
            if ((trainSeats[i].toLowerCase()).contains(name.toLowerCase())) {
                System.out.println("- " + trainSeats[i] + " - seat number " + (i+1));
            }
        }
    }
    
    private static void storeDataToFile(String[] trainSeats) throws Exception {
        try {
            File file = new File("." + File.separator + "names.txt");
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
        
            for (int i = 0; i < trainSeats.length; i++) {
                String name = trainSeats[i];
                writer.write(name + "\n");
            }
            writer.close();
            System.out.println("...\nData has been saved to the file!");
        }
        catch (Exception error) {
            System.out.println("Exception error:\n" + error);
        }
    }
    
    private static void loadFileData(String[] trainSeats) throws Exception {
        try {
            String path = System.getProperty("user.dir");
            Scanner readFile = new Scanner(new BufferedReader(new FileReader(path + File.separator + "names.txt")));
            String fileLine;
            int index = 0;
            while (readFile.hasNext()) { 
                fileLine = readFile.nextLine();
                trainSeats[index] = fileLine;
                index++;
            }
            readFile.close();
            System.out.println("...\nData has been loaded!\nChoose 'V' to see bookings.");
        }
        catch (FileNotFoundException error) {
            System.out.println("Exception error:\nNo data to load!\nAdd and store data in to file to load it later.");
        }
    }
    
    private static void sortByName(String[] trainSeats) {
        
        // Creating new list with booked seats only + keeping their seat number
        ArrayList notEmptySeats = new ArrayList();
        for (int i = 0; i < trainSeats.length; i++) {
            if (!trainSeats[i].equals("e")) {
                trainSeats[i] += (i+1);
                notEmptySeats.add(trainSeats[i]);
            }
        }
        // Bubble sort on ListArray with no empty seats
        int counter = 0;
        while (counter < notEmptySeats.size()) {
            int j;
            String temp;
            for (int i = 0; i < notEmptySeats.size()-1; i++) {
                j = i + 1;
                String wordOne = (String) notEmptySeats.get(i);
                String wordTwo = (String) notEmptySeats.get(j);
                if (wordOne.charAt(0) > wordTwo.charAt(0)) {
                    temp = (String) notEmptySeats.get(i);
                    notEmptySeats.set(i, notEmptySeats.get(j));
                    notEmptySeats.set(j, temp);
                }
            }
            counter++;
        }
        //Displaying names and retrieving their seat numbers
        String name;
        char seatNumber;
        int lastCharIndex;
        for (int i = 0; i < notEmptySeats.size(); i++) {
            name = (String) notEmptySeats.get(i);
            lastCharIndex = name.length()-1;
            seatNumber = name.charAt(lastCharIndex);
            name = name.substring(0, name.length()-1);
            System.out.println(name + " - seat No. " + seatNumber);
        }
    }
}
