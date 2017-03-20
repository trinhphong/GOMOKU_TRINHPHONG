package gomoku;


import javax.swing.JFrame;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
        JFrame frame = new JFrame();
        frame.add(new GUI());
        frame.setTitle("fdfdsf");
        frame.setSize(700, 700);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
		JFrame frame1 = new JFrame();
		frame1.add(new View());
		frame1.setTitle("fdfdsf");
	    frame1.setSize(700, 700);        
	    frame1.setLocationRelativeTo(null);
	    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	    frame1.setVisible(true);
	}

}
