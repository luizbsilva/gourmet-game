package br.com.objective.game.view.widget;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

public class DialogQuestion extends Alert {
	
	
	public DialogQuestion(final String title, final String message) {
		super(AlertType.CONFIRMATION);
		this.setHeaderText(null);
		this.setTitle(title);
		this.setContentText(message);
		
		this.getButtonTypes().setAll(new ButtonType("Não", ButtonBar.ButtonData.NO),
				new ButtonType("Sim", ButtonBar.ButtonData.YES));
	}
}
