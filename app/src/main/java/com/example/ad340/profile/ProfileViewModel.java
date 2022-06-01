package com.example.ad340.profile;

import androidx.core.util.Consumer;

public class ProfileViewModel {

    public static void hasProfile(String uid, Consumer<Boolean> callback) {
        ProfileModel.hasProfile(uid, callback);
    }
}
