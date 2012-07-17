package mygwt.client;

import mygwt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
//import com.smartgwt.client.types.SelectionType;  
//import com.smartgwt.client.widgets.Canvas;  
import com.smartgwt.client.widgets.ImgButton;  
//import com.smartgwt.client.widgets.layout.VLayout;  
//import com.smartgwt.client.widgets.toolbar.ToolStrip;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class HelloSG implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		//添加的范例程序
		/**
		ToolStrip toolStrip = new ToolStrip();  
        toolStrip.setWidth(200);  
        toolStrip.setHeight(24);  
        **/
  
        ImgButton boldButton = new ImgButton();
        RootPanel.get("selectContainer").add(boldButton);
        
        //boldButton.setSize(24);  
        //boldButton.setShowRollOver(false);  
        //boldButton.setSrc("24/text_bold.png");  
        //boldButton.setActionType(SelectionType.CHECKBOX);  
        //toolStrip.addMember(boldButton);  
         
        /**
        ImgButton italicsButton = new ImgButton();  
        italicsButton.setSize(24);  
        italicsButton.setShowRollOver(false);  
        italicsButton.setSrc("icons/24/text_italics.png");  
        italicsButton.setActionType(SelectionType.CHECKBOX);  
        toolStrip.addMember(italicsButton);  
          
        ImgButton underlineButton = new ImgButton();  
        underlineButton.setSize(24);  
        underlineButton.setShowRollOver(false);  
        underlineButton.setSrc("icons/24/text_underlined.png");  
        underlineButton.setActionType(SelectionType.CHECKBOX);  
        toolStrip.addMember(underlineButton);  
          
        ImgButton alignLeftButton = new ImgButton();  
        alignLeftButton.setSize(24);  
        alignLeftButton.setShowRollOver(false);  
        alignLeftButton.setSrc("icons/24/text_align_left.png");  
        alignLeftButton.setActionType(SelectionType.RADIO);  
        alignLeftButton.setRadioGroup("textAlign");  
        toolStrip.addMember(alignLeftButton);  
          
        ImgButton alignRightButton = new ImgButton();  
        alignRightButton.setSize(24);  
        alignRightButton.setShowRollOver(false);  
        alignRightButton.setSrc("icons/24/text_align_right.png");  
        alignRightButton.setActionType(SelectionType.RADIO);  
        alignRightButton.setRadioGroup("textAlign");  
        toolStrip.addMember(alignRightButton);  
          
        ImgButton alignCenterButton = new ImgButton();  
        alignCenterButton.setSize(24);  
        alignCenterButton.setShowRollOver(false);  
        alignCenterButton.setSrc("icons/24/text_align_center.png");  
        alignCenterButton.setActionType(SelectionType.RADIO);  
        alignCenterButton.setRadioGroup("textAlign");  
        toolStrip.addMember(alignCenterButton);  
          
        VLayout layout = new VLayout();  
        layout.setAutoHeight(); 
        **/ 
        
        
        
        //添加完毕

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
