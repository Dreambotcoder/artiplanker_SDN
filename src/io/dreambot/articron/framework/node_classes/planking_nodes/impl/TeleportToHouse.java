package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */

public class TeleportToHouse extends Node {

    @Override
    public boolean isValid(ScriptContext context) {
        return context.getPlanking().shouldTeleportToHouse();
    }

    @Override
    public int onLoop(ScriptContext context) {

        if (context.getDreambot().getBank().isOpen()) {
            if (context.getDreambot().getBank().close()) {
                MethodProvider.sleepUntil(() -> !context.getDreambot().getBank().isOpen(),1000);
            }
        }
        if (context.getPlanking().teleportToHouse()) {
            MethodProvider.sleepUntil(() -> context.getPlanking().isInHouse(),3500);
        }

        return 500;
    }

    @Override
    public int priority() {
        return 0;
    }
}
