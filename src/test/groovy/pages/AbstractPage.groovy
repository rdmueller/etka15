package pages
import geb.Page

class AbstractPage    extends Page {

    static    url = "http://rdmueller.github.io/etka15/vortrag.html"
    static    at = { title ==~ /Spock und Geb.*/ }
    static    content = {
													headline { $('section#main_content h2').text() }
                        }

 }
