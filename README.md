# Spock und Geb

## Übersichtlich und nachvollziehbar Testen für alle!

Code zur Präsentation auf dem karlsruher Entwicklertag 2015

Dieses Repository soll nochmal die in der Präsentation dargestellten Methoden und Vorgehensweisen anhand eines Gradle-Builds und einer trivialen Website (http://rdmueller.github.io/etka15/) verdeutlichen.

Unter https://github.com/rdmueller/etka15/tree/master/src/test/groovy findet man alle relevanten Dateien:

- EntwicklertagSpec.xml : die fachliche Test-Spezifikation
- xml2spec.groovy : das Skript zur Transformation der fachlichen Test-Spezifikation in ein Spock-Testgerüst
- EntwicklertagSpec.groovy : das generierte und von einem Entwickler ausgefüllte Testgerüst
- pages/. : die generierten und von einem Entwickler verfeinerten Seitendefinitionen

Das Gradle-Projekt erzeugt den finalen HTML-Report in [build/report/docs/html5/summary.html](http://rdmueller.github.io/etka15/report/docs/html5/summary.html)
