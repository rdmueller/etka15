package pages
import geb.Page

class StartPage    extends Page {

    static    url = "http://rdmueller.github.io/etka15"
    static    at = { title ==~ /Spock und Geb.*/ }
    static    content = {
                            vortragLink { $('a', text:'Vortrag') }
                            speakerLink { $('a', text:'Speaker') }
                        }

 }
