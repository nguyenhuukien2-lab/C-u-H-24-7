package com.example.roadside.utils;

/** App-wide constants: endpoints, shared-pref keys, request codes. */
public final class Constants {

    private Constants() { }

    public static final boolean DEBUG = true;

    public static final String BASE_URL = "https://api.resq247.vn/v1/";

    public static final String HOTLINE_NUMBER = "19006868";

    // SharedPreferences keys
    public static final String PREFS_NAME = "resq247_prefs";
    public static final String KEY_AUTH_TOKEN = "auth_token";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_PHONE_NUMBER = "phone_number";

    // Intent extras
    public static final String EXTRA_REQUEST_ID = "extra_request_id";
    public static final String EXTRA_PROVIDER_ID = "extra_provider_id";
    public static final String EXTRA_PAYMENT_ID = "extra_payment_id";

    // Permission / activity request codes
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1001;
    public static final int REQUEST_CODE_CAMERA_PERMISSION = 1002;
    public static final int REQUEST_CODE_PICK_PHOTO = 2001;

    // Vehicle types (must match backend enum values)
    public static final String VEHICLE_CAR = "car";
    public static final String VEHICLE_TRUCK = "truck";
    public static final String VEHICLE_MOTORBIKE = "motorbike";
    public static final String VEHICLE_EV = "ev";
}
