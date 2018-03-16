package io.dreambot.articron.framework.api;

import io.dreambot.articron.framework.ScriptContext;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.magic.Normal;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.widgets.WidgetChild;


/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class PlankHandler {

    private final ScriptContext CONTEXT;

    public PlankHandler(ScriptContext context) {
        this.CONTEXT = context;
    }

    public boolean shouldTeleportToHouse() {
        return CONTEXT.getDreambot().getInventory().isFull() && !isInHouse();
    }

    public boolean shouldTeleportToLummy() {
        return isInHouse() && !CONTEXT.getDreambot().getInventory().contains(item -> item.getName().contains("logs"));
    }

    public boolean isInHouse() {
        GameObject portal = CONTEXT.getDreambot().getGameObjects().closest(4525);
        return (portal != null);
    }

    public boolean teleportToHouse() {
        return CONTEXT.getDreambot().getMagic().castSpell(Normal.TELEPORT_TO_HOUSE);
    }

    public boolean shouldPlank() {
        return isInHouse() && CONTEXT.getDreambot().getInventory().isFull()
                && CONTEXT.getDreambot().getInventory().contains(item -> item.getName().contains("logs"))
                && !CONTEXT.getDreambot().getDialogues().inDialogue();
    }

    public boolean canCallServant() {
         WidgetChild callWidget = CONTEXT.getDreambot().getWidgets().getWidgetChild(370,15,3);
         return (callWidget != null) && callWidget.isVisible();
    }

    public boolean callServant() {
        WidgetChild callWidget = CONTEXT.getDreambot().getWidgets().getWidgetChild(370,15,3);
        return canCallServant() && callWidget.interact();
    }

    public boolean canPlank() {
        return isInHouse() && CONTEXT.getDreambot().getInventory().isFull()
                && CONTEXT.getDreambot().getInventory().contains(item -> item.getName().contains("logs"))
                && CONTEXT.getDreambot().getDialogues().inDialogue();
    }

    public boolean doPlank() {

        Dialogues d = CONTEXT.getDreambot().getDialogues();
        if (!d.inDialogue()) {
            return CONTEXT.getDreambot().getInventory().contains(item -> item.getName().contains("logs"));
        }

        if (d.canContinue()) {
            if (d.spaceToContinue()) {
                return doPlank();
            }
        }
        if (d.canEnterInput()) {
            if (d.typeOption(CONTEXT.getDreambot().getInventory().count(item -> item.getName().contains("logs")))) {
                return doPlank();
            }
        }
        if (!d.canContinue()) {
            if (d.chooseOption(d.getOptionIndexContaining("Take to sawmill"))) {
                return doPlank();
            }
            if (d.chooseOption(d.getOptionIndexContaining("Yes"))) {
                return doPlank();
            }
            if (d.chooseOption(d.getOptionIndexContaining("Okay, here's"))) {
                return doPlank();
            }
            if (d.chooseOption(d.getOptionIndexContaining("Take them back to the bank"))) {
                return doPlank();
            }
        }
        return true;
    }
}
