# ShortestPath
Visualisierung des kürzesten Weges in einem Graphen durch Animation des DijkstraAlgorithmus. 

Die Animation wurde in der Java.Swing Umgebung umgesetzt, in einem 35x58 großen Array. 
Der Startpunkt wird per JButton zufällig bei [x][0] mit x=(0,1,2,.....,37) gesetzt. 
Der Zielpunkt zufällig bei [x][y] mit x=(0,1,2,.....,37) und y=(54,55,56,57). 
Der Button RandomAuffüllen generiert zufällig schwarze Punkte bei [x][y] mit x=(0,1,2,.....,37) und y=(1,2,3......,53), die als Barriere dienen für den DijkstraAlgorithmus, der die Distanz zwischen dem Startpunkt und Zielpunkt errechnet und in der Gui wiedergibt. Die besuchten Knoten werden nach jedem Schleifendurchlauf farbig in der GUI aktualisiert.  Der Weg der beiden Punkte wird währenddessen in eine TreeMap gespeichert und zum Ende der Schleife in eine ArrayList gewandelt. Anschließend wird der Weg in Blau visualisiert. 

In derselben Weise wird nun der DepthFirstSearch-Algorithmus in Form des DFS-Button und der BreadthFirstSearch-Algorithmus durch den BFS-Button visualisiert.

Weitere Ziele des Projekts sind die Implementierung anderer Algorithmen, wie eine möglichst geschickte Visualisierung der starken Zusammenhangskomponenten. 
In naher Zukunft wird eine ausführliche Dokumentation und das Zusammenfassen und Vereinfachen vieler Methoden im Quellcode.
