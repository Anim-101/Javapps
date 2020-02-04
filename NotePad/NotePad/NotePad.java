package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class NotePad extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		
		MenuBar notePadMenuBar = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		Menu editMenu = new Menu("Edit");
		Menu viewMenu = new Menu("View");
		Menu formatMenu = new Menu("Format");
		Menu helpMenu = new Menu("Help");
		
		// File Menu Items
		
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem openMenuItem = new MenuItem("Open");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem saveAsMenuItem = new MenuItem("Save As");
		MenuItem exitMenuItem = new MenuItem("Exit");
		
		fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, exitMenuItem);
		
		// Edit Menu Items
		
		MenuItem undoMenuItem = new MenuItem("Undo");
		MenuItem cutMenuItem = new MenuItem("Cut");
		MenuItem copyMenuItem = new MenuItem("Copy");
		MenuItem pasteMenuItem = new MenuItem("Paste");
		MenuItem deleteMenuItem = new MenuItem("Delete");
		MenuItem findMenuItem = new MenuItem("Find");
		MenuItem replaceMenuItem = new MenuItem("Replace");
		MenuItem goToMenuItem = new MenuItem("Go To");
		MenuItem selectAllMenuItem = new MenuItem("Select All");
		MenuItem timeDateMenuItem = new MenuItem("Time / Date");
		
		editMenu.getItems().addAll(undoMenuItem, cutMenuItem, copyMenuItem, pasteMenuItem, deleteMenuItem, findMenuItem, replaceMenuItem, goToMenuItem, selectAllMenuItem, timeDateMenuItem);
		
		// View Menu Items
		
		MenuItem lineMenuItem = new MenuItem("Line");
		MenuItem statusBarMenuItem = new MenuItem("Status Bar");
		
		viewMenu.getItems().addAll(lineMenuItem, statusBarMenuItem);
		
		// Format Menu Items
		
		MenuItem wordWrapMenuItem = new MenuItem("Word Wrap");
		MenuItem fontMenuItem = new MenuItem("Font");
		
		formatMenu.getItems().addAll(wordWrapMenuItem, fontMenuItem);
		
		// Help Menu Items
		
		MenuItem helpMenuItem = new MenuItem("Help");
		MenuItem aboutMenuItem = new MenuItem("About");
		
		helpMenu.getItems().addAll(helpMenuItem, aboutMenuItem);
		
		notePadMenuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, formatMenu, helpMenu);
		
		// Text Area to Insert Notes
		
		
		
		TextArea textArea = new TextArea();
	
		ScrollPane scrollPane = new ScrollPane(textArea);
		
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		
		
		// Line Alignment Area
		
		HBox hbox = new HBox();
		
		// Sub Pane
		
		BorderPane borderPane = new BorderPane();
		
		borderPane.setTop(notePadMenuBar);		
		borderPane.setCenter(scrollPane);
		borderPane.setBottom(hbox);
		
		Scene scene = new Scene(borderPane, 1080, 720);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("NotePad V0.1");
		primaryStage.show();
	}
	
	public static void main(String [] args) {
		
		launch(args);
	}
}
