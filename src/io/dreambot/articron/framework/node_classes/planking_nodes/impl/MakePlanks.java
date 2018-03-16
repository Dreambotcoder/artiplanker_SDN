package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class MakePlanks extends Node {

    @Override
    public boolean isValid(ScriptContext context) {
        return context.getPlanking().canPlank();
    }

    @Override
    public int onLoop(ScriptContext context) {
        context.getPlanking().doPlank();
        return 0;
    }

    @Override
    public int priority() {
        return 0;
    }
}
