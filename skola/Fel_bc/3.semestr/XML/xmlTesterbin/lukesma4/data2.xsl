<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns="http://www.w3.org/1999/xhtml"
version="1.0">
    <xsl:template match="/">
        <html>
            <head>
                <title>PROJEKT fotbalovy zapas</title>
            </head>
            <body>
                
                <xsl:call-template name="zapas"/>
            </body>
        </html>
    </xsl:template>


    <!-- sablona zapas vytvori element, ktery bude mit jako text atribut jmeno turnaje-->
    <xsl:template name="zapas">
        <div>
            <xsl:element name="jmeno_turnaje">
                <xsl:value-of select ="fotbalovy_zapas/@jmeno_turnaje"/>
            </xsl:element>
        </div>
        <div>
            
        </div>
        <xsl:apply-templates />
    </xsl:template>

    <!-- sablona match vytvori div, ktery bude mit jako hodnotu id muzstva-->
    <xsl:template match ="//muzstvo">
        <div>
            <xsl:value-of select ="@id"/>
        </div>
        <xsl:apply-templates />
    </xsl:template>

        <!-- sablona pouziva jeden z xpath vyrazu z minuleho ukolu a pridava text-->
    <xsl:template match="//muzstvo[@domaci='ano']">
        <xsl:element name="fans">
            <xsl:text>Pocet fanousku domaciho celku, kteri prisli na zapas, prekonal vsechny nase predstacvy...</xsl:text>
            <xsl:attribute name="prezdivka">velka_podpora_fanousku</xsl:attribute>
            <xsl:apply-templates />
        </xsl:element>
    </xsl:template>

        <!-- u hracu s prezdivkou je vypsano jen jmeno a prezdivka, sablony nejsou na jejich vnitrek pouzivany-->
    <xsl:template match="//hrac/cislo[prezdivka]">
        <div>
            <xsl:text>  </xsl:text>
            <xsl:value-of select ="./jmeno"/>
        </div>
        <div>
            <xsl:value-of select ="./prezdivka"/>
        </div>
    </xsl:template>

    <xsl:template match = "*">
        <div>
            <xsl:apply-templates />
        </div>


    </xsl:template>
    <xsl:output method="html" indent="yes"/>
</xsl:stylesheet>