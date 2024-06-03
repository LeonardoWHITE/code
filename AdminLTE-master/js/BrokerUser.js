$(function(){
	$(document).ready(function(){
		let b_name = sessionStorage.getItem('b_name');
		$('#b_name').html(b_name);
		console.log(b_name);
		if(b_name){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/user/user/getUserByBroker',
				data: {
					manager : b_name
				},
				success: function (json) {
					if(json){
						$('#BrokerUser').empty();
						for (let i = 0; i <json.content.userByBroker.length; i++) {
							let user = json.content.userByBroker[i];
							let template =
								`<tr data-widget="expandable-table" aria-expanded="false">
								  <td>${user.u_id}</td>
								  <td>${user.u_username}</td>
								  <td>${user.u_age}</td>
								  <td>${user.u_career}</td>
								  <td>${user.u_address}</td>
								  <td>${user.u_phone}</td>
								  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${user.u_username}')" >解除</button></td>
								</tr>
								`;
							$('#BrokerUser').append(template);
						}
						
					}else{
						alert('查询失败');
					}
				}
			});
			
			$('#submit').click(function(){
				let b_name = sessionStorage.getItem('b_name');
				$('#b_name').html(b_name);
				if(b_name){
					$.ajax({
						type: 'POST',
						url: 'http://localhost:8989/user/user/getUserByBroker',
						data: {
							manager : b_name
						},
						success: function (json) {
							if(json){
								for (let i = 0; i <json.content.userByBroker.length; i++) {
									let user = json.content.userByBroker[i];
									$('#BrokerUser').empty();
									let template =
										`<tr data-widget="expandable-table" aria-expanded="false">
										  <td>${user.u_id}</td>
										  <td>${user.u_username}</td>
										  <td>${user.u_age}</td>
										  <td>${user.u_career}</td>
										  <td>${user.u_address}</td>
										  <td>${user.u_phone}</td>
										  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${user.u_username}')" >解除</button></td>
										</tr>
										`;
									$('#BrokerUser').append(template);
								}
							}else{
								alert('查询失败');
							}
						}
					});
				}else{
					window.location.reload(true);
				}
			});
		}	
	});
});

function myfunction(u_username){
	let U_username = u_username;
	sessionStorage.setItem('u_username', U_username);
};

$('#relieveManager').click(function(){
	let b_name = sessionStorage.getItem('b_name');
	let u_username = sessionStorage.getItem('u_username');
	console.log(b_name);
	console.log(u_username)
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/user/user/relieveBroker',
		data: {
			u_username : u_username,
		},
		success: function (json) {
			sessionStorage.removeItem('u_username');
			window.location.reload(true);
			alert('解除成功');
		}
	})
});

