package com.example.finalprojectcovid_19.Model;

import android.net.Uri;

public class ImagePreventionMethods {

    String number,uriImage;

    public ImagePreventionMethods(String number, String uriImage) {
        this.number = number;
        this.uriImage = uriImage;
    }

    public ImagePreventionMethods() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }
}
