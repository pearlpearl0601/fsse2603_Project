package com.fsse2603.project_backend.data.user.domainObject.request;

public class FirebaseUserData{
    private String email;
    private String firebaseUid;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}


