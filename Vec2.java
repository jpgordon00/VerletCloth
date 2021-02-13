package com.blackear.bullshitphysics;

import java.util.Random;

/**
 * @author Jacob Gordon
 * @version 1.0
 * @date 11/17/18
 **/
public class Vec2 {

    /*
   Position of the vector.
    */
    public float x, y;

    /*
    Constructor that accepts floats for x and y.
     */
    public Vec2(float x, float y) {
        this.x = x; this.y = y;
    }

    public int getX() {
        return Math.round(x);
    }

    public int getY() {
        return Math.round(y);
    }

    /*
    Constructor that accepts a Vector2.
     */
    public Vec2(Vec2 v) {
        this.x = v.x; this.y = v.y;
    }

    /*
    Constructor without params default to 0f.
     */
    public Vec2() {
        this.x = 0f; this.y = 0f;
    }

    /*
    Returns a copy of this vec.
     */
    public Vec2 copy() {
        return new Vec2(x, y);
    }

    /*
    Adds floats x and y to this vec.
    Returns vec for chaining.
     */
    public Vec2 add(float x, float y) {
        this.x += x; this.y += y;
        return this;
    }

    /*
    Adds vector v to this vec.
    Returns vec for chaning.
     */
    public Vec2 add(Vec2 v) {
        return add(v.x, v.y);
    }


    /*
    Subs floats x and y to this vec.
    Returnns vec for chaining.
     */
    public Vec2 sub(float x, float y) {
        this.x -= x; this.y -= y;
        return this;
    }

    /*
    Subs vector v to this vec.
    Returns vec for chaning.
     */
    public Vec2 sub(Vec2 v) {
        return sub(v.x, v.y);
    }

    /*
    Returns the DOT PRODUCT of this vector compared to vector 'v'.
     */
    public float dot (Vec2 v) {
        return x * v.x + y * v.y;
    }

    /*
    Multiplies this vector by scalar floats 'x' and 'y'.
    Returns vec for chaining.
     */
    public Vec2 scale(float x, float y) {
        this.x *= x; this.y *= y;
        return this;
    }


    /*
    Mltiplies this vector by scalar floats 'xy'.
    Returns vec for chaining.
     */
    public Vec2 scale(float xy) {
        this.x *= xy; this.y *= xy;
        return this;
    }

    /*
    Multiplies this vector by scalar Vector.
    Returns vec for chaining.
     */
    public Vec2 scale(Vec2 scalar) {
        return scale(scalar.x, scalar.y);
    }

    /*
    Divides this vector by floats 'x' and 'y'.
    Returns this Vector for chaining.
     */
    public Vec2 div(float x, float y) {
        this.x /= x; this.y /= y;
        return this;
    }

    public Vec2 div(float xy) {
        this.x /= xy; this.y /= xy;
        return this;
    }

    /*
    Divides this vector by vector 'v'.
    Returns this vector.
     */
    public Vec2 div(Vec2 v) {
        return div(v.x, v.y);
    }

    /*
    Returns distance of this vector and 'v'.
    NOTE: I did not write this method, taken from LibGDX @ Vector2 class.
    @author com.badlogic.gdx
     */
    public float dst (Vec2 v) {
        final float x_d = v.x - x;
        final float y_d = v.y - y;
        return x_d * x_d + y_d * y_d;
    }


    /*
    Sets this Vectors value equal to 'x' and y'
    Returns this Vector for chaining.
     */
    public Vec2 set(float x, float y) {
        this.x = x; this.y = y;
        return this;
    }

    public Vec2 set(float xy) {
        return set(xy, xy);
    }


    /*
    Sets this Vectors value equal to the inputted Vector 'v'.
    Returns this vector for chaining
     */
    public Vec2 set(Vec2 v) {
        this.x = v.x; this.y = v.y;
        return this;
    }


    /*
    Returns the length of this vector.
     NOTE: calculation uses square root.
     */
    public float getLength() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /*
    Normalizes this float between the whole number float 0f - 1f.
    Returns this vector for chaining
     */
    public Vec2 normalize() {
        float l = getLength();
        if (l == 0) return this;
        return scale(1 / l);
    }

    public boolean[] isGreater(float x, float y) {
        boolean[] b = new boolean[2];
        b[0] = this.x > x;
        b[1] = this.y > y;
        return b;
    }

    public boolean[] isSmaller(float x, float y) {
        boolean[] b = new boolean[2];
        b[0] = this.x < x;
        b[1] = this.y < y;
        return b;
    }

    public boolean[] isGreater(Vec2 v) {
        return isGreater(v.x, v.y);
    }

    public boolean[] isSmaller(Vec2 v) {
        return isSmaller(v);
    }

    public boolean isBigger(float x, float y) {
        return this.x > x && this.y > y;
    }

    public boolean isBigger(Vec2 v) {
        return isBigger(v.x, v.y);
    }

    public Vec2 abs() {
        this.x = Math.abs(x); this.y = Math.abs(y);
        return this;
    }

    public boolean doesEqual(float x, float y) {
        return this.x == x && this.y == y;
    }

    public boolean doesEqual(Vec2 v) {
        return doesEqual(v.x, v.y);
    }

    public int intX() {
        return Math.round(x);
    }

    public int intY() {
       return Math.round(y);
    };

    /*
    Sets random float between '0' and '1'
    Returns this for chaining.
     */
    public Vec2 randomX() {
        x = Pool.INST.random.nextFloat();
        return this;
    }

    /*
    Sets random float between 'min' and 'max'.
     */
    public Vec2 randomX(float max) {
        float f = Pool.INST.random.nextFloat();
        x = f;
        return this;
    }

    public Vec2 randomY() {
        Random r = Pool.INST.random;
        y = r.nextInt();
        return this;
    }

    public void print() {
        System.out.println(this.toString());
    }



    /*
    Adds 'v * s' to current.
    Returns this vector for chaining.
     */
    public Vec2 addsi( Vec2 v, float s )
    {
        v.scale(s);
        return add(v.x, v.y);
    }
    @Override
    public String toString() {
        return "x=" + x + ",y=" + y;
    }
}
