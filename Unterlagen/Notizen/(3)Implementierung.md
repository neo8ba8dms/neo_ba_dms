nutze manuelle Controller-Implementierungen
Spring-data-rest macht zu viele Probleme

Schwerpunkt liegt auf verständlichkeit, nicht toller Architektur o.ä.


###Liste von Reinfällen
- die GraphId muss Long sein nicht long
- in der Applikation mmüssen Username und Password bereit gestellt werden, sonst Fehler (27.1. off Doc)
default Password ist neo4j/neo4j, muss aber geändert werden auf meinem Computer neo4j/neo4
in data/dbms/auth ist gehashtes pwd gespeichert, durch löschen resetten