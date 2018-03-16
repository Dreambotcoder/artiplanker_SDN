package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.GameObject;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class DoBanking extends Node {
    @Override
    public boolean isValid(ScriptContext context) {
        return !context.getPlanking().isInHouse() && !context.getDreambot().getInventory().contains(item -> item.getName().contains("logs"));
    }

    @Override
    public int onLoop(ScriptContext context) {
        if (!context.getDreambot().getBank().isOpen()) {
            GameObject bankChest = context.getDreambot().getGameObjects().closest("Bank chest");
            if (bankChest != null && bankChest.exists() && bankChest.interact("Use")) {
                MethodProvider.sleepUntil(() -> context.getDreambot().getBank().isOpen(), 2000);
            }
        }

        if(context.getDreambot().getInventory().contains(item -> item.getName().contains("plank"))) {
            if (context.getDreambot().getBank().depositAll(item -> item.getName().contains("plank"))) {
                MethodProvider.sleepUntil(() -> !context.getDreambot().getInventory().contains(item -> item.getName().contains("plank")),1000);
            }
        }

        if (context.getDreambot().getBank().withdrawAll("Oak logs")) {
            MethodProvider.sleepUntil(() -> context.getDreambot().getInventory().contains("Oak logs"),1000);
        }

        return Calculations.random(250,500);
    }

    @Override
    public int priority() {
        return 0;
    }
}
