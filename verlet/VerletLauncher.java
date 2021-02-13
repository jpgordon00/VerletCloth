package com.blackear.bullshitphysics.verlet;

import com.blackear.bullshitphysics.Vec2;

import java.awt.*;

/**
 * @author Jacob Gordon
 * @version 1.0
 * @date 12/1/18
 **/
public class VerletLauncher implements Runnable {

    static Vec2 window = new Vec2(900, 600);

    //create instance of VerletLauncher in its own thread.
    public static void main(String[] args) {
        new Thread(new VerletLauncher()).start();
    }

    public Rectangle particleR = new Rectangle(0, 0, 5, 5);//basic rectangle to represent bounding box of particle
    public Rectangle mouseR = new Rectangle(0, 0, 30, 30); //rect to represent bounding box of mouse

    public void run() {
        EZ.initialize(Math.round(window.x), Math.round(window.y));

        Point[] points = new Point[3];
        Stick[] sticks = new Stick[5];
        Sim sim = new Sim(points, sticks);

        if (false) {
            // sim = generateCloth(sim, window); //generate the cloth to fill 'points' and 'sticks'.
            //    points = sim.points;
            //    sticks = sim.sticks;
            points[0] = new Point(new Vec2(100f, 100f), new Vec2(125f, 125f), window);
            points[1] = new Point(new Vec2(100f, 150f), new Vec2(125f, 125f), window);
            points[2] = new Point(new Vec2(150f, 100f), new Vec2(125f, 125f), window);
            points[3] = new Point(new Vec2(150f, 150f), new Vec2(125f, 125f), window);
            points[4] = new Point(new Vec2(200f, 200f), new Vec2(200f, 200f), window);
            points[4].pinned = true;


            sticks[0] = new Stick(points[0], points[1]);
            sticks[1] = new Stick(points[1], points[2]);
            sticks[2] = new Stick(points[2], points[3]);
            sticks[3] = new Stick(points[0], points[2]);
            sticks[4] = new Stick(points[0], points[3]);
            sticks[5] = new Stick(points[1], points[3]);
            sticks[6] = new Stick(points[1], points[4]);
            // sticks[6] = new Stick(points[1], points[3]);
        }

        if (true) {

            generateCloth(sim, window);
            points = sim.points;
            sticks = sim.sticks;
        }





        while (!isInvalid(points)) {}; //pause the code to so the sim can generate

        int iterations = 20; //how many times our links are solved per frame

        System.out.println("Starting simulation with (" + points.length + ") points.");
        System.out.println("Iterations: " + iterations);
        System.out.println("And with (" + getValidLength(sticks) + ") sticks.");


        //current and last mouse position
        Vec2 mPos = new Vec2(EZInteraction.getXMouse(), EZInteraction.getYMouse());
        Vec2 mPos2 = mPos.copy();

        Vec2 mouseVel = new Vec2(0f, 0f);//the vel of our mouse

        Vec2 force = new Vec2(0f, 0f); //the force we apply to every particle
        float mouseDampner = .3f;

        /*
        The loop used is as follows:
        (1) update points with verlet integration
        (2) update point constraints
        (3) iteratively solve for stick constraints
        (4) update grapics
         */
        boolean running = true;
        while (running) {
            //update mouse
            mPos.set(EZInteraction.getXMouse(), EZInteraction.getYMouse());
            mouseVel.set(mPos).sub(mPos2);
            mouseR.setLocation(EZInteraction.getXMouse(), EZInteraction.getYMouse());

         //   System.out.println(mouseVel);

            //calculate dt
            float dt = EZ.getDeltaTime() / 1000f;


            updatePoints(points, mouseR, mouseVel.scale(mouseDampner));
            updatePointConstraints(points);
            for (int i = 0; i < iterations; i++) {
                   updateSticks(sticks, dt);
            }
            updateGraphics(points);

            //refresh screen
            EZ.refreshScreen();
            //save mouse pos
            mPos2 = mPos.copy();
        }
    }

    public void updatePoints(Point[] ponts, Rectangle r, Vec2 force) {
        Vec2 f = new Vec2(); //assume no force
        for (Point p: ponts) {
            particleR.setLocation(p.pos.getX(), p.pos.getY());
            if (r.intersects(particleR) && EZInteraction.isMouseLeftButtonDown()) {
                //mouse is toucing this particle...
                f = force.copy();
                if (!p.render) {
                    for (Stick s: p.sticks) {
                        s.line.setColor(Color.ORANGE);
                    }
                }
            } else {
                if (!p.render) {
                    for (Stick s: p.sticks) {
                        s.line.setColor(Color.DARK_GRAY);
                    }
                }
            }
            if (p == null) continue;
            p.update(f);
        }
    }

    /*
Generates the cloth.
 */
    public Sim generateCloth(Sim sim, Vec2 window) {
        Vec2 startPos = new Vec2(100f, 100f); //start top left corner
        Vec2 gap = new Vec2(10f, 10f);
        int w = 40;
        int h = 20;
        int c = 0;
        int r = 0;
        Point[] ps = new Point[(w + 1) * (h + 1)];
        Stick[] sticks = new Stick[w * h * 5];
        for (int y = 0; y <= h; y++) {
            for (int x = 0; x <= w; x++) {
                Vec2 pos = startPos.copy();
                Vec2 g = gap.copy().scale(x, y);
                //  System.out.println("(" + x + ";" + y + ")");
                pos.add(g); //location of the point
                Point p = new Point(pos, pos, window);

                //attach pm to last pm in last
                if (x != 0 ) {
                    sticks[r] = new Stick(p, ps[c-1]);
                    if (y == 0) sticks[r] = new Stick(p, ps[c-1]);
                    r++;
                }
                //attach pm to the right
                //attach PM to PM @ ((y - 1) * (width+1) + x) in list
                int ind = ((y - 1) * (w+1) + x);
                if (y != 0) {
                    //     System.out.println("current (" + c + ") ind (" + ind + ")");
                    Point p2 = ps[ind];
                    sticks[r] = new Stick(p, p2);
                    r++;
                }

                if (y == 0) p.pinned = true;

                //pin if its on top
                if (y == 0) {
                    p.pinned = true;
                }
                ps[c] = p;//add to da list bruh

                c++;//current index
            }
        }
        //   System.out.println("Sticks: " + r);
        sim.points = ps;
        sim.sticks = sticks;
        return sim;
    }

    public void updateSticks(Stick[] sticks, float dt) {
        for (Stick s: sticks) {
            if (s == null) continue;
            s.update();
        }
    }

    public void updatePointConstraints(Point[] ponts) {
        for (Point p: ponts) {
            if (p == null) continue;
            p.applyConstraints(window.x, window.y);
        }
    }

    public void updateGraphics(Point[] points) {
        for (Point p: points) {
            if (p == null) return;
            p.updateGraphics();
        }
    }

    public boolean isInvalid(Point[] ps) {
        for (Point p: ps) {
            if (p == null) return false;
        }
        return true;
    }

    public int getValidLength(Object[] objs) {
        int c =0;
        for (Object o: objs) {
            if (o == null) continue;
            c++;
        }
        return c;

    }

}

class Sim {
    public Point[] points;
    public Stick[] sticks;

    public Sim(Point[] ps, Stick[] s) {
        points = ps; sticks = s;
    }
}
