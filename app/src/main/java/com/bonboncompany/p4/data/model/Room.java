package com.bonboncompany.p4.data.model;

import com.bonboncompany.p4.R;

public enum Room {

    MARIO ("Mario",R.color.redM, R.drawable.mario),
    LUIGI ("Luigi",R.color.greenLu, R.drawable.luigi),
    PEACH ("Peach",R.color.pinkZ, R.drawable.peach),
    DONKEY ("Donkey",R.color.beigeDo, R.drawable.donkeykong),
    KIRBY ("Kirby",R.color.pinkZ, R.drawable.kirby),
    ZELDA ("Zelda",R.color.pinkT, R.drawable.zelda),
    LINK ("Link",R.color.greenLi, R.drawable.link),
    BOWSER ("Bowser",R.color.yellowB, R.drawable.bowser),
    YOSHI ("Yoshi",R.color.greenY,R.drawable.yoshi),
    DIDDY ("Diddy",R.color.beigeDi,R.drawable.diddy);

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    private final int color;

    private final int icon;

    Room(String name, int color, int icon) {
        this.name = name;
        this.color = color;
        this.icon = icon;
    }

    public String getName() { return name; }

    public int getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
