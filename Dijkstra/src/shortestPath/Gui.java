package shortestPath;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

public class Gui {
	
	static int DistanzRotGrün;
	 static int[] Vorgänger;
	 public static boolean[] visited;
	 public static TreeMap<Integer,ArrayList<Integer>> Liste2;
	 public static boolean NichtMöglich=false;
	
		static int iGrün,jGrün,nGrün;
		static int iRot,jRot,nRot;
		int RandomSpeed=100;
		static int dijkstraspeed=5;
		public JFrame frame;
		public static JButton[][] Button;
		public JButton ZielButton;
		public JButton ClearButton;
		public JButton RandomAuffüllen;
		public JSlider SchwarzSpeed;
		public static JSlider DijkstraSpeed;
		public JButton StartButton;
		public JButton DijkstraButton;
		public JButton SchwarzEntfernen;
		public JButton BlauEntfernen;
		private boolean mousePressed;
		public JLabel ilabel;
		public JLabel jlabel;
		public JLabel nlabel;
		public JLabel Distanz;

		public Gui() {
			Button = new JButton[35][58];    
			setGUI();
		}

		// frame setzen mit Farben und Textfields
		void setGUI() {

			
			frame = new JFrame("SHORTEST-PATH-FINDER");
			
			
			DijkstraSpeed = new JSlider(JSlider.HORIZONTAL,1/2,9,5);
			DijkstraSpeed.setBounds(410, 570, 120, 30);
			
			SchwarzSpeed= new JSlider(JSlider.HORIZONTAL,5,250,100);
			SchwarzSpeed.setBounds(270, 570, 140, 30);
		
			//die JTextFelder einrichten und an die gewünschte Position setzen. 
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
					
					Button[i][j] = new JButton();
					Button[i][j].setBounds(10 + j * 15, 25 + i * 15, 15, 15);
					Button[i][j].setBorder(new LineBorder(Color.BLACK));
					Button[i][j].setBackground(Color.white);
					Button[i][j].addMouseListener(new MouseAdapter() {
				        public void mousePressed(MouseEvent e) {
				        	for (int i = 0; i < Button.length; i++) {
								for (int j = 0; j < Button[0].length; j++) {
								if(e.getSource()==Button[i][j]) {
									ilabel.setText("I="+i);
									jlabel.setText("J="+j);
									nlabel.setText("N="+DykstraAlgorithmus.Umrechnung(i, j));
								}
								}
								}
				        	mousePressed = true;
				            new Thread() {
				                public void run() {
				                    while (mousePressed) {
										if(!(((JButton)e.getSource()).getBackground().equals(Color.RED) ||((JButton)e.getSource()).getBackground().equals(Color.GREEN))) {
											((JButton)e.getSource()).setBackground(Color.BLACK);
					
										}
				                    }
				                }

				            }.start();
				        }
				        public void mouseReleased(MouseEvent e) {
				            mousePressed = false;
				        }
				        });
					frame.add(Button[i][j]);
				}
				
			
			Font font= new Font("SansSerif", Font.BOLD, 13);	
			ilabel = new JLabel("I=");
			ilabel.setBounds(100, 10, 60, 10);
			ilabel.setFont(font);
			jlabel = new JLabel("J=");
			jlabel.setBounds(180, 10, 60, 10);
			jlabel.setFont(font);
			nlabel = new JLabel("N=");
			nlabel.setBounds(250, 10, 60, 10);
			nlabel.setFont(font);
			Distanz = new JLabel("DISTANCE:");
			Distanz.setBounds(500, 5, 220, 15);
			Distanz.setFont(font);
			
