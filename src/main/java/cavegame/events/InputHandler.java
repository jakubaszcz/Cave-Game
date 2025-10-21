package cavegame.events;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {

    /*
    * If I understand it right, when I press a key, the key is store into the array keys as true
    * When I release the key is store into the array keys as false
    * It's for the key callback to know if the key is pressed or not
    * This is a C callback function edited for Java
    */

    // Array of boolean to store the state of the key
    private static final boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST /* All possible keys */];

    // Key callback function
    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (key >= 0 && key < keys.length) {
            keys[key] = (action != GLFW_RELEASE /* If the key is not released, set to true, otherwise set to false */);
        }
    }

    // Get the state of the key
    public static boolean isKeyDown(int key) {
        return keys[key];
    }
}
