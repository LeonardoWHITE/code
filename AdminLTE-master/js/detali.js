$(function(){
	$(document).ready(function(){
		let u_username = sessionStorage.getItem('u_username');
		let o_id = sessionStorage.getItem('O_ID')
		if(o_id){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/order/Order/getOdersByID',
				data: {
					o_id : o_id
				},
				success: function (json) {
					$('#u_username').html(json.content.list.u_username)
					$('#b_name').html(json.content.list.b_name)
					$('#o_id').html(json.content.list.o_id)
					$('#i_id').html(json.content.list.i_id)
					$('#i_price').html(json.content.list.i_price)
					$('#i_name').html(json.content.list.i_name)
					$('#b_e_username').html(json.content.list.b_e_username)
					$('#e_time').html(json.content.list.e_time)
					$('#b_time').html(json.content.list.b_time)
					$('#b_e_phone').html(json.content.list.b_e_phone)
					if(json.content.list.state == "未签字确认"){
						$('#state').html(json.content.list.state + ('(本合同不生效)'));
					}else{
						$('#state').html(json.content.list.state)
						var t = document.getElementById('test');
						t.style.display = 'none';
					}
				}
			});
			
		}else{
			alert('no')
		}
	});
});

// function preview() {
// 	body = window.document.body.innerHTML;
// 	startpoint= "<!--startpoint-->";
// 	endpoint= "<!--endpoint-->";
// 	printdb= body.substring(body.indexOf(startpoint) + 17); 
// 	printdb = printdb.substring(0, printdb.indexOf(endpoint));
// 	window.document.body.innerHTML = printdb ;
// 	window.print();
// 	window.document.body.innerHTML = body ;
// }



$('#back').click(function(){
	let ID = o_id;
	sessionStorage.setItem('O_ID', ID);
	location.href = '../pages/UserOrders.html';
});

$('#confirm').click(function(){
	let confirm = $('#confirmTEXT').val()
	let u_username = sessionStorage.getItem('u_username');
	$.ajax({
		type: 'POST',
		url: 'http://localhost:8989/order/Order/confirm',
		data: {
			confirm : confirm,
			u_username : sessionStorage.getItem('u_username'),
			o_id : sessionStorage.getItem('O_ID')
		},
		success: function (json) {
			alert(json.message);
			window.location.reload(true);
		}
	});
});