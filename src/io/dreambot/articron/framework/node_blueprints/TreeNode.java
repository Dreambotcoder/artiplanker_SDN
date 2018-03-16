package io.dreambot.articron.framework.node_blueprints;

import io.dreambot.articron.framework.ScriptContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Articron
 * Date:   25/06/2017.
 */
public abstract class TreeNode extends Node {

    private List<Node> children = new ArrayList<>();
    private final String NODE_NAME;

    public TreeNode(String nodeName) {
        this.NODE_NAME = nodeName;
    }

    public TreeNode pushChildren(Node... children) {
        Collections.addAll(this.children,children);
        this.children.sort(Comparator.comparingInt(Node::priority));
        return this;
    }

    public void clear() {
        children.clear();
    }

    @Override
    public int onLoop(ScriptContext context) {
        for (Node child : this.children) {
            if (child.isValid(context)) {
                return child.onLoop(context);
            }
        }
        return 600;
    }

    public String getNodeName() {
        return NODE_NAME;
    }

}
