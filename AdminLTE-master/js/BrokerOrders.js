$(function(){
	$(document).ready(function(){
		let b_name = sessionStorage.getItem('b_name');
		$('#b_name').html(b_name);
		console.log(b_name);
		if(b_name){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/order/Order/getOdersByBroker',
				data: {
					b_name : b_name
				},
				success: function (json) {
					if (json.success){
						$('#Orders').empty();
						for (let i = 0; i <json.content.list.length; i++) {
							if(json.content.list[i].state == "已签字确认"){
								let order = json.content.list[i];
								let o_id = order.o_id;
								let template =
										`<tr>
											<td>${order.o_id}</td>
											<td>${order.i_name}</td>
											<td>${order.u_username}</td>
											<td><span class="tag tag-primary">${order.e_time}</span></td>
											<td>${order.state}</td>
											<td><button id="detail11" onclick="detail('${order.o_id}')">详情</button>
												<button type="button" data-toggle="modal" data-target="#list-delete" id="delete" onclick="myfunction('${order.o_id}')">删除</button>
												<button type="button" data-toggle="modal" data-target="#list-confirm" id="confirm" onclick="myfunction('${order.o_id}')">购买订单</button>
											</td>
										</tr>
									`;
								$('#Orders').append(template);
							}else{
								let order = json.content.list[i];
								let o_id = order.o_id;
								let template =
										`<tr>
											<td>${order.o_id}</td>
											<td>${order.i_name}</td>
											<td>${order.u_username}</td>
											<td><span class="tag tag-primary">${order.e_time}</span></td>
											<td>${order.state}</td>
											<td><button id="detail11" onclick="detail('${order.o_id}')">详情</button>
												<button type="button" data-toggle="modal" data-target="#list-delete" id="delete" onclick="myfunction('${order.o_id}')">删除</button>
												<button type="button" style="display:none" data-toggle="modal" data-target="#list-confirm" id="confirm" onclick="myfunction('${order.o_id}')">购买订单</button>
											</td>
										</tr>
									`;
								$('#Orders').append(template);
							}
							
						}
					}
				}
			});
		}	
				
		$('#submit').click(function(){
			let input = $('#search').val();
			if(input.length == 0){
				$.ajax({
					type: 'POST',
					url: 'http://localhost:8989/order/Order/getOdersByUser',
					data: {
						u_username : u_username
					},
					success: function (json) {
						if (json.success){
							$('#Orders').empty();
							for (let i = 0; i <json.content.list.length; i++) {
								let order = json.content.list[i];
								let o_id = order.o_id;
								let template =
										`<tr>
											<td>${order.o_id}</td>
											<td>${order.i_name}</td>
											<td>${order.b_e_username}</td>
											<td><span class="tag tag-primary">${order.e_time}</span></td>
											<td>${order.state}</td>
											<td><button style="margin-right: 20px;" id="detail11" onclick="detail('${order.o_id}')">详情</button>
												<button style="margin-right: 20px;" type="button" data-toggle="modal" data-target="#list-delete" id="delete" onclick="myfunction('${order.o_id}')">删除</button>
												<button type="button" data-toggle="modal" data-target="#list-confirm" id="confirm" onclick="myfunction('${order.o_id}')">购买订单</button>
											</td>
										</tr>
									`;
								$('#Orders').append(template);
							}
						}
					}
				});
			}else{
				window.location.reload(true);
			}
		});
		

		$('#test').click(function(){
			let u_username = sessionStorage.getItem('u_username');
			console.log(u_username)
			location.href = '../pages/detali.html';
		})
	});
});

function myfunction(o_id){
	let ID = o_id;
	sessionStorage.setItem('O_ID', ID);
	console.log(ID);
};

$('#deleteOrder').click(function(){
	let o_id = sessionStorage.getItem('O_ID');
	console.log(o_id);
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/order/Order/deldeteOrder',
		data: {
			o_id : o_id
		},
		success: function (json) {
			window.location.reload(true);
			alert('删除成功');
		}
	})
});

$('#confirm').click(function(){
	let o_id = sessionStorage.getItem('O_ID');
	console.log(o_id);
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/order/Order/buyOrder',
		data: {
			o_id : o_id
		},
		success: function (json) {
			window.location.reload(true);
			alert(json.message)
		}
	})
});

function detail(o_id){
	let ID = o_id;
	sessionStorage.setItem('O_ID', ID);
	if(o_id){
		location.href = '../pages/detali.html';
	}else{
		alert('未选中ID')
	}
}



