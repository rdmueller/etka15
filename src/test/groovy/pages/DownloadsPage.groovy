package pages

import geb.Page

class DownloadsPage extends Page {

    static url = "http://rdmueller.github.io/etka15/downloads.html"
    static at = { title ==~ /Spock und Geb.*/ }
    static content = {

    }

}
