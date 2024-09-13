package com.tools.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.tools.models.ToolsEnum;

public class Main extends JFrame {

	private JPanel mainPanel;
    private CardLayout cardLayout;

	public Main() {
		this.setTitle("Tools");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		Sidebar sidebar = new Sidebar(this);
		this.add(sidebar, BorderLayout.WEST);
		this.add(mainPanel,BorderLayout.CENTER);
		this.setVisible(true);

	}

	public void showPanel(ToolsEnum option) {
		JPanel panel;
		this.removePanelByName("Main Panel");


		switch (option) {
			case BASE_64:
				panel = new Base64(this);
				
				break;
			case CSV_TO_JSON:
				panel = new CSVtoJSON(this);

				break;
			default:
				panel = new JPanel();
				break;
		}

		// Add the panel to the mainPanel and show it
		mainPanel.add(panel, option.toString());
		cardLayout.show(mainPanel, option.toString());

		// panel.setName("Main Panel");
		// this.add(panel, BorderLayout.CENTER);
		// this.revalidate();
		// this.repaint();

	}

    public void removePanelByName(String name) {
        for (Component component : this.getComponents()) {
            if (component instanceof JPanel && name.equals(component.getName())) {
                this.remove(component);
                this.revalidate(); 
                this.repaint();
                break; 
            }
        }
    }

}
