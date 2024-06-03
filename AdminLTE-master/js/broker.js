$(function(){
	$(document).ready(function(){
		let u_username = sessionStorage.getItem('u_username');
		$('#u_username').html(u_username);
		console.log(u_username);
		if(u_username){
			//查询用户信息
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8989/broker/Broker/getAll',
				data: {
					
				},
				success: function (json) {
					if (json.success){
						$('#Broker').empty();
						for (let i = 0; i <json.content.brokers.length; i++) {
							let brokers = json.content.brokers[i];
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
						}
					}
				}
			});
			
			$('#submit').click(function(){
				let u_username = sessionStorage.getItem('u_username');
				if(u_username){
					$.ajax({
						type: 'POST',
						url: 'http://localhost:8989/broker/Broker/getBroker',
						data: {
							b_name : $('#search').val()
						},
						success: function (json) {
							if(json){
								$('#Broker').empty();
								let template =
									`<tr data-widget="expandable-table" aria-expanded="false">
									  <td>${json.b_id}</td>
									  <td>${json.b_realname}</td>
									  <td>${json.b_age}</td>
									  <td>${json.b_gender}</td>
									  <td>${json.b_phone}</td>
									  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-broker" id="delete" onclick="myfunction('${json.b_name}')" >选择</button></td>
									</tr>
									<tr class="expandable-body">
									  <td colspan="6">
										<p>
											${json.b_profile}
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

