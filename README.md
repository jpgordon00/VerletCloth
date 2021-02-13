# VerletCloth
2D physics using Verlet integration in Java to simulate cloth

[GIF](https://i.gyazo.com/de1f831be7a68ca47eb5ef8440ff7f26.mp4)

![](https://i.gyazo.com/226f98908ea420f17b4c124b819ad833.png)

## What is it?
- A 2D physics engine using [Verlet Integration](https://en.wikipedia.org/wiki/Verlet_integration).
    - Verlet Integration stuck out to me because of its mathematical simplicity; it can be implemeneted in only a few lines of code. After implementing it my self I discovered many built-in features that are comparable to full fledged impulse-based physics engine. Verlet can achieve friction and restitution properties, rigid body physics, and spring simulations for soft body physics.
- A cool looking "3D-like" cloth appearence using simple 2D math.

## What frameworks does it us?
- Java
- EZ, a java graphics framework provided by UH Manoa.
- Verlet Integration for the math implementations

## What I learned.
- Rigid body physics and all software physics simulations are just mathematical functions. While the same can be said about any program, math is at the core of a physics engine. A strong understanding of constraints and derivatives are required to fully understand modern rigid body physics engines.
    - The modern solution to a 2D rigid body physics engine is an impulse engine. This type of physics engine was introduced in 2008 by Erin Catto with the [Box2D](https://github.com/erincatto/box2d) physics engine. This engine provided Angry Birds with its foundation as well as many other app that are still using it today. Erin gives a highly informative [GDC](https://www.youtube.com/watch?v=SHinxAhv1ZE) presentation where he explains the math behind Box2D. Erin goes further and provides a [sample project](https://github.com/erincatto/box2d-lite) written in C++. Another example of an impulse engine is this [tutorial](https://www.youtube.com/watch?v=AzA_owsZU04) which again follows Erin's presented math.
- Like how a picture can paint a thousand words, math can express complex ideas in a compressed format. The math behind verlet integration is absurdely simple; it requires only addition and a few computations per update cycle. 
> Verlet integration to me is anything but obvious. The math for verlet is extremely straightforward yet it can be used for complicated simulations. Verlet simulations can encompass rigid body physics, constraints and even soft body physics. It is truly remarkable how unintuitive the code and math is to me. 
