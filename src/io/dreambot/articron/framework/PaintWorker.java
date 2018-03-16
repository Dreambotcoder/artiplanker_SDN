package io.dreambot.articron.framework;

import java.awt.*;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class PaintWorker {

    private ScriptContext context;

    public PaintWorker(ScriptContext context) {
        this.context = context;
    }


    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.drawString("ArtiPlanker 1.1", 10, 50);
        g.drawString("Runtime: " + context.getRuntime(), 10, 70);
        g.drawString("Planks made: " + context.getPlankCount(), 10, 90);
    }
}
