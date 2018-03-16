package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.wrappers.interactive.NPC;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class DemonTalk extends Node {

    private NPC demon;

    @Override
    public boolean isValid(ScriptContext context) {
        demon = context.getDreambot().getNpcs().closest("Demon butler");
        return (demon != null) && demon.distance() <= 2 && !context.getDreambot().getDialogues().inDialogue();
    }

    @Override
    public int onLoop(ScriptContext context) {
        if (demon.interact("Talk-to")) {
            MethodProvider.sleepUntil(() -> context.getDreambot().getDialogues().inDialogue(),1000);
        }
        return 0;
    }

    @Override
    public int priority() {
        return 0;
    }
}
