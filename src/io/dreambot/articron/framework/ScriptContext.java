package io.dreambot.articron.framework;

import io.dreambot.articron.framework.api.PlankHandler;
import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.utilities.Timer;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class ScriptContext {

    private final MethodContext M_CONTEXT;
    private PlankHandler plankHandler;
    private Timer timer;
    private PaintWorker paint;
    private int plankCount;


    public ScriptContext(MethodContext context) {
        this.M_CONTEXT = context;
        this.plankHandler = new PlankHandler(this);
        timer = new Timer();
        this.paint = new PaintWorker(this);
        plankCount = 0;
    }

    public MethodContext getDreambot() {
        return M_CONTEXT;
    }

    public PlankHandler getPlanking() {
        return plankHandler;
    }

    public boolean isInPVPWorld() {
        int currentWorld = M_CONTEXT.getClient().getCurrentWorld();
        return M_CONTEXT.getWorlds().getWorld(currentWorld).isPVP();
    }

    public void incrementPlankCount(int amount) {
        plankCount += amount;
    }
    public int getPlankCount() {
        return plankCount;
    }

    public String getRuntime() {
        return timer.formatTime();
    }


    public PaintWorker getPaint() {
        return paint;
    }

    /**
     * TODO cleaning
     **/
    public boolean hopToPVP(boolean pvp) {
        if (pvp) {
            return M_CONTEXT.getWorldHopper().hopWorld(M_CONTEXT.getWorlds().getWorld(world -> world.isMembers() && world.isPVP()));
        }
        return M_CONTEXT.getWorldHopper().hopWorld(M_CONTEXT.getWorlds().getWorld(world -> world.isMembers() && !world.isPVP()));
    }
}
