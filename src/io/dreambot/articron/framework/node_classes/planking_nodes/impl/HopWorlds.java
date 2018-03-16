package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */

public class HopWorlds extends Node {

    @Override
    public boolean isValid(ScriptContext context) {
        return !context.isInPVPWorld();
    }

    @Override
    public int onLoop(ScriptContext context) {
        if (context.getDreambot().getDialogues().chooseOption(context.getDreambot().getDialogues().getOptionIndexContaining("Switch"))) {
            MethodProvider.sleepUntil(context::isInPVPWorld,1000);
        }

        if (context.hopToPVP(true)) {
            MethodProvider.sleepUntil(context::isInPVPWorld,1000);
        }


        return Calculations.random(500,1000);
    }

    @Override
    public int priority() {
        return 0;
    }
}
