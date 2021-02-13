package com.blackear.bullshitphysics;

import java.util.Random;

/**
 * @author Jacob Gordon
 * @version 1.0
 * @date 11/17/18
 * This class contains a single instance that holds any other utility objects (instead of creating a new instance every time).
 * This should make caching easier in the future.
 * Also contains many parameters for the simulation.
 **/
public class Pool {

    public static final Pool INST = new Pool();

    /*
    This vector to be manipulated on a per-call basis;
    NOTE: use aux.copy() to avoid ill data manipulation on this shared Vector.
     */
    public Vec2 aux = new Vec2();

    /*
    Random object.
     */
    public Random random = new Random();
}
