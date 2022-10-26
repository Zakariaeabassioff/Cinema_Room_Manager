/***
 * Name : ABASSI Zakariae
 * Project : Cinema Room Manager
 * Created on : 19/10/2022
 * Last Modified : 25/10/2022
 */


//STAGE 1/5 : Affichage des places dans une salle de cinema
//STAGE 2/5 : Calcul du prix du billet d'entrée dépendamment du nombre des sièges disponible dans la salle
//STAGE 3/5 : Afficher la place réserver sur le plan de la salle ainsi que le prix de la place souhaitée 
//STAGE 4/5 : Ajouter un menu au programme, et décomposer le programme en plusieurs fonctions 
//STAGE 5/5 : Ajouter une rubrique statistics et ne plus permettre à l'utilisateur d'acheter une place déjà réserver 

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);

    static int purshasedTickets = 0;
    static int totalIncome = 0;
    static int currentIncome = 0;

    public static String[][] createArr(int rows, int seats) {
        String[][] arrangement = new String[rows][seats];

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < seats; k++) {
                arrangement[i][k] = " S";
            }
        }
        return arrangement;
    }


    public static void showSeats(String[][] arr, int rows, int seats) {
        System.out.println();

        System.out.println("Cinema:");
        
        for (int i = 1; i <= seats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i+1);
            for (int k = 0; k < seats; k++) {
                System.out.print(arr[i][k]);
            }
            System.out.println();
        }
    }

    public static void buyTicket(String[][] arr, int rows, int seats) {
        System.out.println();
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (rowNumber <= rows && seatNumber <= seats) {
            if (" S".equals(arr[rowNumber - 1][seatNumber - 1])) {
                arr[rowNumber - 1][seatNumber - 1] = " B";
                purshasedTickets++;
                
                int price = 0;
                if (rows * seats < 60) {
                    price = 10;
                } else {
                    if (rows % 2 == 0) {
                        if (rowNumber < rows/2) {
                            price = 10;
                        } else {
                            price = 8;
                        }
    
                    } else {
                        if (rowNumber <= rows/2) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    }
                }
                
                System.out.println();
                System.out.println("Ticket price: $" + price);
                currentIncome += price;
            } else {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                buyTicket(arr, rows, seats);
            }
        } else {
            System.out.println();
            System.out.println("Wrong input!");
            buyTicket(arr, rows, seats);
        }
    } 

    public static int totalIncome(String[][] arr, int rows, int seats) {
        int total = 0, price = 0;
        int priceFront = 10, priceBack = 8;

        if (rows * seats < 60) {
            price = 10;
            total = rows * seats * price ;
        } else {
            if (rows % 2 == 0) {
                total = (rows/2 * priceFront * seats) + (rows/2 * priceBack * seats);
            } else {
                total = (rows/2 * priceFront * seats) + ((rows - rows/2) * priceBack * seats);
            }
        }
        return total;
    }



    public static void showStats(String[][] arr, int rows, int seats) {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d %n", purshasedTickets);
        System.out.printf("Percentage: %.2f%% %n", (purshasedTickets/(float)(rows*seats))*100);
        System.out.printf("Current income: $%d %n", currentIncome);
        System.out.printf("Total income: $%d %n", totalIncome(arr, rows, seats));
    }

    public static void menu(String[][] arr, int rows, int seats) {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                showSeats(arr, rows, seats);
                menu(arr, rows, seats);
                break;

            case 2:
                buyTicket(arr, rows, seats);
                menu(arr, rows, seats);
                break;
            
            case 3:
                showStats(arr, rows, seats);
                menu(arr, rows, seats);
                break;

            case 0:
                return ;
        }
    }


    public static void main (String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        String arrangement[][] = createArr(rows, seats);

        menu(arrangement, rows, seats); 
    }
}
        
    

