#Entscheidung?! 
EXPRESS-Notation mit Unterstrich vs Java Notation mit CamelCase
evtl. leicht diskutieren
- leichteres finden von Beschreibungen der Werte in (Liste der Norm)
--> beibehalten der EXPRESS-Notation

in Enums Werte-namen beibehalten vs alle Buchstaben groß
--> alle Buchstaben groß

Dieses Dokument wird im Laufe der Arbeit wahrscheinlich noch iterativ geändert.
Es ist daher eine gute Idee es bei Fertigstellung in Git mit einem Tag "initial-java-like-model" zu versehen.
Anmerken in Bachelorarbeit, dass diese Dokumente Zwischen-Schritte sind und hier nicht
sonderlich auf Qualität geachtet wurde.

Das Dokument (5) ist ab tagging jenes Dokumentes nicht mehr zu ändern.
Dieses Dokument diehnt ab dann als Arbeitsgrundlage für sämtliche weiteren Änderungen.
Schlechte Idee, wegen Fehlerkorrektur und evtl. entstehenden Verbesserungen.

#Mapping der EXPRESS-Ausdrücke

Nur nicht triviale Mappings werden angegeben(Bsp. "ENUMERATION OF" --> ... ist trivial).

http://www.omg.org/spec/EXPRESS/1.1/PDF/

###INVERSE
- 2x verwendet
- ändert die Syntax des folgenden SET Kommandos ergänzt um " FOR name_des_attributes_in_referenzierter_klasse"
- es wird also eine Bidirektionale Beziehung eingerichtet
- keine Änderung in diesem Model nötig, aber Kennzeichnung mit Kommentar zum evtl. implementieren in Java

###OPTIONAL
optionale Attribute werden zur vereinfachung grundsätzlich weggelassen

###UNIQUE
- 2x verwendet
- beide male bei gelöschten Entitäten

###SET
- wird als Datentyp "Set" interpretiert
- Kardinalitätsangaben werden ignoriert
- Typ der Elemente des SET wird in Java-Syntax als Generics angegeben

###Allgemeines
- vor Enums steht das Schlüsselwort "enum"
- vor Klassen steht das Schlüsselwort "class"
- die zur einer Klasse/Enum gehörenden Werte werden mit einem Tab eingerückt
- Klassen-Attribute haben einen Datentyp, der wie in Java vor dem Namen steht, dazwischen stehen beliebig viele Leerzeichen Leerzeichen
- evtl. nötige Kommentare werden mit doppeltem Slash Zeichen hinter die entsprechende Stelle geschrieben
- Leerzeichen in Enum-Werten werden durch Unterstrich ersetzt
- die Namen der Enums/Klassen beginnen mit einem Großbuchstaben
- Werte in Enmus werden mit Komma separiert
- Attribute in Klassen werden mit Semikolon separiert
- Ziel ist es Java so ähnlich zu kommen, dass copy-pasten in Java-Dateien ohne große Modifikationen möglich ist,
aber allgemein genug zu bleiben um abstrakt darüber diskutieren zu können(todo evtl. besser begründen)
- Enums/Klassen bekommen wie in Java geschweifte Klammern
- Zugriffsmodifizierer gehören nicht in dieses Modell

Anmerkungen:
- da das Modell initial sehr klein ausgefallen ist, stellt sich die Frage nach dem Sinn,
überhaupt die Norm verwendet zu haben
- einfache Norm-konforme Reduzier -und Erweiterbarkeit
- gute vorhandene Dokumentation(die Norm)
- Nutzung der Beispiele in der Norm für Beispieldaten im DMS möglich

-------------------------------------------------------------------------------------------
-------------------------------------Java-ähnliches-Model----------------------------------
-------------------------------------------------------------------------------------------

enum document_relationship_type{
    TRANSLATION,
    REFERENCING,
    VARIANT,
    LANGUAGE_VARIANT,
    COPY,
    DECOMPOSITION,
    SUBSTITUTION,
    DERIVATION,
    SUPERSEDING,
    AFFECTING,
    ADDITION,
    PEER,
    SEQUENCE,
    SUPPLIED
}    
//delete this 
enum role_type{
    AUTHOR,
    CREATOR,
    CUSTODIAN,
    CUSTOMER,
    EDITOR,
    INSPECTOR,
    LOCAL_REPRESENTATIVE,
    MANUFACTURER,
    OPERATOR,
    OWNER,
    SUPPLIER,
    VENDOR,
    COPYRIGHT_HOLDER,
    PATENT_HOLDER,
    LICENCE_HOLDER
}    
enum description_enumeration_type{
    TITLE,
    DESCRIPTION,
    KEYWORD,
    SUMMARY,
    COMMENT,
    PROPOSAL,
    RESOLUTION,
    EXPERIENCE
}    
//document and version are combined through versioning-architecture
class Document{
    String document_id;
    Set<Document_class> classified_as;
    Set<Document_version> has_versions; //Bidirektional//to delete
}
class Document_version{
    String document_version_id; //to delete
    Document version_of;    //anderes Ende der obigen Bidirektionalen Beziehung//to delete
    Date created_at;
    String path_to_file;
    Set<Description> descriptions
    Set<String> language;
}
class Document_relationship{
    Document_relationship_type relation_type;
    Document relates_document;
    Document relating_document;   
}
class Document_class{
    String id;
    String uses_classification_system;
    Set<Description> description;
}
//to delete, because Document and version combined
class Document_version_relationship{
    Document_version relates;
    Document_version relating;
    Document_relationship_type relation_type;
}
class Logbook_entry{
    String description;
    Document_version is_related_to;
    Date date_and_time;
}
class Description{
    String textual_description;
    Description_enumeration_type type_of;
    String language_code;
}
class Document_version_external_object_reference_relationship{
    Document_version relates;
    External_object_reference relating;
}
class External_object_reference{
    String id;
    String description;
    String type;
}




###Anmerkungen:






















    