<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns="http://www.w3.org/1999/xhtml"
version="1.0">
    
    <!-- Deklarování statické proměnné pro určení částky, která bude znamenat vysokou cenu -->
    <xsl:variable name="minimum" select="2500" />
    <xsl:output method="xml" indent="yes"/>
    
    <!-- Kořenový element / -->
    <xsl:template match="/">
        <html>
            <head>
                <title><xsl:element /></title>
            </head>
            <body>
                <div align="center">
                    <h2>Objednavky</h2>
                </div>
                <xsl:apply-templates mode="vypis"/>
            </body>
        </html>
    </xsl:template>
    
    <!-- mode použit jen prostě aby byl, nenapadlo mně nějaké logické využití -->
    <xsl:template match="obchod" mode="vypis">
        
        <!-- zapamatování si ID obchodu ve kterém zákazník nakupoval -->
        <xsl:param name="IDObchod" select="@id_obchodu" />
        
        <!-- Projde všechny objednávky, které se v danném obchodě uskutečnily -->
        <xsl:for-each select="objednavka">
        
            <div align="center">
                <!-- Vypsání údaje o zákazníkovi -->
                <xsl:call-template name="zakaznik_udaje" />
                <!-- Vypsání ID obchodu -->
                <TEXT>ID obchodu: </TEXT><xsl:value-of select="$IDObchod" />
                <!-- Tabluka nakoupeného zboží -->
                <xsl:call-template name="objednavka" />
                <!-- výpis komentáře -->
                <xsl:call-template name="komentar" />
            </div>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template name="zakaznik_udaje">

<!-- Výpis údajů o každém zákazníkovi -->
        <h3>
            <xsl:value-of select="zakaznik/jmeno"/>
            <TEXT>   (</TEXT>
            <xsl:value-of select="zakaznik/@id"/>
            <TEXT>)</TEXT>
        </h3>
        <div>
            <TEXT>Email: </TEXT>
            <xsl:value-of select="zakaznik/email" />
        </div>
        <div>
            <TEXT>Telefon: </TEXT>
            <xsl:value-of select="zakaznik/telefon" />
        </div>

<!-- Tato sekce rozhoduje, zda bude použita adresa doručovací ( pokud existuje ) nebo adresa doručovací -->
        <xsl:choose>
            <xsl:when test="zakaznik[adresa_dorucovaci]">
                <xsl:call-template name="adresa_dorucovaci" />
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="adresa_bydliste" />
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
  
  <!--  Tabulka objednaných výrobků každého zákazníka -->
    <xsl:template name="objednavka">
        <br />
        <table border="1" width="50%" align="center" >
            <tr bgcolor="#9acd32">
                <th>Nazev</th>
                <th>Pocet kusu</th>
                <th>Cena</th>
                <th>ID vyrobku</th>
            </tr>
            
            <!-- Výpis všech výrobků koupenných v dané objednávce -->
            <xsl:for-each select="zbozi/vyrobek">
                <tr>
                    <!-- nastavení pozadí pro výrobky jejich cena převyšuje minimum (2500) -->
                    <xsl:if test="@cena &gt; $minimum">
                    <xsl:attribute name="bgcolor">
                        gold
                    </xsl:attribute></xsl:if>

                    <xsl:attribute name="id">
                        <xsl:value-of select="@id" />
                    </xsl:attribute>
                    

                    <td>
                        <xsl:value-of select="."/>
                    </td>
                    <td>
                        <xsl:value-of select="@ks"/>
                    </td>
                    <td>
                        <xsl:value-of select="@cena"/><TEXT> Kc</TEXT>
                    </td>
                    <td>
                        <xsl:value-of select="@id"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
        <br />
    </xsl:template>
    
    <!-- Výpis adresy bydliste -->
    <xsl:template name="adresa_bydliste">
        <table border="1">
            <TEXT>Ulice: </TEXT>
            <xsl:value-of select="zakaznik/adresa_bydliste/ulice" />
            <TEXT>...</TEXT>
            <xsl:value-of select="zakaznik/adresa_bydliste/popisne_cislo" />
            <br />
            <TEXT>Mesto: </TEXT>
            <xsl:value-of select="zakaznik/adresa_bydliste/mesto" />
            <br />
            <TEXT>PSC: </TEXT>
            <xsl:value-of select="zakaznik/adresa_bydliste/psc" />
            <br />
        </table>
    </xsl:template>
    
    <!-- Výpis adresy doručovací -->
    <xsl:template name="adresa_dorucovaci">
        <table border="1">
            <TEXT>Ulice: </TEXT>
            <xsl:value-of select="zakaznik/adresa_dorucovaci/ulice" />
            <TEXT>...</TEXT>
            <xsl:value-of select="zakaznik/adresa_dorucovaci/popisne_cislo" />
            <br />
            <TEXT>Mesto: </TEXT>
            <xsl:value-of select="zakaznik/adresa_dorucovaci/mesto" />
            <br />
            <TEXT>PSC: </TEXT>
            <xsl:value-of select="zakaznik/adresa_dorucovaci/psc" />
            <br />
        </table>
    </xsl:template>
    
    <!-- Výpos komentáře -->
    <xsl:template name="komentar">
        <table border="1">
            <tr bgcolor="#cccccc">
                <th>Poznamka</th>
            </tr>
            <tr>
                <td>
                    <!-- Skopírování uzlu do vzduchoprázdna -->
                    <xsl:copy xml:space="vzduchoprazdno" />
                    
                    <!-- Výpis poznámky -->
                    <xsl:copy-of select="poznamka"/>
                    
                    <!-- Vytvoření odkazu na zarážku v dokumentu -->
                    <xsl:if test="poznamka[odkaz]">
                        <a>
                           <xsl:attribute name="href">
                           <xsl:text>#</xsl:text>
                           <xsl:value-of select="poznamka/odkaz/@naid"/>
                           </xsl:attribute>
                           <xsl:value-of select="poznamka/odkaz" />
                        </a>
                    </xsl:if>
                </td>
            </tr>
        </table>
    </xsl:template>
</xsl:stylesheet>