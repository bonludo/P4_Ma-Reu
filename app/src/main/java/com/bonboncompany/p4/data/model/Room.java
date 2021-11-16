package com.bonboncompany.p4.data.model;


import java.util.Arrays;
import java.util.List;

public class Room {

    public static List<Room> Room = Arrays.asList(
            new Room ("Mario"),
            new Room ("Luigi"),
            new Room ("Peach"),
            new Room ("Donkey"),
            new Room ("Toad"),
            new Room ("Zelda"),
            new Room ("Link"),
            new Room ("Bowser"),
            new Room ("Koopa"),
            new Room ("Diddy"));

   public Room(String name) {
   }

    public static List<com.bonboncompany.p4.data.model.Room> getRoom() {
        return Room;
    }
}
