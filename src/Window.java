/** Java Packages. **/
package com.cooksys.assessment;

/** Java Swing Imports. **/
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JList;

/** Java AWT Imports. **/
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Java XML Imports. **/
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/** Java UTIL Imports. **/
import java.util.List;

/** Java IO Imports. **/
import java.io.File;

/**
 * Window class to display the GUI.
 * {@value} list The default model list to hold strings.
 * {@value} left, right The panel default model list to hold jlists.
 * {@value} leftList, rightList The left and right panel jlists in the panels. 
 * {@value} part The computer parts constructor for XML.
 * {@value} frame The jframe window for the content pane with extended swing support.
 **/
public class Window{
	final DefaultListModel<String> list = new DefaultListModel<>();
	protected DefaultListModel<String> left, right;
	protected JList<String> leftList, rightList;
	protected Parts part = new Parts();
	protected JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Window() {
		initialize();
	}
	
	/**
 	 * Initialize the contents of the frame for the GUI design.
 	 * {@value} frame The instantiated jframe object.
	 * {@value} left The instantiated left panel object.
	 * {@value} right The instantiated right panel object.
	 * {@value} leftList The instantiated left jlist object.
	 * {@value} rightList The instantiated right jlist object.
	 * {@value} Add The button for adding to the right objects to the righ panel.
	 * {@value} Remove The button for removing objects from the righ panel.
	 * {@value} menuBar The menu bar to contain menu items for selection.
	 * {@value} menuFile The menu to contain drop-down selectable menu items.
	 * {@value} menuItemLoad The selection item to load XML to object format.
	 * {@value} menuItemSave The selection item to save object to XML format.
	 * {@value} menuExit The selection item to exit the application entirely.
	 * @param frame.setBounds() Sets the size and location of the content pane that contains all objects.
	 * @param leftList.setBounds() Sets the size and location of the left jlist in the content pane.
	 * @param rightList.setBounds() Sets the size and location of the right jlist in the content pane.
	 * @param Add.setBounds() Sets the size and location of the add button in the content pane.
	 * @param Remove.setBounds() Sets the size and location of the remove button in the content pane.
	 * @param menuBar.setBounds() Sets the size and location of the menu bar in the content pane.
	 * @param frame.getContentPane().add(leftList) Adds the left jlist to the content pane.
	 * @param frame.getContentPane().add(rightList) Adds the right jlist to the content pane.
	 * @param frame.getContentPane().add(Add) Adds the add button to the content pane.
	 * @param frame.getContentPane().add(Remove) Adds the remove button to the content pane.
	 * @param frame.getContentPane().add(menuBar) Adds the menu bar to the content pane.
	 * @param frame.getContentPane().setLayout(null) Sets the layout manager to null, with absolute positioning. 
	 * @param frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) The jframe GUI panel exits upon close.
	 * @param addParts() Method execution to add computer parts to left panel.
	 * @param menuBar.add(menuFile) Adds the file menu selection to the menu bar panel.
	 * @param menuFile.add(menuItemLoad) Adds the load selection to the file menu drop-down.
	 * @param menuFile.add(menuItemSave) Adds the save selection to the file menu drop-down.
	 * @param menuFile.add(menuExit) Adds the exit selection to the file menu drop-down.
	 **/
	public void initialize() {
		frame = new JFrame();
		left = new DefaultListModel<String>();
		right = new DefaultListModel<String>();
		leftList = new JList<String>(left);
		rightList = new JList<String>(right);
		JButton Add = new JButton(">>");		
		JButton Remove = new JButton("<<");
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemLoad = new JMenuItem("Load");	
		JMenuItem menuItemSave = new JMenuItem("Save");
		JMenuItem menuExit = new JMenuItem("Exit");

		frame.setBounds(100, 100, 500, 450);
		leftList.setBounds(0, 22, 169, 392);
		rightList.setBounds(321, 22, 163, 392);
		Add.setBounds(217, 148, 49, 31);
		Remove.setBounds(217, 183, 49, 31);
		menuBar.setBounds(0, 0, 484, 21);

		frame.getContentPane().add(leftList);
		frame.getContentPane().add(rightList);
		frame.getContentPane().add(Add);
		frame.getContentPane().add(Remove);
		frame.getContentPane().add(menuBar);
		frame.getContentPane().setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addParts();		
		menuBar.add(menuFile);
		menuFile.add(menuItemLoad);
		menuFile.add(menuItemSave);
		menuFile.add(menuExit);
		
		/**
		 * Action listener for the Add button when selected.
		 * {@value} index The selected index element by the user from the left jlist.
		 * @param list.addElement(leftList.getModel().getElementAt(index)) Adds the element selected by the user at the specified index from the model to the list.
		 * @param rightList.setModel(list) The right jlist model is set with the list content elements.
		 * @param frame.getContentPane().add(rightList) The content pane is updated and added with the right jlist contents.
		 **/
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = leftList.getSelectedIndex();
				list.addElement(leftList.getModel().getElementAt(index));
				rightList.setModel(list);
				frame.getContentPane().add(rightList);
 			}
		});
		
		/**
		 * Action listener for the Remove button when selected.
		 * {@value} index The selected index element by the user from the right jlist.
		 * @param list.removeElement(rightList.getModel().getElementAt(index)) Removes the element selected by the user at the specified index from the model to the list. 
		 * @param rightList.setModel(list) The right jlist model is set with the list content elements.
		 * @param frame.getContentPane().add(rightList) The content pane is updated and added with the right jlist contents.
		 **/
		Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = rightList.getSelectedIndex();
				list.removeElement(rightList.getModel().getElementAt(index));
				rightList.setModel(list);
				frame.getContentPane().add(rightList);
			}
		});

		/**
		 * Action listener for the Load menu item when selected.
		 * @param right.clear() Method execution to clear the right panel of its elements.
		 * @param load() Method execution of load to read and write XML to object.
		 * @exception Prints the stack trace of the error upon thrown.
		 **/
		menuItemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					right.clear();
					load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * Action listener for the Save menu item when selected.
		 * @param save() Method execution of save to write object to XML.
		 * @exception Prints the stack trace of the error upon thrown.
		 **/
		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					save();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/**
		 * Action listener for the Exit menu item when selected.
		 * @param System.exit(0) The application is completed shut down.
		 **/
		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	/**
	 * Method to populate the left panel with computer parts.
	 * {@value} parts
	 * @param left.addElement(parts[i])
	 **/
	private void addParts() {
			String[] parts = {"Case", "Motherboard", "CPU", "GPU", "PSU", "RAM", "HDD"};
			for (int i = 0; i < parts.length; i++) {
				left.addElement(parts[i]);
			}			
	}
	
	/**
	 * Load method to unmarshall XML to Object.
	 * {@value} file 
	 * {@value} jaxbContext 
	 * {@value} jaxbUmarshaller 
	 * {@value} loadParts
	 * @param list.addElement(loadParts.name.get(i))
	 * @param rightList.setModel(list) 
	 * @exception 
	 **/
	private void load() throws Exception {
		try {
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Parts.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Parts loadParts = (Parts) jaxbUnmarshaller.unmarshal(file);
			
			for (int i = 0; i < loadParts.name.size(); i++) {
				list.addElement(loadParts.name.get(i));
			}
			rightList.setModel(list);
		} catch (JAXBException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Save method to marshall Object to XML.
	 * {@value} list 
	 * {@value} size 
	 * {@value} file 
	 * {@value} jaxbContext 
	 * {@value} jaxbMarshaller
	 * @param list.add(rightList.getModel().getElementAt(i))
	 * @param jaxbMarshaller.marshal(part, file)
	 * @exception 
	 **/
	public void save() throws Exception {
		List<String> list = part.getName();
		int size = rightList.getModel().getSize();
		for (int i = 0; i < size; i++) {
			list.add(rightList.getModel().getElementAt(i));
		}
		try {
			File file = new File("file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Parts.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(part, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
