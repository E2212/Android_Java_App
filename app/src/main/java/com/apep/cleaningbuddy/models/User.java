package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.apep.cleaningbuddy.database.Database;

import java.util.List;

@Entity(indices = {@Index(value = "username", unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String username;
    private String password;
    private String language;
    private String theme;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] profilePicture;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public static void addUser(Context context, User user) {
        if (user != null) {
            Database.getDatabase(context).userDao().insert(user);
        }
    }

    public static void updateUser(Context context, User user) {
        if (user != null) {
            Database.getDatabase(context).userDao().update(user);
        }
    }

    public static List<User> getAll(Context context) {
        return Database.getDatabase(context).userDao().getAll();
    }
}
