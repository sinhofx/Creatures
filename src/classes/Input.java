package classes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Input implements KeyListener {

    private boolean[] keys;
    private int speed;
    private Random random;

    public Input() {
        speed = 2;
        keys = new boolean[255];
        random = new Random();
        for (int i = 0; i < keys.length; i++) {
            keys[i] = false;
        }
    }

    public void update(Map map, Actor player) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i]) {
                switch (i) {
                    case KeyEvent.VK_W:
                        player.move(0, -speed, map);
                        break;
                    case KeyEvent.VK_A:
                        player.move(-speed, 0, map);
                        break;
                    case KeyEvent.VK_S:
                        player.move(0, speed, map);
                        break;
                    case KeyEvent.VK_D:
                        player.move(speed, 0, map);
                        break;
                    default:
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (int i = 0; i < keys.length; i++) {
            keys[e.getKeyCode()] = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (int i = 0; i < keys.length; i++) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
