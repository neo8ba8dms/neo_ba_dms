Ziel ist ein Referenzmodell zur Implementierung eines DMS zu finden und partiell umzusetzen.

Anfangs Einigung auf UML-Modell:
- erste heraus gearbeitet welches davon aktuellste (siehe Notizen 1)
- versucht dieses zu modifizieren
- massive Inkonsistenzen gefunden

#(todo)hier sollten noch einige/alle Inkonsistenzen heraus gearbeitet werden

- UML ist wahrscheinlich aus DTD abgeleitet, ohne sich geadnken über Mapping-Strategie zu machen
das ist wahrscheinlich ein Fehler gewesen
- die DTD ist zum Austausch von Metadaten (zur Validierung von XML wie in Bsp. der 82045)
#(todo) vorigen Stichpunkt belegen
- DTD ist evtl. als Referenzmodell geeignet, aber dann bessere Mapping-Strategie nötig

Beziehung Metadatentabelle <--> DTD:
- jeder Eintrag in Metadatentabelle hat einen Pfad zu einem Element in der DTD
- folglich wahrscheinlich äquivalent

#(todo) wenn äquivalent in BA nutzen, dann definieren
äquivalent im Sinne von beschreiben die gleichen Daten

"ANMERKUNG Das Modell kann ebenfalls angewendet werden als ein konzeptionelles Modell für die Erstellung eines
 Dokumentenmanagementsystems." (S. 39)
 
evtl. ein hübsches Bild mit einer Untersuchung zwischen allen Datencontainern der 82045 (DTD, Tabelle, Liste, ...)


#Ergebniss: das EXPRESS-Model wird als Referenzmodel genommen