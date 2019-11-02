//Niamh Hennigan 17418134

import java.awt.*;
import java.awt.event.*;

//import javax.swing.JButton;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame{

	private JButton onOff, light,manual,timed,display;
	private JSlider lightIntensity;
	private int lightIntensityInt;
	private JTextArea status;
	private boolean toggle = true;
	private boolean on = false;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private Container container;
	
	public GUI() {
		//call button super and call it on top
		super("Light Controls");
		
		ImageIcon bulbOff = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\l3off.gif");
		ImageIcon bulbOn  = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\l3.gif");
		
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
		onOff.addActionListener(
				new ActionListener() {
					public void actionPerformed( ActionEvent event) {
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
				});
		
		manual = new JButton("Manual");
		timed = new JButton ("Timed");
		timed.addActionListener(
				new ActionListener() {
					public void actionPerformed( ActionEvent event) {
						
					}
							
				});
		
		display = new JButton ("Display Settings");
		display.addActionListener(
				new ActionListener() {
					public void actionPerformed( ActionEvent event) {
						String onValue;
						if (!on) {
							onValue = "not ";
						}
						else onValue="";
						
						status.setText("The lights are "+ onValue + "on"
									  +"\nThe light intensity is " + lightIntensityInt
										 );
					}	
				});
		
		
		status = new JTextArea ("Displaying settings.... \n \n");
		
		JLabel label = new JLabel("Light Intensity",SwingConstants.CENTER);
		Font font = new Font("",Font.BOLD,12);
		label.setFont(font);
		JLabel label2 = new JLabel("Kitchen \nBathroom \nHall \nBedroom1 ");
		
		constraints.fill = GridBagConstraints.VERTICAL;
		addComponent(light,0,1,1,10);
	//	addComponent(label2,5,1,1,4)
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
	

