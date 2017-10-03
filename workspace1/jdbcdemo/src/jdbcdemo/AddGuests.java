package jdbcdemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGuests extends JFrame {

	private JPanel contentPane;
	private JTextField FirstnameTextField;
	private JTextField LastnameTextField;
	private JTextField AirlineTextField;
	private JTextField FlightnrTextField;
	private JTextField RownrTextField;
	private JTextField SeatpositionTextField;
	private JPanel panel_1;
	private JButton btnOk;
	private JButton btnCancel;


	// The Guest DAO
	private GuestDAO guestDAO;

	//reference to GUIApp
	private GuestsSearchApp guestsSearchApp;		

	// Constructor when the guest is added
	public AddGuests(GuestsSearchApp theGuestsSearchApp, GuestDAO theGuestDAO) {
		this();
		guestDAO = theGuestDAO;
		guestsSearchApp = theGuestsSearchApp;
	}


	/**
	 * Create the frame.
	 */
	public AddGuests() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1023, 436);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblFirstname = new JLabel("firstname:");
		panel.add(lblFirstname);
		
		FirstnameTextField = new JTextField();
		panel.add(FirstnameTextField);
		FirstnameTextField.setColumns(10);
		
		JLabel lblLastname = new JLabel("lastname:");
		panel.add(lblLastname);
		
		LastnameTextField = new JTextField();
		panel.add(LastnameTextField);
		LastnameTextField.setColumns(10);
		
		JLabel lblAirline = new JLabel("airline:");
		panel.add(lblAirline);
		
		AirlineTextField = new JTextField();
		panel.add(AirlineTextField);
		AirlineTextField.setColumns(10);
		
		JLabel lblFlightnr = new JLabel("flightnr");
		panel.add(lblFlightnr);
		
		FlightnrTextField = new JTextField();
		panel.add(FlightnrTextField);
		FlightnrTextField.setColumns(10);
		
		JLabel lblRownr = new JLabel("rownr");
		panel.add(lblRownr);
		
		RownrTextField = new JTextField();
		panel.add(RownrTextField);
		RownrTextField.setColumns(10);
		
		JLabel lblSeatposition = new JLabel("seatposition");
		panel.add(lblSeatposition);
		
		SeatpositionTextField = new JTextField();
		panel.add(SeatpositionTextField);
		SeatpositionTextField.setColumns(10);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						saveGuest(); // calls the method saveGuest()
					}
				});
				
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void saveGuest() {

		// get the guest info from gui
		String firstname = FirstnameTextField.getText();					// get firstname of textfield
		String lastname = LastnameTextField.getText();						// get lastname of textfield	
		String airline = AirlineTextField.getText();						// get airline of textfield
		int flightnr = Integer.parseInt(FlightnrTextField.getText());		// get flightnr of textfield (int = String)
		int rownr = Integer.parseInt(RownrTextField.getText());				// get rownr of textfield (int = String)
		String seatposition = SeatpositionTextField.getText();				// get seatposition of textfield

		//Object created to save it to the database
		Guest tempGuest = new Guest(firstname, lastname, airline, flightnr, rownr, seatposition); 
		
		try {
			// save to the database
			guestDAO.addGuest(tempGuest);

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			guestsSearchApp.refreshGuestsView();
			
			// show success message
			JOptionPane.showMessageDialog(guestsSearchApp,
					"Guest added succesfully.",
					"Guest Added",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(
					guestsSearchApp,
					"Error saving guest: "
							+ exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}


