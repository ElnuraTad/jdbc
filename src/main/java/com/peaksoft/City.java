package com.peaksoft;

public class City {
    int id;
    String citiesName;
    int mayorId;

    public void setId(int id){
        this.id = id;
    }

    public void setCitiesName(String citiesName) {
        this.citiesName = citiesName;
    }

    public void setMayorId(int mayorId){
        this.mayorId = mayorId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", citiesName='" + citiesName + '\'' +
                ", mayorId=" + mayorId +
                '}';
    }
}

