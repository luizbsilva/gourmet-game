package br.com.objective.game.view;

import br.com.objective.game.model.TreeNode;
import br.com.objective.game.view.widget.DialogQuestion;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

public class RootView {
	
	private final RootViewModel rootViewModel;
	
	@FXML
	public void startClickHandle() {
		this.rootViewModel.startSearch();
	}
	
	
	public RootView() {
		rootViewModel = new RootViewModel(this::questionCharacteristic, this::confirmString, this::createDefeatDish,
				this::displayVictoryAlert);
	}
	
	private boolean questionCharacteristic(TreeNode<String> nodeString) {
		final DialogQuestion dialog = new DialogQuestion("Tem esta caracteristica?", nodeString.getValue());
		final Optional<ButtonType> buttonType = dialog.showAndWait();
		
		Boolean createButton = getCreateButton(buttonType);
		if (Objects.nonNull(createButton)) {
			return createButton;
		}
		
		return false;
	}
	
	private Boolean confirmString(TreeNode<String> nodeString) {
		final DialogQuestion dialog = new DialogQuestion("Este é o seu prato?",
				MessageFormat.format("Seu prato é {0}?", nodeString.getValue()));
		final Optional<ButtonType> buttonType = dialog.showAndWait();
		
		Boolean createButton = getCreateButton(buttonType);
		if (Objects.nonNull(createButton)) {
			return createButton;
		}
		
		return null;
	}
	
	private Boolean getCreateButton(Optional<ButtonType> buttonType) {
		if (buttonType.isPresent()) {
			if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
				return true;
			} else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.NO) {
				return false;
			}
		}
		return null;
	}
	
	private TreeNode<String> createDefeatDish(final String dish) {
		
		final TextInputDialog textInputDialog = new TextInputDialog();
		textInputDialog.setTitle("Não sei no que você pensou");
		textInputDialog.setHeaderText(null);
		textInputDialog.setContentText("Qual o prato que você pensou?");
		Optional<String> nameDishOptional = textInputDialog.showAndWait();
		
		if (nameDishOptional.isPresent() && !nameDishOptional.get().trim().isEmpty()) {
			final String nameDish = nameDishOptional.get();
			
			final TextInputDialog characteristicDialog = new TextInputDialog();
			characteristicDialog.setTitle("Não sei no que você pensou");
			characteristicDialog.setHeaderText(null);
			if (dish != null) {
				characteristicDialog
						.setContentText(MessageFormat.format("{0} é ________, mas {1} não.", nameDish, dish));
			} else {
				characteristicDialog.setContentText(MessageFormat.format("{0} é ________.", nameDish));
			}
			
			final Optional<String> characteristicOptional = characteristicDialog.showAndWait();
			
			if (characteristicOptional.isPresent() && !characteristicOptional.get().trim().isEmpty()) {
				final TreeNode<String> resultNode = new TreeNode<String>(
						MessageFormat.format("Seu prato é {0}?", characteristicOptional.get()), null);
				final TreeNode<String> sim = new TreeNode<String>(nameDish, resultNode);
				resultNode.setPositiveNode(sim);
				return resultNode;
			}
		}
		
		return null;
	}
	
	private void displayVictoryAlert(String prato) {
		final Alert alertVictory = new Alert(Alert.AlertType.INFORMATION);
		alertVictory.setHeaderText(null);
		alertVictory.setTitle("Vitória");
		alertVictory.setContentText(MessageFormat.format("Acertei, sabia que você gostava de {0}", prato));
		alertVictory.showAndWait();
	}
	
}
