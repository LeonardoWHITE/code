$(function(){
	$(document).ready(function(){
		let b_name = sessionStorage.getItem('b_name');
		$('#b_name').html(b_name);
		console.log(b_name);
		if(b_name){
			//查询用户信息
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8989/user/user/getAll',
				data: {
					
				},
				success: function (json) {
					if (json.success){
						$('#User').empty();
						for (let i = 0; i <json.content.userList.length; i++) {
							let userList = json.content.userList[i];
							let template =
								`<tr data-widget="expandable-table" aria-expanded="false">
								  <td>${userList.u_id}</td>
								  <td>${userList.u_username}</td>
								  <td>${userList.u_age}</td>
								  <td>${userList.u_career}</td>
								  <td>${userList.u_phone}</td>
								  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${userList.u_username}')" >选择</button></td>
								</tr>
								`;
							$('#User').append(template);
						}
					}
				}
			});
			
			$('#submit').click(function(){
				let b_name = sessionStorage.getItem('b_name');
				if(b_name){
					$.ajax({ 
						type: 'POST',
						url: 'http://localhost:8989/user/user/getUser',
						data: {
							u_username : $('#search').val()
						},
						success: function (json) {
							if(json){
								$('#User').empty();
								let template =
									`<tr data-widget="expandable-table" aria-expanded="false">
									  <td>${json.u_id}</td>
									  <td>${json.u_username}</td>
									  <td>${json.u_age}</td>
									  <td>${json.u_career}</td>
									  <td>${json.u_phone}</td>
									  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${json.u_username}')" >选择</button></td>
									</tr>
									`;
								$('#User').append(template);
							}else{
								alert('查询失败');
								window.location.reload(true);
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

function myfunction(b_name){
	let B_NAME = b_name;
	sessionStorage.setItem('b_name', B_NAME);
};

$('#pickbroker').click(function(){
	let b_name = sessionStorage.getItem('b_name');
	let u_username = sessionStorage.getItem('u_username');
	console.log(b_name);
	console.log(u_username)
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/user/user/PickBroker',
		data: {
			u_username : u_username,
			manager : b_name
		},
		success: function (json) {
			window.location.reload(true);
			alert('选择成功');
		}
	})
});

