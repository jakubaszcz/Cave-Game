package cavegame;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    // Window handle
    private long window;

    // Window title
    private String title = "Cave Game";

    // Window size
    public static class Size {
        public int width;
        public int height;
    }


    public void run() {

        // Initialize GLFW
        init();

        // Run the rendering loop until the user has attempted to close
        loop();

        // Free the window callbacks and destroy the window
        {
            glfwFreeCallbacks(window);
            glfwDestroyWindow(window);

            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }


    private void init() {

        // Setup window size
        Size size = new Size();
        size.width = 800;
        size.height = 600;

        // Setup an error callback. The default implementation
        GLFWErrorCallback.createPrint(System.err).set();

        // Check if glfw is initialized
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Window Hint
        glfwDefaultWindowHints();

        // Works like statistics
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // Make the next window visible
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // Make it also resizable

        // Create the windows
        window = glfwCreateWindow(size.width /* Give the width we init earlier */, size.height /* Give the height we init earlier */, title /* Give the title*/,
                NULL /* The monitor use
                    MemoryUtil.NULL -> for windowed mode
                    GLFW.glfwGetPrimaryMonitor() -> for fullscreen*/,
                NULL /* Context to share ressource with
                    MemoryUtil.NULL -> no sharing
                    Another window's context handle -> share texture, buffer, etc..*/);

        // Check if the window has been initilized
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Create key callack
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            // If player click a choosen key, the window close
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });



        try (MemoryStack stack = stackPush()) {

            // Create array of int size 1, [0, 1]
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Get size and put it in the int buffer
            glfwGetWindowSize(window, pWidth, pHeight);

            // get size of the monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Calculate the size of monitor and of the window to give them a spawn position of the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.width() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);

        // Enable V-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

    }

    private void loop() {
        /* This line is important to allow you to "draw" on the window
        * Without it you probably can't do much */
        GL.createCapabilities();

        // Set the background color
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);


        while (!glfwWindowShouldClose(window)) {
            // Clear the windows every frame
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Swap
            glfwSwapBuffers(window);

            // Allow to use event, key callback etc..
            glfwPollEvents();
        }
    }

    // Equivalant to the main class in C or C++
    public static void main(String[] args) {

        // Make the run function be the main function
        new Main().run();
    }


}