package com.tools.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.tools.controllers.Base64Controller;

public class Base64 extends JPanel {
	Base64(final JFrame frame) {
		this.frame = frame;
		init();
	}

	private JFrame frame;
	private String mode = "Encode";
	private DocumentListener documentListener;
	private JTextArea encoderTextArea;
	private JTextArea decoderTextArea;
	private JButton swapButton;
	private JScrollPane encoderScrollPane;
	private JScrollPane decoderScrollPane;
	private GridBagConstraints layout;

	void init() {
		this.setLayout(new GridBagLayout());
		encoderTextArea = new JTextArea();
		decoderTextArea = new JTextArea();
		encoderTextArea.setBackground(Color.RED);
		decoderTextArea.setBackground(Color.BLUE);
		encoderTextArea.setLineWrap(true);
		encoderTextArea.setWrapStyleWord(true);
		decoderTextArea.setLineWrap(true);
		decoderTextArea.setWrapStyleWord(true);

		encoderScrollPane = new JScrollPane();
		encoderScrollPane.setViewportView(encoderTextArea);
		decoderScrollPane = new JScrollPane();
		decoderScrollPane.setViewportView(decoderTextArea);

		swapButton = new JButton("SWAP");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(swapButton);
		swapButton.addActionListener(new ButtonAction());
		initializeDocumentListener();
		encoderTextArea.getDocument().addDocumentListener(documentListener);
		decoderTextArea.setEditable(false);

		layout = new GridBagConstraints();

		// Configure GridBagConstraints for the left panel
		layout.gridx = 0;
		layout.gridy = 0;
		layout.weightx = 0.4; // 40% of the width
		layout.weighty = 1; // 40% of the width
		layout.fill = GridBagConstraints.BOTH;
		this.add(encoderScrollPane, layout);

		// Configure GridBagConstraints for the center panel
		layout.gridx = 1;
		layout.weightx = 0.2; // 20% of the width
		this.add(buttonPanel, layout);

		// Configure GridBagConstraints for the right panel
		layout.gridx = 2;
		layout.weightx = 0.4; // 40% of the width
		this.add(decoderScrollPane, layout);

	}

	void initLayout() {

	}

	void swap() {
		this.remove(encoderScrollPane);
		this.remove(decoderScrollPane);
		if (mode == "Encode") {

			decoderTextArea.setEditable(true);
			encoderTextArea.setEditable(false);
			encoderTextArea.getDocument().removeDocumentListener(documentListener);
			decoderTextArea.getDocument().addDocumentListener(documentListener);
			layout.gridx = 2;
			this.add(encoderScrollPane, layout);
			layout.gridx = 0;
			this.add(decoderScrollPane, layout);
			mode = "Decode";

		} else {
			decoderTextArea.setEditable(false);
			encoderTextArea.setEditable(true);
			decoderTextArea.getDocument().removeDocumentListener(documentListener);
			encoderTextArea.getDocument().addDocumentListener(documentListener);
			layout.gridx = 0;
			this.add(encoderScrollPane, layout);
			layout.gridx = 2;
			this.add(decoderScrollPane, layout);
			mode = "Encode";

		}
		frame.revalidate();
		frame.repaint();

	}

	void initializeDocumentListener() {
		documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				handleTextChange();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				handleTextChange();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Plain text areas do not use this method
			}

			private void handleTextChange() {
				// Get the current text from the JTextArea

				if (mode == "Encode") {
					String text = encoderTextArea.getText();
					String encodedString = Base64Controller.base64Encode(text);
					decoderTextArea.setText(encodedString);

				} else {

					String text = decoderTextArea.getText();
					// TODO: Check if valid
					String decodedString = Base64Controller.base64Decode(text);
					encoderTextArea.setText(decodedString);
				}
			}

		};
	}

	private class ButtonAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent e) {
			swap();

		}
	}

	// Two buttons
	// One two encode/decode
	// Two to switch mode from encode to decode

}
