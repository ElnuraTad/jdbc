package com.peaksoft;

public class Country {
    int id;
    String countryName;
    double population;
    String language;
    int cityId;

    public void setId(int id){
        this.id = id;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

   public void setCityId(int cityId){
        this.cityId = cityId;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                ", population=" + population +
                ", language='" + language + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
