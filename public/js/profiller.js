// Activates the Carousel
$('.carousel').carousel({
	interval : 5000
})

// Activates Tooltips for Social Links
$('.tooltip-social').tooltip({
	selector : "a[data-toggle=tooltip]"
})

function login() {
	var inputData = JSON.stringify({
		username : $(username).val(),
		secret : $(secret).val()
	});

	$.ajax({
		type : "POST",
		url : "/login",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : inputData,
		success : function(data) {
			document.location = "/profile";
		},
		error : function(XMLHttpRequest, status, error) {
			$("#errorDiv")
					.html('<label>Incorrect Username or Password</label>');
		}
	});
}