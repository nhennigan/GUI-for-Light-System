//Niamh Hennigan 17418134

import java.awt.*;
import java.awt.event.*;

//import javax.swing.JButton;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Graphics;

public class GUI extends JFrame{

	private JButton onOff, light,manual,timed,display;
	private JSlider lightIntensity;
	private int lightIntensityInt;
	private JTextArea status;
	private JTextArea label2;
	private boolean toggle = true;
	private boolean on,time = false;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private Container container;
	
	ImageIcon bulbOff = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\l3off.gif");
	ImageIcon bulbOn  = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\l3.gif");
	
	public GUI() {
		//call button super and call it on top
		super("Light Controls");
		
		ButtonHandler handler = new ButtonHandler();
		
		//new container
		container = getContentPane();
		//container layout
		layout = new GridBagLayout();
		container.setLayout(layout);
		
		constraints = new GridBagConstraints();

		lightIntensity = new JSlider(SwingConstants.HORIZONTAL,1,4,1);
		lightIntensity.setMajorTickSpacing( 1 );
		lightIntensity.setPaintTicks( true );
		lightIntensity.setPaintLabels( true );
		lightIntensity.addChangeListener(
				new ChangeListener() {
					public void stateChanged( ChangeEvent e) {
						setLightIntensityInt(lightIntensity.getValue());
					}
				});
		
		// create onOff button and light button with event handler
		onOff = new JButton("On/Off");
		light = new JButton("Off",bulbOff);
		onOff.addActionListener(handler);
//		onOff.addActionListener(
//				new ActionListener() {
//					public void actionPerformed( ActionEvent event) {
//						//toggles light icon on and off
//						if(toggle) {
//							light.setIcon(bulbOn);
//							light.setText("On");
//							on = true;
//						//	label2 = new JTextArea("Kitchen Light on\nBathroom Light on\nHall Light on\nBedroom1 Light on");
//						}
//						else {
//							light.setIcon(bulbOff);
//							light.setText("Off");
//							on = false;
//						//	label2 = new JTextArea("Kitchen Light off\nBathroom Light off\nHall Light off\nBedroom1 Light off");
//						}
//						toggle= !toggle;
//					}	
//				});
		
		manual = new JButton("Manual");
	//	manual.setActionCommand("disable");
		timed = new JButton ("Timed");
	//	timed.setEnabled(false);
	//	timed.setActionCommand("disable");
		
	
		
		timed.addActionListener(handler);
		manual.addActionListener(handler);
		
		display = new JButton ("Display Settings");
		
		display.addActionListener(handler);
//		display.addActionListener(
//				new ActionListener() {
//					public void actionPerformed( ActionEvent event) {
////						String onValue,timedValue;
////						if (!on) {
////							onValue = "not ";
////						}
////						else onValue="";
////						
////						if (time) {
////							timedValue = "timed";
////						}
////						else timedValue = "manual";
////						
////						status.setText("The lights are "+ onValue + "on"
////									  +"\nThe light intensity is " + lightIntensityInt
////									  +"\nThe lights are set to "  + timedValue);
//					}	
//				}
//					);
		
		
		status = new JTextArea ("Displaying settings.... \n \n");
	
		JLabel label = new JLabel("Light Intensity",SwingConstants.CENTER);
		Font font = new Font("",Font.BOLD,12);
		label.setFont(font);
		//label2 = new JTextArea("Kitchen Light \nBathroom Light \nHall Light \nBedroom1 Light ");
		
		constraints.fill = GridBagConstraints.VERTICAL;
		addComponent(light,0,1,1,10);
		//addComponent(label2,5,1,1,4);
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(onOff, 0, 0, 1,1);
		addComponent(lightIntensity,1,0,1,2);
		addComponent(label,3,0,1,1);
		addComponent(manual,4,0,1,1);
		addComponent(timed,5,0,1,1);
		addComponent(display,6,0,1,1);
		addComponent(status,7,0,1,2);
		
		setSize(400,350);
		setVisible( true) ;
		
		
	}

	public static void main (String arg []) {
		//create GUI
		GUI app = new GUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
//	private class ButtonHandler implements ActionListener{
//		public void actionPerformed( ActionEvent event) {
//			//Displays message when button is pressed
//			if(toggle) {
//				light.setIcon(bulbOn);
//				light.setText("On");
//			}
//			else {
//				light.setIcon(bulbOff);
//				light.setText("Off");
//			}
//			toggle= !toggle;
//		}
//	
//	}
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		
		if (event.getSource() == timed) {
			//timed.setBorder(new LineBorder(Color.blue));
			manual.setBackground(Color.gray);
			timed.setBackground(display.getBackground());
			time = true;
		}
		else if (event.getSource() == manual) {
			manual.setBackground(display.getBackground());
			timed.setBackground(Color.gray);
			time = false;
		}
		else if (event.getSource() == onOff) {
			//toggles light icon on and off
			if(toggle) {
				light.setIcon(bulbOn);
				light.setText("On");
				on = true;
			}
			else {
				light.setIcon(bulbOff);
				light.setText("Off");
				on = false;
			}
			toggle= !toggle;
		}
		else if (event.getSource() == display) {
			String onValue,timedValue;
			if (!on) {
				onValue = "not ";
			}
			else onValue="";
			
			if (time) {
				timedValue = "timed";
			}
			else timedValue = "manual";
			
			status.setText("The lights are "+ onValue + "on"
						  +"\nThe light intensity is " + lightIntensityInt
						  +"\nThe lights are set to "  + timedValue);
		}
		
			
		
	}
	}
	//add components to the container in the correct place
	private void addComponent( Component component, int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy= row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		layout.setConstraints(component, constraints);
		container.add(component);
	}
	
	public void setLightIntensityInt (int value) {
		lightIntensityInt = value;
	}
	
}
	

