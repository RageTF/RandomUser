package com.example.rage.randomuser.model;

import com.example.rage.randomuser.model.retrofit.pojo.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Rage on 15.03.2017.
 */

public class User implements Serializable {

    private String id;
    private String name;
    private String gender;
    private String city;
    private String phone;
    private String photo;
    private String email;

    public User(){

    }

    User(Result result){
        id=UUID.randomUUID().toString();
        name=result.getName().toString();
        gender=result.getGender().toUpperCase();
        city=result.getLocation().getCity().toUpperCase();
        phone=result.getPhone();
        photo=result.getPicture().getThumbnail();
        email=result.getEmail();
    }

    String toJson() throws JSONException {

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("gender",gender);
        jsonObject.put("city",city);
        jsonObject.put("phone",phone);
        jsonObject.put("photo",photo);
        jsonObject.put("email",email);

        return jsonObject.toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
