$(function(){
	$(document).ready(function(){
		let u_username = sessionStorage.getItem('u_username');
		console.log(u_username);
		$('#u_username').html(u_username);
		if(u_username){
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
			//借阅信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/order/Order/NumberOfOrders',
				data: {
					u_username : u_username
				},
				success: function (json) {
					if (json.success) {
						let orders = json.content.number;
						$('#Orders').html(orders)
					}
				}
			});
			//保险经纪人
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8989/broker/Broker/NumberOfBroker',
				data: {
					
				},
				success: function (json) {
					if (json.success) {
						let broker = json.content.number
						$('#Broker').html(broker)
					}
				}
			});
			//需求
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/needs/Needs/NumberOfNeeds',
				data: {
					u_username : u_username
				},
				success: function (json) {
					if (json.success) {
						let needs = json.content.number
						$('#Needs').html(needs)
					}
				}
			});
		}else{
			$('#u_username').html('您未登录，请登录')
		}
	});
	//退出登录
	$('#logout').click(function(){
		sessionStorage.removeItem('u_username');
		let u_username = sessionStorage.getItem('u_username')
		if(u_username == null){
			alert('退出成功')
			location.href = '../pages/login.html';
		}
	})
	
})

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

$('#Submit').click(function(){
	choose_career();
	choose_healthy()
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/function/Function/recommend',
		data: {
			career : sessionStorage.getItem('choose_career'),
			age : sessionStorage.getItem('u_age'),
			healthy : sessionStorage.getItem('choose_healthy')
		},
		success: function (json) {
			alert(json.message);
			var array=new Array();
			for (let i = 0; i <json.content.functionList.length; i++) {
				array.push(json.content.functionList[i].i_name)
			}
			alert('系统推荐保险为：' + array + '\n' +
					'注意：仅为系统推荐保险，具体情况请联系保险经纪人进行深入了解')
		}
	})
});

function choose_career(){
	let career = document.getElementsByName('r1');
	let choose_career;	
	if(career[0].checked){
		choose_career = career[0].value
	}else if(career[1].checked){
		choose_career = career[1].value;
	}else if(career[2].checked){
		choose_career = career[2].value;
	}else if(career[3].checked){
		choose_career = career[3].value;
	}else {
		choose_career = career[4].value;
	}
	sessionStorage.setItem('choose_career',choose_career)
}

function choose_healthy(){
	let healthy = document.getElementsByName('r3');
	let choose_healthy;	
	if(healthy[0].checked){
		choose_healthy = healthy[0].value
	}else if(healthy[1].checked){
		choose_healthy = healthy[1].value;
	}else{
		choose_healthy = healthy[2].value;
	}
	sessionStorage.setItem('choose_healthy',choose_healthy)
}