import domain.FrontOfficeOperator;
import model.Appointment;
import model.EmpModel;
import model.AvailableDateTimeManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;


public class Main {

    private static FrontOfficeOperator frontOfficeOperator;
    private static AvailableDateTimeManager availableDateTimeManager;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        availableDateTimeManager = new AvailableDateTimeManager();
        frontOfficeOperator = new FrontOfficeOperator("1","Front Office Operator");

        while (true) {
            showBanner();
            printMenu();
            out.print("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    makeAppointment(scanner);
                    break;
                case 2:
                    updateAppointmentDetails(scanner);
                    break;
                case 3:
                    viewAppointmentsByDate(scanner);
                    break;
                case 4:
                    searchAppointmentByID(scanner);
                    break;
                case 5:
                    calculateAndAcceptPayment(scanner);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void showBanner() {
        println("********************************************************************");
        println("***************** TOOTHCARE ******************");
        println("********************************************************************\n");
    }


    private static void printMenu() {
        println("Select an option:");
        println("1. Make an appointment");
        println("2. Update appointment details");
        println("3. View appointments by date");
        println("4. Search for an appointment by ID");
        println("5. Calculate and accept payment");
        println("6. Exit");
        println("----------------------------------------");
    }

    public static void println(String content) {
        out.println(content);
    }

    private static void makeAppointment(Scanner scanner) {
        System.out.println("Enter patient name:");
        String patientName = scanner.nextLine();

        System.out.println("Enter date and time (e.g., 6/11/2023, Monday 06.00pm):");
//        String dateTime = scanner.nextLine();

        List<String> availableOptions = availableDateTimeManager.getAvailableDatesAndTimes();
        for (String option : availableOptions) {
            System.out.println(option);
        }

        System.out.println("Enter the desired date and time option:");
        String dateTime = scanner.nextLine();

        System.out.println("Enter treatment type:");
        String treatmentType = scanner.nextLine();

        System.out.println("Enter registration fee:");
        double registrationFee = scanner.nextDouble();

        frontOfficeOperator.makeAppointment(patientName, dateTime, treatmentType, registrationFee);
    }

    private static void updateAppointmentDetails(Scanner scanner) {
        System.out.println("Enter appointment ID to update:");
        String appointmentID = scanner.nextLine();

        Appointment appointmentToUpdate = frontOfficeOperator.searchAppointmentByID(appointmentID);

        if (appointmentToUpdate != null) {
            System.out.println("Enter new treatment types (comma-separated):");
            String treatmentsInput = scanner.nextLine();
            List<String> newTreatments = Arrays.asList(treatmentsInput.split(","));

            // Retain existing treatments and add new ones
            List<String> updatedTreatments = new ArrayList<>(appointmentToUpdate.getTreatments());
            updatedTreatments.addAll(newTreatments);

            frontOfficeOperator.updateAppointmentDetails(appointmentToUpdate, updatedTreatments);
        } else {
            System.out.println("Appointment not found.");
        }
    }

    // View appointment by date
    private static void viewAppointmentsByDate(Scanner scanner) {
        System.out.println("Enter date to view appointments (e.g., 6/11/2023):");
        String date = scanner.nextLine();
        frontOfficeOperator.viewAppointmentsByDate(date);
    }

    // Search appointment by date
    private static void searchAppointmentByID(Scanner scanner) {
        System.out.println("Enter appointment ID to search:");
        String appointmentID = scanner.nextLine();
        Appointment searchedAppointment = frontOfficeOperator.searchAppointmentByID(appointmentID);

        if (searchedAppointment != null) {
            System.out.println("Found Appointment:");
            System.out.println(searchedAppointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }


    private static void calculateAndAcceptPayment(Scanner scanner) {
        System.out.println("Enter appointment ID to calculate and accept payment:");
        String appointmentID = scanner.nextLine();
        Appointment appointment = frontOfficeOperator.searchAppointmentByID(appointmentID);

        out.println("Your current payable amount is " + appointment.calculateTreatmentFee());

        if (appointment != null) {
            frontOfficeOperator.acceptPayment(appointment);
        } else {
            System.out.println("Appointment not found.");
        }
    }
}
