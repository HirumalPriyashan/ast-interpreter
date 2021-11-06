package Standardizer;

import Node.Node;

/**
 * Abstraction for Standardizer 
 * 
 * @author Hirumal Priyashan
 * @version 1.0
 * @since 1.0
 */
public interface Standardizer {
    /**
    * Execute the standardizing process
    * Standardize the node and its children if its not already 
    * standardized otherwise return 
    *
    * @param  node  node to be standardize
    */
    public void standardize(Node node);
}
