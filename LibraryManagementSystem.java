package stactandsort;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("\nChoose an operation:");
            System.out.println("1: Add book");
            System.out.println("2: Remove book");
            System.out.println("3: Get book details");
            System.out.println("4: Display all books");
            System.out.println("5: Sort books by ID");
            System.out.println("6: Display all recent books (Stack)");
            System.out.println("7: Undo last addition");
            System.out.println("8: Check library size");
            System.out.println("9: Check if library is empty");
            System.out.println("10: Exit");

            int choice = getValidChoice(scanner);  // Get valid menu choice

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID to add: ");
                    int bookId = getValidIntInput(scanner);  // Get valid integer input
                    library.addBook(bookId);
                    break;

                case 2:
                    int indexToRemove = getValidIndex(scanner, library, "remove");
                    if (indexToRemove != -1) {
                        library.removeBook(indexToRemove);
                    }
                    break;

                case 3:
                    int indexToGet = getValidIndex(scanner, library, "get");
                    if (indexToGet != -1) {
                        library.getBook(indexToGet);
                    }
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    library.sortBooksByID();
                    break;

                case 6:
                    library.displayAllRecentBooks();  // Display all recent books from the stack
                    break;

                case 7:
                    library.undoLastAddition();  // Undo the last added book
                    break;

                case 8:
                    System.out.println("Library size: " + library.librarySize());
                    break;

                case 9:
                    System.out.println("Is the library empty? " + library.isLibraryEmpty());
                    break;

                case 10:
                    continueLoop = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Get a valid choice from the user
    private static int getValidChoice(Scanner scanner) {
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 10) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();  // Clear the invalid input
            }
        }
        return choice;
    }

    // Get valid integer input
    private static int getValidIntInput(Scanner scanner) {
        int input = -1;
        boolean valid = false;
        while (!valid) {
            try {
                input = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();  // Clear the invalid input
            }
        }
        return input;
    }

    // Get valid index from the user (ensures index is within bounds of the list)
    private static int getValidIndex(Scanner scanner, Library library, String action) {
        int index = -1;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Enter index to " + action + " book: ");
                index = scanner.nextInt();
                if (index >= 0 && index < library.librarySize()) {
                    valid = true;
                } else {
                    System.out.println("Invalid index. Please enter a valid index between 0 and " + (library.librarySize() - 1) + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();  // Clear the invalid input
            }
        }
        return index;
    }
}
