<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns="http://www.w3.org/1999/xhtml"
version="2.0">
    <xsl:template match="/">
        <xsl:variable name="color">
            <xsl:value-of select="blue" />
        </xsl:variable>
        <html>
            <head>
                <title>PROJEKT fotbalovy zapas</title>
            </head>
            <body>
                <table>
                <xsl:call-template name="muzstvo-info">
                    <xsl:with-param name="color" select="$color"  />
                </xsl:call-template>
                </table>
                <xsl:call-template name="zapas"/>
            </body>
        </html>
    </xsl:template>

    <!-- sablona zapas vytvori element, ktery bude mit jako text atribut jmeno turnaje,
    dale spocita muzstva, zavola spravne sablony-->
    <xsl:template name="zapas">
        <div>
            <xsl:element name="jmeno_turnaje">
                <xsl:value-of select ="fotbalovy_zapas/@jmeno_turnaje"/>
            </xsl:element>
        </div>
        <xsl:variable name="pocet-muzstev">
            <xsl:value-of select="count(//muzstvo)" />
        </xsl:variable>
        <div>
            <xsl:text>Celkem muzstev: </xsl:text>
            <xsl:value-of select="$pocet-muzstev" />
        </div>

        <xsl:for-each select="./*">
            <xsl:choose>
            <!--pouziti jednoho xpath vyrazu z minuleho ukolu-->
                <xsl:when test=".//muzstvo[@domaci='ano']">
                    <xsl:call-template name="domaci_muzstvo"/>
                </xsl:when>
                <xsl:when test=".//muzstvo">
                    <xsl:apply-templates select="//muzstvo" mode="universal"/>
                </xsl:when>
                <xsl:otherwise>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:for-each>

        <xsl:call-template name="nejhorsi_hraci" />

    </xsl:template>


    <!-- sablona match vytvori div, ktery bude mit jako hodnotu id muzstva-->
    <xsl:template match ="//muzstvo" mode="universal">
        <div>
            <xsl:value-of select ="@id"/>
        </div>
        <br/>
        <xsl:apply-templates />
    </xsl:template>

        <!-- sablona pridava text-->
    <xsl:template name="domaci_muzstvo" >
        <xsl:element name="fans">
            <xsl:attribute name="prezdivka">velka_podpora_fanousku</xsl:attribute>
            <xsl:text>Pocet fanousku domaciho celku, kteri prisli na zapas, prekonal vsechny nase predstavy...</xsl:text>
            <xsl:apply-templates select="//muzstvo" mode="universal"/>
        </xsl:element>
    </xsl:template>

        <!-- u hracu je vypsano jmono, pripadne prezdivka a poznamka, ze hrac je kapitan-->
    <xsl:template match="//hrac/cislo">
        <div>
            <xsl:value-of select ="./jmeno"/>
            <xsl:if test="./prezdivka">
                <xsl:text>---</xsl:text>
                <xsl:value-of select ="./prezdivka"/>
            </xsl:if>
            <xsl:if test="../kapitan">
                <xsl:text>--------Je kapitan? JE KAPITAN.</xsl:text>
            </xsl:if>
        </div>
    </xsl:template>

    <!--tato sablona zabranuje zpracovani elementu kapitan -->
    <xsl:template match="//kapitan">
    </xsl:template>
    
    <!-- prvni hrac v seznamu, ktery fauloval bude vybran jako nejhorsi hrac -->
    <xsl:template name="nejhorsi_hraci">
        <div>
            <br/>
            <xsl:text>
                nejhorsi hrac/i zapasu:
            </xsl:text>
            <br />
           <!-- -->
            <xsl:apply-templates select="//hrac/cislo[faul]/jmeno/text() "/>
            <xsl:apply-templates select="//muzstvo/sestava/hrac[position() = 1] "/>
        </div>
        <br/>
    </xsl:template>

    <!-- vybere jmeno faulujiciho hrace a odradkuje-->
    <xsl:template match="//hrac/cislo[faul]/jmeno/text()">
        <xsl:value-of select="."/>
        <br />
    </xsl:template>

    <!--vybere prvniho hrace z kazdeho tymu, neni to prime pouziti xpath vyrazu z min. ukolu, protoze ten byl spatne, nicmene mel delat presne toto-->
    <xsl:template match="//muzstvo/sestava/hrac[position() = 1]">
        <xsl:value-of select="./cislo/jmeno/text()"/>
        <br />
    </xsl:template>
    
    <xsl:template name="muzstvo-info">
		
		<xsl:param name="color" />

		<!-- Vytvori element tr - radek tabulky -->
		<xsl:element name="tr">
			<!-- nastavi pro ni barvu textu z parametru -->
			<xsl:attribute name="style">
				color: <xsl:copy-of select="$color" />
			</xsl:attribute>

			<!-- Vypise data -->
		<xsl:for-each select="//muzstvo">
			<td><xsl:value-of select="@id"/></td>	
				<!-- Zkopiruje uzel prvniho hrace muzstva -->
			<td>
                                <xsl:copy>
					<xsl:apply-templates select="//muzstvo/sestava/hrac[position() = 1]" />
				</xsl:copy>
			</td>	
		  </xsl:for-each>
		</xsl:element>
	</xsl:template>

    

    <!-- zneplatneni zpracovani sablony -->
    <xsl:template match="skore">
    </xsl:template>

    <!-- zneplatneni zpracovani sablony -->
    <xsl:template match="karty">
    </xsl:template>

    <!-- zneplatneni zpracovani sablony -->
    <xsl:template match="goly">
    </xsl:template>

    <!-- zneplatneni zpracovani sablony -->
    <xsl:template match="zaznam">
    </xsl:template>

    <!-- zneplatneni zpracovani sablony -->
    <xsl:template match="rozhodci">
    </xsl:template>
    
    <xsl:output method="html" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>
</xsl:stylesheet>