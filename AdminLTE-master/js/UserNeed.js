$(function(){
	$(document).ready(function(){
		let u_username = sessionStorage.getItem('u_username');
		let b_name = sessionStorage.getItem('b_name');
		$('#u_username').html(u_username);
		console.log(u_username);
		console.log(b_name);
		if(u_username){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/needs/Needs/UserGetNeed',
				data: {
					u_username : u_username
				},
				success: function (json) {
					if (json.success){
						$('#UserNeed').empty();
						for (let i = 0; i <json.content.needsList.length; i++) {
							let needs = json.content.needsList[i];
							let template =
								`<tr data-widget="expandable-table" aria-expanded="false">
								  <td>${needs.n_id}</td>
								  <td>${needs.u_username}</td>
								  <td>${needs.b_name}</td>
								  <td>${needs.b_id}</td>
								  <td>${needs.date}</td>
								  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-Needs" id="delete" onclick="myfunction('${needs.n_id}')" >删除需求</button></td>
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

$('#confirm').click(function(){
	let u_username = sessionStorage.getItem('u_username');
	location.href = '../pages/continued.html';
});

$('#deleteNeed').click(function(){
	let n_id = sessionStorage.getItem('n_id');
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/needs/Needs/delectNeed',
		data: {
			n_id : n_id
		},
		success: function (json) {
			if(json.success){
				window.location.reload(true);
				alert("删除成功")
			}
		}
	})
});

$('#ConfirmAdd').click(function(){
	let u_username = sessionStorage.getItem('u_username');
	let b_name = sessionStorage.getItem('b_name');
	let needs = $('#AddText').val()
	if(b_name != null){
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/needs/Needs/AddNeeds',
			data: {
				u_username : u_username,
				b_name : b_name,
				needs : needs
			},
			success: function (json) {
				window.location.reload(true);
				alert('提交成功');
			}
		})
	}
});


$('#submit').click(function(){
	let b_id = $('#search').val()
	console.log(b_id)
	if(b_id.length != 0){
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/needs/Needs/selectNeedByb_id',
			data: {
				b_id : b_id
			},
			success: function (json) {
				if (json.success){
					$('#UserNeed').empty();
					for (let i = 0; i <json.content.NeedsList.length; i++) {
						let needs = json.content.NeedsList[i];
						console.log(needs)
						let template =
							`<tr data-widget="expandable-table" aria-expanded="false">
							  <td>${needs.n_id}</td>
							  <td>${needs.u_username}</td>
							  <td>${needs.b_name}</td>
							  <td>${needs.b_id}</td>
							  <td>${needs.date}</td>
							  <td><button style="margin-right: 0px;" type="button" data-toggle="modal" data-target="#list-Needs" id="delete" onclick="myfunction('${needs.n_id}')" >删除需求</button></td>
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

