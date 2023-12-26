package model;

import java.util.ArrayList;
import java.util.List;

public class AvailableDateTimeManager {
    private  List<String> availableDatesAndTimes;

    public AvailableDateTimeManager() {
        availableDatesAndTimes = new ArrayList<>();
        // Initialize available dates and times, you can customize this based on your needs
        initializeAvailableDatesAndTimes();
    }

    private void initializeAvailableDatesAndTimes() {
        // Populate the availableDatesAndTimes list with initial values
        availableDatesAndTimes.add("6/11/2023, Monday 06.00pm");
        availableDatesAndTimes.add("6/11/2023, Monday 07.00pm");
        availableDatesAndTimes.add("6/11/2023, Monday 08.00pm");
        availableDatesAndTimes.add("8/11/2023, Wednesday 06.00pm");
        availableDatesAndTimes.add("8/11/2023, Wednesday 07.00pm");
        availableDatesAndTimes.add("8/11/2023, Wednesday 08.00pm");
        availableDatesAndTimes.add("11/11/2023, Saturday 03.00pm");
        availableDatesAndTimes.add("11/11/2023, Saturday 04.00pm");
        availableDatesAndTimes.add("11/11/2023, Saturday 06.00pm");
        availableDatesAndTimes.add("11/11/2023, Saturday 07.00pm");
        availableDatesAndTimes.add("11/11/2023, Saturday 08.00pm");
        availableDatesAndTimes.add("11/11/2023, Saturday 09.00pm");
        availableDatesAndTimes.add("12/11/2023, Sunday 03.00pm");
        availableDatesAndTimes.add("12/11/2023, Sunday 04.00pm");
        availableDatesAndTimes.add("12/11/2023, Sunday 06.00pm");
        availableDatesAndTimes.add("12/11/2023, Sunday 07.00pm");
        availableDatesAndTimes.add("12/11/2023, Sunday 08.00pm");
        availableDatesAndTimes.add("12/11/2023, Sunday 09.00pm");

        // You can extend this list or modify it based on your requirements
    }

    public List<String> getAvailableDatesAndTimes() {
        return availableDatesAndTimes;
    }

    public boolean allocateDateTime(String dateTime) {
        // Check if the dateTime is available, and allocate it if available
        if (availableDatesAndTimes.contains(dateTime)) {
            availableDatesAndTimes.remove(dateTime);
            return true;
        }
        return false;
    }

    public void deallocateDateTime(String dateTime) {
        // Deallocate the dateTime, making it available again
        availableDatesAndTimes.add(dateTime);
    }
}

