<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : napaStranek.xsl
    Created on : 1. červen 2009, 9:19
    Author     : mary
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Mapa stranek</title>
                <link href="styll.css" media="screen" rel="stylesheet" type="text/css" />
            </head>
            <body>
                
                 <div >
                  <img  src="vinice_pokus7.jpg" id="obrazek" alt="vinice"/>   
                 </div>
                 <br />
                 <div class="zarovnaniNavigace">
                  <a class="nabidkaVNavigaci" href="hlavni_stranka.html"><xsl:value-of select="rozdeleni/hlavniStranka"/></a>
                  <a class="nabidkaVNavigaci" href="nabidka_vin.html"><xsl:value-of select="rozdeleni/nabidkaVin/nabV"/></a>
                  <a class="nabidkaVNavigaci" href="koho_tu_muzete_potkat.html"><xsl:value-of select="rozdeleni/potkat"/></a>
                  <a class="nabidkaVNavigaci" href="kontakt.html"><xsl:value-of select="rozdeleni/kontakt"/></a>
                  <a class="nabidkaVNavigaci"><xsl:value-of select="rozdeleni/mapaStran"/></a>
                    
                 </div>
                <h2>Mapa stránek</h2>
                <div id="mapaStr">
                <ul>
                <xsl:apply-templates select="rozdeleni">
                </xsl:apply-templates>
                
                </ul>
                
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="hlavniStranka">
        <li><a href="hlavni_stranka.html">
            <xsl:value-of select="."/>
        </a></li>
    </xsl:template>
    <xsl:template match="nabidkaVin">
        <li>
            <xsl:value-of select="nabV"/>
        <ul><li><a href="nabidka_vin.html"><xsl:value-of select="//nazev"/></a></li>
       
            <ul>
               <li> 
                  <a href="nabidka_vin.html">
                     Stránka: <xsl:value-of select="//stranka1"/><br />
                  </a>            
               </li>
               <li> 
                   <a href="nabidka_vin_strana2.html">
                    Stránka: <xsl:value-of select="//stranka2"/><br />
                    </a>            
               </li>           
             </ul> 
        </ul>
        
        <ul><li><a href="nabidka_vin_cervene.html"><xsl:value-of select="//nazev1"/></a></li>
       
        <ul>
            <li> 
                <a href="nabidka_vin_cervene.html">
                Stránka: <xsl:value-of select="//stranka1"/><br />
        </a>            
                  </li>
                     <li> 
                      <a href="nabidka_vin_cervene_strana2.html">
                      Stránka: <xsl:value-of select="//stranka2"/><br />
                      </a>            
                  </li>           
        </ul> 
        </ul>       
        <ul><li><a href="nabidka_vin_ruzove.html"><xsl:value-of select="//nazev2"/></a></li></ul>
        <ul><li><a href="nabidka_vin_sumive.html"><xsl:value-of select="//nazev3"/></a></li></ul>
        <ul><li><a href="nabidka_vin_stacene.html"><xsl:value-of select="//nazev4"/></a></li></ul>
        <ul><li><a href="nabidka_vin_specialni.html"><xsl:value-of select="//nazev5"/></a></li></ul>
    </li>
    </xsl:template>
    
    <xsl:template match="potkat">
        <li>
           <a href="koho_tu_muzete_potkat.html"> <xsl:value-of select="."/></a>
        </li>
    </xsl:template>
    <xsl:template match="kontakt">
        <li>
           <a href="kontakt.html"> <xsl:value-of select="."/></a>
        </li>
    </xsl:template>
     <xsl:template match="mapaStran">
        <li>
            <xsl:value-of select="."/>
        </li>
    </xsl:template>
</xsl:stylesheet>
