package io.dreambot.articron.framework.node_blueprints;

import io.dreambot.articron.framework.ScriptContext;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public abstract class Node {

    public abstract boolean isValid(ScriptContext context);

    public abstract int onLoop(ScriptContext context);

    public abstract int priority();
}
