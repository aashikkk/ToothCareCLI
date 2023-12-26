package model;

import java.util.ArrayList;
import java.util.List;

    public class Appointment {
    private static int nextAppointmentId = 1;

    private String appointmentId;
    private String patientName;
    private String dateTime;
    private List<String> treatments;
    private double registrationFee;
    private boolean isPaid;
    private double paymentAmount;
    private static List<Appointment> appointments = new ArrayList<>();

    public Appointment(String patientName, String dateTime, String treatmentType, double registrationFee) {
        this.appointmentId = "A" + nextAppointmentId++;
        this.patientName = patientName;
        this.dateTime = dateTime;
        this.treatments = new ArrayList<>();
        this.treatments.add(treatmentType);
        this.registrationFee = registrationFee; // Assuming a fixed registration fee amount
    }

    public static List<Appointment> getAppointments() {
        return appointments;
    }

    public static void setAppointments(List<Appointment> appointments) {
        Appointment.appointments = appointments;
    }

    public boolean isRegistrationFeePaid() {
        return registrationFee == 1000.0; // Assuming a fixed registration fee amount
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void addTreatment(String treatmentType) {
        // Add a new treatment to the appointment
        treatments.add(treatmentType);
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    public double calculateTotalFee() {
        // Logic to calculate total fee based on treatments and additional fees
        double treatmentFee = calculateTreatmentFee();
        return registrationFee + treatmentFee;
    }

    public double calculateTreatmentFee() {
        double treatmentFee = 0.0;
        // Assuming fixed prices for treatment types
        for (String treatment : treatments) {
            switch (treatment.toLowerCase()) {
                case "cleanings":
                    treatmentFee += 2500.0;
                    break;
                case "whitening":
                    treatmentFee += 1500.0;
                    break;
                case "filling":
                    treatmentFee += 5000.0;
                    break;
                case "nerve filling":
                    treatmentFee += 7500.0;
                    break;
                case "root canal therapy":
                    treatmentFee += 10000.0;
                    break;
                default:
                    System.out.println("Incorrect treatment. Try again");
            }
        }
        return treatmentFee;
    }

    public static List<Appointment> getAppointmentsByDate(String date) {
        // Logic to retrieve appointments by date
        List<Appointment> appointmentsByDate = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDateTime().startsWith(date)) {
                appointmentsByDate.add(appointment);
            }
        }
        return appointmentsByDate;
    }


    public static Appointment getAppointmentById(String appointmentId){
        for (Appointment appointment : appointments){
            if (appointment.getAppointmentId().equalsIgnoreCase(appointmentId)) {
                return appointment;
            }
        }
        return null; // Return null if no appointment with the specified ID is found
    }

    public void payRegistrationFee(double registrationFee) {
        // Logic to handle payment of registration fee
        this.registrationFee = registrationFee;
        this.isPaid = true;
        System.out.println("Registration fee paid for Appointment ID: " + this.appointmentId);
    }

    public void acceptPayment(double paymentAmount, boolean isPaid) {
        // Logic to handle payment acceptance
        this.paymentAmount = paymentAmount;
        this.isPaid = isPaid;
        System.out.println("Payment accepted for Appointment ID: " + this.appointmentId);
        System.out.println("Invoice:");
        System.out.println(this.toString()); // method to format the invoice
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + "\n" +
                "Patient Name: " + patientName + "\n" +
                "Date and Time: " + dateTime + "\n" +
                "Treatments: " + treatments + "\n" +
                "Registration Fee: $" + registrationFee + "\n" +
                "Treatment Fee: $" + calculateTreatmentFee() + "\n" +
                "Total Fee: $" + calculateTotalFee() + "\n" +
                "Is Paid: " + isPaid + "\n";
    }
}
