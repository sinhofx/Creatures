package classes;

import java.util.Random;

public class Logic {
    Random random;
    
    public Logic() {
        random = new Random();
    }

    public void updateEnemies(Actor enemies[], Map map) {
        int moveX = (random.nextInt(3) - 1) * 5; //-1, 0, or 1
        int moveY = (random.nextInt(3) - 1) * 5;
        enemies[random.nextInt(enemies.length)].move(moveX, moveY, map);
    }
}
