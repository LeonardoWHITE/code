$(function(){
	let u_username = sessionStorage.getItem('u_username');
	$('#u_username').html(u_username);
	console.log(u_username);
	$(document).ready(function(){
		if(u_username){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/user/user/getUser',
				data: {
					u_username : u_username
				},
				success: function (json) {
					sessionStorage.removeItem('b_name')
					sessionStorage.setItem('b_name',json.manager);
					let b_name = sessionStorage.getItem('b_name')
					if(b_name != null){
						
					}
				}
			});
		}
		//查询用户信息
		let b_name = sessionStorage.getItem('b_name');
		console.log(b_name)
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/broker/Broker/getBroker',
			data: {
				b_name : sessionStorage.getItem('b_name')
			},
			success: function (json) {
				if(json){
					$('#UserBroker').empty();
					let template =
						`<tr data-widget="expandable-table" aria-expanded="false">
						  <td>${json.b_id}</td>
						  <td>${json.b_realname}</td>
						  <td>${json.b_age}</td>
						  <td>${json.b_gender}</td>
						  <td>${json.b_phone}</td>
						  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${json.b_name}')" >解除</button></td>
						</tr>
						<tr class="expandable-body">
						  <td colspan="6">
							<p>
								${json.b_profile}
							</p>
						  </td>
						</tr>
						`;
					$('#UserBroker').append(template);
				}else{
					
				}
			}
		});
	});
});

function myfunction(b_name){
	let B_NAME = b_name;
	sessionStorage.setItem('b_name', B_NAME);
};

$('#submit').click(function(){
	let u_username = sessionStorage.getItem('u_username');
	if(u_username){
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/function/Function/getInsurance',
			data: {
				i_id : $('#search').val()
			},
			success: function (json) {
				if(json){
					console.log(json.i_id);
					$('#Broker').empty();
					let template =
						`<tr data-widget="expandable-table" aria-expanded="false">
						  <td>${brokers.b_id}</td>
						  <td>${brokers.b_realname}</td>
						  <td>${brokers.b_age}</td>
						  <td>${brokers.b_gender}</td>
						  <td>${brokers.b_phone}</td>
						  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${brokers.b_name}')" >选择</button></td>
						</tr>
						<tr class="expandable-body">
						  <td colspan="6">
							<p>
								${brokers.b_profile}
							</p>
						  </td>
						</tr>
						`;
					$('#Broker').append(template);
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

$('#relieveUser').click(function(){
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
			sessionStorage.removeItem('b_name');
			if (sessionStorage.getItem("b_name") == null) {
				     window.location.reload(true);
				     alert('解除成功');
			}
		}
	})
});

