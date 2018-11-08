package domain;

import domain.Person;

public class Worker extends Person {


    private int experienceLevel, hoursPerDay;
    private double salaryPerHour;
    private JobPosition jobPosition;

    public enum JobPosition {
        Developer,
        Tester,
        ProjectManager,
        TeamLeader
    }

    public Worker(long ID, String firstName, String lastName, String address, int experienceLevel, int hoursPerDay, double salaryPerHour, JobPosition jobPosition) {
        super(ID, firstName, lastName, address);
        this.experienceLevel = experienceLevel;
        this.hoursPerDay = hoursPerDay;
        this.salaryPerHour = salaryPerHour;
        this.jobPosition = jobPosition;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(int hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public double getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }


}
