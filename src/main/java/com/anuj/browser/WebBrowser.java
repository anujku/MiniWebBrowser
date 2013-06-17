package com.anuj.browser;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("unused")
public class WebBrowser extends JFrame implements ActionListener, HyperlinkListener {

  private static final long serialVersionUID = 1L;
  private JEditorPane editorPane;
  private JScrollPane scrollPane;
  private JMenuBar menubar;
  private JMenu menu;
  private JMenuItem exit;
  private Button close;
  private Button go;
  private TextField textField;
  private URL url;

  @SuppressWarnings("deprecation")
  public WebBrowser() {

    super("Mini Web Browser");
    setSize(950, 700);

    Container c = getContentPane();

    go = new Button("Go");
    go.addActionListener(this);

    menubar = new JMenuBar();
    menu = new JMenu("Menu");
    exit = new JMenuItem("Exit");
    exit.addActionListener(this);

    menu.add(exit);
    menubar.add(menu);

    close = new Button("Close");
    close.addActionListener(this);

    textField = new TextField("http://www.");
    textField.addActionListener(this);

    editorPane = new JEditorPane();
    editorPane.setEditable(false);
    editorPane.addHyperlinkListener(this);

    setJMenuBar(menubar);
    c.setLayout(null);

    scrollPane = new JScrollPane(editorPane);

    scrollPane.setBounds(10, 50, 940, 650);
    textField.setBounds(10, 20, 740, 25);
    go.setBounds(751, 20, 50, 25);
    close.setBounds(803, 20, 50, 25);

    c.add(close);
    c.add(scrollPane);
    c.add(go);
    c.add(textField);
    show();
  }

  public void actionPerformed(ActionEvent event) {
    setTitle("Mini Web Browser");
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    Object obj = event.getSource();
    String urlAddress = textField.getText();

    if (obj == go || obj == textField) {
      try {
        editorPane.setPage(urlAddress);
        setTitle(urlAddress);

      } catch (IOException ei) {
        try {
          editorPane.setPage("http://www.google.com");
        } catch (IOException se) {
          se.printStackTrace();
        }
      }
    } else if (obj == close) {
      System.exit(0);
    }

    else if (obj == exit) {
      System.exit(0);
    }

  }

  public void hyperlinkUpdate(HyperlinkEvent ea) {
    if (ea.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      try {
        editorPane.setPage(ea.getURL().toString());
        textField.setText(ea.getURL().toString());
      } catch (IOException ei) {
        try {
          editorPane.setPage("http://www.google.com");
        } catch (IOException se) {
          se.printStackTrace();
        }

      }
    }
  }

  public static void main(String[] args) {
    WebBrowser t = new WebBrowser();
    t.setVisible(true);
  }

}