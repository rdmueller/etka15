//script to convert an xml-workbook-Spec into a groovy-class-Spec

//helper 
def rowToList = { row ->
    def list = []
    def correction = 0
    row.Cell.eachWithIndex { cell, index ->
        def numCellsToJump = 0
        if (cell."@ss:Index".text()) {
            numCellsToJump = (cell."@ss:Index".text().toInteger()) - (index+1+correction)
            numCellsToJump.times { list << '' }
            correction += numCellsToJump
        }
        list << cell.text()
    }
    return list
}
def blockTranslation = [
                        //german
                        'gegeben'    : 'given:  ',
                        'wenn'       : 'when:   ',
                        'und'        : 'and:    ',
                        'dann'       : 'then:   ',
                        'erwartet'   : 'expect: ',
                        'wobei'      : 'where:  ',
                        //english
                        'given'      : 'given:  ',
                        'when'       : 'when:   ',
                        'and'        : 'and:    ',
                        'then'       : 'then:   ',
                        'expect'     : 'expect: ',
                        'where'      : 'where:  ',
                       ]

//the main loop
new File('.').listFiles({file,name-> name ==~ /.*Spec.xml$/} as FilenameFilter).each { file ->
    println "found $file"
    def workbook = new XmlSlurper().parseText(file.text)
    //first extract page definitions
    def pageDefs = [:]
    new File('./pages').mkdirs()
    workbook.Worksheet.each { worksheet ->
        def name = worksheet."@ss:Name".text()
        if (name.endsWith('Seiten') || name.endsWith('Pages')) {
            worksheet.Table.Row[1..-1].each { row ->
                def (readable,url,technical) = rowToList(row)
                pageDefs[readable]=[url:url,name:technical]
                def code = """\
                            package pages
                            import geb.Page
                            import spock.lang.Ignore

                            class $technical    extends Page {
                                
                                static    url = "$url"
                                //TODO: 
                                static    at = { title ==~ /some regexp/ }
                                static    content = {
                                
                                                    }
                             
                             }
                            """.stripIndent()
                new File("./pages/${technical}.groovy").write(code)
            }
        }
    }
    workbook.Worksheet.each { worksheet ->
        def withinFeature = 0
        def name = worksheet."@ss:Name".text()
        if (name.endsWith('Spec')) {
            def pages = []
            def code = """\
                        import geb.spock.GebReportingSpec
                        import spock.lang.Ignore
                        %pageImports%
                        
                        class ${name} extends GebReportingSpec {
                        
                       """.stripIndent()
            worksheet.Table.Row[1..-1].each { row ->
                def (featureName,block,description,response,screenshot,comment) = rowToList(row)
            if (featureName) {
                code += "${withinFeature++?"    }\n\n":''}    @Ignore(\"not implemented\")\n    def '${featureName.replaceAll("'",'\'')}'() {\n"
            } else {
                block = block?.toLowerCase()?.replaceAll("[^a-z]","")

                block = blockTranslation[block]?:block
                def matcher = description =~ /^"(.*)"$/
                if (matcher) {
                    description = matcher[0][1].replaceAll('""','"')
                }
                
                code += "        ${block}\"${description?.replaceAll('"',"'")}\"${comment?.trim()?"\t//$comment":""}\n"
                if (response) {
                    if (block.startsWith('given')) {
                        code += "            to ${pageDefs[response].name}\n"
                        if (screenshot) {
                            code += "            report '$screenshot'\n"
                        }
                    } else {
                        if (screenshot) {
                            code += "            report '$screenshot'\n"
                        }
                        code += "            at ${pageDefs[response].name}\n"
                    }
                    pages << response
                }
                code += "            //TODO: implement test step\n"
            }
        }   
        code += "    }\n}\n"
        pages.unique().each { if (!pageDefs[it]) throw new Exception("can't find page definition for '$it' in spec definition sheet")}
        code = code.replaceAll('(?ms)%pageImports%',pages.unique().collect{"import pages.${pageDefs[it].name}"}.join("\n"))
        //println pages.unique().collect{"import pages.${pageDefs[it].name}"}.join("\n")
        def targetName = name+'.groovy'
        new File (targetName).write(code)
        println "wrote $targetName"
    } 
}
}