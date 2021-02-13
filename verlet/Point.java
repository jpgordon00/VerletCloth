package com.blackear.bullshitphysics.verlet;

import com.blackear.bullshitphysics.Vec2;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Jacob Gordon
 * @version 1.0
 * @date 12/1/18
 * An implementation of a Point that uses Verlet integration as its numerical integrator. Velocity manipulation is
 * omited and instead derived directly from its position and last position.
 *
 **/
public class Point {

    public Vec2 window;
    public Vec2 pos;
    public Vec2 posLast;
    public Vec2 vel;//Velocity is derived from pos-posLast in respect to time and is calculated during every time-step.
    public boolean pinned = false;

    public final float friction = 1f;
    public final float restitution = .7f;

    final float diam = 10f;
    public Color color;

    public ArrayList<Stick> sticks; //keep track of sticks linked to this particle!

   //for rendering
   public EZCircle ezCircle;

   public boolean render = false;

    public Point(Vec2 pos, Vec2 posLast, Vec2 window) {
        this.pos = pos;
        this.posLast = posLast;
        vel = new Vec2();
        this.window = window;
        sticks = new ArrayList<>();

         color = Color.DARK_GRAY;
        if (render) ezCircle = EZ.addCircle(pos.intX(), pos.intY(), 5, 5, Color.BLUE, true);
    }

    /*
    Update this point using Verlet integration.
    Integrate, Apply gravity, forces and constraints.
     */
    public void update(Vec2 force) {
        Vec2 aux = new Vec2();
        if (pinned) {
            aux.set(pos);
        }
        vel.set(pos.copy().sub(posLast)); //derive vel
        posLast = pos.copy(); //save last pos before its integrated
      //  vel.scale(friction); //use friction
        pos.add(vel);//integrate
        if (pinned) {
            pos.set(aux);
            posLast.set(aux);
        }

        /*
        Apply forces & constraints using 'pos'.
         */
        applyForces(force);
       // applyConstraints(window.x, window.y);

       // ezCircle.translateTo(pos.x, pos.y);

    }

    public void updateGraphics() {
        if (render) ezCircle.translateTo(pos.x, pos.y);
    }

    /*
    Applies forces directly to the position.
     */
    public void applyForces(Vec2 f) {
        if (pinned) return;
        float gravity = 2f;
        pos.add(0f, gravity);
        pos.add(f);
    }

    /*
    Applies position constraints directly to the position.
     */
    public void applyConstraints(float width, float height) {
        Vec2 aux = new Vec2();
        if (pinned) {
            aux = pos.copy();
        }
        //update our stick constraints first if applicable
        if (!sticks.isEmpty()) {
            for (Stick s : sticks) {
                s.update();
            }
        }
        vel.set(pos);
        vel.sub(posLast);
        vel.scale(friction);

        if (pos.x < diam) {//left side
            pos.x = diam;
            posLast.set(pos.x + vel.x * restitution, posLast.y); //change speed
        } else if (pos.x > width) {//right side
            pos.x = width;
            posLast.set(pos.x + vel.x * restitution, posLast.y); //change speed
        }
        if (pos.y < diam) {//bottom side
            pos.y = diam;
            posLast.set(posLast.x, pos.y + vel.y * restitution); //change speed
        } else if (pos.y > height) { //topside
            pos.y = height;
            posLast.set(posLast.x, pos.y + vel.y * restitution); //change speed
        }
        if (pinned) {
            pos = aux;
            posLast = aux;
        }
    }
}
