package test;

import code.ClinicCalendar;
import code.Doctor;
import code.PatientAppointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest {
    private ClinicCalendar calendar;

    @BeforeEach
    void init (){
        calendar = new ClinicCalendar(LocalDate.of(2019,1,28));
    }
    @Test
     void allowEntryOfAnAppointment(){

        calendar.addAppointment("Emir", "Hadzimehmedovic", "avery",
                "9/01/2019 01:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1,appointments.size());
        PatientAppointment enteredAppt = appointments.get(0);
        assertAll(
                ()-> assertEquals("Emir", enteredAppt.getPatientFirstName()),
                ()-> assertEquals("Hadzimehmedovic", enteredAppt.getPatientLastName()),
                ()-> assertSame(Doctor.avery, enteredAppt.getDoctor()),
                ()-> assertEquals("9/1/2019 01:00 popodne",
                enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
        );

    }
    @Test
    void returnTrueForHasAppointmentsIfThereIsAreAppointments(){
        calendar.addAppointment("Emir", "Hadzimehmedovic","avery","9/1/2019 1:00 pm");
        assertTrue(calendar.hasAppointment(LocalDate.of(2019,9,1)));
    }
    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments (){
        assertFalse(calendar.hasAppointment(LocalDate.of(2019,9,1)));
    }
    @Test
    void returnCurrentDaysAppointments (){
        calendar.addAppointment("Emir", "Hadzimehmedovic",
                "avery","01/28/2019 1:00 pm");
        calendar.addAppointment("Emir", "Hadzimehmedovic",
                "avery","01/28/2019 2:00 pm");
        calendar.addAppointment("Emir", "Hadzimehmedovic",
                "avery","01/28/2019 3:00 pm");
        assertEquals(3,calendar.getTodayAppointments().size());
    }
}