			StartButton = new JButton("Startpunkt");
			StartButton.setBounds(30, 570, 120, 30);
			StartButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					FärbenGrün();
					Startpunkt();
					
				}
				
			});
			
			
			ZielButton = new JButton("Zielpunkt");
			ZielButton.setBounds(30, 600, 120, 30);
			ZielButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					FärbenRot();
					
					ZielFärben();
					
				}
				
			});
			
			SchwarzEntfernen= new JButton("BLACK");
			SchwarzEntfernen.setBounds(180, 570, 80, 30);
			SchwarzEntfernen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Schwarzentfernen();
					
				}
				
			});
			
			BlauEntfernen= new JButton("BLAU");
			BlauEntfernen.setBounds(180, 630, 80, 30);
			BlauEntfernen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					BlauEntfernen();
					
				}
				
				
			});
			
			ClearButton = new JButton("clear");
			ClearButton.setBounds(180, 600, 80, 30);
			ClearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clear();
					
				}
				
			});
			
			RandomAuffüllen = new JButton("RandomAuffüllen");
			RandomAuffüllen.setBounds(270, 600, 140, 30);
			RandomAuffüllen.addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
		        	RandomSpeed = SchwarzSpeed.getValue();
		            mousePressed = true;
		                    Timer timer = new Timer(RandomSpeed, new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
						
									if(mousePressed==false) {
										((Timer)e.getSource()).stop();
									}
								randomAuffüllen();
									
								}
		                    	
		                    });timer.start();
		                
		        }
		        public void mouseReleased(MouseEvent e) {
		            mousePressed = false;
		        }
		        });
			DijkstraButton = new JButton("DijkstraAlgo");
			DijkstraButton.setBounds(410, 600, 120, 30);
			DijkstraButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					BlauEntfernen();
					for(int i=0;i<Button.length;i++) {
							if(Button[i][0].getBackground().equals(Color.GREEN)) {
								iGrün=i;
								jGrün=0;
								Button[i][0].setBackground(Color.WHITE);
						}
						for(int j=Button[0].length-6;j<Button[0].length;j++) {
							if(Button[i][j].getBackground().equals(Color.RED)) {
								iRot=i;
								jRot=j;
								Button[i][j].setBackground(Color.WHITE);
							}
						}
					}
					 nGrün = DykstraAlgorithmus.Umrechnung(iGrün, jGrün);
					 nRot  = DykstraAlgorithmus.Umrechnung(iRot, jRot);
					DykstraAlgorithmus dijkstra = new DykstraAlgorithmus(Button);
						int[][] result=dijkstra.adjazenzmatrix.clone();
						int[][] result1=result.clone();
						
						//Übergangslösung
						int a= Dijkstra(result1,nGrün,nRot);
						
						Distanz.setText("DISTANCE: "+a);
						if(a==Integer.MAX_VALUE) {
							Distanz.setText("DISTANCE: "+"NICHT MÖGLICH!");
							NichtMöglich=true;
						}
						
					Dijkstra1(result,nGrün,nRot);

						

				}				
				
			});
				
			
			
			
			frame.add(StartButton);
			frame.add(ZielButton);
			frame.add(ClearButton);
			frame.add(RandomAuffüllen);
			frame.add(SchwarzSpeed);
			frame.add(DijkstraButton);
			frame.add(ilabel);
			frame.add(jlabel);
			frame.add(nlabel);
			frame.add(Distanz);
			frame.add(SchwarzEntfernen);
			frame.add(BlauEntfernen);
			frame.add(DijkstraSpeed);
			}
			frame.setSize(900, 700);
			center();
			frame.setLayout(null);
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
		}

	
		void center() {
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation(dimension.width / 2 - frame.getWidth() / 2, dimension.height / 2 - frame.getHeight() / 2);
		}
		
		void ZielFärben() {
			Random random = new Random();
			int Breite =Button[0].length- (random.nextInt(4)+1);
			int Höhe   = random.nextInt(Button.length);
			Button[Höhe][Breite].setBackground(Color.RED);
			Button[Höhe][Breite].setBorder(new LineBorder(Color.RED));

		}
		
		private void FärbenRot() {
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
				
			if(Button[i][j].getBackground().equals(Color.RED)) {
			Button[i][j].setBackground(Color.WHITE);
			Button[i][j].setBorder(new LineBorder(Color.BLACK));
			return;
			}	
				}
			}
		}
		
		private void FärbenGrün() {
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
				
			if(Button[i][j].getBackground().equals(Color.GREEN)) {
			Button[i][j].setBackground(Color.WHITE);
			Button[i][j].setBorder(new LineBorder(Color.BLACK));
			return;
			}	
				}
			}
		}
		
		void clear() {
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
				Button[i][j].setBackground(Color.WHITE);
				Button[i][j].setBorder(new LineBorder(Color.BLACK));
				}
				}
			Werkseinstellungen();
		}
		
		private void BlauEntfernen() {
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
					if(!(Button[i][j].getBackground().equals(Color.GREEN)|| Button[i][j].getBackground().equals(Color.RED)||Button[i][j].getBackground().equals(Color.BLACK) )){
				Button[i][j].setBackground(Color.WHITE);
				Button[i][j].setBorder(new LineBorder(Color.BLACK));
				}
				}
				}
			Werkseinstellungen();
		}
		private void Schwarzentfernen() {
			
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
					if(!(Button[i][j].getBackground().equals(Color.GREEN)|| Button[i][j].getBackground().equals(Color.RED)||Button[i][j].getBackground().equals(Color.BLUE))){
				Button[i][j].setBackground(Color.WHITE);
				Button[i][j].setBorder(new LineBorder(Color.BLACK));
				}
				}
				}
			Werkseinstellungen();
			
		}
		
		private void randomAuffüllen() {
			Random random = new Random();
			int Breite = random.nextInt(Button[0].length-6);
			int Höhe   = random.nextInt(Button.length);
			Button[Höhe][Breite+1].setBackground(Color.BLACK);
			ilabel.setText("I="+Höhe);
			jlabel.setText("J="+Breite);
			nlabel.setText("N="+DykstraAlgorithmus.Umrechnung(Höhe, Breite));

			
		}
		private void Startpunkt() {
			Random random = new Random();
			int Höhe = random.nextInt(Button.length);
			Button[Höhe][0].setBackground(Color.GREEN);
			Button[Höhe][0].setBorder(new LineBorder(Color.GREEN));
		}
		
		private void Werkseinstellungen() {
			ilabel.setText("I=");
			jlabel.setText("J=");
			nlabel.setText("N=");
			Distanz.setText("DISTANCE:");
		}
		
		private static void CyanWeg() {
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
					if(Button[i][j].getBackground().equals(Color.CYAN)){
				Button[i][j].setBackground(Color.WHITE);
				Button[i][j].setBorder(new LineBorder(Color.BLACK));
				}
				}
				}
		}
		

