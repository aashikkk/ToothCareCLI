package domain;

import java.util.List;
import java.util.Scanner;

import model.AvailableDateTimeManager;
import model.Appointment;

public class FrontOfficeOperator extends Employee {
    private AvailableDateTimeManager availableDateTimeManager;

    public FrontOfficeOperator(String employeeId, String name) {
        super(employeeId, name, "Front Office Operator");
        this.availableDateTimeManager = new AvailableDateTimeManager();
    }

    public AvailableDateTimeManager getAvailableDateTimeManager() {
        return availableDateTimeManager;
    }

    public boolean makeAppointment(String patientName, String dateTime, String treatmentType, double registrationFee) {
        // Check if the dateTime is available before making an appointment
        if (availableDateTimeManager.allocateDateTime(dateTime)) {

            if (!isValidTreatmentType(treatmentType)) {
                System.out.println("Invalid treatment type. Appointment not successful.");
                // Deallocate the reserved date and time
                availableDateTimeManager.deallocateDateTime(dateTime);
                return false;
            }

            // Logic to create a new appointment and return the appointment object with the specified employee
            Appointment newAppointment = new Appointment(patientName, dateTime, treatmentType, registrationFee);


            // Check if the registration fee is paid
            if (!newAppointment.isRegistrationFeePaid()) {
                System.out.println("Registration fee not paid. Appointment not successful.");
                // Deallocate the reserved date and time
                availableDateTimeManager.deallocateDateTime(dateTime);
                return false;
            }

            // Additional logic to handle the appointment
            System.out.println("Appointment successfully allocated.");
            System.out.println("Your Appointment Id: " + newAppointment.getAppointmentId());
            // Add the appointment to the list
            Appointment.getAppointments().add(newAppointment);
            return true;
        }
        System.out.println("Appointment slot not available.");
        return false;
    }

    public void updateAppointmentDetails(Appointment appointment, List<String> newTreatments) {
        // Logic to update appointment details
        // Update treatment types
        appointment.setTreatments(newTreatments);

        // Calculate and display the updated invoice
        double totalFee = appointment.calculateTotalFee();
        System.out.println("Appointment details updated successfully.");
        System.out.println("Updated Invoice:" + totalFee);
        System.out.println(appointment);
    }

    public void viewAppointmentsByDate(String date) {
        // Logic to display appointments filtered by date
        System.out.println("Appointments on " + date );
        // Assuming a method getAppointmentsByDate exists in Appointment class
        List<Appointment> appointments = Appointment.getAppointmentsByDate(date);
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    public Appointment searchAppointmentByID(String appointmentID) {
        // Logic to search for an appointment using the appointment ID
        return Appointment.getAppointmentById(appointmentID);
    }

    public void acceptPayment(Appointment appointment) {
        Scanner sc = new Scanner(System.in);

        // Logic to accept payment and view an invoice
        double totalFee = appointment.calculateTotalFee();
        double treatmentFee = appointment.calculateTreatmentFee();

        System.out.println("Enter payment amount: ");
        double paymentAmount = sc.nextDouble();

        if (treatmentFee == paymentAmount) {
            appointment.acceptPayment(paymentAmount, treatmentFee == paymentAmount);
            System.out.println("Payment received for Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Payment Received: $" + paymentAmount);
            System.out.println("Invoice:");
            System.out.println("Total Fee: $" + totalFee);
            System.out.println("Total Received Amount: $" + totalFee);
        } else {
            System.out.println("Payment not fully settled, Please make full payment");
            acceptPayment(appointment);
        }
    }
    private boolean isValidTreatmentType(String treatmentType) {
        // Add logic to check if the treatment type is valid
        // You can use a predefined list of valid treatment types or any other validation criteria
        String[] validTreatmentTypes = {"cleanings", "whitening", "filling", "nerve filling", "root canal therapy"};
        for (String validType : validTreatmentTypes) {
            if (validType.equalsIgnoreCase(treatmentType)) {
                return true;
            }
        }
        return false;
    }

}
