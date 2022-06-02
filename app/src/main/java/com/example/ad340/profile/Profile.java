package com.example.ad340.profile;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Profile implements Serializable {
    private String uid;
    private String name;
    private String occupation;
    private String about;
    private int yyyy;
    private int mm;
    private int dd;
    private int age;

    public Profile() {}

    public Profile (
            String uid,
            String name,
            String occupation,
            String about,
            int yyyy,
            int mm,
            int dd
    ) {
        this.uid = uid;
        this.name = name;
        this.occupation = occupation;
        this.about = about;
        this.yyyy = yyyy;
        this.mm = mm;
        this.dd = dd;
        updateAge();
    }

    public void updateAge() {
        LocalDate now = LocalDate.now();
        LocalDate DOB = LocalDate.of(yyyy, mm, dd);
        Period age = Period.between(DOB, now);
        this.age = age.getYears();
    }

    public String getUid() { return uid; }
    public String getName() {
        return name;
    }
    public String getOccupation() { return occupation; }
    public String getAbout() { return about; }
    public int getYYYY() { return yyyy; }
    public int getMM() { return mm; }
    public int getDD() { return dd; }
    public int getAge() { return age; }
}
