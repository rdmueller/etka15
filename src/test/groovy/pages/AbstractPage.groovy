package pages
import geb.Page

class AbstractPage    extends Page {

    static    url = "http://rdmueller.github.io/etka15/vortrag.html"
    //TODO: 
    static    at = { title ==~ /some regexp/ }
    static    content = {

                        }

 }
