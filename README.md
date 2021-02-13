# VerletCloth
2D physics using Verlet integration in Java to simulate cloth

![https://i.gyazo.com/226f98908ea420f17b4c124b819ad833.png]

## What is it?
- A 2D physics engine using [Verlet Integration](https://en.wikipedia.org/wiki/Verlet_integration).
    - Verlet Integration stuck out to me because of its mathematical simplicity; it can be implemeneted in only a few lines of code. After implementing it my self I discovered many built-in features that are comparable to full fledged impulse-based physics engine. Verlet can achieve friction and restitution properties, rigid body physics, and spring simulations for soft body physics.
- A cool looking "3D-like" cloth appearence using simple 2D math.

## What I learned.
- Rigid body physics and all software physics simulations are just mathematical functions. While the same can be said about any program, math is at the core of a physics engine. A strong understanding of constraints and derivatives are required to fully understand modern rigid body physics engines.
    - The class example of 2D rigid body is an impulse engine. This type of physics engine was introduced in 2008 by Erin Catto with the [Box2D](https://github.com/erincatto/box2d) physics engine. This engine provided Angry Birds with its foundation as well as many other app that are still using it today. Erin gives a highly informative [GDC](https://www.youtube.com/watch?v=SHinxAhv1ZE) presentation where he explains the math behind Box2D. Erin goes further and provides a [sample project](https://github.com/erincatto/box2d-lite) written in C++. Another example of an impulse engine is this [tutorial](https://www.youtube.com/watch?v=AzA_owsZU04) which again follows Erin's presented math.
