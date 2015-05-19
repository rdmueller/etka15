import geb.spock.GebReportingSpec
import pages.StartPage
import pages.AbstractPage
import pages.SpeakerPage

class EntwicklertagSpec extends GebReportingSpec {

    def 'Aufruf der Vortragsbeschreibung'() {
        given:  "Interessent befindet sich auf der Startseite"
            to StartPage
            report 'Startseite'
        when:   "der Benutzer auf 'Vortrag' klickt,"
            vortragLink.click()
            report 'Abstract des Vortrags'
            at AbstractPage
        then:   "sieht er die Zusammenfassung des Vortrags"
            headline ==~ /Vortrag/
    }

    def 'Ansicht der Referenten'() {
        given:  "Interessent befindet sich auf der Startseite"
            to StartPage
        when:   "der Benutzer auf 'Speaker' klickt,"	//test
            speakerLink.click()
            report 'Liste Referenten'
            at SpeakerPage
        then:   "sieht er die Beschreibung der 2 Referenten"	//auf 2 Beschreibungen prüfen!
            headlines.size() == 2
            headlines[1].text() == "Ralf Müller"
    }

    def 'Zurueck Link'() {
        given:  "Interessent befindet sich auf der Vortrags-Seite"
            to AbstractPage
            report 'Zusammenfassung des Vortrags'
        when:    "der Benutzer auf 'Home' klickt,"
            homeLink.click()
        then:   "befindet er sich wieder auf der Startseite"
            report 'Startseite'
            at StartPage
    }
}
