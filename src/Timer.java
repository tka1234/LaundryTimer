import javax.swing.*; import javax.swing.border.Border; import java.awt.event.*; import java.awt.*; import java.util.ArrayList; import java.util.Calendar;
public class Timer extends JFrame{
	private static final long serialVersionUID = 1L;
	public Timer() {
		super("Laundry Timer");
		JPanel controlPanel = new JPanel(new GridLayout(5, 1));
		Border gap = BorderFactory.createEmptyBorder(5,5,5,5);
		controlPanel.setBorder(gap);
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (Exception e) {e.printStackTrace(); }
		JPanel panel1 = new JPanel(new GridLayout(1, 3));
		label1 = new JLabel("Colors");
		label1.setIcon(new ImageIcon("col.png"));
		panel1.add(label1);
		bar1 = new JProgressBar();
		bar1.setMaximum(100);
		panel1.add(bar1);
		button1 = new JButton("Start Wash");
		button1.addActionListener(new OSDListener());
		panel1.add(button1);
		controlPanel.add(panel1);
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
		controlPanel.add(panel2);
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
		JPanel panel4 = new JPanel(new GridLayout(1, 3));
		label4 = new JLabel("Socks");
		label4.setIcon(new ImageIcon("sox.png"));
		panel4.add(label4);
		bar4 = new JProgressBar();
		bar4.setMaximum(100);
		panel4.add(bar4);
		button4 = new JButton("Start Wash");
		button4.addActionListener(new OSDListener());
		panel4.add(button4);
		controlPanel.add(panel4);
		JPanel panel5 = new JPanel(new GridLayout(1, 3));
		label5 = new JLabel("Bedding");
		label5.setIcon(new ImageIcon("bed.png"));
		panel5.add(label5);
		bar5 = new JProgressBar();
		bar5.setMaximum(100);
		panel5.add(bar5);
		button5 = new JButton("Start Wash");
		button5.addActionListener(new OSDListener());
		panel5.add(button5);
		controlPanel.add(panel5);
		
		setContentPane(controlPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(400, 175);
		setResizable(false); }
	private JLabel label1, label2, label3, label4, label5;
	private JProgressBar bar1, bar2, bar3, bar4, bar5;
	private JButton button1, button2, button3, button4, button5;
	public static ArrayList<Integer> finishHours = new ArrayList<Integer>();
	public static ArrayList<Integer> finishMins = new ArrayList<Integer>();
	public static ArrayList<Integer> timers = new ArrayList<Integer>();
	public static ArrayList<Integer> timersMax = new ArrayList<Integer>();
	
	public static void main(String args[]) throws InterruptedException {
		Timer x = new Timer();
		for (int i = 1; i < 6; i++) {
			finishHours.add(0);
			finishMins.add(0);
			timers.add(0);
			timersMax.add(1); }
		System.out.println(finishHours.size());
		int starttime = currentMinute();
		while (true) {
			if (starttime != currentMinute()) {
				System.out.println("tip");
				starttime = currentMinute();
				for (int i = 0; i < 4; i++) if (timers.get(i) > 0) timers.set(i, timers.get(i) - 1);
			}
			x.bar1.setValue((int) Math.floor(timers.get(0) / timersMax.get(0)));
			System.out.println(timers.get(0));
			x.bar2.setValue((int) Math.floor(timers.get(1) / timersMax.get(1)));
			x.bar3.setValue((int) Math.floor(timers.get(2) / timersMax.get(2)));
			x.bar4.setValue((int) Math.floor(timers.get(3) / timersMax.get(3)));
			x.bar5.setValue((int) Math.floor(timers.get(4) / timersMax.get(4)));
			Thread.sleep(500);
		} }
	public static int currentHour() {return Calendar.getInstance().get(Calendar.HOUR_OF_DAY); }
	public static int currentMinute() {return Calendar.getInstance().get(Calendar.MINUTE); }
	
	private class OSDListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (button1.isFocusOwner()) {
				button1.setText("Start Drying");
				finishHours.set(1, currentHour());
				finishMins.set(1, currentMinute() + 45);
				if (finishMins.get(1) > 59) {
					finishMins.set(1, finishMins.get(1) - 60);
					finishHours.set(1, finishHours.get(1) + 1); }
				if (finishHours.get(1) > 12) finishHours.set(1, finishHours.get(1) - 12);
				timers.set(1, 45);
				timersMax.set(1, 45);
				System.out.println("Finishing at " + finishHours.get(1) + ":" + finishMins.get(1));
			}
			if (button2.isFocusOwner()) { }
			if (button3.isFocusOwner()) { }
			if (button4.isFocusOwner()) { }
			if (button5.isFocusOwner()) { }
			} } }
