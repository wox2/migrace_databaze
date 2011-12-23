(: Seznam hracu s nejvetsim poctem faulu :)
<html>
	<title>Nejvice faulujici hraci: </title>
	<body>
		<ul>
{
for $hrac in fn:doc("../data.xml")//cislo
where fn:count($hrac/faul)= fn:max
    (
        for $hrac2 in fn:doc("../data.xml")//cislo
        return fn:count($hrac2/faul)
    )                
          return element li {fn:data($hrac/jmeno)}
}
    </ul>
  </body>
</html>
