package com.treehole.domain;

public class User {
    Integer id;
    String username;
    String nickname;
    String motto;
    String phone;
    String headImage;
    String likeArticles;
    String disLikeArticles;
    String likeComments;
    String disLikeComments;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLikeArticles() {
        return likeArticles;
    }

    public void setLikeArticles(String likeArticles) {
        this.likeArticles = likeArticles;
    }

    public String getDisLikeArticles() {
        return disLikeArticles;
    }

    public void setDisLikeArticles(String disLikeArticles) {
        this.disLikeArticles = disLikeArticles;
    }

    public String getLikeComments() {
        return likeComments;
    }

    public void setLikeComments(String likeComments) {
        this.likeComments = likeComments;
    }

    public String getDisLikeComments() {
        return disLikeComments;
    }

    public void setDisLikeComments(String disLikeComments) {
        this.disLikeComments = disLikeComments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
}
