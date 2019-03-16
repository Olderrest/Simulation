package com.stasuma.objects;


public class SimulationTime {
    private static int minute;
    private static int hour;
    private static int day;
    private static int month;
    private static int year;

    private static int yearsFinal;
    private static int monthsFinal;
    private static int daysFinal;

    private static boolean endSimulation;

    private static int startWorkTime = 5;
    private static int endWorkTime = 22;

    private SimulationTime() {
        year = 1;
        month = 1;
        day = 1;
        hour = 0;
        minute = 0;
    }

    private SimulationTime(int years, int months, int days, int hours, int minutes) {
        year = years;
        month = months;
        day = days;
        hour = hours;
        minute = minutes;
    }

    static SimulationTime getNewStartTime() {
        return new SimulationTime();
    }

    static void addMinutes(int min) {
        for (int i = 0; i < min; i++) {
            minute += 1;
            if (minute == 60) {
                hour++;
                minute = 0;
                if (hour == 24) {
                    day++;
                    hour = 0;
                    if (day == getDaysInMonth()) {
                        month++;
                        day = 1;
                        if (month > 12) {
                            year++;
                            month = 1;
                        }
                    }
                }
            }
            if (checkEndSimulation()) {
                endSimulation = true;
                return;
            }
        }
    }

    static boolean checkEndSimulation() {
        return (year > yearsFinal) && (month > monthsFinal) && (day > daysFinal);
    }

    private static int getDaysInMonth() {
        if (month % 2 == 0) {
            return 31;
        } else if (month == 2) {
            return 28;
        } else {
            return 30;
        }
    }

    double calculateCoeff(double highCoeffValue, double mediumCoeffValue, double lowCoeffValue) {
        if ((hour >= 5) && (hour <= 10)) {
            return highCoeffValue;
        } else if ((hour >= 11) && (hour <= 16)) {
            return lowCoeffValue;
        } else {
            return mediumCoeffValue;
        }
    }

    static StopTime getStopTime(int min) {
        StopTime stopTime = new StopTime(year, month, day, hour, minute);
        stopTime.addMinute(min);
        return stopTime;
    }

    public static SimulationTime getCurrentTime() {
        return new SimulationTime(year, month, day, hour, minute);
    }


    void setSimulationFinalData(int year, int month, int day) {
        yearsFinal = year;
        monthsFinal = month;
        daysFinal = day;
    }

    public boolean isEndSimulation() {
        return endSimulation;
    }

    boolean checkWorkTime() {
        return ((hour >= startWorkTime) && (hour <= endWorkTime));
    }

    public static int getMinute() {
        return minute;
    }

    public static int getHour() {
        return hour;
    }

    public static int getDay() {
        return day;
    }

    public static int getMonth() {
        return month;
    }

    public static int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "SimulationTime{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute + "}";
    }

    public static class StopTime {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;

        public StopTime() {
        }

        public StopTime(int year, int month, int day, int hour, int minute) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
        }

        public void addMinute(int min) {
            for (int i = 0; i < min; i++) {
                minute += 1;
                if (minute >= 60) {
                    hour++;
                    minute = 0;
                    if (hour >= 24) {
                        day++;
                        hour = 0;
                        if (day > getDaysInMonth()) {
                            month++;
                            day = 1;
                            if (month > 12) {
                                year++;
                                month = 1;
                            }
                        }
                    }
                }
            }
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        @Override
        public String toString() {
            return "StopTime{" +
                    "year=" + year +
                    ", month=" + month +
                    ", day=" + day +
                    ", hour=" + hour +
                    ", minute=" + minute +
                    '}';
        }
    }
}
