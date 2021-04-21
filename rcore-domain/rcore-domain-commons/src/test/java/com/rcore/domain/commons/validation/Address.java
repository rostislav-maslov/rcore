package com.rcore.domain.commons.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@CheckStreet(groups = Default.class)
@Builder
@Data
public class Address {

    @NotNull
    private String id;

    private String street;

    @NotNull
    @Valid
    private City city;

    @Builder
    @Data
    public static class City {

        @NotNull
        private String id;

        @NotNull
        @Size(min = 1, max = 10)
        private String name;

        @NotNull
        @Valid
        private Location location;

        @AllArgsConstructor(staticName = "of")
        @Data
        public static class Location {

            @NotNull
            private Double lat;

            @NotNull
            private Double lon;
        }
    }
}
