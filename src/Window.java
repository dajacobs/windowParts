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
		
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = leftList.getSelectedIndex();
				list.addElement(leftList.getModel().getElementAt(index));
				rightList.setModel(list);
				frame.getContentPane().add(rightList);
 			}
		});
		
		Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = rightList.getSelectedIndex();
				list.removeElement(rightList.getModel().getElementAt(index));
				rightList.setModel(list);
				frame.getContentPane().add(rightList);
			}
		});

		menuItemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//right.clear();
					//load();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//save();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		menuExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	private void addParts() {
			String[] parts = {"Case", "Motherboard", "CPU", "GPU", "PSU", "RAM", "HDD"};
			for (int i = 0; i < parts.length; i++) {
				left.addElement(parts[i]);
			}			
	}

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
