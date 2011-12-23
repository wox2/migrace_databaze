function show_page(page)
{
	switch(page)
	{
		case -1:
			page = actual + 1 == max ? actual : actual + 1;
			break;
		case -2:
			page = actual == min ? actual : actual - 1;
			break;
	}
	if(page == actual)
		return;
	document.getElementById("page" + actual).disabled="";
	document.getElementById("page" + page).disabled="disabled";
	actual = page;
	document.getElementById("picture").src = file + "." + actual + ".png";
}
