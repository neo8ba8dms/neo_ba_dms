#Anmerkungen:
- Allgemeine Beschreibung
- jeweils die im Java-ähnlichen Modell verwendeten Klassen und Attribute mappen (abkürzung: "Attr")
- die Anwendungsfälle bauen teilweise aufeinander auf, daher von ober nach unten lesen


#(1) Dokumente anlegen
Es soll eine Oberfläche mit Formular geben, wo die nachfolgend gelisteten Werte angelegt werden können.
Diese sollen persistent gespeichert werden in Neo4J.
Es soll eine erste Version dieses Dokumentes angelegt werden.

Attr.: 
    Document.document_id                    -->Textfeld
    Document.classified_as                  -->soll fürs erste ein Eingabefeld für einen String sein
    Document.has_versions                   -->soll automatisch angelegt werden
    
    Document_version.document_version_id    -->soll automatisch vergeben werden
    Document_version.version_of             -->???
    Document_version.created_at             -->automatisch generiert
    Document_version.path_to_file           -->fürs erste Eingabefeld für String
    Document_version.descriptions           -->
    
    
#(2) Reale Dateien hochladen


#(3) Listenansicht für alle Dokumente
Hier sollen Dokumente gelöscht werden können.


#(4) Update Operation auf Dokumente


#(5) Dateien sollen in der Dokumentenansicht herunter geladen werden können


#(6) Es sollen alle Dokumentenversionen eines Dokumentes aufgelistet werden können



#(7) Es soll ein Klassifikationskonzept implementiert werden


#(8) Es soll ein Ordnerkonzept implementiert werden


#(9) CRUD Operationen auf Realweltobjekte


#(10) Dokumentenbeziehungen sollen umgesetzt werden 