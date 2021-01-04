package br.com.objective.game.view;

import br.com.objective.game.model.TreeNode;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class RootViewModel {
	
	private final TreeNode<String> root;
	private final Function<TreeNode<String>, Boolean> onNextString;
	private final Function<TreeNode<String>, Boolean> onShot;
	private final Function<String, TreeNode<String>> onDefeat;
	private final Consumer<String> onWinner;
	
	RootViewModel(final Function<TreeNode<String>, Boolean> onNextString, Function<TreeNode<String>, Boolean> onShot,
				  Function<String, TreeNode<String>> onDefeat, Consumer<String> onWinner) {
		this.onNextString = onNextString;
		this.onShot = onShot;
		this.onDefeat = onDefeat;
		this.onWinner = onWinner;
		
		this.root = new TreeNode<String>("Seu prato é massa?", null);
		final TreeNode<String> positiveNode = new TreeNode<String>("lasanha", root);
		final TreeNode<String> negativeNode = new TreeNode<>("bolo do chocolate?", root);
		
		root.setPositiveNode(positiveNode);
		root.setNegativeNode(negativeNode);
	}
	
	public void startSearch() {
		search(this.root);
	}
	
	private void search(TreeNode<String> stringNode) {
		if (stringNode.isLeaf()) {
			shot(stringNode);
		} else {
			final boolean clickYes = onNextString.apply(stringNode);
			final TreeNode<String> nextString = stringNode.getNext(clickYes);
			if (Objects.isNull(nextString)) {
				shot(stringNode);
			} else {
				search(nextString);
			}
		}
	}
	
	private void shot(TreeNode<String> current) {
		final boolean result = onShot.apply(current);
		if (result) {
			onWinner.accept(current.getValue());
		} else {
			getSarchNewItem(current);
		}
	}
	
	private void getSarchNewItem(TreeNode<String> current) {
		final TreeNode<String> newItem = onDefeat.apply(current.getValue());
		if (Objects.nonNull(newItem)) {
			newItem.setNegativeNode(current);
			newItem.setMaster(current.getMaster());
			
			if (newItem.getMaster().getPositiveNode().equals(current)) {
				newItem.getMaster().setPositiveNode(newItem);
			} else {
				newItem.getMaster().setNegativeNode(newItem);
			}
			current.setMaster(newItem);
		}
	}
}
