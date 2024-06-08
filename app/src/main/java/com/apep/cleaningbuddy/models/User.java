package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.apep.cleaningbuddy.database.Database;
import com.apep.cleaningbuddy.exceptions.UserNotFoundException;
import com.apep.cleaningbuddy.utils.ArrayUtils;
import com.apep.cleaningbuddy.utils.Cryptor;

import java.util.Arrays;
import java.util.List;

@Entity(indices = {@Index(value = "username", unique = true)})
public class User {
    @Ignore
    private static final String PASSWORD_SALT = "KJx#1cEMVB1b";
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String username;
    private byte[] password;
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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = Cryptor.hashPassword(ArrayUtils.combine(password, PASSWORD_SALT.getBytes()));
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

    public User() {}

    public boolean checkUserPassword(byte[] password) {
        Cryptor.hashPassword(ArrayUtils.combine(password, PASSWORD_SALT.getBytes()));
        return (Arrays.equals(this.password, password));
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

    public static User getUserByUsername(Context context, String username) throws UserNotFoundException {
        User user = Database.getDatabase(context).userDao().getUserByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found.");
        }
        return user;
    }

}
