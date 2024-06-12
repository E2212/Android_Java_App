package com.apep.cleaningbuddy.database;

import android.content.Context;

import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.models.User;

import java.security.SecureRandom;
import java.util.Random;

public class DatabaseSeeder {

    private Context context;
    private Random random;

    public DatabaseSeeder() {
        this.random = new SecureRandom();
    }

    public void seedDatabase(Context context) {
        if (Room.getAll(context).isEmpty()) {
            seedRooms();
        }

        if (Task.getAll(context).isEmpty()) {
            seedTasks();
        }

        if (User.getAll(context).isEmpty()) {
            seedUsers();
        }
    }

    private void seedRooms() {
        String[] roomNames = {
                "Krusty Krab", "Chum Bucket", "SpongeBob's Pineapple",
                "Patrick's Rock", "Squidward's House", "Goo Lagoon",
                "Mrs. Puff's Boating School", "Shady Shoals Rest Home",
                "Jellyfish Fields", "Sandy's Treedome"
        };

        for (String roomName : roomNames) {
            Room room = new Room();
            room.setName(roomName);
            Room.addRoom(this.context, room);
        }
    }

    private void seedTasks() {
        String[] taskNames = {
                "Clean Grill", "Mop Floors", "Restock Ingredients",
                "Clean Dining Area", "Inspect Kitchen", "Catch Jellyfish",
                "Feed Gary", "Water Plants", "Polish Trophies", "Clean Windows",
                "Wash Dishes", "Take Out Trash", "Repair Boatmobile",
                "Dust Furniture", "Organize Supplies", "Rake Leaves",
                "Paint Fence", "Vacuum Floors", "Wash Clothes", "Clean Bathroom"
        };

        String[] taskDescriptions = {
                "Clean the grill after closing.",
                "Mop all the floors in the building.",
                "Restock all the necessary ingredients.",
                "Clean the dining area for the customers.",
                "Inspect the kitchen for hygiene and safety.",
                "Catch jellyfish at Jellyfish Fields.",
                "Feed Gary his favorite food.",
                "Water all the plants in the Treedome.",
                "Polish all the trophies and awards.",
                "Clean all the windows until they sparkle.",
                "Wash all the dirty dishes in the kitchen.",
                "Take out the trash to the dumpster.",
                "Repair the boatmobile for the next lesson.",
                "Dust all the furniture in the house.",
                "Organize all the supplies in the closet.",
                "Rake the leaves in the backyard.",
                "Paint the fence to keep it looking new.",
                "Vacuum the floors in the living room.",
                "Wash and dry all the clothes.",
                "Clean the bathroom thoroughly."
        };

        for (int i = 0; i < taskNames.length; i++) {
            Task task = new Task();
            task.setName(taskNames[i]);
            task.setInterval(random.nextInt(7) + 1);
            task.setDescription(taskDescriptions[i]);
            task.setRoomId(random.nextInt(10) + 1);
            Task.addTask(this.context, task);
        }
    }

    private void seedUsers() {
        createUser("Spongebob");
        createUser("Patrick");
        createUser("Squidward");
        createUser("Mr. Krabs");
    }

    private void createUser(String username) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(generateRandomPassword());
        User.addUser(this.context, newUser);
    }

    private byte[] generateRandomPassword() {
        byte[] password = new byte[16];
        random.nextBytes(password);
        return password;
    }
}