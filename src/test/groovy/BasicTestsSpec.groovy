import geb.spock.GebReportingSpec
import spock.lang.Ignore
import pages.StartPage
import pages.AbstractPage
import pages.SpeakerPage

class BasicTestsSpec extends GebReportingSpec {

    def 'Access the session description'() {
        given:  "User is on start page"
            to StartPage
            report 'start page'
            //TODO: implement test step
        when:   "the user clicks on 'Vortrag' (session)"
            talkLink.click()
            report 'abstract of session'
            at AbstractPage
        then:   "the first conference day is shown"
            headline ==~ /Vortrag/
    }

    @Ignore("not implemented")
    def 'Speaker descriptions'() {
        given:  "User is on start page"
            to StartPage
            //TODO: implement test step
        when:   "the user clicks on 'Speaker'"	//test
            report 'list of speakers'
            at SpeakerPage
            //TODO: implement test step
        then:   "the description of two speakers is shown"	//test that _2_ descriptions are shown!
            //TODO: implement test step
    }

    @Ignore("not implemented")
    def 'Back-Link'() {
        given:  "User is on the page of a session"
            to AbstractPage
            //TODO: implement test step
        and:    "user clicks on 'Home'"
            report 'start page'
            at StartPage
            //TODO: implement test step
        expect:   "the start page is shown again"
            //TODO: implement test step
    }
}
