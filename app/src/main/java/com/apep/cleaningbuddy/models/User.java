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
    @Ignore
    public static User loggedInUser;
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    private String username;
    private byte[] password;

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
        this.password = password;
    }

    public User() {}

    public boolean checkUserPassword(byte[] password) {
        byte[] hashedPassword = Cryptor.hashPassword(ArrayUtils.combine(password, PASSWORD_SALT.getBytes()));
        boolean valid = (Arrays.equals(this.password, hashedPassword));
        if (valid){
            loggedInUser = this;
        }
        return valid;
    }

    public void setHashedPassword(byte[] password) {
        // Dit kan niet in setPassword() omdat de Room library die methode ook aanroept.
        this.password = Cryptor.hashPassword(ArrayUtils.combine(password, PASSWORD_SALT.getBytes()));
    }

    public static void addUser(Context context, User user) {
        if (user != null) {
            // We gaan er van uit dat een nieuwe User alleen wordt aangemaakt tijdens registratie
            loggedInUser = user;
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
    public static User getLoggedInUser(){
        return loggedInUser;
    }
    public static void logout() {
        loggedInUser = null;
    }

    @Override
    public String toString() {
        return username;
    }
}
