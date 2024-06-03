$(function(){
	$(document).ready(function(){
		let b_name = sessionStorage.getItem('b_name');
		let b_id = sessionStorage.getItem('b_id');
		console.log(b_name);
		console.log(b_id);
		$('#b_name').html(b_name);
		if(b_name){
			//图书类型
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8989/function/Function/NumberOfInsurance',
				data: {
					
				},
				success: function (json) {
					if (json.success) {
						let Insurance = json.content.number
						$('#Insurance').html(Insurance)
					}
				}
			});
			//管理订单
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/order/Order/NumberOfOrdersB',
				data: {
					b_name : b_name
				},
				success: function (json) {
					if (json.success) {
						let orders = json.content.number;
						$('#Orders').html(orders)
					}
				}
			});
			//用户
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8989/user/user/NumberOfUsers',
				data: {
					
				},
				success: function (json) {
					if (json.success) {
						let User = json.content.number
						$('#User').html(User)
					}
				}
			});
			//需求
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/needs/Needs/selectNeedByb_id',
				data: {
					b_id : b_id
				},
				success: function (json) {
					if (json.success) {
						let needs = json.content.NeedsList.length
						$('#Needs').html(needs)
					}
				}
			});
		}else{
			$('#b_name').html('您未登录，请登录')
		}
		
		// if(u_username){
		// 	$.ajax({
		// 		type: 'GET',
		// 		url: '/bank/user/userDetails',
		// 		data: {
		// 			username: username
		// 		},
		// 		success: function (json) {
		// 			if (json.success) {
		// 				$('#login').html(json.content.user.showname).attr('href','bank-personal.html');
		// 				$('#register').addClass('hidden');
		// 				$('#logout').removeClass('hidden');
		// 				$('#user-showname').html(username)
		// 			}
		// 		}
		// 	});
		// }
	});
	//退出登录
	$('#logout').click(function(){
		sessionStorage.removeItem('b_name');
		let b_name = sessionStorage.getItem('b_name')
		if(b_name == null){
			alert('退出成功')
			location.href = '../pages/Brokerlogin.html';
		}
	})
	
})

