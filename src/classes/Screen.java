package classes;

import enums.Tile;
import java.util.Random;
import utilities.MathTools;
import utilities.Vector2i;

public class Screen {

    private final int WIDTH = Game.DEFAULT_TILE_SIZE * Game.MAP_SIZE_X;
    private final int HEIGHT = Game.DEFAULT_TILE_SIZE * Game.MAP_SIZE_Y;
    private int[][] pixels;
    private Vector2i topLeft, bottomRight;
    private Random random;

    public Screen() {
        pixels = new int[WIDTH][HEIGHT];
        random = new Random();
        topLeft = new Vector2i(0, 0);
        bottomRight = new Vector2i(WIDTH, HEIGHT);
    }

    public void renderTiles(Map map, Actor player) { //don't change this
        for (int y = topLeft.getY(); y < bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
                int xp = x + topLeft.getX();
                int yp = y + topLeft.getY();
                int tileX = xp / Game.DEFAULT_TILE_SIZE;
                int tileY = yp / Game.DEFAULT_TILE_SIZE;
                if (xp >= 0 && xp < WIDTH && yp >= 0 && yp < HEIGHT) {
                    pixels[xp][yp] = map.getMap()[tileX][tileY].getColor();

                }
            }
        }
    }

    public void renderPlayer(Actor player) {
        for (int y = topLeft.getY(); y < bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
                if (x >= player.getX() && y >= player.getY()
                        && x < player.getX() + player.getSizeX()
                        && y < player.getY() + player.getSizeY()) {
                    pixels[x][y] = player.getColor();
                }
            }
        }
    }

    public void renderEnemies(Actor[] enemies) { //needs optimization
        for (int y = topLeft.getY(); y < bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
                for (Actor en : enemies) {
                    if (x >= en.getX() && y >= en.getY()
                            && x < en.getX() + en.getSizeX()
                            && y < en.getY() + en.getSizeY()) {
                        pixels[x][y] = en.getColor();
                    }
                }
            }
        }
    }

    public void renderFOV(Map map, Actor player) {
        for (float theta = 0; theta < 360; theta += .2) {
            for (int r = 10; r < 50; r++) {
                int x = (int) (MathTools.polarToX(r, theta) + player.getX() + player.getSizeX() / 2);
                int y = (int) (MathTools.polarToY(r, theta) + player.getY() + player.getSizeY() / 2);
                if (x > 0 && x < WIDTH && y > 0 && y < HEIGHT) {
                    Tile currentTile = map.getMap()[x / Game.DEFAULT_TILE_SIZE][y / Game.DEFAULT_TILE_SIZE];
                    if (currentTile.isSolid()) {
                        break;
                    }
                    pixels[x][y] = player.getColor();
                }
            }
        }
    }

    public void renderBorders() { //fix later
        for (int y = topLeft.getY(); y < bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x < bottomRight.getX(); x++) {
                if (x >= 0 && x < WIDTH - 1 && y >= 0 && y < HEIGHT - 1) {
                    if (pixels[x][y] != pixels[x + 1][y]
                            || pixels[x][y] != pixels[x][y + 1]) {
                        pixels[x][y] = 0;
                    }
                }
            }
        }
    }

    public void updateCornerPins(Actor player) {
        //Corner pins are off by almost exactly 40 pixels, for some reason.
        //Origin of that number is unclear. Artifically offsetting this
        //does not stop tearing problems with enemies. This works for now.
        topLeft = new Vector2i(player.getX() - (WIDTH / 2), player.getY() - (HEIGHT / 2));
        bottomRight = new Vector2i(player.getX() + (WIDTH / 2), player.getY() + (HEIGHT / 2));
        //System.out.println("TOP LEFT: " + topLeft.getX() + ", " + topLeft.getY());
        //System.out.println("BOTTOM RIGHT : " + bottomRight.getX() + ", " + bottomRight.getY());
    }

    public int[][] getPixels() {
        return pixels;
    }

    public Vector2i getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Vector2i topLeft) {
        this.topLeft = topLeft;
    }

    public Vector2i getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Vector2i bottomRight) {
        this.bottomRight = bottomRight;
    }

}
