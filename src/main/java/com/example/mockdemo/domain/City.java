package com.example.mockdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = City.Builder.class)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY,
        content = JsonInclude.Include.NON_NULL)
public class City implements Serializable {
    @Valid
    private Integer id;
    @Valid
    @NotNull
    private String cityName;

    public City() {
    }

    public City(Builder builder) {
        this.id = builder.id;
        this.cityName = builder.cityName;
    }

    public static class Builder{
        private Integer id;
        private String cityName;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public City build() {
            return new City(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
