package de.schwarz.integration.biketrip.entity

enum Gender {
    MALE(1)
    , FEMALE(2)
    , UNKNOWN(0)
    , OTHER(-1)

    private final int gender

    Gender(int gender) {
        this.gender = gender
    }

    int getGender() {
        return gender
    }

    static Gender from(int gender) {
        values().find { it.gender == gender } ?: OTHER
    }

}