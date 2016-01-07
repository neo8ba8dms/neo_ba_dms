wichtige Frage: Modell frei/intuitiv interpretieren vs genau recherchieren und möglichst exakt mappen
- EXPRESS lernen ist zu aufwändig für nur diesen Anwendungsfall
- EXPRESS scheint kaum noch verwendet zu werden
- Paper von OMG evtl. angeben

-->Folgerung: intuitiv interpretieren

S. 76 bis 82 der 82045

Anmerkung: in späterem Dokument muss eine Mapping-Strategie angegeben werden, da ja intuitive Interpretation

#(todo) allgemeine Aussagen dazu, warum eigentlich das Modell beschnitten wird
- Reduzierung des Aufwandes, da sonst zuviel für eine Bachelorarbeit
- manches ist wenig bis gar nicht relevant

#Beschneidung des Modells
- von oben nach unten einmal durch, danach iteratives anpassen an Anwendungsfälle
- Angaben:  Name, (bleibt oder gelöscht oder geändert), Begründung für jeweilige Entscheidung
- bei geändert angeben, was geändert wurde
- evtl. gute Idee die "Klassen-Beschreibung" in Tabelle mit einzufügen(Leser muss nicht jedesmal nachsehen,
 gut um BA bei Bedarf zu strecken)

document_relationship_type
- bleibt
- Schwerpunkt der Bachelorarbeit


role_type
- geändert
- party raus
- simpel zu realisieren


description_enumeration_type
- bleibt
- simpel zu realisieren


ENTITY document;
- geändert
- document_id wird attribut von String
- attr. external document gelöscht
- Kernklasse ohne die ein DMS keinen Sinn machen würde


ENTITY document_id
- gelöscht
- zur Vereinfachung braucht ein Dokument nur ein String Attribut, keine eigene Klasse


ENTITY document_id_domain
- gelöscht
- zur Vereinfachung werden nur einfache ID's vergeben


ENTITY document_version
- geändert
- OPTIONAl-Attr. fallen weg
- attr. external document gelöscht
- einfügen von Attribut created_at (siehe digital_file)
- sollte ein Attribut document_version_id haben (oder?), daher einfügen als String
- Versionierung soll einer der unterstützten Anwendungsfälle sein


ENTITY document_version_id
- gelöscht
- unklar, wie das im EXPRESS-Model eigentlich funktionieren soll
- suche mit strg-f in 82045 ergibt, dass diese Entity nirgends im EXPRESS-Model verwendet wird
- wird als einfacher String in document_version behandelt 


ENTITY external_document
- gelöscht
- ein externes Dokument ist ein dokument das nicht vom DMS verwaltet wird, aber zu dem Beziehungen bestehen können
- verkompliziert das DMS nur

ENTITY party
- gelöscht
"The Party is an abstract supertype of Person and Organization"
- verkompliziert das DMS unnötig


ENTITY document_relationship
- geändert
- OPTIONAL fällt weg
- Schwerpunkt der Bachelorarbeit


ENTITY document_class
- geändert
"The Document_class is a collection of attributes allowing to assign multiple classifications to a document."
- OPTIONAL weg
- description weg
- Begr. description weg: ???
- Klassifikation ist einer der soll-Anwendungsfälle des DMS-Prototypen


ENTITY classification_system;
- geändert
- OPTIONAL weg
- Klassifikation ist einer der soll-Anwendungsfälle des DMS-Prototypen



ENTITY document_version_relationship;
- geändert
- OPTIONAL weg
- Dokumenten-Beziehungen sind Schwerpunkt, Beziehungen zwischen Versionen sind "nice-to-have"
und noch nicht entschieden, ob umzusetzen

ENTITY logbook_entry;
- geändert
- party raus
- date_and_time zu Java-Date
- einfaches Auditing ist "nice-to-have"-feature noch nicht entschieden ob umzusetzen


ENTITY person
- gelöscht
- fürs erste keine Nutzerverwaltung geplant


ENTITY party_role;
- gelöscht
- keine party


