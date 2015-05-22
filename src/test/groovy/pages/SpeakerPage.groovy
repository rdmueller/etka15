package pages

import geb.Page

class SpeakerPage extends Page {

    static url = "http://rdmueller.github.io/etka15/speaker.html"

    static at = { title ==~ /Spock und Geb.*/ }

    static content = {
        homeLink(to: StartPage) { $('a', text: 'Home') }
        mainContent { $('#main_content') }
        speaker {number -> mainContent.find('h3', number)}
        speakerName {number -> speaker(number).text()}
        details(required: false) {number -> speaker(number).next(".det")}
    }

}
