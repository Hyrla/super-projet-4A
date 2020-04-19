package com.esiea.tp4A;

import com.esiea.tp4A.domain.Direction;

public class Position implements com.esiea.tp4A.domain.Position {
    @Override
    public int getX() {
        return this.getX();
    }

    @Override
    public int getY() {
        return this.getY();
    }

    @Override
    public Direction getDirection() {
        return this.getDirection();
    }

    @Override
    public String toString(){
        return "(" + getX() +","+getY()+","+getDirection()+")";
    }
}
