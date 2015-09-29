
function show_message(title, body, flag) {
	console.log("show message");
}

function change_lang(lang) {
	$.get("/main/setlang/" + lang, function (data, status) {
		if (data.status === "error") {
			show_message("Error", "error", true);
		}
	});
}
