package org.nlp.utils;

import java.util.*;

/**
 * Created by fangy on 13-12-17.
 * 哈夫曼树
 */
public class HuffmanTree {

//    private TreeSet<HuffmanNode> tree = new TreeSet<HuffmanNode>();

    public static void make(Collection<? extends HuffmanNode> nodes){

        TreeSet<HuffmanNode> tree = new TreeSet<HuffmanNode>(nodes);

        while (tree.size() > 1){
            HuffmanNode left = tree.pollFirst();
            HuffmanNode right = tree.pollFirst();
            HuffmanNode parent = left.merge(right);
            tree.add(parent);
        }

    }

    public static List<HuffmanNode> getPath(HuffmanNode leafNode){

        HuffmanNode hn = leafNode;
        List<HuffmanNode> nodes = new LinkedList<HuffmanNode>();
        while ((hn = hn.getParent()) != null) {
            nodes.add(hn);
        }
        Collections.reverse(nodes);

        return nodes;
    }

}
