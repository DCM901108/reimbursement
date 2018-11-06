window.onload = function() {
	loadLogin();
	document.getElementById('toLogin').addEventListener('click', loadLogin);
	document.getElementById('toRegister').addEventListener('click', loadRegister);
	document.getElementById('toHome').addEventListener('click', loadHome);
	//document.getElementById('toProfile').addEventListener('click', loadProfile);
	document.getElementById('toLogout').addEventListener('click', logout);
}

function viewAll()
{
	
	console.log("in viewAll()")
	
	let xhr = new XMLHttpRequest();
	
	xhr.open("GET", "viewallreimb", true);
	
	xhr.send();
	
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState == 4 && xhr.status ==200)
			{
			a = JSON.parse(xhr.responseText);
				for(let i = 0; i<a.length; i++)
				{
					console.log(a[i])
					console.log(a.amount)
					let r = "<tr>" +
							"<td id='id'>" 			+ a[i].id + "</td>" +
							"<td id='description'>" + a[i].description + "</td>" +
							"<td id = 'amount'>" 	+ a[i].amount + "</td>" +
							"<td id = 'resolver'>"	+ a[i].resolver + "</td>" +
							"<td id = 'recipt'>" 	+ a[i].recipt + "</td>" +
							"<td id = 'author' >"	+ a[i].author + "</td>" +
							"<td id = 'type'>" 		+ a[i].type + "</td>" +
							"<td id = 'submitted>" 	+ a[i].submitted + "</td>" +
							"<td id = 'resolved'>" 	+ a[i].resolved + "</td>" +
							"<td id = 'status'>" 	+ a[i].status + "</td>" +
							"<td><button id='approve' type='button' class='btn btn-success'>"+"Approve"+"</button></td>" +
							"<td><button type='deny' class='btn btn-danger'>" + "Deny" + "</button></td>" +
							"</tr>"
					
					$('#manager-body').append(r);	
				}
			}
	}
}

function isAuthenticated(){
	
}

function loadLogin() {
	console.log('in loadLogin()');
	
	let xhr = new XMLHttpRequest();	
	xhr.open('GET', 'login.view', true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadLoginInfo();
		}
	}
}

function login(){
	console.log("in login()");
	
	let username = $("#login-username").val();
	let password = $("#login-password").val();
	
	let credentials = [username, password];
	let credentialsJSON = JSON.stringify(credentials);

	let xhr = new XMLHttpRequest();
	
	xhr.open('POST', 'login', true);
	xhr.send(credentialsJSON);
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			let user = JSON.parse(xhr.responseText);
			console.log(user);
			if (user) {
				alert('load successful');
				window.localStorage.setItem('user', xhr.responseText);
				loadHome();
				console.log(`user id: ${user.id} login successful`);
			} else {
				$("#login-message").show().html('Invalid credentials');
			}
		}
	}
}

function loadRegister() {
	console.log('in loadRegister()');
	
	let xhr = new XMLHttpRequest();
	
	xhr.open('GET', 'register.view', true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadRegisterInfo();
		}
	}
}

function loadHome() {
	console.log('in loadHome()');
	
	let xhr = new XMLHttpRequest();
	
	xhr.open('GET', 'home.view', true);
	xhr.send();
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			document.getElementById('view').innerHTML = xhr.responseText;
			loadHomeInfo();
		}
	}
}

function loadHomeInfo(){
	console.log('in loadHomeInfo()');
	
	let userType = JSON.parse(window.localStorage.getItem('user'));
	/*
	let ticket = {
			id: '',
			amount: '',
			submitted: '',
			resolved: '',
			description: '',
			receipt: '',
			author: '',
			resolver: '',
			status_id: '',
			type_id: ''
			
	}
	
	let ticketList = {
			
	}/**/
	
	// Check the active user's type and show only the appropriate table.
	if (userType.user_type == 2){
		//$("#employee-view").hide();
		
	} else {
		//$("#manager-view").hide();
		viewById(userType)
		viewAll();
		$(".table").on('click','tr',function(e){
		    e.preventDefault();
		    var id = $(this).children();
		    console.log(id[1].innerHTML)
		}); 
	}/**/
}


function viewById(userType) 
{
	console.log("viewById()")
	console.log(userType.ers_users_id)
	let sender = userType.ers_users_id;
	
	
	
	let xhr = new XMLHttpRequest();
	
	xhr.open('POST', "viewByAuthor", true)
	
	xhr.send(sender);
	
	xhr.onreadystatechange = function()
	{
		if (xhr.readyState == 4 && xhr.status == 200)
		{
			a = JSON.parse(xhr.responseText);
			for(let i = 0; i<a.length; i++)
			{
				console.log(a[i])
				console.log(a.amount)
				let r = "<tr>" +
							"<td id='id'>" + a[i].id + "</td>" +
							"<td id='description'>" + a[i].description + "</td>" +
							"<td id = 'amount'>" + a[i].amount + "</td>" +
							"<td id = 'resolver'>" + a[i].resolver + "</td>" +
							"<td id = 'recipt'>" + a[i].recipt + "</td>" +
							"<td id = 'author' >" + a[i].author + "</td>" +
							"<td id = 'type'>" + a[i].type + "</td>" +
							"<td id = 'submitted>" + a[i].submitted + "</td>" +
							"<td id = 'resolved'>" + a[i].resolved + "</td>" +
							"<td id = 'status'>" + a[i].status + "</td>" +
							"</tr>"
				
				$('#employee-body').append(r);	
								
			}
				
		}
	}
	
}

