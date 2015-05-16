/*
	This is the Geb configuration file.

	See: http://www.gebish.org/manual/current/configuration.html
*/


import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.phantomjs.PhantomJSDriver
import grails.plugin.filmstrip.FilmStripReportingListener

waiting {
    timeout = 2
}

environments {

    // run via “./gradlew chromeTest”
    // See: http://code.google.com/p/selenium/wiki/ChromeDriver
    chrome {
        driver = { new ChromeDriver() }
        reportingListener = new grails.plugin.filmstrip.FilmStripReportingListener()
    }

    // run via “./gradlew firefoxTest”
    // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
    firefox {
        driver = { new FirefoxDriver() }
        reportingListener = new grails.plugin.filmstrip.FilmStripReportingListener()
    }

    phantomJs {
        driver = { new PhantomJSDriver() }
        reportingListener = new grails.plugin.filmstrip.FilmStripReportingListener()
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://gebish.org"