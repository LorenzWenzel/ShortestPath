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
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
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
	
	static int DistanzRotGr�n;
	 static int[] Vorg�nger;
	 public static boolean[] visited;
	 public static TreeMap<Integer,ArrayList<Integer>> Liste2;
	 public static boolean NichtM�glich=false;
	 public static Stack<Integer> stack;
	
		static int iGr�n,jGr�n,nGr�n;
		static int iRot,jRot,nRot;
		int RandomSpeed=100;
		static int dijkstraspeed=5;
		static int bfsspeed;
		public JFrame frame;
		public static JButton[][] Button;
		public JButton ZielButton;
		public JButton ClearButton;
		public JButton RandomAuff�llen;
		public JSlider SchwarzSpeed;
		public static JSlider DijkstraSpeed;
		public JSlider DFSSpeed;
		public static JSlider BFSSpeed;
		public JButton StartButton;
		public JButton DijkstraButton;
		public JButton SchwarzEntfernen;
		public JButton BlauEntfernen;
		public JButton PinkEntfernen;
		public JButton DFSButton;
		public JButton BFSButton; 
		
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
			
			BFSSpeed = new JSlider(JSlider.HORIZONTAL,1/2,25,8);
			BFSSpeed.setBounds(650, 570, 120, 30);
			
			DFSSpeed = new JSlider(JSlider.HORIZONTAL,1/2,25,10);
			DFSSpeed.setBounds(530, 570, 120, 30);
			
			DijkstraSpeed = new JSlider(JSlider.HORIZONTAL,1/2,9,5);
			DijkstraSpeed.setBounds(410, 570, 120, 30);
			
			SchwarzSpeed= new JSlider(JSlider.HORIZONTAL,5,250,100);
			SchwarzSpeed.setBounds(270, 570, 140, 30);
		
			//die JTextFelder einrichten und an die gew�nschte Position setzen. 
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
									nlabel.setText("N="+Matrix.Umrechnung(i, j));
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
					
					F�rbenGr�n();
					Startpunkt();
					
				}
				
			});
			
			
			ZielButton = new JButton("Zielpunkt");
			ZielButton.setBounds(30, 600, 120, 30);
			ZielButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					F�rbenRot();
					
					ZielF�rben();
					
				}
				
			});
			
			SchwarzEntfernen= new JButton("BLACK");
			SchwarzEntfernen.setBounds(180, 580, 80, 30);
			SchwarzEntfernen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Schwarzentfernen();
					
				}
				
			});
			PinkEntfernen = new JButton("PINK");
			PinkEntfernen.setBounds(180, 640, 80, 30);
			PinkEntfernen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					PinkEntfernen();
					Werkseinstellungen();
				}
				
			});
			
			BlauEntfernen= new JButton("BLAU");
			BlauEntfernen.setBounds(180, 610, 80, 30);
			BlauEntfernen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					BlauEntfernen();
					Werkseinstellungen();
				}
				
				
			});
			
			ClearButton = new JButton("clear");
			ClearButton.setBounds(180, 550, 80, 30);
			ClearButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					clear();
					
				}
				
			});
			
			RandomAuff�llen = new JButton("RandomAuff�llen");
			RandomAuff�llen.setBounds(270, 600, 140, 30);
			RandomAuff�llen.addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
		        	RandomSpeed = SchwarzSpeed.getValue();
		            mousePressed = true;
		                    Timer timer = new Timer(RandomSpeed, new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
						
									if(mousePressed==false) {
										((Timer)e.getSource()).stop();
									}
								randomAuff�llen();
									
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

					NichtM�glich=false;
					BlauEntfernen();
					for(int i=0;i<Button.length;i++) {
							if(Button[i][0].getBackground().equals(Color.GREEN)) {
								iGr�n=i;
								jGr�n=0;
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
					 nGr�n = Matrix.Umrechnung(iGr�n, jGr�n);
					 nRot  = Matrix.Umrechnung(iRot, jRot);
					 if(nRot==0) {
							JOptionPane.showMessageDialog(null, "Legen sie ein Ziel fest", "Kein Ziel", JOptionPane.ERROR_MESSAGE);
							Button[iGr�n][jGr�n].setBackground(Color.GREEN);
							return;
						}
					Matrix dijkstra = new Matrix(Button);
						int[][] result=dijkstra.adjazenzmatrix.clone();
						int[][] result1=result.clone();
						
						//�bergangsl�sung
						int a= Dijkstra(result1,nGr�n,nRot);
						
						Distanz.setText("DISTANCE: "+a);
						if(a==Integer.MAX_VALUE) {
							Distanz.setText("DISTANCE: "+"NICHT M�GLICH!");
							NichtM�glich=true;
						}
						
					Dijkstra1(result,nGr�n,nRot);

						

				}				
				
			});
			
			BFSButton = new JButton ("BFS-Button");
			BFSButton.setBounds(650, 600, 120, 30);
			BFSButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					NichtM�glich=false;
					PinkEntfernen();
					for(int i=0;i<Button.length;i++) {
						if(Button[i][0].getBackground().equals(Color.GREEN)) {
							iGr�n=i;
							jGr�n=0;
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
					 nGr�n = Matrix.Umrechnung(iGr�n, jGr�n);
					 nRot  = Matrix.Umrechnung(iRot, jRot);
						Matrix BFS = new Matrix(Button);
						BreadthFirstSearch(BFS.adjazenzmatrix,nGr�n);
				}
				
			});
			
			DFSButton = new JButton("DFS-Button");
			DFSButton.setBounds(530, 600, 120, 30);
			DFSButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

			
					PinkEntfernen();
					for(int i=0;i<Button.length;i++) {
						if(Button[i][0].getBackground().equals(Color.GREEN)) {
							iGr�n=i;
							jGr�n=0;
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
				 nGr�n = Matrix.Umrechnung(iGr�n, jGr�n);
				 nRot  = Matrix.Umrechnung(iRot, jRot);
					Matrix DFS = new Matrix(Button);
					DepthFirstSearch(DFS.adjazenzmatrix,nGr�n);
					
				}
				
			});	
			
			
			
			frame.add(StartButton);
			frame.add(ZielButton);
			frame.add(ClearButton);
			frame.add(RandomAuff�llen);
			frame.add(SchwarzSpeed);
			frame.add(DijkstraButton);
			frame.add(ilabel);
			frame.add(jlabel);
			frame.add(nlabel);
			frame.add(Distanz);
			frame.add(SchwarzEntfernen);
			frame.add(BlauEntfernen);
			frame.add(DijkstraSpeed);
			frame.add(DFSButton);
			frame.add(PinkEntfernen);
			frame.add(DFSSpeed);
			frame.add(BFSButton);
			frame.add(BFSSpeed);
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
		
		void ZielF�rben() {
			Random random = new Random();
			int Breite =Button[0].length- (random.nextInt(4)+1);
			int H�he   = random.nextInt(Button.length);
			Button[H�he][Breite].setBackground(Color.RED);
			Button[H�he][Breite].setBorder(new LineBorder(Color.RED));

		}
		
		private void F�rbenRot() {
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
		
		private void F�rbenGr�n() {
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
					if(!(Button[i][j].getBackground().equals(Color.GREEN)|| Button[i][j].getBackground().equals(Color.RED)||Button[i][j].getBackground().equals(Color.BLACK)||Button[i][j].getBackground().equals(Color.PINK) )){
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
					if(!(Button[i][j].getBackground().equals(Color.GREEN)|| Button[i][j].getBackground().equals(Color.RED)||Button[i][j].getBackground().equals(Color.BLUE)||Button[i][j].getBackground().equals(Color.PINK))){
				Button[i][j].setBackground(Color.WHITE);
				Button[i][j].setBorder(new LineBorder(Color.BLACK));
				}
				}
				}
			Werkseinstellungen();
			
		}
		
		private void randomAuff�llen() {
			Random random = new Random();
			int Breite = random.nextInt(Button[0].length-6);
			int H�he   = random.nextInt(Button.length);
			Button[H�he][Breite+1].setBackground(Color.BLACK);
			ilabel.setText("I="+H�he);
			jlabel.setText("J="+Breite);
			nlabel.setText("N="+Matrix.Umrechnung(H�he, Breite));

			
		}
		private void Startpunkt() {
			Random random = new Random();
			int H�he = random.nextInt(Button.length);
			Button[H�he][0].setBackground(Color.GREEN);
			Button[H�he][0].setBorder(new LineBorder(Color.GREEN));
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
		private static void PinkEntfernen() {
			
			for (int i = 0; i < Button.length; i++) {
				for (int j = 0; j < Button[0].length; j++) {
					if(Button[i][j].getBackground().equals(Color.PINK)){
				Button[i][j].setBackground(Color.WHITE);
				Button[i][j].setBorder(new LineBorder(Color.BLACK));
				}
				}
			}						
		}
		

public static void Dijkstra1(int[][] adjazenzmatrix,int nGr�n,int nRot) {
	dijkstraspeed = DijkstraSpeed.getValue();
	
	Vorg�nger= new int[adjazenzmatrix.length];
	visited = new boolean[adjazenzmatrix.length];
	for(int i=0;i<visited.length;i++) {
		visited[i]=false;
	}
	int distance[] = new int[adjazenzmatrix.length];	
	for(int i=0;i<adjazenzmatrix.length;i++) {
		distance[i]=Integer.MAX_VALUE;
	}
	distance[nGr�n]=0;
	TreeMap<Integer,ArrayList<Integer>> Liste2= new TreeMap<>();
	
	SwingUtilities.invokeLater(() -> {

		Timer timer = new Timer(dijkstraspeed, new ActionListener() {
			
			boolean fertig = false;
			int i=0;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(NichtM�glich) {
			Matrix dijkstra= new Matrix(Button);
			BreadthFirstSearch(dijkstra.adjazenzmatrix,nGr�n);	
			((Timer)arg0.getSource()).stop();
			}else {

		
		for(int i=0; i<adjazenzmatrix.length;i++) {
		if(visited[i]) {
			if(visited[nRot])fertig=true;
			
			if(Button[Matrix.UmrechungIndex1(i)][i%58].getBackground()!=Color.BLACK) {
			Button[Matrix.UmrechungIndex1(i)][i%58].setBackground(Color.CYAN);
			}
		}
		}
		
		if(i==adjazenzmatrix.length-1) {
		JOptionPane.showMessageDialog(null, "Nicht gefunden");
		}
		
		if(fertig) {
			
			CyanWeg();
			
			DistanzRotGr�n = distance[nRot];
			Vorg�nger[nGr�n]=Integer.MIN_VALUE;
			
			ArrayList<Integer> Ergebnis = new ArrayList<>(Matrix.Rute(Vorg�nger, nRot));
			for(Integer i: Ergebnis) {
				Button[Matrix.UmrechungIndex1(i)][i%58].setBackground(Color.BLUE);
			}
			Button[iGr�n][0].setBackground(Color.GREEN);
			Button[iRot][jRot].setBackground(Color.RED);
			
			
			((Timer)arg0.getSource()).stop();
		
		}
		
		int minVertex = Matrix.findMinVertex(distance,visited);
		visited[minVertex]=true;
		//Iterationen, um die kleinste Distanz festzulegen, in Tabellenform w�re dies horizontal
		for(int j = 0;j<adjazenzmatrix.length;j++) {
			if(adjazenzmatrix[minVertex][j]!=0 && !visited[j] && distance[minVertex] !=Integer.MAX_VALUE) {
				int newDist = distance[minVertex] + adjazenzmatrix[minVertex][j];
//				System.out.print(distance[minVertex] +  "  " + adjazenzmatrix[minVertex][j]+ "  " + newDist + "  " + distance[j] + " i="+i+" j="+j +" "+java.util.Arrays.toString(Vorg�nger)+" "+Liste2+"\n");
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
						Vorg�nger[j]=nGr�n;
						continue;
					}
					int n=Matrix.TreeIntoArrayList(Liste2).get(i-1);
					Vorg�nger[j]=n;
//					System.out.print(java.util.Arrays.toString(Vorg�nger)+"\n");
					// j gibt die tats�chlichen Knoten wieder und i gibt nur an, bei dem wie vielten Knoten wir sind aber nicht an welcher Position dieser ist.
//				System.out.print(j+ " " + i + "\n");
				}
			}
		}
		Button[iGr�n][0].setBackground(Color.GREEN);
		Button[iRot][jRot].setBackground(Color.RED);
		i++;
	}
	}
		});timer.start();

		});
	
}
		
		
		
		
		
		
		
		
		
		
		
		
		public static int Dijkstra(int[][] adjazenzmatrix,int nGr�n,int nRot){
			
			
			visited = new boolean[adjazenzmatrix.length];
			for(int i=0;i<visited.length;i++) {
				visited[i]=false;
			}
			int distance[] = new int[adjazenzmatrix.length];	
			for(int i=0;i<adjazenzmatrix.length;i++) {
				distance[i]=Integer.MAX_VALUE;
			}
			distance[nGr�n]=0;
			//Iterationen der Knoten.
			//Erster Integer gibt die Distanz an(newDist). Die ArrayList gibt alle Knoten an, die in einer Iteration 
	
			for(int i=0;i<adjazenzmatrix.length-1;i++) {

				int minVertex = Matrix.findMinVertex(distance,visited);
				visited[minVertex]=true;
				//Iterationen, um die kleinste Distanz festzulegen, in Tabellenform w�re dies horizontal
				for(int j = 0;j<adjazenzmatrix.length;j++) {
					if(adjazenzmatrix[minVertex][j]!=0 && !visited[j] && distance[minVertex] !=Integer.MAX_VALUE) {
						int newDist = distance[minVertex] + adjazenzmatrix[minVertex][j];
						if(newDist<distance[j]) {
							distance[j]= newDist;
						}
					}
				}
			}
			DistanzRotGr�n = distance[nRot];
			return DistanzRotGr�n;
		}

		
		private void DepthFirstSearch(int[][] adjazenzmatrix,int nGr�n) {
			
			stack = new Stack<Integer>();
			visited = new boolean[adjazenzmatrix.length];
			for(int i=0;i<visited.length;i++) {
				visited[i]=false;
			}
		
			visited[nGr�n]=true;
			stack.add(nGr�n);
			SwingUtilities.invokeLater(() -> {

				
				Timer timer = new Timer(DFSSpeed.getValue(), new ActionListener() {

					int node = stack.pop();
					@Override
					public void actionPerformed(ActionEvent e) {
					
						for(int i=0;i<visited.length;i++) {
							if(visited[i]) {
							Button[Matrix.UmrechungIndex1(i)][i%58].setBackground(Color.PINK);
							}	
						}Button[iGr�n][jGr�n].setBackground(Color.GREEN);
						
						for(int i=0; i<visited.length;i++) {
							
							if(adjazenzmatrix[node][i]!=0 && !visited[i]) {
								visited[i]=true;
								stack.add(i);
							}
						}
						if(stack.isEmpty()) {
							((Timer)e.getSource()).stop();
						}else {
							node=stack.pop();
						}
						
					}
					
				});
				timer.start();
				
			});
				
		}
		

		public static void BreadthFirstSearch(int[][] adjazenzmatrix,int nGr�n){
			Queue<Integer> queue = new LinkedList<Integer>();
			boolean[] abgeschlossen = new boolean[adjazenzmatrix.length];
			visited = new boolean[adjazenzmatrix.length];
			for(int i=0;i<visited.length;i++) {
				visited[i]=false;
				abgeschlossen[i]=false;
			}
			visited[nGr�n]=true;
			queue.add(nGr�n);
			
			 bfsspeed = BFSSpeed.getValue();
			if(NichtM�glich)bfsspeed=DijkstraSpeed.getValue();
			SwingUtilities.invokeLater(() -> {

				
				
						
				Timer timer = new Timer(bfsspeed, new ActionListener() {

					
					int node = queue.poll();
					@Override
					public void actionPerformed(ActionEvent arg0) {
		
						for(int i=0;i<visited.length;i++) {
							if(visited[i]) {
							Button[Matrix.UmrechungIndex1(i)][i%58].setBackground(Color.PINK);
							}	
						}Button[iGr�n][jGr�n].setBackground(Color.GREEN);
						
						
						for(int i=0; i<visited.length;i++) {
							if(adjazenzmatrix[node][i]!=0 && !visited[i]) {
								visited[i]=true;
								queue.add(i);
							}
						}
						if(queue.isEmpty()) {
							((Timer)arg0.getSource()).stop();
						}else {
							
						node=queue.poll();
						}
					}
			});timer.start();
				
			});	
			
		}
		
		
		
		
		
		public static void main(String[] args) {			
			Gui a = new Gui();
			
//			int[][] Matrix = {{0,1,1,0},{0,0,1,0},{0,1,0,1},{0,1,0,0}};
//			int[][] Matrix2 = {{0,7,11,0,0,14},{7,0,10,15,0,0},{11,10,0,11,0,2},{0,15,11,0,6,0},{0,0,0,6,0,9},{14,0,2,0,9,0}};
				
//				System.out.print(DykstraAlgorithmus.Dijkstra(Matrix2, 3, 5));
//				System.out.print(java.util.Arrays.toString(DykstraAlgorithmus.Vorg�nger));
//					System.out.print(DykstraAlgorithmus.Rute(DykstraAlgorithmus.Vorg�nger, 5));
					
					
					
		}
		
}