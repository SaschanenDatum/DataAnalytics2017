#Idee:
 
Jede CrossRoad besitzt einen eigenen EsperService welcher sich um das CEP dieser Kreuzung k�mmert.
Traffic Control erstellt neue Cars gibt ihnen eine Route welche den Cars einen Start punkt geben. 
Car k�nnen per getNextPoint sich den n�chsten Punkt ihrer Route holen. Wenn dieser Punkt der Endpunkt ist melden
sie das sie Fertig sind.
Ist der N�chste Punkt eine CrossRoad meldet das Car sich an. Das erzeugt ein neues CarIncoming event. 
�ber das Ampel umschaltevent werden neue CarLeave Events erzeugt. Daf�r kann pro seite jedes Car geholt werden (eine Liste)
Jede CrossRoad hat eine zeit die jedes Auto braucht um diese zu �berqueren
Jede CrossRoad hat eine Ampel
Jede Ampel hat eine mindest Gr�nzeit. Diese kann ignoriert werden wenn keine Autos mehr da sind.
Es gibt eine Maximal Anzahl an Autos, welche auf einer Seite kommen d�rfen (derzeit 5).

####TODOs:
Stage 1 und 2 m�ssen auf jedenfall erledigt werden wenn wir am 24. etwas zeigen wollen Stage 3 ist um den Hedi zu 
beindrucken und die ? Stage ist eher f�r unser Ego/Gewissen
 #####Stage 1 Grundlegend Funkionierendes Beispiel
 	DONE - Autos m�ssen wissen aus welcher Richtung sie in eine neue CrossRoad fahren um ihr event anmelden zu k�nnen
 	? Fertige Autos m�ssen sicher und sauber aus den System entfernt werden. 
 	Johnnes Grundlegende Events m�ssen definiert und im EsperService implementiert werden
 	Johannes Windows und Consumtionmodes m�ssen implementiert werden
 	Sascha Zeitbedingungen (CrossRoad (Done) und Light) m�ssen implementiert werden
 	Sascha Traffic Control muss das Beispiel vorbereiten. 
 
 #####Stage 2 Fortgeschrittenes Beispel
 	- Jede CrossRoad ist ein eigener Thread
 	- Thread sicherheit ist sichergestellt
 	- Eigener Thread der Autos an Random Punkten erzeugt
 	
 #####Stage 3 unn�tig Fortgeschrittenes Beispiel 
 	- �ber eine GUI werden Kreuzungen und ihre zust�nde angezeigt
 	- Es gibt jetzt N Kreuzungen welche �ber die GUI erstellt werden k�nnen
 	- Autos w�hlen Routen nach min oder max Zeit die es brauchen w�rde. 
 	- CrossRaods k�nnen sind konfigurierbar (�berquerungszeit, max autos pro seite)
 	- Linksabbierger erhalten eigene Regeln
 	- Unf�lle werden ber�cksichtigt
 	
 #####Stage ? Streber Mode
 	- Java Convetions werden eingehalten
 	- Tests werden geschrieben
 	- Dokumentation
 	- Sauberer Code 