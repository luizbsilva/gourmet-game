package br.com.objective.game.model;

public class TreeNode<T> {
	
	private final T value;
	
	private TreeNode<T> master;
	
	private TreeNode<T> positiveNode;
	
	private TreeNode<T> negativeNode;
	
	public TreeNode(T value, TreeNode<T> master) {
		this.value = value;
		this.master = master;
	}
	
	public TreeNode<T> getNext(final boolean isNext) {
		if (isNext) {
			return positiveNode;
		} else {
			return negativeNode;
		}
		
	}
	
	public boolean isLeaf() {
		return positiveNode == null && negativeNode == null;
	}
	
	public T getValue() {
		return value;
	}
	
	public TreeNode<T> getMaster() {
		return master;
	}
	
	public void setMaster(TreeNode<T> master) {
		this.master = master;
	}
	
	public TreeNode<T> getPositiveNode() {
		return positiveNode;
	}
	
	public void setPositiveNode(TreeNode<T> positiveNode) {
		this.positiveNode = positiveNode;
	}
	
	public TreeNode<T> getNegativeNode() {
		return negativeNode;
	}
	
	public void setNegativeNode(TreeNode<T> negativeNode) {
		this.negativeNode = negativeNode;
	}
	
}
