package com.example.h297015.myapplication.Records;

import java.util.ArrayList;

public class Record {
    private String name;
    private String role;
    private String phone;
    private String email;
    private String location;
    private boolean isFavorite;
    private boolean isTS; // tech support
    private boolean isFSE; // field service engineer
    private int zipCode;

    public Record(String name, String role, String phone, String email, String location, boolean isFavorite, boolean isTS, boolean isFSE, int zipCode) {
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.location = location;
        this.isFavorite = isFavorite;
        this.isTS = isTS;
        this.isFSE = isFSE;
        this.zipCode = zipCode;
    }

    public boolean isTS() {
        return isTS;
    }

    public void setTS(boolean TS) {
        isTS = TS;
    }

    public boolean isFSE() {
        return isFSE;
    }

    public void setFSE(boolean FSE) {
        isFSE = FSE;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public static ArrayList<Record> createRecordsList(int numRecords) {
        ArrayList<Record> records = new ArrayList<Record>();

        records.add(
                new Record("Jerry Springer",
                        "Technical Support Engineer",
                        "1234567890",
                        "Jerry.Springer@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        true,
                        false,
                        82861
                )
        );
        records.add(
                new Record("Morgan Freeman",
                        "Field Service Engineer",
                        "1234567890",
                        "Morgan.Freeman@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        false,
                        true,
                        33325
                )
        );
        records.add(
                new Record("Timothy David",
                        "Technical Support Engineer",
                        "602300549",
                        "David.Timothy@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        true,
                        false,
                        85034
                )
        );
        records.add(
                new Record("Patrick Heath",
                        "Field Service Engineer",
                        "1234567890",
                        "Heath.Patrick@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        false,
                        true,
                        85034
                )
        );
        records.add(
                new Record("Bradley Thompson",
                        "Field Service Engineer",
                        "1234567890",
                        "Thompson.Bradley@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        false,
                        true,
                        85034
                )
        );
        records.add(
                new Record("Degraff Rich",
                        "Field Service Engineer",
                        "1234567890",
                        "Rich.Degraff@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        false,
                        true,
                        85034
                )
        );
        records.add(
                new Record("Albert Einstein",
                        "Technical Support Engineer",
                        "1234567890",
                        "Albert.Einstein@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        true,
                        false,
                        33325
                )
        );
        records.add(
                new Record("Winston Churchill",
                        "Technical Support Engineer",
                        "1234567890",
                        "Jerry.Springer@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        true,
                        false,
                        82861
                )
        );
        records.add(
                new Record("Elvis Presley",
                        "Technical Support Engineer",
                        "1234567890",
                        "Elvis.Presley@honeywell.com",
                        "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                        false,
                        true,
                        false,
                        82861
                )
        );

        for (int i = 1; i <= numRecords; i++) {
            if ( i % 5 == 0){
                records.add(
                        new Record("Jerry Springer" + i,
                            "Technical Support Engineer",
                            "1234567890",
                            "Jerry.Springer@honeywell.com",
                            "1944 E Sky Harbor Cir N, Phoenix, AZ 85034",
                            false,
                            true,
                            false,
                            82861
                    )
                );
            }
        }

        return records; // TODO: sort by date
    }
}
