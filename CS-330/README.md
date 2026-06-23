# CS-330 Final Reflection

## How do I approach designing software?

1. What new design skills has your work on the project helped you to craft?
   - This project has helped me learn how abstraction can benefit the implementation of legacy libraries. It also taught me how to break down complex objects into individual parts.

2. What design process did you follow for your project work?
   - I started by breaking down the objects in my scene into basic shapes using the methods in ShapeMeshes.cpp. After constructing the most complex object in the scene (the pillar), I isolated that process into a function (DrawPillar(xCoord, zCoord)). Since there would be multiple pillars in a line, the code could be modularized to accept x- and z-values to determine where to construct the pillars. Constructing my code this way resulted in a much cleaner main() function. The next step was to create the environment outside the pillars. Since everything in the environment only needed to be drawn once, I isolated all of the room renderings into DrawRoom(). Once all of the objects were in the appropriate places, I could start implementing detailed aspects about the objects, such as textures, materials, and lighting. Finally, I enabled boundaries to create the effect of being in the room.

3. How could tactics from your design approach be applied in future work?
   - The modular approach to drawing pillars and the room can translate to software development functionality in general. It is best practice to modularize code to make it cleaner and reusable. The way I structured my code would allow me to reuse the pillar function in other environments and to generate frameworks for additional rooms.

## How do I approach developing programs?

1. What new development strategies did you use while working on your 3D scene?
   - This project is the first time I utilized debugging tracking methods. For example, while experimenting with lighting, I recorded the values I tried so I would not repeat the same mistakes while debugging my program. I also incorporated more planning into developing my scene than I normally do. For example, I wrote down all the coordinates of the various objects in the room so they made mathematical sense, rather than just entering values that "looked good." This created a methodical approach to developing the scene, rather than a reactive approach.

2. How did iteration factor into your development?
   - I had to do several iterations during the texturing and lighting phases of development to obtain the precise effect I aimed to recreate. Finding the right stone textures can be surprisingly difficult, and even when I found the right texture, applying it to different objects in the scene produced ugly renderings. It wasn't until adding lighting that the textures came to life. Implementing mipmaps was also a very important step in improving the appearance of textures on objects, particularly circular ones such as spheres and toruses.

3. How has your approach to developing code evolved throughout the milestones, which led you to the project’s completion?
   - At the beginning of my project, I developed the code for the pillar. I quickly realized that entering the same 12 objects repeatedly would be extremely redundant, since only two values changed throughout the pillar's construction. This was the beginning of segmenting my drawings into different functions. One redundancy I had was that my pillar initially included the long box above it, which was created for each pillar instance. Later in development, I decided that even though the redundancy wouldn't have a major effect on memory or rendering, it would still be sloppy to create 5 instances of each of the same two boxes. So, I refactored my code to separate the support beams above the pillars from pillar generation, rather than including them in it. These changes helped shift my mindset to recognizing and eliminating redundancy. It may not matter in a small application such as the 3D scene I'm creating, but in larger applications, redundancies like that can impose significant overhead, unnecessarily bogging down the system.

## How can computer science help me in reaching my goals?

1. How do computational graphics and visualizations give you new knowledge and skills that can be applied in your future educational pathway?
   - I do not see an application of computational graphics and visualization at a 3D scale in my current educational trajectory. However, I would like to learn more about video game development in the future, and this course provided a fundamental understanding of what it entails. The knowledge and skills from this course will be critical as I move into game development environments such as Unity or Unreal Engine, as they provide me with a granular understanding of what happens "under the hood." Understanding the 3D graphics pipeline will undoubtedly provide guidance when implementing features in game development software and shed light on issues encountered during debugging.

2. How do computational graphics and visualizations give you new knowledge and skills that can be applied in your future professional pathway?
   - I do not yet know how graphics and visualizations will serve me in my professional career. However, I worked as a CAD architect for a year, and I have been 3D printing for years. This course has helped me understand how CAD and 3D printing software operate and comprehend various features they offer. For example, I didn't really understand what purpose orthogonal views provided in CAD software. After this course, I have a better understanding of their application and the purpose they serve. 

OpenGL education enabled by https://learnopengl.com/
