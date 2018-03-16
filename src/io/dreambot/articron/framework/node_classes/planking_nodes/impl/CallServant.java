package io.dreambot.articron.framework.node_classes.planking_nodes.impl;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.Node;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.wrappers.widgets.WidgetChild;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class CallServant extends Node {


    private final int OPTIONS_WIDGET = 261;
    private final int HOUSE_BUTTON_WIDGET = 75;

    @Override
    public boolean isValid(ScriptContext context) {
        return context.getPlanking().shouldPlank();
    }

    @Override
    public int onLoop(ScriptContext context) {
        if (!context.getDreambot().getTabs().isOpen(Tab.OPTIONS)) {
            if (context.getDreambot().getTabs().open(Tab.OPTIONS)) {
                MethodProvider.sleepUntil(() -> context.getDreambot().getTabs().isOpen(Tab.OPTIONS),1000);
            }
        }
        WidgetChild houseButton = context.getDreambot().getWidgets().getWidgetChild(OPTIONS_WIDGET,HOUSE_BUTTON_WIDGET);
        if (houseButton != null && houseButton.isVisible()) {
            if (houseButton.interact()) {
                MethodProvider.sleepUntil(() -> context.getPlanking().canCallServant(),2000);
            }
        }

        if (context.getPlanking().canCallServant()) {
            if (context.getPlanking().callServant()) {
                MethodProvider.sleepUntil(() -> context.getDreambot().getDialogues().inDialogue(),2000);
            }
        }
        return Calculations.random(400,800);
    }

    @Override
    public int priority() {
        return 0;
    }
}
