package io.dreambot.articron.framework.node_blueprints;

import io.dreambot.articron.framework.ScriptContext;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class BaseNode extends TreeNode {

    public BaseNode(String nodeName) {
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
