package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.magic.Normal;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class TeleportToLummy extends Node {

    @Override
    public boolean isValid(ScriptContext context) {
        return context.getPlanking().shouldTeleportToLummy();
    }

    @Override
    public int onLoop(ScriptContext context) {
        if (context.getDreambot().getMagic().castSpell(Normal.LUMBRIDGE_TELEPORT)) {
            MethodProvider.sleepUntil(() -> !context.getPlanking().isInHouse(),5000);
        }
        return 500;
    }

    @Override
    public int priority() {
        return 0;
    }
}
