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
            at AbstractPage
            report 'Abstract des Vortrags'
        then:   "sieht er die Zusammenfassung des Vortrags"
            headline ==~ /Vortrag/
    }

    def 'Ansicht der Referenten'() {
        given:  "Interessent befindet sich auf der Startseite"
            at StartPage
            //TODO: implement test step
        when:   "der Benutzer auf 'Speaker' klickt,"	//test
            at SpeakerPage
            report 'Liste Referenten'
            //TODO: implement test step
        then:   "sieht er die Beschreibung der 2 Referenten"	//auf 2 Beschreibungen prüfen!
            //TODO: implement test step
    }

    def 'Zurück-Link'() {
        given:  "Interessent befindet sich auf der Vortrags-Seite"
            at AbstractPage
            //TODO: implement test step
        when:    "der Benutzer auf 'Home' klickt,"
            at StartPage
            report 'Startseite'
            //TODO: implement test step
        then:   "befindet er sich wieder auf der Startseite"
            //TODO: implement test step
    }
}
