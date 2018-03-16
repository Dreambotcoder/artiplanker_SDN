package io.dreambot.articron.framework.node_classes.planking_nodes;

import io.dreambot.articron.framework.ScriptContext;
import io.dreambot.articron.framework.node_blueprints.TreeNode;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class PlankTree extends TreeNode {

    public PlankTree(String nodeName) {
        super(nodeName);
    }

    @Override
    public boolean isValid(ScriptContext context) {
        return true;
    }

    @Override
    public int priority() {
        return 0;
    }
}
