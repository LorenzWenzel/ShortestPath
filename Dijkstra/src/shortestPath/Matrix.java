package shortestPath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

import javax.swing.JButton;

public class Matrix {
JButton[][] Buttons;
int[][] adjazenzmatrix;
public static int iGr�n,jGr�n,nGr�n;
public static int iRot,jRot,nRot;
int AnzahlKnoten;
static int DistanzRotGr�n;
 static int[] Vorg�nger;
 public static boolean[] visited;
//35*58= 2030 Knoten.
public Matrix(JButton[][] Buttons) {

	
	this.Buttons=Buttons;
	int Grenze=-1;
	this.AnzahlKnoten=Buttons.length*Buttons[0].length;
	this.adjazenzmatrix= new int[AnzahlKnoten][AnzahlKnoten];
	for(int i=0;i<AnzahlKnoten;i++) {
		if(i%Buttons[0].length==0) {
			Grenze++;
		}
		for(int j=0;j<AnzahlKnoten;j++) {
			
		adjazenzmatrix[i][j]=0;
			
		}
		try {
		if(WeiseFarbe(Buttons[Grenze][i%Buttons[0].length+1])) {
			adjazenzmatrix[i][Umrechnung(Grenze,i%Buttons[0].length+1)]=1;
		}
		}catch(IndexOutOfBoundsException e) {
			
		}
		try {
		if(WeiseFarbe(Buttons[Grenze][i%Buttons[0].length-1])) {
			adjazenzmatrix[i][Umrechnung(Grenze,i%Buttons[0].length-1)]=1;
		}
		}catch(IndexOutOfBoundsException e) {
			
		}
		try {
		if(WeiseFarbe(Buttons[Grenze+1][i%Buttons[0].length])) {
			adjazenzmatrix[i][Umrechnung(Grenze+1,i%Buttons[0].length)]=1;
		}
		}catch(IndexOutOfBoundsException e) {
			
		}
		
		try {
		if(WeiseFarbe(Buttons[Grenze-1][i%Buttons[0].length])) {
			adjazenzmatrix[i][Umrechnung(Grenze-1,i%Buttons[0].length)]=1;
		}
		}catch(IndexOutOfBoundsException e) {
			
		}}
		
	}
		

//W�hlt den Knoten mit der geringsten Distanz aus. 
public static int findMinVertex(int[] distance,boolean visited[]) {
	int minVertex = -1;
	for(int i=0;i<distance.length;i++) {
		if(!visited[i] && (minVertex == -1 || distance[i] <distance[minVertex])) {
			minVertex=i;
		}
	}
	return minVertex;
	
}



private boolean WeiseFarbe(JButton a) {
	if(a.getBackground().equals(Color.WHITE))return true;
	return false;
}

public static int Umrechnung(int H�he,int Breite) {
	int n=H�he*58;
	return n+Breite;
}

public static int UmrechungIndex1(int n) {
	
	return (n-(n%58))/58;
}


public static ArrayList<Integer> Rute(int[] Vorg�nger, int nRot) {
	ArrayList<Integer> Rute = new ArrayList<>();
	
	
	int j=nRot;
	while(true) {
		Rute.add(j);		
		try {
		j=Vorg�nger[j];		
		}catch(Exception e) {
			break;
	}
	}
		
	Rute.remove(Rute.size()-1);
	return Rute;
}


public static ArrayList<Integer> TreeIntoArrayList(TreeMap<Integer,ArrayList<Integer>> Liste){
	ArrayList<Integer> Liste1 = new ArrayList<>();
	for(ArrayList<Integer> n:Liste.values()) {
		for(Integer m: n) {
			Liste1.add(m);
		}
	}
	
	
	return Liste1;
}




}
