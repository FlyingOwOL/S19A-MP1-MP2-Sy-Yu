public class Month {
    private final String monthName;
    private final int numberOfDays;
    private final Day[] days;

    public Month(String monthName, int numberOfDays) {
        this.monthName = monthName;
        this.numberOfDays = numberOfDays;
        this.days = new Day[numberOfDays];

        for (int i = 0; i < numberOfDays; i++) {
            this.days[i] = new Day(i + 1);
        }
    }

    public String getMonthName() {
        return monthName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public Day[] getDays() {
        return days;
    }

    public Day getDay(int dayNumber) {
        if (dayNumber >= 1 && dayNumber <= numberOfDays) {
            return days[dayNumber - 1];
        } else {
            return null;
        }
    }
}
