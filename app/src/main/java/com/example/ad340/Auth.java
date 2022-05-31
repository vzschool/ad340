package com.example.ad340;

import com.google.firebase.auth.FirebaseAuth;

public class Auth {
    private static FirebaseAuth auth;
    public static FirebaseAuth getAuth() {
        return auth;
    }
    public static void setAuth(FirebaseAuth inAuth) {
        if (auth == null) {
            auth = inAuth;
        }
    }
}
