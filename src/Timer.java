import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*; import java.util.ArrayList; import java.util.Calendar;
public class Timer extends JFrame{
	private static final long serialVersionUID = 1L;
	public static int washtime = 45;
	public Timer() {
		super("Laundry Timer");
		JPanel controlPanel = new JPanel(new GridLayout(5, 1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) {e.printStackTrace(); }
	//Colors Pane
		JPanel panel1 = new JPanel(new GridLayout(1, 5));
		label1 = new JLabel("Colors");
		label1.setIcon(new ImageIcon("col.png"));
		panel1.add(label1);
		bar1 = new JProgressBar();
		bar1.setMaximum(100);
		panel1.add(bar1);
		button1 = new JButton("Start Wash");
		button1.addActionListener(new OSDListener());
		panel1.add(button1);
		m1 = new JTextField(32);
		m1.setText("Machine No.");
		panel1.add(m1);
		t1 = new JTextField(32);
		t1.setText("45 min.");
		panel1.add(t1);
		controlPanel.add(panel1);
	//Whites Pane
		JPanel panel2 = new JPanel(new GridLayout(1, 3));
		label2 = new JLabel("Whites");
		label2.setIcon(new ImageIcon("wht.png"));
		panel2.add(label2);
		bar2 = new JProgressBar();
		bar2.setMaximum(100);
		panel2.add(bar2);
		button2 = new JButton("Start Wash");
		button2.addActionListener(new OSDListener());
		panel2.add(button2);
		m2 = new JTextField(32);
		m2.setText("Machine No.");
		panel2.add(m2);
		t2 = new JTextField(32);
		t2.setText("45 min.");
		panel2.add(t2);
		controlPanel.add(panel2);
	//Towels Pane
		JPanel panel3 = new JPanel(new GridLayout(1, 3));
		label3 = new JLabel("Towels");
		label3.setIcon(new ImageIcon("tow.png"));
		panel3.add(label3);
		bar3 = new JProgressBar();
		bar3.setMaximum(100);
		panel3.add(bar3);
		button3 = new JButton("Start Wash");
		button3.addActionListener(new OSDListener());
		panel3.add(button3);
		controlPanel.add(panel3);
		m3 = new JTextField(32);
		m3.setText("Machine No.");
		panel3.add(m3);
		t3 = new JTextField(32);
		t3.setText("45 min.");
		panel3.add(t3);
		JPanel panel4 = new JPanel(new GridLayout(1, 3));
	//Socks Pane
		label4 = new JLabel("Socks");
		label4.setIcon(new ImageIcon("sox.png"));
		panel4.add(label4);
		bar4 = new JProgressBar();
		bar4.setMaximum(100);
		panel4.add(bar4);
		button4 = new JButton("Start Wash");
		button4.addActionListener(new OSDListener());
		panel4.add(button4);
		m4 = new JTextField(32);
		m4.setText("Machine No.");
		panel4.add(m4);
		t4 = new JTextField(32);
		t4.setText("45 min.");
		panel4.add(t4);
		controlPanel.add(panel4);
	//Bed Pane
		JPanel panel5 = new JPanel(new GridLayout(1, 3));
		label5 = new JLabel("Sheets");
		label5.setIcon(new ImageIcon("bed.png"));
		panel5.add(label5);
		bar5 = new JProgressBar();
		bar5.setMaximum(100);
		panel5.add(bar5);
		button5 = new JButton("Start Wash");
		button5.addActionListener(new OSDListener());
		panel5.add(button5);
		m5 = new JTextField(32);
		m5.setText("Machine No.");
		panel5.add(m5);
		t5 = new JTextField(32);
		t5.setText("45 min.");
		panel5.add(t5);
		controlPanel.add(panel5);
	//Main stuff
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(600, 200);
		setResizable(false); }
	private JLabel label1, label2, label3, label4, label5;
	private JProgressBar bar1, bar2, bar3, bar4, bar5;
	private JButton button1, button2, button3, button4, button5;
	private JTextField m1, m2, m3, m4, m5, t1, t2, t3, t4, t5;
	public static ArrayList<Integer> finishHours = new ArrayList<Integer>();
	public static ArrayList<Integer> finishMins = new ArrayList<Integer>();
	public static ArrayList<Integer> timers = new ArrayList<Integer>();
	public static ArrayList<Integer> timersMax = new ArrayList<Integer>();
	//Main method
	public static void main(String args[]) throws InterruptedException {
		Timer x = new Timer();
		for (int i = 0; i < 5; i++) {
			finishHours.add(0);
			finishMins.add(0);
			timers.add(0);
			timersMax.add(1); }
		int starttime = currentMinute();
		while (true) {
			//Only do this loop on time change
			if (starttime != currentMinute()) {
				starttime = currentMinute();
				//Decreases remaining time on any active timers
				for (int i = 0; i < 5; i++) if (timers.get(i) > 0) timers.set(i, timers.get(i) - 1);
				//Updates the OSD progress bar and remaining time for any active timers
				if (timersMax.get(0) != 1) {
					x.bar1.setValue((int) Math.floor(100 * (timersMax.get(0) - timers.get(0)) / timersMax.get(0)));
					x.t1.setText(timers.get(0) + " min.");
					if (finishMins.get(0) < 10) x.label1.setText("Colors (" + finishHours.get(0) + ":0" + finishMins.get(0) + ")");
					else x.label1.setText("Colors (" + finishHours.get(0) + ":" + finishMins.get(0) + ")"); }
				if (timersMax.get(1) != 1) {
					x.bar2.setValue((int) Math.floor(100 * (timersMax.get(1) - timers.get(1)) / timersMax.get(1)));
					x.t2.setText(timers.get(1) + " min.");
					if (finishMins.get(1) < 10) x.label2.setText("Whites (" + finishHours.get(1) + ":0" + finishMins.get(1) + ")");
					else x.label2.setText("Whites (" + finishHours.get(1) + ":" + finishMins.get(1) + ")"); }
				if (timersMax.get(2) != 1) {
					x.bar3.setValue((int) Math.floor(100 * (timersMax.get(2) - timers.get(2)) / timersMax.get(2)));
					x.t3.setText(timers.get(2) + " min.");
					if (finishMins.get(2) < 10) x.label3.setText("Towels (" + finishHours.get(2) + ":0" + finishMins.get(2) + ")");
					else x.label3.setText("Towels (" + finishHours.get(2) + ":" + finishMins.get(2) + ")"); }
				if (timersMax.get(3) != 1) {
					x.bar4.setValue((int) Math.floor(100 * (timersMax.get(3) - timers.get(3)) / timersMax.get(3)));
					x.t4.setText(timers.get(3) + " min.");
					if (finishMins.get(3) < 10) x.label4.setText("Socks (" + finishHours.get(3) + ":0" + finishMins.get(3) + ")");
					else x.label4.setText("Socks (" + finishHours.get(3) + ":" + finishMins.get(3) + ")"); }
				if (timersMax.get(4) != 1) {
					x.bar5.setValue((int) Math.floor(100 * (timersMax.get(4) - timers.get(4)) / timersMax.get(4)));
					x.t5.setText(timers.get(4) + " min.");
					if (finishMins.get(4) < 10) x.label5.setText("Sheets (" + finishHours.get(4) + ":0" + finishMins.get(4) + ")");
					else x.label5.setText("Sheets (" + finishHours.get(4) + ":" + finishMins.get(4) + ")"); }
				if (timers.get(0) == 0 && x.button1.getText() != "Drying" && timersMax.get(0) != 1) {
					timersMax.set(0, 1);
					x.bar1.setValue(0);
					x.t1.setEnabled(true);
					x.m1.setText("On Machine " + x.m1.getText());
					x.t1.setText("Dryer Time");
					x.label1.setText("Colors");
					x.button1.setEnabled(true); }
				else if (timers.get(0) == 0 && x.button1.getText() == "Drying" && timersMax.get(0) != 1) {
					x.button1.setText("Complete");
					timersMax.set(0, 1);
					x.label1.setText("Colors");
					x.bar1.setValue(0);
					x.m1.setText("On Machine " + x.m1.getText()); }
				if (timers.get(1) == 0 && x.button2.getText() != "Drying" && timersMax.get(1) != 1) {
					timersMax.set(1, 1);
					x.bar2.setValue(0);
					x.t2.setEnabled(true);
					x.m2.setText("On Machine " + x.m2.getText());
					x.t2.setText("Dryer Time");
					x.label2.setText("Whites");
					x.button2.setEnabled(true); }
				else if (timers.get(1) == 0 && x.button2.getText() == "Drying" && timersMax.get(1) != 1) {
					x.button2.setText("Complete");
					timersMax.set(1, 1);
					x.label2.setText("Whites");
					x.bar2.setValue(0);
					x.m2.setText("On Machine " + x.m2.getText()); }
				if (timers.get(2) == 0 && x.button3.getText() != "Drying" && timersMax.get(2) != 1) {
					timersMax.set(2, 1);
					x.bar3.setValue(0);
					x.t3.setEnabled(true);
					x.m3.setText("On Machine " + x.m3.getText());
					x.t3.setText("Dryer Time");
					x.label3.setText("Towels");
					x.button3.setEnabled(true); }
				else if (timers.get(2) == 0 && x.button3.getText() == "Drying" && timersMax.get(2) != 1) {
					x.button3.setText("Complete");
					timersMax.set(2, 1);
					x.label3.setText("Towels");
					x.bar3.setValue(0);
					x.m3.setText("On Machine " + x.m3.getText()); }
				if (timers.get(3) == 0 && x.button4.getText() != "Drying" && timersMax.get(3) != 1) {
					timersMax.set(3, 1);
					x.bar4.setValue(0);
					x.t4.setEnabled(true);
					x.m4.setText("On Machine " + x.m4.getText());
					x.t4.setText("Dryer Time");
					x.label4.setText("Socks");
					x.button4.setEnabled(true); }
				else if (timers.get(3) == 0 && x.button4.getText() == "Drying" && timersMax.get(3) != 1) {
					x.button4.setText("Complete");
					timersMax.set(3, 1);
					x.label4.setText("Socks");
					x.bar4.setValue(0);
					x.m4.setText("On Machine " + x.m4.getText()); }
				if (timers.get(4) == 0 && x.button5.getText() != "Drying" && timersMax.get(4) != 1) {
					timersMax.set(4, 1);
					x.bar5.setValue(0);
					x.t5.setEnabled(true);
					x.m5.setText("On Machine " + x.m5.getText());
					x.t5.setText("Dryer Time");
					x.label5.setText("Sheets");
					x.button5.setEnabled(true); }
				else if (timers.get(4) == 0 && x.button5.getText() == "Drying" && timersMax.get(4) != 1) {
					x.button5.setText("Complete");
					timersMax.set(4, 1);
					x.label5.setText("Sheets");
					x.bar5.setValue(0);
					x.m5.setText("On Machine " + x.m5.getText()); } } Thread.sleep(500); } }
	public static int currentHour() {return Calendar.getInstance().get(Calendar.HOUR_OF_DAY); }
	public static int currentMinute() {return Calendar.getInstance().get(Calendar.MINUTE); }
	private class OSDListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (button1.isFocusOwner() && !m1.getText().equals("Machine No.")) {
				if (button1.getText() != "Start Drying") {
					button1.setText("Start Drying");
					t1.setText("45 min.");
					//Calculate finish time
					finishHours.set(0, currentHour());
					finishMins.set(0, currentMinute() + washtime);
					if (finishMins.get(0) > 59) {
						finishMins.set(0, finishMins.get(0) - 60);
						finishHours.set(0, finishHours.get(0) + 1); }
					if (finishHours.get(0) > 12) finishHours.set(0, finishHours.get(0) - 12);
					//Set timer
					timers.set(0, washtime);
					timersMax.set(0, washtime);
					//Time finished label
					if (finishMins.get(0) < 10) label1.setText("Colors (" + finishHours.get(0) + ":0" + finishMins.get(0) + ")");
					else label1.setText("Colors (" + finishHours.get(0) + ":" + finishMins.get(0) + ")");
					//Blocks button
					t1.setEnabled(false);
					m1.setEnabled(false);
					button1.setEnabled(false); }
				else if (!m1.isEnabled()) {
					m1.setEnabled(true);
					m1.setText("Machine No."); }
				else if (!m1.getText().equals("Machine No.") && !t1.getText().equals("Dryer Time")) {
					m1.setEnabled(false);
					t1.setEnabled(false);
					button1.setEnabled(false);
					button1.setText("Drying");
					timers.set(0, Integer.parseInt(t1.getText()));
					timersMax.set(0, Integer.parseInt(t1.getText()));
					t1.setText(timersMax.get(0) + " min.");
					//Calculate finish time
					finishHours.set(0, currentHour());
					finishMins.set(0, currentMinute() + timersMax.get(0));
					if (finishMins.get(0) > 59) {
						finishMins.set(0, finishMins.get(0) - 60);
						finishHours.set(0, finishHours.get(0) + 1); }
					if (finishHours.get(0) > 12) finishHours.set(0, finishHours.get(0) - 12);
					//Time finished label
					if (finishMins.get(0) < 10) label1.setText("Colors (" + finishHours.get(0) + ":0" + finishMins.get(0) + ")");
					else label1.setText("Colors (" + finishHours.get(0) + ":" + finishMins.get(0) + ")"); } }
			if (button2.isFocusOwner() && !m2.getText().equals("Machine No.")) {
				if (button2.getText() != "Start Drying") {
					button2.setText("Start Drying");
					t2.setText("45 min.");
					//Calculate finish time
					finishHours.set(1, currentHour());
					finishMins.set(1, currentMinute() + washtime);
					if (finishMins.get(1) > 59) {
						finishMins.set(1, finishMins.get(1) - 60);
						finishHours.set(1, finishHours.get(1) + 1); }
					if (finishHours.get(1) > 12) finishHours.set(1, finishHours.get(1) - 12);
					//Set timer
					timers.set(1, washtime);
					timersMax.set(1, washtime);
					//Time finished label
					if (finishMins.get(1) < 10) label2.setText("Whites (" + finishHours.get(1) + ":0" + finishMins.get(1) + ")");
					else label2.setText("Whites (" + finishHours.get(1) + ":" + finishMins.get(1) + ")");
					//Blocks button
					t2.setEnabled(false);
					m2.setEnabled(false);
					button2.setEnabled(false); }
				else if (!m2.isEnabled()) {
					m2.setEnabled(true);
					m2.setText("Machine No."); }
				else if (!m2.getText().equals("Machine No.") && !t2.getText().equals("Dryer Time")) {
					m2.setEnabled(false);
					t2.setEnabled(false);
					button2.setEnabled(false);
					button2.setText("Drying");
					timers.set(1, Integer.parseInt(t2.getText()));
					timersMax.set(1, Integer.parseInt(t2.getText()));
					t2.setText(timersMax.get(1) + " min.");
					//Calculate finish time
					finishHours.set(1, currentHour());
					finishMins.set(1, currentMinute() + timersMax.get(1));
					if (finishMins.get(1) > 59) {
						finishMins.set(1, finishMins.get(1) - 60);
						finishHours.set(1, finishHours.get(1) + 1); }
					if (finishHours.get(1) > 12) finishHours.set(1, finishHours.get(1) - 12);
					//Time finished label
					if (finishMins.get(1) < 10) label2.setText("Whites (" + finishHours.get(1) + ":0" + finishMins.get(1) + ")");
					else label2.setText("Whites (" + finishHours.get(1) + ":" + finishMins.get(1) + ")"); } }
			if (button3.isFocusOwner() && !m3.getText().equals("Machine No.")) {
				if (button3.getText() != "Start Drying") {
					button3.setText("Start Drying");
					t3.setText("45 min.");
					//Calculate finish time
					finishHours.set(2, currentHour());
					finishMins.set(2, currentMinute() + washtime);
					if (finishMins.get(2) > 59) {
						finishMins.set(2, finishMins.get(2) - 60);
						finishHours.set(2, finishHours.get(2) + 1); }
					if (finishHours.get(2) > 12) finishHours.set(2, finishHours.get(2) - 12);
					//Set timer
					timers.set(2, washtime);
					timersMax.set(2, washtime);
					//Time finished label
					if (finishMins.get(2) < 10) label3.setText("Towels (" + finishHours.get(2) + ":0" + finishMins.get(2) + ")");
					else label3.setText("Towels (" + finishHours.get(2) + ":" + finishMins.get(2) + ")");
					//Blocks button
					t3.setEnabled(false);
					m3.setEnabled(false);
					button3.setEnabled(false); }
				else if (!m3.isEnabled()) {
					m3.setEnabled(true);
					m3.setText("Machine No."); }
				else if (!m3.getText().equals("Machine No.") && !t3.getText().equals("Dryer Time")) {
					m3.setEnabled(false);
					t3.setEnabled(false);
					button3.setEnabled(false);
					button3.setText("Drying");
					timers.set(2, Integer.parseInt(t3.getText()));
					timersMax.set(2, Integer.parseInt(t3.getText()));
					t3.setText(timersMax.get(2) + " min.");
					//Calculate finish time
					finishHours.set(2, currentHour());
					finishMins.set(2, currentMinute() + timersMax.get(2));
					if (finishMins.get(2) > 59) {
						finishMins.set(2, finishMins.get(2) - 60);
						finishHours.set(2, finishHours.get(2) + 1); }
					if (finishHours.get(2) > 12) finishHours.set(2, finishHours.get(2) - 12);
					//Time finished label
					if (finishMins.get(2) < 10) label3.setText("Towels (" + finishHours.get(2) + ":0" + finishMins.get(2) + ")");
					else label3.setText("Towels (" + finishHours.get(2) + ":" + finishMins.get(2) + ")"); } }
			if (button4.isFocusOwner() && !m4.getText().equals("Machine No.")) {
				if (button4.getText() != "Start Drying") {
					button4.setText("Start Drying");
					t4.setText("45 min.");
					//Calculate finish time
					finishHours.set(3, currentHour());
					finishMins.set(3, currentMinute() + washtime);
					if (finishMins.get(3) > 59) {
						finishMins.set(3, finishMins.get(3) - 60);
						finishHours.set(3, finishHours.get(3) + 1); }
					if (finishHours.get(3) > 12) finishHours.set(3, finishHours.get(3) - 12);
					//Set timer
					timers.set(3, washtime);
					timersMax.set(3, washtime);
					//Time finished label
					if (finishMins.get(3) < 10) label4.setText("Socks (" + finishHours.get(3) + ":0" + finishMins.get(3) + ")");
					else label4.setText("Socks (" + finishHours.get(3) + ":" + finishMins.get(3) + ")");
					//Blocks button
					t4.setEnabled(false);
					m4.setEnabled(false);
					button4.setEnabled(false); }
				else if (!m4.isEnabled()) {
					m4.setEnabled(true);
					m4.setText("Machine No."); }
				else if (!m4.getText().equals("Machine No.") && !t4.getText().equals("Dryer Time")) {
					m4.setEnabled(false);
					t4.setEnabled(false);
					button4.setEnabled(false);
					button4.setText("Drying");
					timers.set(3, Integer.parseInt(t4.getText()));
					timersMax.set(3, Integer.parseInt(t4.getText()));
					t4.setText(timersMax.get(3) + " min.");
					//Calculate finish time
					finishHours.set(3, currentHour());
					finishMins.set(3, currentMinute() + timersMax.get(3));
					if (finishMins.get(3) > 59) {
						finishMins.set(3, finishMins.get(3) - 60);
						finishHours.set(3, finishHours.get(3) + 1); }
					if (finishHours.get(3) > 12) finishHours.set(3, finishHours.get(3) - 12);
					//Time finished label
					if (finishMins.get(3) < 10) label4.setText("Socks (" + finishHours.get(3) + ":0" + finishMins.get(3) + ")");
					else label4.setText("Socks (" + finishHours.get(3) + ":" + finishMins.get(3) + ")"); } }
			if (button5.isFocusOwner() && !m5.getText().equals("Machine No.")) {
				if (button5.getText() != "Start Drying") {
					button5.setText("Start Drying");
					t5.setText("45 min.");
					//Calculate finish time
					finishHours.set(4, currentHour());
					finishMins.set(4, currentMinute() + washtime);
					if (finishMins.get(4) > 59) {
						finishMins.set(4, finishMins.get(4) - 60);
						finishHours.set(4, finishHours.get(4) + 1); }
					if (finishHours.get(4) > 12) finishHours.set(4, finishHours.get(4) - 12);
					//Set timer
					timers.set(4, washtime);
					timersMax.set(4, washtime);
					//Time finished label
					if (finishMins.get(4) < 10) label5.setText("Sheets (" + finishHours.get(4) + ":0" + finishMins.get(4) + ")");
					else label5.setText("Sheets (" + finishHours.get(4) + ":" + finishMins.get(4) + ")");
					//Blocks button
					t5.setEnabled(false);
					m5.setEnabled(false);
					button5.setEnabled(false); }
				else if (!m5.isEnabled()) {
					m5.setEnabled(true);
					m5.setText("Machine No."); }
				else if (!m5.getText().equals("Machine No.") && !t5.getText().equals("Dryer Time")) {
					m5.setEnabled(false);
					t5.setEnabled(false);
					button5.setEnabled(false);
					button5.setText("Drying");
					timers.set(4, Integer.parseInt(t5.getText()));
					timersMax.set(4, Integer.parseInt(t5.getText()));
					t5.setText(timersMax.get(4) + " min.");
					//Calculate finish time
					finishHours.set(4, currentHour());
					finishMins.set(4, currentMinute() + timersMax.get(4));
					if (finishMins.get(4) > 59) {
						finishMins.set(4, finishMins.get(4) - 60);
						finishHours.set(4, finishHours.get(4) + 1); }
					if (finishHours.get(4) > 12) finishHours.set(4, finishHours.get(4) - 12);
					//Time finished label
					if (finishMins.get(4) < 10) label5.setText("Sheets (" + finishHours.get(4) + ":0" + finishMins.get(4) + ")");
					else label5.setText("Sheets (" + finishHours.get(4) + ":" + finishMins.get(4) + ")"); } }
			} } }
