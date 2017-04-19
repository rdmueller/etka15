package grails.plugin.filmstrip

import geb.report.ReportState
import geb.report.Reporter
import geb.report.ReportingListener
import groovy.json.JsonOutput

/**
 * Extracts all information gathered as JSON to a file which is later processed
 * by the plugin.
 *
 * @author rdmueller
 */
class FilmStripReportingListener implements ReportingListener {

    def report = [tests:[],reports:[]]

    void onReport(Reporter reporter, ReportState reportState, List<File> reportFiles) {
        def out =  new File(reportState.browser.config.reportsDir.path, 'gebReportInfo.json')
        def (testNum,reportNum,testLabel,reportLabel) = reportState.label.split('(?<!-)-(?!-)') // only split on single '-'
        //re-escape dashes
        testLabel   = testLabel.replaceAll('--','-')
        reportLabel = reportLabel.replaceAll('--','-')
        def res = [
                spec:[
                        label:reportState.browser.reportGroup,
                        test:[
                                num:(testNum),
                                label:testLabel,
                                report:[
                                        time:new Date().time,
                                        num:reportNum,
                                        label:reportLabel,
                                        url:reportState.browser.driver.currentUrl,
                                        page:reportState.browser.page.toString(),
                                        files:reportFiles.collect { it.absolutePath }
                                ]
                        ]
                ]
        ]

        out << JsonOutput.toJson(res)+"\n"
    }
}
