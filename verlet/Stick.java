package com.blackear.bullshitphysics.verlet;

import com.blackear.bullshitphysics.Vec2;

import java.awt.*;

/**
 * @author Jacob Gordon
 * @version 1.0
 * @date 12/1/18
 * This class represents a distance constraint between two verlet-integrated points.
 **/
public class Stick {

    public Point p1;
    public Point p2;
    public float length;

    public Vec2 temp;

    public float stiffness = 1f;

    public boolean render = true;
    public final int thx = 4; //thickness of line
    public EZLine line;

    //constructor
    public Stick(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        temp = new Vec2();
        length = temp.set(p2.pos).sub(p1.pos).getLength();
        if (render) line = EZ.addLine(p1.pos.getX(), p1.pos.getY(), p2.pos.getX(), p2.pos.getY(), Color.DARK_GRAY, thx);
        p1.sticks.add(this); p2.sticks.add(this);
    }

    public void update() {
        if (false) {//codingmath's wau of stick
            float dx = p2.pos.x - p1.pos.x;
            float dy = p2.pos.y - p1.pos.y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);
            float diff = length - distance;
            float perc = diff / distance / 2;
            Vec2 offset = new Vec2(dx * perc, dy * perc);
            if (!p1.pinned) p1.pos.add(offset.copy().scale(-1f));
            if (!p2.pinned) p2.pos.add(offset);
        }

        if (true) {//leo's way to code stick
            temp.set(p2.pos);
            temp.sub(p1.pos);
            float l = temp.getLength();
            float d = (l - length * stiffness);
            temp.normalize();
            temp.scale(d * .5f * stiffness); //diff * elasticity
            if (!p1.pinned) p1.pos.add(temp.copy().scale(1f));
            if (!p2.pinned) p2.pos.add(temp.copy().scale(-1f));
        }

        if (render) {
            line.setPoint1(p1.pos.getX(), p1.pos.getY());
            line.setPoint2(p2.pos.getX(), p2.pos.getY());
        }

    }
}