function loadProfile() {
	console.log('in loadProfile()');
}

function loadLoginInfo() {
	console.log('in loadLoginInfo()');
	
	$('#login-message').hide();
	$("#login").on('click', login);
	$('#toRegisterBtn').on('click', loadRegister);
}

function logout() {
	console.log('in logout()');
	
	window.localStorage.removeItem('user');
	
	let xhr = new XMLHttpRequest;
	xhr.open('GET', 'logout',true);
	xhr.send();
	xhr.onreadystatechange = function (){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log('Session has been invalidated!');
			loadLogin();
		}
	}
}

function loadRegisterInfo() {
	console.log('in loadRegisterInfo()');
	
	$('#reg-message').hide();
	
	$('#fn').blur(isRegisterFormValid);
	$('#ln').blur(isRegisterFormValid);
	$('#email').blur(isRegisterFormValid);
	$('#reg-username').blur(isRegisterFormValid);
	$('#reg-password').blur(isRegisterFormValid);
	
	$('#reg-username').blur(validateUsername); // same as document.getElementById('reg-username').addEventListener('blur', function, boolean);
	$('#email').blur(validateEmail);
	
	$('#register').attr('disabled', true);
	$('#register').on('click', register);
	
}

function isRegisterFormValid() {
	let form = [
		$('#fn').val(), 
		$('#ln').val(), 
		$('#email').val(), 
		$('#reg-username').val(), 
		$('#reg-password').val()
	];
	
	if(!(form[0] && form[1] && form[2] && form[3] && form[4])) $('#register').attr('disabled', true);
	else $('#register').attr('disabled', false);
}

function validateUsername() {
	console.log('in validateUsername()');
	
	let username = $('#reg-username').val();
	console.log(username);
	
	if(username !== '') {
		let usernameJSON = JSON.stringify(username);
		let xhr = new XMLHttpRequest();
		
		xhr.open('POST', 'username.validate', true);
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.send(usernameJSON);
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200) {
				console.log(xhr.responseText);
				let username = JSON.parse(xhr.responseText);
				if(!username) {
					$('#reg-message').show();
					$('#reg-message').html('Username is already in use! Please try another!');
					$('#register').attr('disabled', true);
				} else {
					$('#reg-message').hide();
				}
				
			}
		}
	}
}

function validateEmail() {
	console.log('in validateEmail()' + 'this is the value' + $('#email').val());
	
	let email = $('#email').val();
	
	if(email) {
		let emailJSON = JSON.stringify(email);
		let xhr = new XMLHttpRequest();
		
		xhr.open('POST', 'email.validate', true);
		xhr.setRequestHeader('Content-type', 'application/json');
		xhr.send(emailJSON);
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200) {
				console.log(xhr.responseText);
				let email = JSON.parse(xhr.responseText);
				if(!email) {
					$('#reg-message').show();
					$('#reg-message').html('Email is already in use! Please try another!');
					$('#register').attr('disabled', true);
				} else {
					$('#reg-message').hide();
				}
			}
		}
	}
}

function register() {
	console.log('in register()');
	
	$('#register').attr('disabled', true);
	let key;
	if($('#manager').val()=="key")
	{
		key = 2;
	}else
	{
		key = 1;
	}
	console.log(key+" this is the key");
	let user = {
			ers_users_id: 7,
			user_first_name: $('#fn').val(),
			user_last_name: $('#ln').val(),
			user_email: $('#email').val(),
			ers_username: $('#reg-username').val(),
			ers_password: $('#reg-password').val(),
			user_type: key
	}
	
	let userJSON = JSON.stringify(user);
	console.log(userJSON);
	let xhr = new XMLHttpRequest();
	
	xhr.open('POST', 'register', true);
	xhr.send(userJSON);
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			if(!xhr.responseText) {
				$('#message').show().html('Something went wrong...');
			} else {
				$('#message').hide();
				alert('Enrollment successful! Please login using your credentials.');
				loadLogin();
			}
		}
	}
}

function createTicket(){
	let authUser = JSON.parse(window.localStorage.getItem('user'));
	
	let ticket = {
		// Reminder: DB object has 9 fields
		amount: $('#amount').val(),
		description: $('#description').val(),
		author: authUser.ers_users_id,
		type: $('#type').val()
	}/**/
	console.log(ticket);
	
	let ticketJSON = JSON.stringify(ticket);
	let xhr = new XMLHttpRequest();
	
	xhr.open('POST', 'reimbursementservlet', true);
	console.log(ticketJSON);
	xhr.send(ticketJSON);
	
	
	// Does nothing yet, but needs to handle ReimbursementServlet response.
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			if(!xhr.responseText){
				console.log("We've encountered  an error...");		
				console.log(xhr.responseText);		
			} else {
				
				console.log("Update successful!");
				console.log(xhr.responseText);
			}
		} 
	}
}

function approveTicket(){
	let ticket = {
			
	}
}

function denyTicket(){
	
}