##Gründe für Änderungen:
- Reduzierung der Komplexität, des Umfangs
- Weglassen von für Untersuchung irrelevanten Teilen

###Anmerkungen
Synchronisation von Diagramm und Model bricht irgendetwas.
Unterlasse es und nehme Änderungen in beiden manuell vor!

Papyrus bricht ständig: keine Antwort, keine Ahnung, keine Garantien das irgendetwas funktioniert.

Speichern in Eclipse nicht vergessen, sonst alles nicht Commitbar.

Diese Änderungen werden successiv vorgenommen.
D.h. Änderungen von Änderungen von ... sind möglich.

Die Commits in Github werden nach folgendem Schema benannt:
uml-changed(*Referenz auf Änderung*)-->*was wurde getan*

#Auflistung der Änderungen mit Begründung

###Änderung 1
**Weglassen der Klassen: ElectronicAddress & PostalAddress & PhysicalAddress**
**Löschen der entsprechenden Beziehungen**
**erstetzen von entsprechenden Referenzen in Address durch Strings**
>- dieser Aspekt ist relativ isoliert und lässt sich daher leicht weg oder hinzu nehmen
>- wenig relevant für Untersuchung

###Änderung 2
**Löschen der Klasse Location**
**Löschen der Referenz in Carrier**
>für simple DMS irrelevante Klasse
