<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>
<xsl:template match="/Pages">
<html>
  <head>
            
 <link href="style.css" rel="stylesheet" type="text/css" title="default" media="screen"/>
    <link href="style1.css" rel="alternate stylesheet" type="text/css" title="alternative" media="screen"/> 
    <link href="print.css" rel="stylesheet" type="text/css" media="print"/>
   
    
     <script type="text/javascript" src="styleswitcher.js"></script>
     
      <title>Mapa stránek (XML) [Stránky Martina Lukeše] </title>
    
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    
  </head>

  <body>
     <div id ="center">
     
     <h2>Mapa stránek (XML)</h2>
     
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
      
     </div>
     <div id="left">
         <ul class ="seznam">
              <li>
                  <a href="index.html">Hlavní stránka</a>
              </li> 
              <li>
                  <a href="atletika.html">Atletika</a>
              </li>
              <li>
                  <a href="pripominky.html">Připomínky</a>
              </li>
              <li>
                  <a href="about_me.html">O mně</a>
              </li>
              <li>
                  <a href="mapa.html">Mapa</a>
              </li>
              <li><a href="mapaXML.xml">Mapa stránek (XML)</a></li>
         </ul>    
     </div>
     
     <div id="top">
                    <h1 class="nazev_stranky">Mapa Stranek v formatu XML</h1><br/>
              </div>
     
              <div id="skin">
                     Skin:
                    <a href="#" onclick="setActiveStyleSheet('default'); return false;">Základní</a>
              
                    <a href="#" onclick="setActiveStyleSheet('alternative'); return false; " >Alternativní</a>
              </div>
     <div id="right">
         <h2>Zajímavé odkazy</h2>
         <a href="http://sp4.cz/">Sportovní oddíl Spartak P4</a><br/>
         <a href="http://google.com/">Google</a><br/>
         <a href="http://www.wizards.com/">Wizards.com</a><br/>
         <a href="http://fel.cvut.cz/">FEL</a><br/>
     </div>
     
     
      
      <div id="bottom">
        <span> Wox2 2009 |</span>
        <a href="#top">Nahoru</a>
        
        <span>| XHTML 1.0 Strict </span>
      </div>

  </body>
</html>
  </xsl:template>

</xsl:stylesheet>