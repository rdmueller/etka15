# Spock und Geb

## Übersichtlich und nachvollziehbar Testen für alle!

Code zur Präsentation "Übersichtlich und nachvollziehbar Testen für alle!"

Dieses Repository soll nochmal die in der Präsentation dargestellten Methoden und Vorgehensweisen anhand eines Gradle-Builds und einer trivialen Website (http://rdmueller.github.io/etka15/) verdeutlichen.

Unter https://github.com/rdmueller/etka15/tree/master/src/test/groovy findet man alle relevanten Dateien:

- EntwicklertagSpec.xml : die fachliche Test-Spezifikation
- xml2spec.groovy : das Skript zur Transformation der fachlichen Test-Spezifikation in ein Spock-Testgerüst
- EntwicklertagSpec.groovy : das generierte und von einem Entwickler ausgefüllte Testgerüst
- pages/. : die generierten und von einem Entwickler verfeinerten Seitendefinitionen

Das Gradle-Projekt erzeugt den finalen HTML-Report in [build/report/docs/html5/summary.html](http://rdmueller.github.io/etka15/report/docs/html5/summary.html)

To run the project, type

`gradle test`

on the commandline. To use UTF-8 encoded characters in your test descriptions, make sure that the file encoding on your system is set to UTF-8 by setting the environment varialbe `GRADLE_OPTS` to `"-Dfile.encoding=UTF-8"`. If you use the powershell on windows, you can do this by executing

`$env:GRADLE_OPTS="-Dfile.encoding=UTF-8"`
