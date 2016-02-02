# neo_ba_dms

- everything is configured for localhost use
- needs a neo4j instance running
- neo4j is password-protected with the higher versions
- the username/password need to be known to this app (go to Application.java and change "http://localhost:7474","neo4j","neo4")
- the second parameter is the username and the third the password
- installs javascript-dependencies with bower (command: "bower install")
- start application with: "mvn spring-boot:run"
- view application on "http://localhost:8080"

####has still errors:
- clicking very fast on the buttons can get the app into an error-state which requires restart
