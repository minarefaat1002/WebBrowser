package application;
import  javafx.scene.control.Button;
import  javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
public class MainSceneController implements Initializable {
	// @FXML annotation enables an FXMLLoader to inject values defined in an FXML file into references in the current class.
	@FXML
	private WebView webView; // WebView can create and manage a WebEngine and display its content.
	@FXML
	private TextField searchingField;
	@FXML
	private Button previous; // previous button used for going to the previous page.
	@FXML
	private Button next;     // next button used for going to the next page.
	@FXML
	private ComboBox <String> history; // the combo box is for history
	private WebEngine engine; // WebEngine is a non-visual object capable of managing one Web page at a time.
	private WebHistory webHistory;
	private String homePage; // the default home page will be google website.
	private double webZoom; // zooming factor.
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		previous.setDisable(true); // disabling the previous button because there isn't previous pages right now
		next.setDisable(true);//disabling the next button because there isn't following pages right now.
		engine = webView.getEngine();
		homePage = "www.google.com"; // the default page.
		searchingField.setText(homePage);
		engine.load("http://"+searchingField.getText()); // loading google website.
		history.getItems().add(0,searchingField.getText()); // adding the google page to the history record.
		webZoom = 1; // default zooming is 1.
	}
	public void loadPage() {
		engine.load("http://"+searchingField.getText()); // getting the website entered by the user and load it.
		history.getItems().add(0,searchingField.getText());//adding the website to the history record.
		next.setDisable(true); // disabling the next button cause there's no following pages.
		previous.setDisable(false);//enabling the previous button cause there's some previous pages.

	}
	public void refreshPage() {
		engine.reload(); // reloading the current page.
	}
	public void zoomIn() {
		webZoom+=.2; // increasing the zooming factor by 0.2
		webView.setZoom(webZoom);
	}
	public void zoomOut() {
		webZoom-=.2; //decreasing the zooming factor by 0.2
		webView.setZoom(webZoom);
	}
	
	public void nextPage() {
		webHistory = engine.getHistory();// getting the history searched by the user.
		ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
		webHistory.go(1); // going to the next page .
		previous.setDisable(false);
		if(webHistory.getCurrentIndex() == entries.size()-1) // if the user is in the last page then disable the next button
			next.setDisable(true);
		searchingField.setText(entries.get(webHistory.getCurrentIndex()).getUrl()); 
	}
	public void previousPage() {
		webHistory = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
		webHistory.go(-1); // goint to the previous page .
		next.setDisable(false);
		if(webHistory.getCurrentIndex()==0) { // if the user is in the first page then disable the previous button.
			previous.setDisable(true);
		}
		searchingField.setText(entries.get(webHistory.getCurrentIndex()).getUrl());
}
	public void chooseWebsite() {
		searchingField.setText(history.getValue());
		loadPage();
		previous.setDisable(true);
		
	}
}