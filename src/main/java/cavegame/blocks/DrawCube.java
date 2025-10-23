package cavegame.blocks;

import static org.lwjgl.opengl.GL11.*;

public class DrawCube
{
    public void DrawCube(float x, float y, float z, float size)
    {

        // Values are reversed, keep it in mind

        // Set colors
        glColor3f(0.0f, 1.0f, 1.0f);

        // Begin the square
        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + size, y);
        glVertex2f(x + size, y - size);
        glVertex2f(x, y - size);
        glEnd();
        // End the square

        // Push the matrix
        glPopMatrix();
    }

}
