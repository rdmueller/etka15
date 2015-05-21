package pages

import geb.Page

class StartPage extends Page {

    static url = "http://rdmueller.github.io/etka15"

    static at = { title ==~ /Spock und Geb.*/ }

    static content = {
        talkLink { $('a', text: 'Vortrag') }
        speakerLink { $('a', text: 'Speaker') }
        downloadLink { $('a', text: 'Downloads') }
    }

}
