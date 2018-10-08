// Part of Escala.
// Written by Tiger Sachse.

package escala.structures;

import java.util.*;
import escala.structures.*;

// Holds a graph of skills.
public class SkillTree {
    private String treeID;
    private HashMap<Integer, Node> nodes;

    // Construct a new skill tree with an ID.
    public SkillTree(String treeID) {
        this.treeID = treeID;
        nodes = new HashMap<>();
    }

    // Add a skill and its children to the tree.
    public void addSkill(Skill skill, HashSet<Integer> children) {
        nodes.put(skill.getID(), new Node(skill, children));
    }

    // Adjust all the parent counters for each node.
    public void adjustParents() {
        for (Node node : nodes.values()) {
            for (int child : node.getChildren()) {
                nodes.get(child).incrementParents();
            }
        }
    }

    public ArrayList<Skill> getAvailableSkills() {
        return null;
    }

    public ArrayList<Skill> getUnavailableSkills() {
        return null;
    }

    public ArrayList<Skill> getAllSkills() {
        return null;
    }

    public boolean buySkill(int skillID) {
        return false;
    }

    public Skill getSkill(int skillID) {
        return null;
    }

    // Return a string representation of the tree.
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            builder.append(entry.getValue());
            builder.append("\n");
        }

        return builder.toString();
    }
}

// Holds a skill and graph information in a skill tree.
class Node {
    private Skill skill;
    private int parents;
    private boolean purchased;
    private HashSet<Integer> children;

    // Create a new node with a skill and a specified child set.
    public Node(Skill skill, HashSet<Integer> children) {
        this.skill = skill;
        this.children = children;

        parents = 0;
        purchased = false;
    }

    // Add to the parent count for this node.
    public void incrementParents() {
        parents++;
    }

    // Get an integer array of this node's children.
    public int[] getChildren() {
        int index = 0;
        int[] children = new int[this.children.size()];

        for (int child : this.children) {
            children[index] = child;
            index++;
        }

        return children;
    }

    // Create a string representation of this node.
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(skill.toString() + "\n");
        builder.append(String.format("purchased: %s\n", String.valueOf(purchased)));
        builder.append(String.format("parents: %d\n", parents));
        builder.append("children:");

        for (Integer child : children) {
            builder.append(String.format(" %s", child.toString()));
        }
        builder.append("\n");

        return builder.toString();
    }
}
