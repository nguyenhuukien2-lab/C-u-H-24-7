package com.example.roadside.utils;

import android.content.Context;
import android.content.SharedPreferences;

/** Thin wrapper around SharedPreferences for auth/session state. */
public class SharedPrefsHelper {

    private final SharedPreferences prefs;

    public SharedPrefsHelper(Context context) {
        this.prefs = context.getApplicationContext()
                .getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token) {
        prefs.edit().putString(Constants.KEY_AUTH_TOKEN, token).apply();
    }

    public String getAuthToken() {
        return prefs.getString(Constants.KEY_AUTH_TOKEN, null);
    }

    public void saveUserId(String userId) {
        prefs.edit().putString(Constants.KEY_USER_ID, userId).apply();
    }

    public String getUserId() {
        return prefs.getString(Constants.KEY_USER_ID, null);
    }

    public void savePhoneNumber(String phoneNumber) {
        prefs.edit().putString(Constants.KEY_PHONE_NUMBER, phoneNumber).apply();
    }

    public String getPhoneNumber() {
        return prefs.getString(Constants.KEY_PHONE_NUMBER, null);
    }

    public void saveUserSession(String userId, String email, String name) {
        prefs.edit()
                .putString(Constants.KEY_USER_ID, userId)
                .putString(Constants.KEY_USER_EMAIL, email != null ? email : "")
                .putString(Constants.KEY_USER_NAME, name != null ? name : "Khách hàng")
                .putString(Constants.KEY_AUTH_TOKEN, "supabase_jwt_active")
                .apply();
    }

    public boolean isLoggedIn() {
        return getAuthToken() != null;
    }

    public void clearSession() {
        prefs.edit().clear().apply();
    }
}
