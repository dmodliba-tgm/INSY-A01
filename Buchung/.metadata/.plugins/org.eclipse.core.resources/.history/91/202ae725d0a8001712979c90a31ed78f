package jdbcdemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.List;


public class GuestsSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	
	//reference to GuestDAO, because the GUI needs to have a handle to the DAO to call methods on it
	private GuestDAO guestDAO;

	private JPanel panel_1;
	private JButton btnAddGuest;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestsSearchApp frame = new GuestsSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public GuestsSearchApp() {
		
		
		// create the DAO
		try {
			guestDAO = new GuestDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); // Errors during construction 
		}
		
		
		
		
		setTitle("Guest Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterLastName = new JLabel("Enter last name");
		panel.add(lblEnterLastName);
		
		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		
		
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				try {
					
					//get lastname from the textfield
					String lastname = lastNameTextField.getText();			 

					//empty guest list
					List<Guest> guests = null; 								 

					if (lastname != null && lastname.trim().length() > 0) {
						
						//if the textfield isnt empty, search guest by lastname (searchGuests)
						guests = guestDAO.searchGuests(lastname);			
					} else {
						
						// if textfield is empty, search all guests (getAllGuests)
						guests = guestDAO.getAllGuests();					
					}
					
					// create the table and update the "table"
					// make use of the GuestTableModel. Passing the guests and the actual model
					GuestTableModel model = new GuestTableModel(guests); 
					
					table.setModel(model);

				} catch (Exception exc) {
					
					 //Error messages if necessary
					JOptionPane.showMessageDialog(GuestsSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		
		
		
		panel.add(btnSearch);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnAddGuest = new JButton("Add Guest");
		btnAddGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//creates a new dialog
				AddGuests dialog = new AddGuests(GuestsSearchApp.this, guestDAO);
				
				//show dialog
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnAddGuest);
	}
	
	
	
	
	// the guestview is being refresehed
	public void refreshGuestsView() {
		try {
			
			// A list of guests
			List<Guest> guests = guestDAO.getAllGuests();
			
			// create a model and updates the table
			GuestTableModel model = new GuestTableModel(guests);
			
			table.setModel(model);
		}catch(Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
