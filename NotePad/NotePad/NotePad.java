package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCodeCombination;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

public class NotePad extends Application {
	
	String openFileLocation = "", saveFileLocation = "";
	
	int toggleNumber = 0;
	
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
		MenuItem wordMenuItem = new MenuItem("Word");
		MenuItem statusMenuItem = new MenuItem("Status");
		
		viewMenu.getItems().addAll(lineMenuItem, wordMenuItem, statusMenuItem);
		
		// Format Menu Items
		
		MenuItem wrapMenuItem = new MenuItem("Wrap");
		MenuItem fontMenuItem = new MenuItem("Font");
		
		formatMenu.getItems().addAll(wrapMenuItem, fontMenuItem);
		
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
		
		// Line - Word Alignment Area
		
		HBox hbox = new HBox();
		
		Label lineLabel = new Label();
		
		Label wordLabel = new Label();
		
		// * Event Handling
		
		// ** File MenuItem Event Handling
		
		// -> New Text File
		
		// --- Starts
		
		newMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				saveFileLocation = "";
				
				textArea.setText("");
			}
		});
		
		// --- Ends
		
		// -> Open Text File
		
		// --- Starts
		
		openMenuItem.setOnAction(new EventHandler<ActionEvent> () {
		
			public void handle(ActionEvent event) {
				
				FileChooser fileChooser = new FileChooser();
				
				FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("txt files", "*.txt");
				
				fileChooser.getExtensionFilters().add(extension);
				
				File openFile = fileChooser.showOpenDialog(primaryStage);
				
				try {
					
					FileReader readFile = new FileReader(openFile);
					
					Scanner scanner = new Scanner(readFile);
					
					String text = "";
					
					while(scanner.hasNextLine()) {
						
						text = text + scanner.nextLine();
						
						text = text + "\n";
					}
					
					textArea.setText(text);
					
					saveFileLocation = openFile.getPath();
					
					scanner.close();
					
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		// --- Ends
		
		// -> Save Text File
		
		// --- Starts
		
		saveMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				if(saveFileLocation.equals("")) {
					
					if(textArea.getText().equals("") || textArea.getText().equals(null)) {
						
						Alert alert = new Alert(AlertType.ERROR);
						
						alert.setTitle("Empty File");
						alert.setHeaderText(null);
						alert.setContentText("Can't Save, It's an Empty File");
						alert.showAndWait();
					}
					else {
											
						FileChooser fileChooser = new FileChooser();
						
						FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("txt files", "*.txt");
					
						fileChooser.getExtensionFilters().add(extension);
						
						File saveFile = fileChooser.showSaveDialog(primaryStage);
						
						saveFileLocation = saveFile.getPath();
						
						try {
							
							if(saveFile != null) {
																
									PrintWriter writeFile = new PrintWriter(saveFile);
									
									writeFile.println(textArea.getText());
									
									writeFile.close();
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}
					}					
				}
				else {
				
					File file = new File(saveFileLocation);
					
					try {
						
						if(file != null) {
															
								PrintWriter writeFile = new PrintWriter(file);
								
								writeFile.println(textArea.getText());
							
								writeFile.close();
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		});
		
		// --- Ends
		
		// -> Save As Text File
		
		// --- Starts
		
		saveAsMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				if(textArea.getText().equals("") || textArea.getText().equals(null)) {
					
					Alert alert = new Alert(AlertType.ERROR);
					
					alert.setTitle("Empty File");
					alert.setHeaderText(null);
					alert.setContentText("Can't Save, It's an Empty File");
					alert.showAndWait();
				}
				else {
										
					FileChooser fileChooser = new FileChooser();
					
					FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("txt files", "*.txt");
				
					fileChooser.getExtensionFilters().add(extension);
					
					File saveFile = fileChooser.showSaveDialog(primaryStage);
					
					try {
						
						if(saveFile != null) {
							
							saveFileLocation = saveFile.getPath();
						
							System.out.println(saveFileLocation);
						
							PrintWriter writeFile = new PrintWriter(saveFile);
							
							writeFile.println(textArea.getText());
						
							writeFile.close();
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		// --- Ends
		
		// -> Exit NodePad
		
		// --- Starts
		
		exitMenuItem.setOnAction(e-> System.exit(0));
		
		// --- Ends
		
		// ** Edit MenuItem Event Handling
		
		// -> Undo Text File
		
		// --- Starts
		
		undoMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
					textArea.undo();
			}
		});
		
		// --- Ends
		
		// ** Default Event Handling
		
		KeyCombination keyCombinationCtrlZ = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
		
		KeyCombination keyCombinationCtrlY = new KeyCodeCombination(KeyCode.Y, KeyCombination.SHORTCUT_DOWN);
		
		KeyCombination keyCombinationCtrlS = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
		
		textArea.setOnKeyPressed(new EventHandler<KeyEvent> () {
			
			public void handle(KeyEvent event) {
				
				// -> CTRL + Z = Shortcut Key to Undo Text
				
				// --- Starts
				
				if(keyCombinationCtrlZ.match(event)) {
					
					textArea.undo();
				}
				
				// --- Ends
				
				// -> CTRL + Y = Shortcut Key to Redo Text
				
				// --- Starts
				
				if(keyCombinationCtrlY.match(event)) {
									
					textArea.redo();
				}
				
				// --- Ends
				
				// -> CTRL + S = Shortcut Key to Save Text
				
				// --- Starts
				
				if(keyCombinationCtrlS.match(event)) {
					
					if(saveFileLocation == "") {
						
						if(textArea.getText().equals("") || textArea.getText().equals(null)) {
							
							Alert alert = new Alert(AlertType.ERROR);
							
							alert.setTitle("Empty File");
							alert.setHeaderText(null);
							alert.setContentText("Can't Save, It's an Empty File");
							alert.showAndWait();
						}
						else {
												
							FileChooser fileChooser = new FileChooser();
							
							FileChooser.ExtensionFilter extension = new FileChooser.ExtensionFilter("txt files", "*.txt");
						
							fileChooser.getExtensionFilters().add(extension);
							
							File saveFile = fileChooser.showSaveDialog(primaryStage);
							
							saveFileLocation = saveFile.getPath();
							
							try {
								
								if(saveFile != null) {
																	
										PrintWriter writeFile = new PrintWriter(saveFile);
										
										writeFile.println(textArea.getText());
									
										writeFile.close();
								}
								
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					else {
						
						File file = new File(saveFileLocation);
						
						try {
							
							if(file != null) {
																
									PrintWriter writeFile = new PrintWriter(file);
									
									writeFile.println(textArea.getText());
								
									writeFile.close();
							}
							
						} catch (IOException e) {
							e.printStackTrace();
						}						
					}
				}
				
				// --- Ends
				
				// -> Default Line Count
				
				// --- Starts
				
				String lineString = textArea.getText();
				
				String [] lineStringArray = lineString.trim().split("[\n|\r]");
				
				int lineCount = lineStringArray.length;
				
				lineLabel.setText("Lines: " + lineCount);
				
				// --- Ends
				
				// -> Default Word Count
				
				// --- Starts
				
				String wordString = textArea.getText();
				
				String [] wordStringArray = wordString.trim().split("\\s+");
				
				int wordCount = wordStringArray.length;
				
				wordLabel.setText("Words: " + wordCount);
			}
		});
		
		
		// ** View MenuItem Event Handling
		
		// -> Show Line Count
		
		// --- Starts
		
		lineMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				String string = textArea.getText();
				
				String [] stringArray = string.trim().split("[\n|\r]");
				
				int lineCount = stringArray.length;
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				alert.setTitle("Lines");
				alert.setHeaderText(null);
				alert.setContentText("Lines Count: " + lineCount);
				alert.showAndWait();
			}
		});
		
		// --- Ends
		
		// -> Show Word Count
		
		// --- Starts
		
		wordMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				String string = textArea.getText();
				
				String [] stringArray = string.trim().split("\\s+");
				
				int wordCount = stringArray.length;
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				alert.setTitle("Words");
				alert.setHeaderText(null);
				alert.setContentText("Words Count: " + wordCount);
				alert.showAndWait();
			}
		});
		
		// --- Ends
		
		// -> Show Status
		
		// --- Starts
		
		statusMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				
				alert.setTitle("Status");
				alert.setHeaderText(null);
				
				if(saveFileLocation.equals("")) {
					
					alert.setContentText("Not Saved");
				}
				
				else {
					
					alert.setContentText("Saved");
				}
				
				alert.showAndWait();
			}
		});
		
		// --- Ends
		
		// ** Format MenuItem Event Handling
		
		// -> Wrap Text
		
		// --- Starts
				
		wrapMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
								
				if(toggleNumber == 0) {
					
					textArea.setWrapText(false);
					
					toggleNumber = 1;
				}
				else {
					textArea.setWrapText(true);
					
					toggleNumber = 0;
				}
			}
		});
		
		// --- Ends
		
		// -> Text Fonts
		
		// --- Starts
		
		fontMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
			}
		});
		
		// --- Ends
		
		// ** Help MenuItem Event Handling
		
		// -> Help
		
		// --- Starts
		
		helpMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				TextArea helpTextArea = new TextArea();
				
				helpTextArea.setDisable(true);
				
				ScrollPane helpScrollPane = new ScrollPane(helpTextArea);
				
				helpScrollPane.setFitToHeight(true);
				helpScrollPane.setFitToWidth(true);
				
				BorderPane helpBorderPane = new BorderPane();
				
				helpBorderPane.setCenter(helpScrollPane);
				
				Scene helpScene = new Scene(helpBorderPane, 600, 400);
				
				Stage helpStage = new Stage();
				
				helpStage.setScene(helpScene);
				helpStage.setTitle("Help");
				helpStage.setResizable(false);
				helpStage.show();				
			}
		});
		
		// --- Ends
		
		// -> About
		
		// --- Starts
		
		aboutMenuItem.setOnAction(new EventHandler<ActionEvent> () {
			
			public void handle(ActionEvent event) {
				
				TextArea aboutTextArea = new TextArea(
						" \tWelcome to JavaFX NotePade v0.1\n" 
						+ " \t   Author: Anim-101\n"
						+ " \t   Version: 0.1\n"
						);
				
				aboutTextArea.setStyle("-fx-background-color: wheat; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold");
				aboutTextArea.setDisable(true);
				
				ScrollPane aboutTextAreaScrollPane = new ScrollPane(aboutTextArea);
				
				aboutTextAreaScrollPane.setFitToHeight(true);
				aboutTextAreaScrollPane.setFitToWidth(true);
				
				BorderPane aboutBorderPane = new BorderPane();
				
				aboutBorderPane.setCenter(aboutTextAreaScrollPane);
				
				Scene aboutScene = new Scene(aboutBorderPane, 400, 400);
				
				Stage aboutStage = new Stage();
				
				aboutStage.setScene(aboutScene);
				aboutStage.setTitle("About");
				aboutStage.setResizable(false);
				aboutStage.show();
			}
		});
		
		// --- Ends
		
		// Sub Pane
		
		hbox.setSpacing(20);
		
		hbox.getChildren().addAll(lineLabel, wordLabel);
		
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
