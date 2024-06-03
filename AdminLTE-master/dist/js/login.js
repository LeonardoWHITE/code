$(function(){
	$('.login').click(function(){
		let u_username = $('#u_username').val()
		let u_password = $('#u_password').val()
		if(u_username.length == 0){
			return alert('用户名不能为空');
		}else if(u_password.length == 0){
			return alert('密码不能为空');
		}else{
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/user/user/login',
				data: {
					u_username: u_username,
					u_password: u_password
				},
				success: function (json) {
					if (json.success) {
						sessionStorage.setItem('u_username', json.content.u_username);
						// location.href = 'bank-index.html';
						return alert('yes');
					} else {
						alert(json.message);
						// location.href = 'bank-login.html';
						return alert('no');
					}
				}
			});
		}
	})
})
	//点击与Login按钮
	// $('.login').click(function () {
	// 	// let check = document.getElementById('login_checkbox').checked;
	// 	if (check){
	// 		$.ajax({
	// 			type: 'POST',
	// 			url: '/bank/user/login',
	// 			data: {
	// 				username: $('#username').val(),
	// 				password: $('#password').val(),
	// 				code: $('#verifycode').val()
	// 			},
	// 			success: function (json) {
	// 				if (json.success) {
	// 					sessionStorage.setItem('username', json.content.username);
	// 					location.href = 'bank-index.html';
	// 				} else {
	// 					alert(json.message);
	// 					location.href = 'bank-login.html';
	// 				}
	// 			}
	// 		});
	// 	}else {
	// 		alert("Please read the details and check!!!");
	// 	}
	// });
	
	// $.ajax{
	// 	url:"http://localhost:8989/user/user/login",
	// 	data:{
	// 		username=username,
	// 		password=password
	// 	},
	// 	dataType:'json',//服务器返回json格式数据
	// 	type:'post',//HTTP请求类型
	// 	timeout:10000,//超时时间设置为10秒；
		
	// 	success:function(data){
	// 		console.log(data);
	// 	},
	// 	error:function(xhr,type,errorThrown){
	// 		console.log('er'');
	// 	}
	// };