ENTITY contract;
- gelöscht
- verkompliziert das DMS unnötig


ENTITY organization
- gelöscht
- wenn keine Personenverwaltung, dann definitiv keine Organisationen


ENTITY project;
- gelöscht
- Projekte sind kein geplantes Feature dieses DMS


ENTITY description;
- bleibt
- einfach umzusetzen und nice-to-have


ENTITY document_version_external_object_reference_relationship;
- geändert
- OPTIONAL weg
- Dokumente(Versionen) sollen auf Realweltobjekte verweisen können, dazu wird eine Beziehung benötigt


ENTITY external_object_reference;
- bleibt
- Dokumente(Versionen) sollen auf Realweltobjekte verweisen können


ENTITY carrier;
- gelöscht
The entity carrier provides the identification of a particular carrier.???(Datenträger)
- das speichern der Dokumente wird in einen Ordner im Filesystem des Servers vorgenommen,
daher ist kein kompliziertes Carrier-Konzept nötig

ENTITY location;
- gelöscht
- location bezieht sich auf carrier(dieser ist gelöscht)


ENTITY electronic_address
- gelöscht
- siehe address

ENTITY postal_address
- gelöscht
- siehe address


ENTITY address
- gelöscht
- address wird nur von location(gelöscht) genutzt und hat sonst keine Beziehungen zu anderen klassen
- außerdem sind alle Attribute der Unterklassen OPTIONAL

ENTITY effectivity
- gelöscht
- verkompliziert DMS-Prototyp unnötig


ENTITY dated_effectivity
- gelöscht
- verkompliziert DMS-Prototyp unnötig


ENTITY time_interval_based_effectivity
- gelöscht
- verkompliziert DMS-Prototyp unnötig


ENTITY party_relationship;
- gelöscht
- keine party


ENTITY stored_document_representation;
- gelöscht
- die gespeicherten Dokumente im DMS-Prototyp werden ausschließlich als Text vorliegen


ENTITY digital_file
- geändert
- OPTIONAL weg
- stored_at weg
- sobald neue document_version folgt Speicherung, daher sollte dies bereits von document_version übernommen werden
- einfügen von Attribut: Speicherort String
- speichern von Dateien soll möglich sein, diese müssen representiert werden

ENTITY compound_document_file
- gelöscht
- ist nicht als Feature geplant


ENTITY document_representation_carrier_relationship;
- gelöscht
- benötigt carrier und stored_document_representation (beide gelöscht)
- address_of_document_representation_on_carrier bereits als digital_file.Speicherort umgesetzt


ENTITY medium;
- gelöscht
- Dokumente werden nur in Ordner als Datei gespeichert


ENTITY medium_caracteristics;
- gelöscht
- weil medium gelöscht


ENTITY date_and_time;
- gelöscht
- Funktionalität wird bereits besser von Java zur Verfügung gestellt


ENTITY document_revision;
- gelöscht
- Revision ist nicht als Feature geplant

ENTITY recipient;
- gelöscht
- macht keinen Sinn ohne Personen im DMS


ENTITY party_in_subscription_list;
- gelöscht
- alles OPTIONAL und keine subscription_list geplant


ENTITY subscription_list;
- gelöscht
- keine subscription_list geplant

ENTITY document_version_distibution_relationship;
- gelöscht
- keine Beschreibung vorhanden

ENTITY recipient_action_on_document_version;
- gelöscht
- macht keinen Sinn ohne Personen

ENTITY distribution
-gelöscht
- macht keinen Sinn ohne Personen


ENTITY RASAD_status;
- gelöscht
NOTE RASAD is an abbreviation standing for Release, Access, Security, Approval and Distribution management.
- macht keinen Sinn ohne role, party and security-features

ENTITY RASAD_status_relationship;
-gelöscht
- macht keinen Sinn ohne RASAD_status


ENTITY physical_address
- gelöscht
- siehe address



ENTITY party_to_address_relationship;
- gelöscht
- macht keinen Sinn ohne party


