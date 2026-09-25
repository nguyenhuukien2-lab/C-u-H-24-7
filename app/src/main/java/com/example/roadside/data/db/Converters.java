package com.example.roadside.data.db;

import androidx.room.TypeConverter;

import com.example.roadside.data.models.Request;

public class Converters {

    @TypeConverter
    public static Request.Status fromString(String value) {
        if (value == null) {
            return Request.Status.PENDING;
        }
        try {
            return Request.Status.valueOf(value);
        } catch (IllegalArgumentException e) {
            return Request.Status.PENDING;
        }
    }

    @TypeConverter
    public static String statusToString(Request.Status status) {
        return status == null ? Request.Status.PENDING.name() : status.name();
    }
}
