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

Notes:
-Creatures tend to slow the game in large numbers, and with larger maps.
-Creatures currently suffer from some screen tearing/pixel error.
-Black borders around tiles and player/creatures are buggy and are disabled in the code.
-360 degree field of view visualization works, but is slow. It is disabled in the code.
-Corner pins work, but they are imprecise by about 40 pixels, and the render's interpretation of them
  narrows as the player approaches "void" space.
  
Goals:
-Fix screen tearing/pixel error on enemies
-Fix corner pins to use correct coordinates
-Fix corner pins to be properly interpreted when faced with void space
-General optimization of large maps, possibly including a switch to one-dimensional arrays
-Additon of a game manager, such that the user can choose the type/size of map
