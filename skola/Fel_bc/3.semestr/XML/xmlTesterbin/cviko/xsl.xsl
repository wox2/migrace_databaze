<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"

version="1.0">
<xsl:output method="xml" indent="yes"/>

<xsl:template match="/">

    <html>
      <head>
         <title> PROJEKTY </title>
      </head>   
      <body>
      
      
      
      <xsl:template match="@*">
        <xsl:apply-templates select=".@*"/>
        <xsl: apply-templates />
      </body>
    </html>
      
</xsl:templates>

<xsl: >      













</xsl:stylesheet>