public static void Dijkstra1(int[][] adjazenzmatrix,int nGrün,int nRot) {
	dijkstraspeed = DijkstraSpeed.getValue();
	
	Vorgänger= new int[adjazenzmatrix.length];
	visited = new boolean[adjazenzmatrix.length];
	for(int i=0;i<visited.length;i++) {
		visited[i]=false;
	}
	int distance[] = new int[adjazenzmatrix.length];	
	for(int i=0;i<adjazenzmatrix.length;i++) {
		distance[i]=Integer.MAX_VALUE;
	}
	distance[nGrün]=0;
	TreeMap<Integer,ArrayList<Integer>> Liste2= new TreeMap<>();
	
	SwingUtilities.invokeLater(() -> {

		Timer timer = new Timer(dijkstraspeed, new ActionListener() {
			
			boolean fertig = false;
			int i=0;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		for(int i=0; i<adjazenzmatrix.length;i++) {
		if(visited[i]) {
			if(visited[nRot])fertig=true;
			
			if(Button[DykstraAlgorithmus.UmrechungIndex1(i)][i%58].getBackground()!=Color.BLACK) {
			Button[DykstraAlgorithmus.UmrechungIndex1(i)][i%58].setBackground(Color.CYAN);
			}
		}
		}
		
		if(i==adjazenzmatrix.length-1) {
		JOptionPane.showMessageDialog(null, "Nicht gefunden");
		}
		
		if(fertig) {
			
			CyanWeg();
			if(NichtMöglich) {
				JOptionPane.showMessageDialog(null, "Das Ziel kann nicht erreicht werden", "Nicht erreichbar", JOptionPane.ERROR_MESSAGE);
			}else {
			DistanzRotGrün = distance[nRot];
			Vorgänger[nGrün]=Integer.MIN_VALUE;
			
			ArrayList<Integer> Ergebnis = new ArrayList<>(DykstraAlgorithmus.Rute(Vorgänger, nRot));
			for(Integer i: Ergebnis) {
				Button[DykstraAlgorithmus.UmrechungIndex1(i)][i%58].setBackground(Color.BLUE);
			}}
			Button[iGrün][0].setBackground(Color.GREEN);
			Button[iRot][jRot].setBackground(Color.RED);
			
			
			((Timer)arg0.getSource()).stop();
		
		}
		
		int minVertex = DykstraAlgorithmus.findMinVertex(distance,visited);
		visited[minVertex]=true;
		//Iterationen, um die kleinste Distanz festzulegen, in Tabellenform wäre dies horizontal
		for(int j = 0;j<adjazenzmatrix.length;j++) {
			if(adjazenzmatrix[minVertex][j]!=0 && !visited[j] && distance[minVertex] !=Integer.MAX_VALUE) {
				int newDist = distance[minVertex] + adjazenzmatrix[minVertex][j];
//				System.out.print(distance[minVertex] +  "  " + adjazenzmatrix[minVertex][j]+ "  " + newDist + "  " + distance[j] + " i="+i+" j="+j +" "+java.util.Arrays.toString(Vorgänger)+" "+Liste2+"\n");
				if(newDist<distance[j]) {
					
					distance[j]= newDist;
//					System.out.print(Liste+ "\n");
//					System.out.print(TreeIntoArrayList(Liste)+"\n");
					ArrayList<Integer> Hilfsliste = new ArrayList<>();
					Hilfsliste.add(j);
					if(Liste2.containsKey(newDist)) {
						Liste2.get(newDist).add(j);
					}else {
					Liste2.put(newDist, Hilfsliste);
					}
//					System.out.print(Liste2+"\n");
//					System.out.print("||||" +TreeIntoArrayList2(Liste2)+"\n");
					
					if(i==0) {
						Vorgänger[j]=nGrün;
						continue;
					}
					int n=DykstraAlgorithmus.TreeIntoArrayList(Liste2).get(i-1);
					Vorgänger[j]=n;
//					System.out.print(java.util.Arrays.toString(Vorgänger)+"\n");
					// j gibt die tatsächlichen Knoten wieder und i gibt nur an, bei dem wie vielten Knoten wir sind aber nicht an welcher Position dieser ist.
//				System.out.print(j+ " " + i + "\n");
				}
			}
		}
		Button[iGrün][0].setBackground(Color.GREEN);
		Button[iRot][jRot].setBackground(Color.RED);
		i++;
	}
	
		});timer.start();

		});
	
}
		
		
		
		
		
		
		
		
		
		
		
		
		public static int Dijkstra(int[][] adjazenzmatrix,int nGrün,int nRot){
			
			
			visited = new boolean[adjazenzmatrix.length];
			for(int i=0;i<visited.length;i++) {
				visited[i]=false;
			}
			int distance[] = new int[adjazenzmatrix.length];	
			for(int i=0;i<adjazenzmatrix.length;i++) {
				distance[i]=Integer.MAX_VALUE;
			}
			distance[nGrün]=0;
			//Iterationen der Knoten.
			//Erster Integer gibt die Distanz an(newDist). Die ArrayList gibt alle Knoten an, die in einer Iteration 
	
			for(int i=0;i<adjazenzmatrix.length-1;i++) {

				int minVertex = DykstraAlgorithmus.findMinVertex(distance,visited);
				visited[minVertex]=true;
				//Iterationen, um die kleinste Distanz festzulegen, in Tabellenform wäre dies horizontal
				for(int j = 0;j<adjazenzmatrix.length;j++) {
					if(adjazenzmatrix[minVertex][j]!=0 && !visited[j] && distance[minVertex] !=Integer.MAX_VALUE) {
						int newDist = distance[minVertex] + adjazenzmatrix[minVertex][j];
//						System.out.print(distance[minVertex] +  "  " + adjazenzmatrix[minVertex][j]+ "  " + newDist + "  " + distance[j] + " i="+i+" j="+j +" "+java.util.Arrays.toString(Vorgänger)+" "+Liste2+"\n");
						if(newDist<distance[j]) {
							distance[j]= newDist;
						}
					}
				}
			}
			DistanzRotGrün = distance[nRot];
			return DistanzRotGrün;
		}

		//Wählt den Knoten mit der geringsten Distanz aus. 

		
		
		
		
		
		public static void main(String[] args) {			
			Gui a = new Gui();
			
//			int[][] Matrix = {{0,1,1,0},{0,0,1,0},{0,1,0,1},{0,1,0,0}};
//			int[][] Matrix2 = {{0,7,11,0,0,14},{7,0,10,15,0,0},{11,10,0,11,0,2},{0,15,11,0,6,0},{0,0,0,6,0,9},{14,0,2,0,9,0}};
				
//				System.out.print(DykstraAlgorithmus.Dijkstra(Matrix2, 3, 5));
//				System.out.print(java.util.Arrays.toString(DykstraAlgorithmus.Vorgänger));
//					System.out.print(DykstraAlgorithmus.Rute(DykstraAlgorithmus.Vorgänger, 5));
					
					
					
		}
		
}