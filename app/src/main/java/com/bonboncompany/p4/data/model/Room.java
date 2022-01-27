package com.bonboncompany.p4.data.model;

import com.bonboncompany.p4.R;

public enum Room {

    MARIO(R.color.redM, R.drawable.mario),
    LUIGI(R.color.greenLu, R.drawable.luigi),
    PEACH(R.color.pinkT, R.drawable.peach),
    DONKEY(R.color.beigeDo, R.drawable.donkeykong),
    KIRBY(R.color.white, R.drawable.kirby),
    ZELDA(R.color.pinkZ, R.drawable.zelda),
    LINK(R.color.greenLi, R.drawable.link),
    BOWSER(R.color.yellowB, R.drawable.bowser),
    YOSHI(R.color.greenY,R.drawable.yoshi),
    DIDDY(R.color.beigeDi,R.drawable.diddy);

    private final int color;

    private final int icon;

    Room(int color, int icon) {
        this.color = color;
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    public int getColor() {
        return color;
    }

}
