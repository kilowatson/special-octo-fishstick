package com.tools.view;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVtoJSON extends JPanel
{
    CSVtoJSON(final JFrame frame)
    {
		this.frame = frame;
		init();
    }

    private JFrame frame;
    private JTextArea textAreaCSV;
    private JTextArea textAreaJSON;
    private JButton buttonChooseFile;
    private JButton buttonConvert;
    private JButton buttonSave;
    private JLabel labelCSVInput;
    

    void init()
    {
        // Setup the frame
        setLayout(new BorderLayout());

        // Create label and buttons
        labelCSVInput = new JLabel("Choose a file or Enter CSV in the text box:");
        buttonChooseFile = new JButton("Choose a File");
        buttonConvert = new JButton("Convert");
        buttonSave = new JButton("Save JSON File");

        // Text areas for CSV and JSON
        textAreaCSV = new JTextArea(10,10);
        textAreaJSON = new JTextArea(10, 10);
        textAreaJSON.setEditable(false); // Make JSON text area read-only

        JScrollPane scrollCSV = new JScrollPane(textAreaCSV);
        JScrollPane scrollJSON = new JScrollPane(textAreaJSON);

        // Create panels
        JPanel panelTop = new JPanel();
        JPanel panelCenter = new JPanel();
        JPanel panelBottom = new JPanel();

        panelTop.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(2, 1));
        panelBottom.setLayout(new FlowLayout());

        // Add components to panels
        panelTop.add(labelCSVInput);
        panelTop.add(buttonChooseFile);
        panelCenter.add(scrollCSV);
        panelCenter.add(scrollJSON);
        panelBottom.add(buttonConvert);
        panelBottom.add(buttonSave);

        // Add panels to the frame
        this.add(panelTop, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelBottom, BorderLayout.SOUTH);

        
        // Add action listeners
        buttonChooseFile.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                chooseCSVFile();
            }
        });

        buttonConvert.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCSVToJSON();
            }
        });

        buttonSave.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJSONFile();
            }
        });

        setVisible(true);
    }

    private void chooseCSVFile() 
    {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) 
            {
                String line;
                StringBuilder csvContent = new StringBuilder();
                while ((line = br.readLine()) != null) 
                {
                    csvContent.append(line).append("\n");
                }
                textAreaCSV.setText(csvContent.toString());
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    private void convertCSVToJSON() 
    {
        String csvText = textAreaCSV.getText();
        if (csvText.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "CSV data is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] lines = csvText.split("\n");
        if (lines.length == 0) 
        {
            JOptionPane.showMessageDialog(this, "Invalid CSV data!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // First line is the header
        String[] headers = lines[0].split(",");
        JSONArray jsonArray = new JSONArray();

        for (int i = 1; i < lines.length; i++) 
        {
            String[] data = lines[i].split(",");
            JSONObject jsonObject = new JSONObject();

            for (int j = 0; j < headers.length; j++) 
            {
                jsonObject.put(headers[j], data[j]);
            }
            jsonArray.put(jsonObject);
        }

        textAreaJSON.setText(jsonArray.toString(4)); // Pretty-print the JSON
    }

    private void saveJSONFile()
    {
        String jsonText = textAreaJSON.getText();
        if (jsonText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No JSON data to save", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter fileWriter = new FileWriter(fileToSave + ".json")) {
                fileWriter.write(jsonText);
                JOptionPane.showMessageDialog(this, "File saved successfully");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
