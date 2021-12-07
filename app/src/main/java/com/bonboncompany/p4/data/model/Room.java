package com.bonboncompany.p4.data.model;

import android.graphics.Color;

import com.bonboncompany.p4.R;

public enum Room {

    MARIO(R.color.redM),
    LUIGI(R.color.greenLu),
    PEACH(R.color.pinkT),
    DONKEY(R.color.beigeDo),
    TOAD(R.color.white),
    ZELDA(R.color.pinkZ),
    LINK(R.color.greenLi),
    BOWSER(R.color.yellowB),
    YOSHI(R.color.greenY),
    DIDDY(R.color.beigeDi);

    private final int color;

    Room(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

}
