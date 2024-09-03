package com.tools.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tools.models.ToolsEnum;

public class Sidebar extends JPanel {

	public Sidebar(Main main) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.pink);

		for (ToolsEnum tool : ToolsEnum.values()) {

			JButton button = createButton(tool.toString());

			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					main.showPanel(tool);

				}
			});
			this.add(button);

		}

	}

	JButton createButton(String option) {
		JButton button = new JButton(option);
		button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
		return button;
	}

}
