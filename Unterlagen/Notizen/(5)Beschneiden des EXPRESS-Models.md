wichtige Frage: Modell frei/intuitiv interpretieren vs genau recherchieren und möglichst exakt mappen
- EXPRESS lernen ist zu aufwändig für nur diesen Anwendungsfall
- EXPRESS scheint kaum noch verwendet zu werden
- Paper von OMG evtl. angeben

-->Folgerung: intuitiv interpretieren

S. 76 bis 82

Anmerkung: in späterem Dokument muss eine Mapping-Strategie angegeben werden, da ja intuitive Interpretation

#(todo) allgemeine Aussagen dazu, warum eigentlich das Modell beschnitten wird
- Reduzierung des Aufwandes, da sonst zuviel für eine Bachelorarbeit
- manches ist wenig bis gar nicht relevant

#Beschneidung des Modells
- von oben nach unten
- Angaben:  Name, (bleibt oder gelöscht oder geändert), Begründung für jeweilige Entscheidung
- bei geändert angeben, was geändert wurde

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
- ein Dokument braucht nur ein String Attribut, keine eigene Klasse


ENTITY document_id_domain
- gelöscht
- zur Vereinfachung werden nur einfache ID's vergeben


ENTITY document_version
- geändert
- OPTIONAl-Attr. fallen weg
- attr. external document gelöscht
- sollte ein Attribut document_version_id haben (oder?), daher einfügen als String
- Versionierung soll einer der unterstützten Anwendungsfälle sein


ENTITY document_version_id
- gelöscht
- unklar, wie das im EXPRESS-Model eigentlich funktionieren soll
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


ENTITY document_class


ENTITY classification_system;


ENTITY document_version_relationship;


ENTITY logbook_entry;
- party raus


ENTITY person
- party raus


ENTITY party_role;
- gelöscht
- keine party


ENTITY contract;


ENTITY organization
- party raus


ENTITY project;


ENTITY description;


ENTITY document_version_external_object_reference_relationship;


ENTITY external_object_reference;


ENTITY carrier;


ENTITY location;


ENTITY electronic_address


ENTITY postal_address


ENTITY address


ENTITY effectivity
- party raus


ENTITY dated_effectivity


ENTITY time_interval_based_effectivity


ENTITY party_relationship;
- gelöscht
- keine party


ENTITY stored_document_representation;


ENTITY digital_file


ENTITY compound_document_file


ENTITY document_representation_carrier_relationship;


ENTITY medium;


ENTITY medium_caracteristics;


ENTITY date_and_time;


ENTITY document_revision;


ENTITY recipient;



ENTITY party_in_subscription_list;
- gelöscht
- alles OPTIONAL


ENTITY subscription_list;
- keine party


ENTITY document_version_distibution_relationship;


ENTITY recipient_action_on_document_version;


ENTITY distribution


ENTITY RASAD_status;
- attr. external document gelöscht
- keine party



ENTITY RASAD_status_relationship;


ENTITY physical_address


ENTITY party_to_address_relationship;
- keine party


