package jdbcdemo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class FlughaefenSearchApp extends JFrame {

	private JPanel contentPane;
	private JTextField cityTextField;
	private JTable table;
	
	private java.util.List<Flughaefen> flughaefen = null;
	private FlughafenDAO flughafenDAO;	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlughaefenSearchApp frame = new FlughaefenSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FlughaefenSearchApp() {
		
		// DAO erstellen
		try {
			flughafenDAO = new FlughafenDAO();
		} catch(Exception exc) {
			JOptionPane.showMessageDialog(this, "Error" + exc, "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		setTitle("FlughaefenSearchApp");
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
		
		JLabel lblNewLabel = new JLabel("Abflug: ");
		panel.add(lblNewLabel);
		
		cityTextField = new JTextField();
		panel.add(cityTextField);
		cityTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String city = cityTextField.getText();

					

					if (city != null && city.trim().length() > 0) {
						flughaefen = flughafenDAO.searchFlughaefen(city);
					} else {
						flughaefen = flughafenDAO.getAllFlughaefen();
					}
					
					// create the model and update the "table"
					FlughaefenTableModel model = new FlughaefenTableModel(flughaefen);
					
					table.setModel(model);
					
					/*
					for (Employee temp : employees) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(FlughaefenSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		panel.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
