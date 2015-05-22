import geb.spock.GebReportingSpec
import pages.StartPage
import pages.AbstractPage
import pages.SpeakerPage
import spock.lang.Ignore

class EntwicklertagSpec extends GebReportingSpec {

    def 'Aufruf der Vortragsbeschreibung'() {
        given: "Besucher befindet sich auf der Startseite"
            to StartPage
            report 'Startseite'
        when: "der Benutzer auf 'Vortrag' klickt,"
            talkLink.click()
        then: "sieht er die Zusammenfassung des Vortrags"
            report 'Abstract des Vortrags'
            at AbstractPage
            report 'Abstract des Vortrags'
            headline ==~ /Vortrag/

    }

    def 'Ansicht der Referenten'() {
        given: "Besucher befindet sich auf der Startseite"
            to StartPage

        when: "der Benutzer auf 'Speaker' klickt,"
            speakerLink.click()

        then: "sieht er die Referenten des Vortrags"
            report 'Liste Referenten'
            at SpeakerPage
            speakerName(1) == 'Ralf D. Müller'
            mainContent.find('h3').size() == 2

        when: "der Benutzer auf 'Home' klickt,"
            homeLink.click()

        then: "befindet er sich wieder auf der Startseite"
            report 'Startseite'
            at StartPage
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

    @Ignore("Not yet implemented")
    def 'irgendwas anderes'() {
        expect: "sdf"
            5 == 5
    }
}
