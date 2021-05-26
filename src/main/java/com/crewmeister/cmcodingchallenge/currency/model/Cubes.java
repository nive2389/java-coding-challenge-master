package com.crewmeister.cmcodingchallenge.currency.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Cubes {
    @JsonProperty("Cube")
    private List<Cube> cube;
    private String time;

    public List<Cube> getCube() {
        return cube;
    }

    public void setCube(List<Cube> cube) {
        this.cube = cube;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class Cube {

        private double rate;
        private String currency;

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }
}