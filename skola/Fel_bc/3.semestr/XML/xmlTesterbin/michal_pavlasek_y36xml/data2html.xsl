<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    
<xsl:output method="xhtml" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>   

<xsl:key name="porady" match="//porad" use="@id_poradu"/>
<xsl:key name="osoby" match="//clovek" use="@id_cloveka"/>

    <xsl:template match="/">     
        <html xmlns="http://www.w3.org/1999/xhtml">
            <head>
                <title>televizni program</title>
            </head>
            <body>                
                <h1>Televizni program</h1>                
                <xsl:for-each select="/tv_program/vysilani">
                    <h2><xsl:value-of select="key ('porady', co/@ref)/nazev" /></h2>
                    <p>
                    <xsl:value-of select="kanal"/>
                        <xsl:if test="key ('porady', co/@ref)/popis">
                            <xsl:text>, </xsl:text>
                            <xsl:value-of select="key ('porady', co/@ref)/popis"/>
                        </xsl:if>                    
                        
                    <br/>               
                    <xsl:value-of select="day-from-dateTime(zacatek)"/>
                    <xsl:text>.</xsl:text>
                    <xsl:value-of select="month-from-dateTime(zacatek)"/>
                    <xsl:text>., </xsl:text>
                        
                    <xsl:value-of select="hours-from-dateTime(zacatek)"/>
                    <xsl:text>:</xsl:text>
                    <xsl:value-of select="minutes-from-dateTime(zacatek)"/>
                    <xsl:text> - </xsl:text>
                        
                    <xsl:value-of select="hours-from-dateTime(konec)"/>
                    <xsl:text>:</xsl:text>
                     <xsl:choose>
                         <xsl:when test="minutes-from-dateTime(konec) &lt; 10">
                             <xsl:text>0</xsl:text>
                             <xsl:value-of select="minutes-from-dateTime(konec)"/>   
                         </xsl:when>
                         <xsl:otherwise>
                             <xsl:value-of select="minutes-from-dateTime(konec)"/>
                         </xsl:otherwise>
                     </xsl:choose>                                                
                    <br/>
                        
                    <xsl:if test="key( 'osoby', (key('porady',  co/@ref)/reziser/@ref))">    
                        <xsl:text>rezie: </xsl:text>
                        <xsl:value-of select="key( 'osoby', (key('porady',  co/@ref)/reziser/@ref))/jmeno"/>
                        <xsl:text> </xsl:text>
                        <xsl:value-of select="key( 'osoby', (key('porady',  co/@ref)/reziser/@ref))/prijmeni"/><br/>
                    </xsl:if>
                    
                    <xsl:if test="key( 'osoby', (key('porady',  co/@ref)/herci/@ref))">
                        <xsl:text>herci: </xsl:text>
                        <xsl:for-each select="key( 'osoby', (key('porady',  co/@ref)/herci/@ref))">
                            <xsl:value-of select="jmeno"/>
                            <xsl:text> </xsl:text>
                            <xsl:value-of select="prijmeni"/>
                            <xsl:text>, </xsl:text>
                        </xsl:for-each>
                    </xsl:if>
                    </p>                    
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>