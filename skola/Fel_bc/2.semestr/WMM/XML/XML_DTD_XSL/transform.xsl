<?xml version="1.0"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="home">
    <html>
      <head>
        <title>Mapa stránek (XML)</title>
      </head>
      <body>
        
        <h1>Mapa stránek (XML)</h1>

        <ul>
          <xsl:for-each select=".">
            <li>
              <xsl:value-of select="title"/>
              <ul>
                <xsl:for-each select="stranka">
                  <li>
                    <xsl:value-of select="title"/>
                    <ul>
                      <xsl:for-each select="subStranka">
                        <li>
                          <xsl:value-of select="title"/>
                        </li>
                      </xsl:for-each>
                    </ul>
                  </li>
                </xsl:for-each>
              </ul>
            </li>
          </xsl:for-each>
        </ul>
        
            
            
          
        
       
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>