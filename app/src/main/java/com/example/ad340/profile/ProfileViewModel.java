package com.example.ad340.profile;

import androidx.core.util.Consumer;

public class ProfileViewModel {
    private final ProfileModel profileModel;

    public ProfileViewModel () {
        profileModel = new ProfileModel();
    }

    public void updateProfile(Profile profile) {
        profileModel.updateProfile(profile);
    }

    public void getProfile(String uid, Consumer<Profile> callback) {
        profileModel.getProfile(uid, callback);
    }
}
