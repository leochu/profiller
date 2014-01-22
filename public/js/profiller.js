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
	$("#registrationErrorDiv").empty();
	var inputData = JSON.stringify({
		username : $(reg_username).val(),
		secret : $(reg_secret).val(),
		secretConfirm : $(reg_secret_confirm).val()
	});

	$.ajax({
		type : "POST",
		url : "/register",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : inputData,
		success : function(data) {
			$('#registerModal').modal('hide')
			$('#registeredMessageDiv').html(
					'<label class="success_message">' + data.email
							+ ' has been succesfully registered.</label>')
		},
		error : function(XMLHttpRequest, status, error) {
			var errorMessage = XMLHttpRequest.responseJSON.errorMessage;

			$("#registrationErrorDiv")
					.html(
							'<label class="error_message">' + errorMessage
									+ '</label>');
		}
	});
}

function clearRegModal() {
	$("#reg_username").val('');
	$("#reg_secret").val('');
	$("#reg_secret_confirm").val('');
	$("#registrationErrorDiv").empty();
}