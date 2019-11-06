//Niamh Hennigan 17418134

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI extends JFrame{

	private JButton onOff, light,display;
	private JSlider lightIntensity;
	private int lightIntensityInt = 1;
	private boolean toggle = true;
	private boolean on,time = false;
	private JTextArea status;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private Container container;
	private JRadioButtonMenuItem manual,timed;
	
	ImageIcon[] array = new ImageIcon[4];
	ImageIcon bulbOff = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\l3off.gif");
	
	public GUI() {
		//call button super and call it on top
		super("Light Controls");
		
		//create array for icons for the lightbulb
		ImageIcon bulbOn3  = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\l3.gif");
		ImageIcon bulbOn1  = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\level1.gif");
		ImageIcon bulbOn2  = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\level2.gif");
		ImageIcon bulbOn4  = new ImageIcon("C:\\Users\\niamh\\OneDrive\\Pictures\\level4.gif");
		array[0] = bulbOn1;
		array[1] = bulbOn2;
		array[2] = bulbOn3;
		array[3] = bulbOn4;
		
		//create handler, container, layout and constraints
		ButtonHandler handler = new ButtonHandler();
		container = getContentPane();
		layout = new GridBagLayout();
		container.setLayout(layout);
		constraints = new GridBagConstraints();

		//creates slider for lightIntensity and it's handler
		lightIntensity = new JSlider(SwingConstants.HORIZONTAL,1,4,1);
		lightIntensity.setMajorTickSpacing( 1 );
		lightIntensity.setPaintTicks( true );
		lightIntensity.setPaintLabels( true );
		lightIntensity.addChangeListener(
				new ChangeListener() {
					public void stateChanged( ChangeEvent e) {
						//updates the int value lightIntensityInt and set the light icon if lights are on
						setLightIntensityInt(lightIntensity.getValue());
						if(on) {
							light.setIcon(array[lightIntensityInt-1]);
						}
						System.out.println("The lights have been set to intensity "+ lightIntensityInt);
					}
				});
		
		// create onOff, light,status and display buttons 
		onOff = new JButton("On/Off");
		light = new JButton("Off",bulbOff);
		display = new JButton ("Display Settings");
		status = new JTextArea ("Displaying settings.... \n \n");
		
		// create manual and timed radio buttons
		manual = new JRadioButtonMenuItem("Manual");
		manual.setBackground(Color.WHITE);
		manual.setSelected(true);
		timed = new JRadioButtonMenuItem("Timed");
		timed.setBackground(Color.white);

		ButtonGroup group = new ButtonGroup();
		group.add(manual);
		group.add(timed);
		
		//Add actionListener handler to the button components
		onOff.addActionListener(handler);
		timed.addActionListener(handler);
		manual.addActionListener(handler);
		display.addActionListener(handler);
		manual.addActionListener(handler);
		timed.addActionListener(handler);
	
		//label for slider
		JLabel label = new JLabel("Light Intensity",SwingConstants.CENTER);
		Font font = new Font("",Font.BOLD,12);
		label.setFont(font);
		
		//lays out the components and adds them to the container
		constraints.fill = GridBagConstraints.VERTICAL;
		addComponent(light,0,1,1,10);
		constraints.fill = GridBagConstraints.BOTH;
		addComponent(onOff, 0, 0, 1,1);
		addComponent(lightIntensity,1,0,1,2);
		addComponent(label,3,0,1,1);
		addComponent(timed,5,0,1,1);
		addComponent(manual,4,0,1,1);
		addComponent(display,6,0,1,1);
		addComponent(status,7,0,1,2);
		
		setSize(500,350);
		setVisible( true) ;
		
		
	}

	public static void main (String arg []) {
		//create GUI
		GUI app = new GUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		
		// if timed button is pressed lights functionality turn to timed and manual is deactivated
			if (event.getSource() == timed) {
				time = true;
				System.out.println("The lights have been set to timed");
			}
			// if manual button is pressed lights functionality turn to timed and timed is deactivated
			else if (event.getSource() == manual) {
				time = false;
				System.out.println("The lights have been set to manual");
			}
		// if onOff button is pressed the lights are turned on to the correct intensity
		else if (event.getSource() == onOff) {
			//toggles light icon on and off
			if(toggle) {
				light.setIcon(array[lightIntensityInt-1]);
				light.setText("On");
				on = true;
				System.out.println("The lights have been turned on");
			}
			else {
				light.setIcon(bulbOff);
				light.setText("Off");
				on = false;
				System.out.println("The lights have been turned off");
			}
			toggle= !toggle;
		}
		// if display button is pressed a message pops up with the settings
		else if (event.getSource() == display) {
			String onValue,timedValue;
			if (!on) { onValue = "not "; }
			else onValue="";
			
			if (time) { timedValue = "timed";}
			else timedValue = "manual";

			status.setText ("The lights are "+ onValue + "on"
						  +"\nThe light intensity is " + lightIntensityInt
						  +"\nThe lights are set to "  + timedValue);
			//JOptionPane.showMessageDialog(null,status);
			System.out.println("The lights settings have been displayed in a text area");
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
	
	//sets the int needed for lightIntensity
	public void setLightIntensityInt (int value) {
		lightIntensityInt = value;
	}
	
}
	

