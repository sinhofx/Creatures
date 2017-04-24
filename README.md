# Creatures
A small tile-based game in pure Java

Controls:
WASD

Feature list:
-60 FPS that is fairly smooth and consistent
-Hex color RGB graphics through Java's BufferedImage class
-Randomly generated terrain with grass, water, mud, and a border of void tiles
-Randomized quad enemies that are passive and move on their own
-Controllable character with collision detection on solid surfaces
-Optional FOV visualization

Bugs/Issues:
-Too many creatures slows the game. Considering handling spawning in chunks.
-Black borders around tiles and player/creatures are buggy and are disabled in the code.
-360 degree field of view visualization works, but is slow. It is disabled in the code.
  
Goals:
-Move to sprite-based graphics for greater detail.
-Additon of a game manager, such that the user can choose the type/size of map
