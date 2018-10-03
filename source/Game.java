// Part of Escala.
// Written by Spencer Phillips and Tiger Sachse.

package escala;

import escala.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.*;

// Stores the game status and many variables.
public class Game {

    public static enum Difficulty {
        EASY, NORMAL, HARD
    }

    public static enum Background {
        NORMAL, FAST, PAUSED 
    }

    private int width;
    private int height;
    private int goalFPS;
    private double scale;
    private JFrame frame;
    private Portal portal;
    private int gameSpeed;
    private int frameHeight;
    private boolean isRunning;
    private boolean isPaused;
    private Difficulty difficulty;
    private HashMap<String, Region> regions;
    private HashMap<String, BufferedImage> backgrounds;

    // Create a new game state with some defaults.
    public Game() throws SQLException, IOException {

        // Set frame size and other visual variables.
        scale = 1;
        frame = null;
        width = 1152;
        height = 648;
        goalFPS = 60;
        gameSpeed = 1;
        frameHeight = 47;

        // Set game status variables.
        isRunning = false;
        isPaused = false;
        difficulty = Difficulty.NORMAL;

        // Load all backgrounds.
        backgrounds = Portal.getBackgrounds();

        // Load the database and get all regions.
        portal = new Portal();
        regions = portal.getAllRegions();
    }

    // Get the game width.
    public int getWidth() {
        return (int) ((double) width * scale);
    }

    // Get the game height.
    public int getHeight() {
        return (int) ((double) height * scale);
    }

    // Get the resolution scale of the window.
    public double getScale() {
        return scale;
    }

    // Check if the game is running.
    public boolean gameIsRunning() {
        return isRunning;
    }

    // Check if game is paused.
    public boolean gameIsPaused() {
        return isPaused;
    }

    //Returns Speed of Game
    //Range 0-2
    public int getGameSpeed(){
        return this.gameSpeed;
    }


    // Get the goal FPS.
    public int getGoalFPS() {
        return goalFPS;
    }

    // Get the frame time.
    public long getFrameTime() {
        return (long) (1.0 / goalFPS * 1000.0);
    }

    // Get the height of a frame.
    public int getFrameHeight() {
        return frameHeight;
    }

    // Get the game frame.
    public JFrame getFrame() {
        return frame;
    }

    // Get the difficulty of the present frame.
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public BufferedImage getBackground(Background background) {
        if (background == Background.NORMAL) {
            return backgrounds.get("Normal");
        }
        else if (background == Background.FAST) {
            return backgrounds.get("Fast");
        }
        else if (background == Background.PAUSED) {
            return backgrounds.get("Paused");
        }
        else {
            return null;
        }
    }

    // Get a region by name from loaded data.
    public Region getRegion(String name) {
        return regions.get(name);
    }

    // Get a list of all regions.
    public ArrayList<Region> getAllRegions() {
        return new ArrayList<Region>(regions.values());
    }

    // Get a random event.
    public Event getRandomEvent() {
        return portal.getRandomEvent(0.0, 1.0);
    }

    // Get a random positive event.
    public Event getRandomPositiveEvent() {
        return portal.getRandomEvent(0.55, 1.0);
    }

    // Get a random negative event.
    public Event getRandomNegativeEvent() {
        return portal.getRandomEvent(0.0, 0.45);
    }

    // Get a random event with a specific alignment interval.
    public Event getRandomEvent(double minAlign, double maxAlign) {
        return portal.getRandomEvent(minAlign, maxAlign);
    }

    // Set the scale of the window.
    public void setScale(double scale) {
        this.scale = scale;
    }

    // Set the game running flag to false.
    public void stopGame() {
        isRunning = false;
    }

    // Set the game running flag to true.
    public void startGame() {
        isRunning = true;
    }

    // Pause the game.
    public void pauseGame() {
        setGameSpeed(0);
        isPaused = true;
    }

    // Unpause the game.
    public void continueGame() {
        setGameSpeed(1);
        isPaused = false;
    }

    // Speed up the game.
    public void increaseSpeed() {
        isPaused = false;
        setGameSpeed(2);
    }

    // Set the game speed.
    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    // Set the goal FPS.
    public void setGoalFPS(int goalFPS) {
        this.goalFPS = goalFPS;
    }

    // Set the current frame of the game.
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    // Set the difficulty of the game.
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    // Close the database portal.
    public void closePortal() {
        portal.close();
    }
}
