# train-booking-system
Coursework for a Java Programming year-1

Design and implement a menu driven program for booking seats on a Train with 8 seats.
You will need to adhere to good programming style and conventions, for example, avoid magic
numbers in your code by declaring a global constant (final) to represent the number of seats for your
Train.

static final int SEATING_CAPACITY = 8;

All other variables must be local. Pass variables as parameters if they are needed in other methods.
Use good naming conventions for your variables and methods, and add suitable comments.
The Train’s seating will be represented by an array of strings.

String [] Train = new String[SEATING_CAPACITY];

In this representation, Train[4] = “e”, means that seat 4 of the Train is empty, Train[1] = “Mary
Drew” means that Mary is occupying seat 1. Seat 0 may not make sense, but ignore it for now. (Hint.
You may start by using code which is similar to the hotel code given in your notes)
Once the basic code runs then add code to ‘Views All seats’ and ‘Add customer to seat’ into separate
methods, and test it works. You can build up your test cases as you develop your program (see 2
below).

Then add a menu system which will allow the user to choose what they want to select. Enter an ‘A’
to add a customer to a seat, and a ‘V’ to view all seats. Ensure that all menu options call a separate
method to execute the option. When an ‘A’ is pressed it should call the Add method, a ‘V’ should call
the View method.

Add extra methods to do each of the following menu options. The user should be able
to choose from the menu what the program does, until they enter ‘Q’ which should quit the
program.

 E: Display Empty seats
 D: Delete customer from seat
 F: Find the seat for a given customers name
 S: Store program data in to file
 L: Load program data from file
 O: View seats Ordered alphabetically by name. (Using the bubble sort algorithm in the notes)
