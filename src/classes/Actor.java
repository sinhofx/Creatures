package classes;

import java.util.Random;

public class Actor {

    private int x;
    private int y;
    private int sizeX;
    private int sizeY;
    private boolean controllable;
    private int color;

    public Actor(int x, int y, int sizeX, int sizeY, boolean controllable, int color) {
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.controllable = controllable;
        this.color = color;
    }

    public void spawn(Map map) {
        Random random = new Random();
        int spawnX = random.nextInt(map.getSizeX() * Game.DEFAULT_TILE_SIZE);
        int spawnY = random.nextInt(map.getSizeY() * Game.DEFAULT_TILE_SIZE);
        if (map.getMap()[spawnX / Game.DEFAULT_TILE_SIZE][spawnY / Game.DEFAULT_TILE_SIZE].isSolid()) {
            spawn(map);
        } else {
            this.x = spawnX;
            this.y = spawnY;
        }
    }

    public void move(int xOffset, int yOffset, Map map) {
        int moveX = this.x + xOffset;
        int moveY = this.y + yOffset;
        if (!map.getMap()[moveX / Game.DEFAULT_TILE_SIZE][moveY / Game.DEFAULT_TILE_SIZE].isSolid()) {
            this.x = moveX;
            this.y = moveY;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public boolean isControllable() {
        return controllable;
    }

    public void setControllable(boolean controllable) {
        this.controllable = controllable;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
