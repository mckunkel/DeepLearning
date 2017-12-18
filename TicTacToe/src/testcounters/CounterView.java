package testcounters;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CounterView {
	private JFrame frame;
	private JPanel gui;
	private JButton button;
	private JLabel count;

	public CounterView() {
		customizeFrame();
		createMainPanel();
		createCountText();
		createButton();
		addComponentsToFrame();
	}

	private void customizeFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void createMainPanel() {
		gui = new JPanel();
		gui.setLayout(new BorderLayout());
	}

	private void createCountText() {
		count = new JLabel("0");
		count.setHorizontalAlignment(SwingConstants.CENTER);
	}

	// private void createButton() {
	// button = new JButton("Start counter");
	// button.addActionListener(new ActionListener() {
	// public void actionPerformed(ActionEvent e) {
	// // Create the worker
	// SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
	//
	// @Override
	// protected Void doInBackground() throws Exception {
	// for (int i = 0; i <= 50; i++) {
	// Thread.sleep(100);
	// System.out.println(i);
	// count.setText(Integer.toString(i));
	// }
	//
	// return null;
	// }
	// };
	//
	// // Start the worker
	// worker.execute();
	// }
	// });
	// }
	private void createButton() {
		button = new JButton("Start counter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(false); // can't click while counter is
											// running
				Thread background = new Thread(new Runnable() {
					@Override
					public void run() {
						for (int i = 0; i <= 50; i++) {
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(i);
							count.setText(Integer.toString(i));
						}
						button.setEnabled(true); // click-able after the counter
													// ends
					}
				});
				background.start();
			}
		});
	}

	private void addComponentsToFrame() {
		gui.add(count, BorderLayout.CENTER);
		gui.add(button, BorderLayout.SOUTH);
		frame.add(gui);
		frame.pack();
	}

	public void activate() {
		frame.setVisible(true);
	}
}
