$(function(){
	$(document).ready(function(){
		let o_id = sessionStorage.getItem('O_ID');
		$('o_id').html(o_id);
		console.log(o_id)
		if(o_id){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/order/Order/getOdersByID',
				data: {
					o_id : o_id
				},
				success: function (json) {
					$('#u_username').html(json.content.list[0].u_username)
					$('#b_name').html(json.content.list[0].b_name)
					$('#o_id').html(json.content.list[0].o_id)
					$('#i_id').html(json.content.list[0].i_id)
					$('#i_price').html(json.content.list[0].i_price)
					$('#i_name').html(json.content.list[0].i_name)
					$('#b_e_username').html(json.content.list[0].b_e_username)
					$('#e_time').html(json.content.list[0].e_time)
					$('#b_time').html(json.content.list[0].b_time)
					$('#b_e_phone').html(json.content.list[0].b_e_phone)
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