package com.parking.parkinglot.common;

public class CarDto {
    private Long id;
    private String parkingSpot;
    private String licensePlate;
    private String ownerName;

    public Long getId() {
        return id;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public CarDto(String parkingSpot, Long id, String licensePlate, String ownerName) {
        this.parkingSpot = parkingSpot;
        this.id = id;
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
    }
}
