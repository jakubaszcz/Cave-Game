package cavegame;

import cavegame.blocks.DrawCube;
import cavegame.entity.Player;
import cavegame.events.InputHandler;
import cavegame.world.World;
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

    // Instantiate our first entity
    Player player = new Player();

    // Instantiate our first block
    DrawCube drawCube = new DrawCube();


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
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_ANY_PROFILE); // allows glBegin/glEnd

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
        glfwSetKeyCallback(window, InputHandler::keyCallback /* Input handler class, located in the events package */);

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
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);

        /* This line is important to allow you to "draw" on the window
         * Without it you probably can't do much */
        GL.createCapabilities();

        System.out.println("OpenGL version: " + glGetString(GL_VERSION));


        // Enable V-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, size.width, size.height, 1, 1, 100);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

    }

    private void loop() {

        // Set the background color
        glClearColor(0f, 0f, 0f, 1f); // black background


        while (!glfwWindowShouldClose(window)) {
            // Clear the windows every frame
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


            if (InputHandler.isKeyDown(GLFW_KEY_ESCAPE)) {
                glfwSetWindowShouldClose(window, true);
            }


            // Draw a cube
            drawCube.DrawCube(400, 300, 0, 100f);

            // Swap
            glfwSwapBuffers(window); // Set to blank

            // Allow using event, key callback, etc.
            glfwPollEvents();
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    // Equivalant to the main class in C or C++
    public static void main(String[] args) {

        // Create a world
        World world = new World(new World.WorldCoordinates(4, 4, 4));

        // Make the run function be the main function
        new Main().run();
    }


}