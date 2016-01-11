#Anmerkungen:
- Allgemeine Beschreibung
- jeweils die im Java-ähnlichen Modell verwendeten Klassen und Attribute mappen (abkürzung: "Attr")
- die Anwendungsfälle bauen teilweise aufeinander auf, daher von ober nach unten lesen
- dieses Dokument wird noch weit detailierter, wenn die Erfahrungen aus der eigentlichen Implementierungsarbeit
gewonnen worden sind


#(1) CRUD-Operationen auf Dokumente
Es soll eine Oberfläche mit Formular geben, wo die nachfolgend gelisteten Werte gelesen/angelegt/bearbeitet
werden können.
Diese sollen persistent gespeichert werden in Neo4J.

Attr.: 
    Document.document_id                    -->Textfeld
    Document.classified_as                  -->soll fürs erste ein Eingabefeld für einen String sein
    
    //folgende Attr. erst bei Versionierung
    Document.has_versions                   -->soll automatisch angelegt werden    
    Document_version.document_version_id    -->soll automatisch vergeben werden
    Document_version.version_of             -->???
    Document_version.created_at             -->automatisch generiert
    Document_version.descriptions           -->Anzeige aller Descriptions mit CRUD
    Document_version.language               -->String Eingabefeld
    
    
#(2) Dateien hochladen
Es soll ein Ordner in der Konfiguration der Application festgelegt werden.
In diesem Ordner sollen die Dateien nach einem festen Schema abgelegt werden.(todo)

Attr.:
    Document_version.path_to_file           -->fürs erste Eingabefeld für String


#(3) Dateien herunter laden
Dateien solllen gefunden werden und wenn sie verfügbar sind bereit gestellt werden.

#(4) Listenansicht für alle Dokumente
Es sind alle Dokumente anzuzeigen(die id) und für jedes Dokument die folgenden Operationen:
- das Dokument löschen
- zur Bearbeitungsansicht des Dokumentes wechseln

Des weiteren soll hier ein link "neues Dokument anlegen" sein, bei dessen anklicken ein neues Dokument angelegt wird
und zur Bearbeitungsansicht dieses Dokumentes gewechselt wird.

#(?) Es soll ein Klassifikationskonzept implementiert werden
Dokumente sollen 0 bis * viele Klassifikationsmerkmale haben.
Diese können von 1 bis * vielen Bäumen stammen.
Dieses Feature soll das Eingabefeld aus Anwendungsfall (1) "Document.classified_as" ersetzen.
Es sollen dazu alle Klassifikationsmerkmale aufgelistet werden.
Jedem Listenelement:
- ist ein Link zugeordnet, der zu der Klassifikationsansicht und dem Element führt
- ist ein löschen Button zugeordnet
- keine Updatemöglichkeit zugeordnet( mal sehen)

Es soll die Möglichkeit geben neue Klassifikationsmerkmale hinzuzufügen.
Dies soll über eine Auswahl in der Klassifikationsansicht geschehen.
Die Klassifikationsansicht soll mit einem geeigneten Java-Script-Framework(jsTree) visualisiert
und interactiv gemacht werden.

#(?) Es soll ein Ordnerkonzept implementiert werden
Analog zu Klassifikation(nur anderer Name)


#(?) CRUD Operationen auf Realweltobjekte


#(?) Dokumentenbeziehungen sollen umgesetzt werden
In der Dokumentenansicht soll eine Liste von Dokumentenbeziehungen angezeigt werden.
Einzelne Elemente sollen gelöscht werden können.
Neue Beziehungen sollen angelegt werden können.
Evtl. Diskussion über 82045 -- SDN --Neo4J Zusammenspiel.


#(?) Versionierung umsetzen
Das könte tricky sein. Erstmal den rest implementieren.


#(?) Einfaches Logging
Gewisse Events sollen gelogt werden.
Das ist evtl. unsinnig, nicht relevant.
(evtl. löschen von Logbook_entry)

