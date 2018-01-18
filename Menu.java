import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class Menu extends JPanel {

	/**
	 * Create the panel.
	 */
	public boolean play = false;
	public Menu m = this;

	public Menu(Game g) {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JLabel lblPixelMage = new JLabel("Pixel Mage");
		lblPixelMage.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, lblPixelMage, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblPixelMage, 0, SpringLayout.EAST, this);
		lblPixelMage.setFont(new Font("Tahoma", Font.BOLD, 27));
		springLayout.putConstraint(SpringLayout.NORTH, lblPixelMage, 30, SpringLayout.NORTH, this);
		add(lblPixelMage);

		JButton btnPlay = new JButton("Play");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				hideMenu();
				g.start();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnPlay, 30, SpringLayout.SOUTH, lblPixelMage);
		springLayout.putConstraint(SpringLayout.WEST, btnPlay, 100, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnPlay, -100, SpringLayout.EAST, this);
		add(btnPlay);

		JButton btnInstructions = new JButton("Instructions");
		springLayout.putConstraint(SpringLayout.NORTH, btnInstructions, 15, SpringLayout.SOUTH, btnPlay);
		springLayout.putConstraint(SpringLayout.WEST, btnInstructions, 0, SpringLayout.WEST, btnPlay);
		springLayout.putConstraint(SpringLayout.EAST, btnInstructions, 0, SpringLayout.EAST, btnPlay);
		add(btnInstructions);

	}

	public void hideMenu() {
		setVisible(false);
	}
}
