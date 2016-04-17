import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import javax.swing.filechooser.*;
import javax.swing.text.*;
class Notepad extends JFrame {

private JTextArea tp;
private JMenu m,mm;
private JCheckBox mmm;
static JMenuBar mb;
private JMenuItem m1,m2,m3,m4,mm1,mm2,mm3;
static JPanel p;
void menuCreation(){
	p=new JPanel();
	mb=new JMenuBar();
	tp=new JTextArea("");
		p.setLayout(new GridLayout(1,1));
		tp.setLineWrap(true);
		tp.setVisible(true);
	JScrollPane scroll=new JScrollPane(tp);
	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
		scroll.setPreferredSize(new Dimension(250,250));
		p.add(scroll); 
	
//file menu
	m=new JMenu("File");
	ImageIcon i1 =new ImageIcon("images/Open.png");
	m1=new JMenuItem("Open",i1);
	m1.addActionListener( new ActionListener(){
		public void actionPerformed(ActionEvent e3){
			JFileChooser jf=new JFileChooser();
			FileNameExtensionFilter fx=new FileNameExtensionFilter("txt and programming files","txt","java","c","cpp");
			jf.setFileFilter(fx);
			int option=jf.showOpenDialog(m1);
			if(option==JFileChooser.APPROVE_OPTION)
			{
				tp.setText("");
				try(BufferedReader f=new BufferedReader(new FileReader(jf.getSelectedFile().getPath()))){
					
					//BufferedReader f=new BufferedReader(new FileReader(jf.getSelectedFile().getPath()));
					String currentline;
					while((currentline=f.readLine())!=null){
					tp.append(currentline+"\n");
					
					}
					tp.setVisible(true);
					
				}
				catch(Exception ex){JOptionPane.showMessageDialog(null,"File was not readable");}
				}
						
		}
			
	});
	
	ImageIcon i2 =new ImageIcon("images/Save.png");
	m2=new JMenuItem("Save",i2);
	m2.addActionListener( new ActionListener(){
		public void actionPerformed(ActionEvent e3){
			JFileChooser js=new JFileChooser();
			int option=js.showSaveDialog(m2);
			if(option==JFileChooser.APPROVE_OPTION)
			{
				try(BufferedWriter fs=new BufferedWriter(new FileWriter(js.getSelectedFile().getPath()))){
					
					//BufferedReader f=new BufferedReader(new FileReader(jf.getSelectedFile().getPath()));
					String currenttext=tp.getText();
					fs.write(currenttext);
					}
				
				catch(Exception ex){JOptionPane.showMessageDialog(null,"File was not readable");}
				}
			
				
				}
			
		}
		
		
		
	);
		
	ImageIcon i3 =new ImageIcon("images/Close.png");
	m3=new JMenuItem("Close",i3);
	m3.addActionListener( new ActionListener(){
		public void actionPerformed(ActionEvent e3){
			int option = JOptionPane.showConfirmDialog(null,"Do you really want to Quit ?"," Exit Menu",JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION)
			System.exit(0);
			
		}
		
	});
	ImageIcon i4 =new ImageIcon("images/New.png");
	m4=new JMenuItem("New",i4);
	m4.addActionListener(new ActionListener(){
		
     public void actionPerformed(ActionEvent e4){
		 tp.setText("");
		 tp.setVisible(true);
		 
	 }		
	
	});
	
	m.add(m4);
	m.add(m1);
	m.add(m2);
	m.add(m3);
	mb.add(m);
 //edit menu
	mm=new JMenu("Edit");
	ImageIcon ii1 =new ImageIcon("images/Cut.png");
	mm1=new JMenuItem(new DefaultEditorKit.CutAction());
	mm1.setText("Cut");
	mm1.setIcon(ii1);
	ImageIcon ii2 =new ImageIcon("images/Copy.png");
	mm2=new JMenuItem(new DefaultEditorKit.CopyAction());
	mm2.setText("Copy");
	mm2.setIcon(ii2);
	
	ImageIcon ii3 =new ImageIcon("images/Paste.png");
	mm3=new JMenuItem(new DefaultEditorKit.PasteAction());
	mm3.setText("Paste");
	mm3.setIcon(ii3);
	
	mm.add(mm1);
	mm.add(mm2);
	mm.add(mm3);
	mb.add(mm);
//view menu
	//ImageIcon iii3 =new ImageIcon("images/Highlight.png");
	mmm=new JCheckBox("Highlight");
	//mmm.setIcon(iii3);
	mmm.addItemListener(new ItemListener(){
		public void itemStateChanged(ItemEvent e8){
			
			tp.setSelectionColor(Color.orange);
		if(e8.getStateChange()==ItemEvent.DESELECTED)
				tp.setSelectionColor(Color.lightGray);
		
		}
		
		
		
		});
	final JSlider slider = new JSlider(JSlider.HORIZONTAL, 10, 30, 12);  
	slider.setMinorTickSpacing(1);  
	slider.setMajorTickSpacing(3);  
 	
  	slider.setPaintTicks(true);  
	slider.setPaintLabels(true); 
	
	slider.addChangeListener(new ChangeListener(){
		
		public void stateChanged(ChangeEvent e6){
		
		Font font=new Font("Courier",12,slider.getValue());
		tp.setFont(font);
	
		}
		
		
		});
	/*
	ImageIcon iii2 =new ImageIcon("images/Font Color.png");
	mmm2=new JMenuItem("Font Color",iii2);
	//mmm2.addActionListener(
	ImageIcon iii3 =new ImageIcon("images/Highlight.png");
	mmm3=new JMenuItem("Font Style",iii3);
	//mmm.add(mmm1);
	mmm.add(mmm2);
	mmm.add(mmm3);*/
	mb.add(mmm);
	 mb.add(slider);
	}
public static void main (String[] args){
Notepad n=new Notepad();
n.menuCreation();
n.setLayout(new BorderLayout());
n.add(mb,BorderLayout.NORTH);
n.add(p,BorderLayout.CENTER);

n.setSize(new Dimension(500,500));
n.setLocation(200,100);
n.setVisible(true);
n.setDefaultCloseOperation(EXIT_ON_CLOSE);

}

}
