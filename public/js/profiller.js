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

	$
			.ajax({
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
							.html(
									'<label class="error_message">Incorrect Username or Password</label>');
				}
			});
}

function register() {
	var inputData = JSON.stringify({
		username : $(reg_username).val(),
		secret : $(reg_secret).val(),
		secretconfirm : $(reg_secret_confirm).val()
	});

	$.ajax({
		type : "POST",
		url : "/register",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : inputData,
		success : function(data) {
			$('#registerModal').modal('hide')

		},
		error : function(XMLHttpRequest, status, error) {
			$("#registrationErrorDiv").html(
					'<label class="error_message">ErrorMessage</label>');
		}
	});
}

function clearRegModal() {
	
}