package grails.plugin.filmstrip
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

/**
 * Created by Ralf on 24.03.2015.
 */
class ReportConverter {
    static convertJsonReport() {

            def reportsDir = "build/reports/geb/"
            def thisPath = new File('.').canonicalPath.replaceAll('\\\\','/')
            def gebReports = new File(reportsDir, "gebReportInfo.json").text.replaceAll('\\\\','/')
            def allReports = [specs:[]]
            def jsonSlurper = new JsonSlurper()
            gebReports.eachLine { json ->
                def reportLine = jsonSlurper.parseText(json)
                if (!(reportLine.spec.label in allReports.specs.label)) {
                    allReports.specs << [label:reportLine.spec.label,tests:[]]
                }
                def spec = allReports.specs.find { spec -> spec.label==reportLine.spec.label }
                if (!(reportLine.spec.test.num in spec.tests.num)) {
                    spec.tests << [
                            num:reportLine.spec.test.num,
                            label: reportLine.spec.test.label,
                            reports: []
                    ]
                }
                def test = spec.tests.find { test -> test.num==reportLine.spec.test.num }
                if (!(reportLine.spec.test.report.num in test.reports.num)) {
                    test.reports << [
                            time:reportLine.spec.test.report.time,
                            num:reportLine.spec.test.report.num,
                            label:reportLine.spec.test.report.label,
                            url:reportLine.spec.test.report.url,
                            files:[]
                    ]
                    println "**** new report"
                } else {
                    println "**** old report"
                }
                def report = test.reports.find { report -> report.num==reportLine.spec.test.report.num }
                if (!(reportLine.spec.test.report.files in report.files)) {
                    report.files += reportLine.spec.test.report.files.collect{"."+it.replaceAll('//','/')-thisPath-reportsDir}
                } else {
                    println "*"*80
                    println report.files.toString()
                }
            }

            def newJson = JsonOutput.toJson(allReports)
            new File(reportsDir, "gebReportInfo2.json").write(JsonOutput.prettyPrint(newJson))
    }

}
