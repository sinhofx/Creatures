package classes;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 600;
    public static final int HEIGHT = WIDTH;
    public static int DEFAULT_TILE_SIZE = 16;
    public static int MAP_SIZE_X = 32;
    public static int MAP_SIZE_Y = 32;

    private Dimension size;
    private JFrame frame;
    private boolean running;
    private Thread thread;
    private BufferedImage image;
    private int[] pixels;
    private Random random;

    private Clock clock;
    private Logic logic;
    private Screen screen;
    private Map map;
    private Actor player;
    private Input input;
    private Actor[] enemies;
    private double delta;

    public Game() {
        //top level sizing/buffering
        running = false;
        size = new Dimension(WIDTH, HEIGHT);
        frame = new JFrame();
        frame.setPreferredSize(size);
        frame.pack();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        //top level graphics/controls/logic
        screen = new Screen(); 
        input = new Input();
        clock = new Clock();
        logic = new Logic();
        frame.addKeyListener(input);        
        //create map
        map = new Map(MAP_SIZE_X, MAP_SIZE_Y);
        map.generate();
        //spawn player
        player = new Actor(0, 0, 10, 10, true, 0xFF0000);
        player.spawn(map);              
        //spawn 100 enemies
        random = new Random();
        enemies = new Actor[100];
        for (int i = 0; i < 100; i++) {
            enemies[i] = new Actor(0, 0, random.nextInt(15), random.nextInt(15), false, random.nextInt());
            enemies[i].spawn(map);
        }       
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex);
        }
        System.exit(0);
        
    }

    @Override
    public void run() {
        int FPS = 60;
        delta = 0;
        long initTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double oTime = 1_000_000_000 / FPS;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - initTime) / oTime;
            initTime = now;
            while (delta >= 1) {
                update();
                delta--;
                frames++;
            }
            render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle("Creatures - FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void update() {
        clock.setTime(System.currentTimeMillis());
        input.update(map, player);
        logic.updateEnemies(enemies, map);
        screen.updateCornerPins(player);
    }

    private void render() {
        screen.renderTiles(map, player);
        screen.renderPlayer(player);
        screen.renderEnemies(enemies); //buggy and slow
        //screen.renderFOV(map, player); //buggy and slow
        //screen.renderBorders(); //somewhat broken
       

        for (int i = 0; i < pixels.length; i++) { //don't change this, it works
            int x = (i % WIDTH) + player.getX() - WIDTH / 2;
            int y = (i / HEIGHT) + player.getY() - HEIGHT / 2;
            if (x >= 0 && x < Game.DEFAULT_TILE_SIZE * Game.MAP_SIZE_X && y >= 0 && y < Game.DEFAULT_TILE_SIZE * Game.MAP_SIZE_Y) {
                pixels[i] = screen.getPixels()[x][y]; //center screen around player
            } else {
                pixels[i] = 0;
            }
        }

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        //draw here
        g.drawImage(image, 0, 0, null);
        //stop here
        g.dispose();
        bs.show();

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.add(game);
        game.frame.setVisible(true);
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setFocusable(true);

        game.start();

    }
}
