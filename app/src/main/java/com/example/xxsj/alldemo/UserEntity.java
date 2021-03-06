package com.example.xxsj.alldemo;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by xxsj on 2017/10/31.
 */

public class UserEntity {
    private String username;
    private String nickname;
    private int age;
    private String image;

    @BindingAdapter("image")
    public static void Imageload(ImageView imageView, String s){

        Glide.with(imageView.getContext()).load(s).into(imageView);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public UserEntity() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity(int age, String nickname, String username) {
        this.age = age;
        this.nickname = nickname;
        this.username = username;
    }
}
