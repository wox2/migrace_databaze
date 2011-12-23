<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : data2.xsl.xsl
    Created on : November 15, 2009, 6:37 PM
    Author     : quick
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

	<!-- Nevypisovat vsechny uzly -->
	<xsl:template match="text()" />

    <xsl:template match="/">
        <html>
            <head>
                <title>Flights stats</title>
            </head>
            <body>
				<h1>Flights</h1>
				<!--Vytvori list letu-->
				<xsl:apply-templates mode="list"/>

				<!--Pro kazdy let vypise cestujici-->
				<xsl:apply-templates mode="detail"/>
            </body>
        </html>
    </xsl:template>

	<!--Vytvori list letu-->
	<xsl:template match="flights" mode="list">
		<h2>Flight list</h2>
		<ul>
			<!--Projde vsechny lety a vypise jeho cislo do seznamu-->
			<xsl:for-each select="flight">
				<li><xsl:value-of select="@number"/></li>
			</xsl:for-each>
		</ul>
	</xsl:template>

	<!--Vypise detailni info pro let-->
	<xsl:template match="flight" mode="detail">
		<h2>Flight detail <xsl:value-of select="@number"/></h2>

		<!--Vola vytvoreni listu cestujicich-->
		<xsl:call-template name="passengers-list" />
	</xsl:template>

	<!--Vypise list cestujicich-->
	<xsl:template name="passengers-list">
		<h3>All passengers</h3>
		<table>
			<tr>
				<th>Gender</th>
				<th>Name</th>
				<th>Natal year</th>
			</tr>
			<!-- Vypise vsechny cestujicic -->
			<xsl:apply-templates select="passengers/passenger" />
		</table>

		<!-- Pokud pro let existuji nejake deti, tak vypise jejich seznam -->
		<xsl:if test="count(passengers/passenger[@gender='INF']) &gt; 0">
			<h3>Infants</h3>
			<table>
				<tr>
					<th>Gender</th>
					<th>Name</th>
					<th>Natal year</th>
				</tr>

				<!-- Vypise vsechny deti na letu -->
				<xsl:apply-templates select="passengers/passenger[@gender='INF']" />
			</table>
		</xsl:if>
	</xsl:template>

	<!-- Vypsani cestujiciho -->
	<xsl:template match="passenger">

		<!-- vytvori promennou s barvou textu pro cestujiciho (barva podle pohlavi) -->
		<xsl:variable name="color">
			<xsl:choose>
				<xsl:when test="@gender = 'MR'">
					<xsl:text>blue</xsl:text>
				</xsl:when>
				<xsl:when test="@gender = 'MRS'">
					<xsl:text>red</xsl:text>
				</xsl:when>
				<xsl:when test="@gender = 'CHD'">
					<xsl:text>green</xsl:text>
				</xsl:when>
				<xsl:when test="@gender = 'INF'">
					<xsl:text>brown</xsl:text>
				</xsl:when>
			</xsl:choose>
		</xsl:variable>

		<!-- zavola sablonu pro vypsani cestujiciho. Jako parametr je barva textu -->
		<xsl:call-template name="passenger-info">
			<xsl:with-param name="color" select="$color" />
		</xsl:call-template>

	</xsl:template>

	<!--Vypise cestujiciho zvolenou barvou textu -->
	<xsl:template name="passenger-info">
		
		<xsl:param name="color" />

		<!-- Vytvori element tr - radek tabulky -->
		<xsl:element name="tr">
			<!-- nastavi pro ni barvu textu z parametru -->
			<xsl:attribute name="style">
				color: <xsl:copy-of select="$color" />
			</xsl:attribute>

			<!-- Vypise data -->
			<td><xsl:value-of select="@gender"/></td>
			<td>
				<xsl:text> </xsl:text>
				<xsl:value-of select="first_name"/>
				<xsl:text> </xsl:text>
				<xsl:value-of select="middle_name"/>
				<xsl:text> </xsl:text>
				<xsl:value-of select="last_name"/>
			</td>
			<td>
				<!-- Zkopiruje uzel roku narozeni -->
				<xsl:copy>
					<xsl:apply-templates select="birthday/year" />
				</xsl:copy>
			</td>
		</xsl:element>
	</xsl:template>

	<!-- vypise rok narozeni -->
	<xsl:template match="year">
		<xsl:value-of select="text()"/>
	</xsl:template>

</xsl:stylesheet>
