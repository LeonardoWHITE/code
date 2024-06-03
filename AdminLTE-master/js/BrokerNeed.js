$(function(){
	$(document).ready(function(){
		let b_name = sessionStorage.getItem('b_name');
		$('#b_name').html(b_name);
		console.log(b_name);
		if(b_name){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/needs/Needs/selectNeedByb_id',
				data: {
					b_id : sessionStorage.getItem('b_id')
				},
				success: function (json) {
					if (json.success){
						$('#UserNeed').empty();
						for (let i = 0; i <json.content.NeedsList.length; i++) {
							let needs = json.content.NeedsList[i];
							let template =
								`<tr data-widget="expandable-table" aria-expanded="false">
								  <td>${needs.n_id}</td>
								  <td>${needs.u_username}</td>
								  <td>${needs.u_id}</td>
								  <td>${needs.date}</td>
								  <td>
								  	<button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#FeedBack" id="delete" onclick="myfunction('${needs.n_id}')" >反馈需求</button>
								  </td>
								</tr>
								<tr class="expandable-body">
								  <td colspan="6">
									<p>
										<span style="color: red;">需求中心：</span>${needs.needs}
									</p>
									<p>
										<span style="color: red;">需求反馈：</span>${needs.feedback}
									</p>
								  </td>
								</tr>
								`;
							$('#UserNeed').append(template);
						}
					}
				}
			});
			
			//搜索框
			
		}	
	});
});

function myfunction(n_id){
	sessionStorage.setItem('n_id',n_id);
	console.log(n_id);
};

// function Addfunction(b_name){
// 	let B_NAME = b_name;
// 	sessionStorage.setItem('b_name', B_NAME);
// };

$('#AddOrder').click(function(){
	let b_name = sessionStorage.getItem('b_name');
	let u_username = $('#username').val()
	console.log(u_username)
	let b_e_username = $('#b_e_username').val()
	let b_e_phone = $('#b_e_phone').val()
	let i_id = $('#i_id').val()
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/order/Order/AddOrder',
		data: {
			u_username : u_username,
			b_name : b_name,
			b_e_username : b_e_username,
			b_e_phone : b_e_phone,
			i_id : i_id
		},
		success: function (json) {
			if(json.success){
				window.location.reload(true);
				alert("订单成功")
			}
		}
	})
});

$('#Feedback').click(function(){
	let b_name = sessionStorage.getItem('b_name');
	let backTEXT = $('#backTEXT').val()
	if(b_name != null){
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/needs/Needs/feedbackNeed',
			data: {
				n_id : sessionStorage.getItem('n_id'),
				feedback : backTEXT
			},
			success: function (json) {
				window.location.reload(true);
				alert('反馈成功');
			}
		})
	}
});


$('#submit').click(function(){
	let u_id = $('#search').val()
	console.log(u_id)
	if(u_id.length != 0){
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/needs/Needs/selectNeedByu_id',
			data: {
				u_id : u_id,
				b_name : sessionStorage.getItem('b_name')
			},
			success: function (json) {
				if (json.success){
					$('#UserNeed').empty();
					for (let i = 0; i <json.content.NeedsList.length; i++) {
						let needs = json.content.NeedsList[i];
						let template =
							`<tr data-widget="expandable-table" aria-expanded="false">
							  <td>${needs.n_id}</td>
							  <td>${needs.u_username}</td>
							  <td>${needs.u_id}</td>
							  <td>${needs.date}</td>
							  <td>
								<button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#FeedBack" id="delete" onclick="myfunction('${needs.n_id}')" >反馈需求</button>
							  </td>
							</tr>
							<tr class="expandable-body">
							  <td colspan="6">
								<p>
									<span style="color: red;">需求中心：</span>${needs.needs}
								</p>
								<p>
									<span style="color: red;">需求反馈：</span>${needs.feedback}
								</p>
							  </td>
							</tr>
							`;
						$('#UserNeed').append(template);
					}
				}
			}
		});
	}else{
		window.location.reload(true);
		alert('请输入想搜索的经纪人ID')
	}
	
});

