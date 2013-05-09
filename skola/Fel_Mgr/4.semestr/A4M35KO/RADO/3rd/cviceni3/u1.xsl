<?xml version="1.0" encoding="utf8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:p="http://moon.felk.cvut.cz/~vranyj1/xmlns/projects/0.1"
    xmlns:db="http://docbook.org/ns/docbook"
    xmlns="http://www.w3.org/1999/xhtml"
    version="1.0">
  <xsl:output method="xml" indent="yes"/>

  <xsl:template match="/">
    <html>
      <head>
	<title>Seznam projektů</title>
      </head>
      <body>
    <ul>
      <xsl:for-each select="p:project-group">
        <li>
          <xsl:value-of select="p:info/db:title"/>
          <ul>
          <xsl:for-each select="p:project-group">
            <li>
              <xsl:value-of select="p:info/db:title"/>
              <xsl:for-each select="p:project">
                <li>
                  <xsl:value-of select="p:info/db:title"/>
                </li>

              </xsl:for-each>
            </li>
            <xsl:for-each select="p:project-group">
            <li>
              <xsl:value-of select="p:info/db:title"/>
            </li>
            
          </xsl:for-each>
            
          </xsl:for-each>
          </ul>
        </li>
      </xsl:for-each>

     
    </ul>
      </body>
    </html>

  </xsl:template>

</xsl:stylesheet>