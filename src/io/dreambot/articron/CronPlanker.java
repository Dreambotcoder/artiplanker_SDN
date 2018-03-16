package io.dreambot.articron;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.ScriptManager;
import io.dreambot.articron.framework.node_classes.planking_nodes.PlankTree;
import io.dreambot.articron.framework.node_classes.planking_nodes.impl.*;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.InventoryListener;
import org.dreambot.api.wrappers.items.Item;

import java.awt.*;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
@ScriptManifest(category = Category.MONEYMAKING, name = "ArtiPlanker", author = "Articron", version = 1.1D, description ="Planks in your POH on PVP worlds" )
public class CronPlanker extends AbstractScript implements InventoryListener{

    private ScriptContext context;
    private ScriptManager manager;

    @Override
    public void onStart() {
        context = new ScriptContext(this);
        manager = new ScriptManager(context);
        manager.pushNodes(
                new PlankTree("planking")
                        .pushChildren(new HopWorlds())
                        .pushChildren(new DoBanking())
                        .pushChildren(new TeleportToHouse())
                        .pushChildren(new DemonTalk())
                        .pushChildren(new CallServant())
                        .pushChildren(new MakePlanks())
                        .pushChildren(new TeleportToLummy())
        );
    }

    @Override
    public int onLoop() {
        return manager.onLoop();
    }

    @Override
    public void onPaint(Graphics graphics) {
        context.getPaint().draw((Graphics2D) graphics);
    }

    @Override
    public void onItemChange(Item[] items) {
        for (Item item : items) {
            if (item.getName().contains("logs")) {
                context.incrementPlankCount(item.getAmount());
            }
        }
    }
}
