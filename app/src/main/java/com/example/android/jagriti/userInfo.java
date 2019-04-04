package com.example.android.jagriti;

import android.net.Uri;

public class userInfo {

    public String  name,address,phone, phone1, phone2,phone3;
    public Uri uri;
    public userInfo(){}

    public userInfo(String mname,String maddress, String mphone, String mPhone1 ,String mphone2, String mphone3){
        name=mname;

        address=maddress;
        phone=mphone;
        phone1=mPhone1;
        phone2=mphone2;
        phone3=mphone3;
    }

}
