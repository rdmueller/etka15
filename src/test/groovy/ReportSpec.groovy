import geb.spock.GebReportingSpec
import spock.lang.Ignore

@Ignore
class ReportSpec extends GebReportingSpec {

    def "check homepage for talk"() {
        when:
            go "http://rdmueller.github.io/etka15"
        then:
            title =~ "Spock und Geb"
            $('li').size() == 4
            $("#logo").@alt  == "Github.com"
        when:
            $("#report").click()
        then:
            title == "Spock Test Results"
            waitFor {
                $("div#result").present
            }
    }
}
