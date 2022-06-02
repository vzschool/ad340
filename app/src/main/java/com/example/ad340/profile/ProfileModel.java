package com.example.ad340.profile;

import android.util.Log;
import androidx.core.util.Consumer;
import com.example.ad340.MainActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileModel {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final FirebaseFirestore db;

    public ProfileModel() {
        db = FirebaseFirestore.getInstance();
    }

    public void updateProfile(Profile profile) {
        db.collection("users").document(profile.getUid())
                .set(profile)
                .addOnSuccessListener(aVoid -> Log.d(TAG, "DocumentSnapshot successfully written!"))
                .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
    }

    public void getProfile(String uid, Consumer<Profile> callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(uid);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    callback.accept(document.toObject(Profile.class));
                } else {
                    Log.d(TAG, "No such document");
                    callback.accept(null);
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        });
    }
}
