// Free for personal and non-commercial use
// http://www.jsworld.com/

var section_id;
var section_status = 0;
var menu_timeout_id;

function get_element(name) {
	if (document.all) {
		return document.all[name];
	} else {
		return document.getElementById(name);
	}
}

function collapse_menu() {
	var sections_array = new Array();
	sections_array = section_ids.split(";");
	var i = 0;
	var ul_element;
	while (sections_array[i]) {
		ul_element = get_element("menu-"+sections_array[i]);
		ul_element.style.display="none";
		i++;
	}
}

function section_on_now(tmp_section_id) {
        section_id = tmp_section_id;
        section_status = 1;
        section_show(); 
}

function menu_on() {
    if (section_status == 3) {
        clearTimeout(menu_timeout_id);
        section_status = 2;
   } 
}

function menu_off() {
    if (section_status == 2) {
        section_status = 3;
        menu_timeout_id = setTimeout("section_hide()",300);
    }
}

function section_off() {
    if (section_status == 2) { 
        section_status = 3;
        menu_timeout_id = setTimeout("section_hide()",300);
    } else if (section_status == 1) { 
        clearTimeout(menu_timeout_id);
        section_status = 0;
    }
}

function section_on(tmp_section_id) {
    if (section_status == 0) {
        section_status = 1;
        section_id = tmp_section_id;
        menu_timeout_id = setTimeout("section_show()",150);
    } else if (section_status == 3) {
        clearTimeout(menu_timeout_id);
        if ( section_id == tmp_section_id) {
            section_status = 2;
        } else {
            section_id = tmp_section_id;
            section_status = 1;
            section_show();
        }
    }
}

function section_show() {
    if (section_status == 1) {
        collapse_menu();
        ul_element = get_element("menu-"+section_id);
        ul_element.style.display="block"; 
        section_status = 2;
    } 
}

function section_hide() {
    if (section_status == 1) {
        section_status = 0;
    } else if (section_status == 3) {
        collapse_menu();
        section_status = 0;
    }
}
