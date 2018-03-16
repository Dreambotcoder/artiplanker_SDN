package io.dreambot.articron.framework;

import io.dreambot.articron.framework.node_blueprints.BaseNode;
import io.dreambot.articron.framework.node_blueprints.Node;
import io.dreambot.articron.framework.node_blueprints.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public class ScriptManager {

    private List<Node> activeNodes = new ArrayList<>();
    private final ScriptContext CONTEXT;
    private final TreeNode BASE_NODE;

    public ScriptManager(ScriptContext context) {
        this.CONTEXT = context;
        BASE_NODE = new BaseNode("root");
        activeNodes.add(BASE_NODE);
    }

    public void pushNodes(Node... children) {
        BASE_NODE.pushChildren(children);
    }

    public void clearParent(String parentId, boolean hardRemove) {
        TreeNode parent = findParent(parentId);
        if (parent != null) {
            parent.clear();
            if (hardRemove) {
                this.activeNodes.remove(parent);
            }
        }
    }

    private TreeNode findParent(String parentId) {
        for (Node node : activeNodes) {
            if (node instanceof TreeNode) {
                TreeNode tNode = (TreeNode) node;
                if (tNode.getNodeName().equals(parentId))
                    return tNode;
            }
        }
        return null;
    }

    public int onLoop() {
        for (Node node : activeNodes) {
            if (node.isValid(CONTEXT)) {
                return node.onLoop(CONTEXT);
            }
        }
        return 600;
    }

}
