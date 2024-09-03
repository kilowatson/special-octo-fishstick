package com.tools.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Base64 extends JPanel {
	Base64() {
		init();
	}

	void init() {
		this.setLayout(new GridLayout(1, 2));
		JTextArea textarea1 = new JTextArea();
		JTextArea textarea2 = new JTextArea();
		textarea1.setBackground(Color.RED);
		textarea2.setBackground(Color.BLUE);
		this.add(textarea1);
		this.add(textarea2);
		// this.add(textarea1, BorderLayout.WEST);
		// this.add(textarea2, BorderLayout.EAST);

	}

}
