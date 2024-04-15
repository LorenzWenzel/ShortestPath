# ShortestPath
Visualisierung des kürzesten Weges in einem Graphen durch Animation des DijkstraAlgorithmus. 
Visualisierung des Breadth-First-Search-Algorithmus und des Depth-First-Search-Algorithmus. 

## Depth-First-Search, Breadth-Frist-Search, Dijkstra Demonstration:




https://github.com/LorenzWenzel/ShortestPath/assets/73563833/56935272-2596-4b81-89aa-b18699a51fe7






## shortest path: 
![image](https://github.com/LorenzWenzel/ShortestPath/assets/73563833/0777de05-ab89-4b08-ba38-2d21b749193f)

## Dijkstra:
![image](https://github.com/LorenzWenzel/ShortestPath/assets/73563833/3cac518f-2692-4428-a62e-b4dcd7e5b9b3)

## DFS-Algo: 
![image](https://github.com/LorenzWenzel/ShortestPath/assets/73563833/72483a84-1ee1-4f86-a28e-6313da5bfbe5)

## BFS-Algo
![image](https://github.com/LorenzWenzel/ShortestPath/assets/73563833/91455900-e5b0-46d1-8461-f7aabd99cf8c)

Die Animation wurde in der Java.Swing Umgebung umgesetzt, in einem 35x58 großen Array. 
Der Startpunkt wird per JButton zufällig bei [x][0] mit x=(0,1,2,.....,37) gesetzt. 
Der Zielpunkt zufällig bei [x][y] mit x=(0,1,2,.....,37) und y=(54,55,56,57). 
Der Button RandomAuffüllen generiert zufällig schwarze Punkte bei [x][y] mit x=(0,1,2,.....,37) und y=(1,2,3......,53), die als Barriere dienen für den DijkstraAlgorithmus, der die Distanz zwischen dem Startpunkt und Zielpunkt errechnet und in der Gui wiedergibt. Die besuchten Knoten werden nach jedem Schleifendurchlauf farbig in der GUI aktualisiert.  Der Weg der beiden Punkte wird währenddessen in eine TreeMap gespeichert und zum Ende der Schleife in eine ArrayList gewandelt. Anschließend wird der Weg in Blau visualisiert. 

In derselben Weise wird nun der DepthFirstSearch-Algorithmus in Form des DFS-Button und der BreadthFirstSearch-Algorithmus durch den BFS-Button visualisiert.

Weitere Ziele des Projekts sind die Implementierung anderer Algorithmen, wie eine möglichst geschickte Visualisierung der starken Zusammenhangskomponenten. 
In naher Zukunft wird eine ausführliche Dokumentation und das Zusammenfassen und Vereinfachen vieler Methoden im Quellcode.

