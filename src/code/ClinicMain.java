package code;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClinicMain {
    private static ClinicCalendar calendar;

    //Method for starting up the system

    public static void main(String[]args)throws Throwable{
        calendar = new ClinicCalendar(LocalDate.now());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient Intake Computer System !\n\n");
        String lastOption ="";
        while (!lastOption.equalsIgnoreCase("x")){
            lastOption = displayMenu(scanner);
        }
        System.out.println("\nExiting System...\n");
    }
    //Menu for communicate with user
    private static String displayMenu(Scanner scanner)throws Throwable{
        System.out.println("Please select an option:");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments ");
        System.out.println("X. Exit System.");
        System.out.print("Option: ");
        String option = scanner.next();
        switch (option){
            case "1": performPatientEntry(scanner);
            return option;
            case "2": performAllAppointments();
            return option;
            default:
                System.out.println("Invalid option, please re-enter.");
                return option;
        }
    }
    //Adding appointments for patient
    private static void performPatientEntry(Scanner scanner){
        scanner.nextLine();
        System.out.println("\n\nPlease Enter Appointment Info:");
        System.out.print(" Patient Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print(" Patient First Name: ");
        String firstName = scanner.nextLine();
        System.out.print(" Appointment Date (M/d/yyyy h:m a)");
        String when = scanner.nextLine();
        System.out.print(" code.Doctor Last Name: ");
        String doc = scanner.nextLine();
        try {
            calendar.addAppointment(firstName, lastName, doc, when);
        } catch (Throwable t){
            System.out.println("Error! " + t.getMessage());
            return;
        }
        System.out.println("Patient entered successfully.\n\n");
    }
    //viewing an appointments
private static void performAllAppointments()throws Throwable{
    System.out.println("\n\nAll Appointments in System:");
    for(PatientAppointment appointment : calendar.getAppointments()){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
        String apptTime = formatter.format(appointment.getAppointmentDateTime());
        System.out.println(String.format("%s: %s, %s\t\tcode.Doctor: %s", apptTime, appointment.getPatientLastName(), appointment.getPatientFirstName(), appointment.getDoctor().getName()));
    }
    System.out.println("\nPress any key to continue...");
}
}