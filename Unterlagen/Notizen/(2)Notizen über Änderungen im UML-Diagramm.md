###Gründe für Änderungen:
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


###Allgemeine geplante Änderungen
- keine Sicherheitsaspekte
- Reduzierung der Komplexität durch löschen von Klassen mit nur einem Attribut
###Was soll definitiv bleiben?
- Mehrsprachigkeit( bezieht sich nur auf Tags, die Dokumenten zugeordnet werden)
- Versionierung
- Dokumentenbeziehungen
- speichern von Dateien in Ordner auf Festplatte
- Dokumentenklassifikation
- Ordnerhierachie
- Bezug zu realen Objekten

###Diskussion: Wie soll Mehrsprachigkeit abgebildet werden?
- im Allgemeinen soll es ein simples DMS wie Seeddms werden,
daher wird Mehrsprachigkeit eher restriktiv behandelt
- das DMS ist ausgelegt für kleine Unternehmen mit geringen Anforderungen
- jedes Dokument kann mit belibig vielen Sprachtags markiert werden
- jede Summary kann mit genau einem Sprachtag markiert werden
- die restlichen Entitäten/Strings werden nicht mit Sprachtags versehen




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
>- für simple DMS irrelevante Klasse

###Änderung 3
**Löschen der Klassen: Title & KeyWords**  
**Ersetzen der Referenzen in Description mit String für Title**  
**Ersetzen der Referenzen in Description mit String-array für KeyWords**
>- reduziert die Komplexität
>- evtl. Fehler im Model: es gibt zwar die Referenz LanguageCode,
> aber keine Beziehung
>- wenn der Languagecode zu berücksichtigen wäre verkompliziert sich alles wieder
>- Konklusion: alles nur Strings, Mehrsprachigkeit wird hier nicht berücksichtigt

###Änderung 4
**hinzufügen einer Beziehung zwischen Summary und LanguageCode**
>- sieht nach einem Fehler aus der korrigiert wird
>- evtl. so geplant wegen Übersichtlichkeit?

###Änderung 5
**delete ACLGroup**
>- no security features




















