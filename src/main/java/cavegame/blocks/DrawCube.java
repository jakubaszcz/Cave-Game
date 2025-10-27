package cavegame.blocks;


import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class DrawCube
{

    Random random = new Random();

    public void DrawCube(float x, float y, float z, float size)
    {
        // Save the current matrix
        glPushMatrix();

        float half = size / 2;

        // Drawing with vertices
        float[][][] vertices = {
                // Front
                {
                        {x - half, y - half, z - half}, // Bottom left
                        {x + half, y - half, z - half}, // Bottom right
                        {x + half, y + half, z - half}, // Top right
                        {x - half, y + half, z - half} // Top left

                        /*
                        * (x, y + size, z - size)   (x + size, y + size, z - size)
                        *
                        *
                        * (x, y, z - half)          (x + size, y, z - size)
                        *
                        * */

                },
                // Back
                {
                        {x - half, y - half, z + half},
                        {x + half, y - half, z + half},
                        {x + half, y + half, z + half},
                        {x - half, y + half, z + half}

                        /*
                        * (x, y + size, z + size)   (x + size, y + size, z + size)
                        *
                        *
                        * (x, y, z + half)          (x + size, y, z + size)
                        *
                        * */
                },
                // Left
                {
                        {x - half, y - half, z + half},
                        {x, y, z - half},
                        {x, y + half, z - half},
                        {x, y + half, z + half}

                        /*
                        * (x, y + size, z + size)   (x, y + size, z + size)
                        *
                        *
                        * (x, y, z + size)          (x, y, z - size)
                        *
                        * */
                },
                // Right
                {
                        {x + half, y, z - half},
                        {x + half, y, z + half},
                        {x + half, y + half, z + half},
                        {x + half, y + half, z - half}

                        /*
                        * (x, y + size, z - size)   (x + size, y + size, z + size)
                        *
                        *
                        * (x + size, y, z - half)          (x + size, y, z + size)
                        *
                        * */
                },
                // Top
                {
                        {x, y + half, z - half},
                        {x + half, y + half, z - half},
                        {x + half, y + half, z + half},
                        {x, y + half, z + half}

                        /*
                        * (x, y + size, z + size)   (x + size, y + size, z + size)
                        *
                        *
                        * (x, y + size, z - half)          (x + size, y + size, z - size)
                        *
                        * */
                },
                // Bottom
                {
                        {x, y, z - half},
                        {x + half, y, z - half},
                        {x + half, y, z + half},
                        {x, y, z + half}

                        /*
                        * (x, y, z - size)   (x + size, y, z - size)
                        *
                        *
                        * (x, y, z + half)          (x + size, y, z + size)
                        *
                        * */
                }
        };


        glBegin(GL_QUADS);
        for (float[][] vertex : vertices) {
            glColor3f(random.nextFloat(), random.nextFloat(), random.nextFloat());
            for (float[] point : vertex) {
                glVertex3f(point[0], point[1], point[2]);
            }
        }
        glEnd();

        // Restore the matrix
        glPopMatrix();
    }

}
