$(function(){
	$(document).ready(function(){
		let u_username = sessionStorage.getItem('u_username');
		let b_name = sessionStorage.getItem('b_name');
		$('#u_username').html(u_username);
		$('#b_name').html(b_name);
		console.log(u_username);
		if(u_username || b_name){
			//查询用户信息
			$.ajax({
				type: 'GET',
				url: 'http://localhost:8989/function/Function/getAll',
				data: {
					
				},
				success: function (json) {
					if (json.success){
						$('#Insurance').empty();
						for (let i = 0; i <json.content.functionsList.length; i++) {
							let insurance = json.content.functionsList[i];
							let i_id = insurance.i_id;
							let template =
								`<tr data-widget="expandable-table" aria-expanded="false">
								  <td>${insurance.i_id}</td>
								  <td>${insurance.i_name}</td>
								  <td>${insurance.i_price}</td>
								  <td>${insurance.i_time}个月</td>
								  <td>${insurance.i_profile}</td>
								</tr>
								<tr class="expandable-body">
								  <td colspan="5">
									<p>
										${insurance.i_detali}
									</p>
								  </td>
								</tr>
								`;
							$('#Insurance').append(template);
						}
					}
				}
			});
			
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
								$('#Insurance').empty();
								let template =
									`<tr data-widget="expandable-table" aria-expanded="false">
									  <td>${json.i_id}</td>
									  <td>${json.i_name}</td>
									  <td>${json.i_price}</td>
									  <td>${json.i_time}个月</td>
									  <td>${json.i_profile}</td>
									</tr>
									<tr class="expandable-body">
									  <td colspan="5">
										<p>
											${json.i_detali}
										</p>
									  </td>
									</tr>
									`;
								$('#Insurance').append(template);
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

$('#type').click(function(){
	let i_profile = $("#InsuranceType option:selected").val();
	console.log(i_profile)
	if(i_profile){
		$.ajax({
			type: 'POST',
			url: 'http://localhost:8989/function/Function/InsuranceType',
			data: {
				i_profile : i_profile
			},
			success: function (json) {
				console.log(json.success)
				if(json.success){
					$('#Insurance').empty();
					for (let i = 0; i < json.content.functionList.length; i++) {
						let insurance = json.content.functionList[i];
						let template =
							`<tr data-widget="expandable-table" aria-expanded="false">
							  <td>${insurance.i_id}</td>
							  <td>${insurance.i_name}</td>
							  <td>${insurance.i_price}</td>
							  <td>${insurance.i_time}个月</td>
							  <td>${insurance.i_profile}</td>
							</tr>
							<tr class="expandable-body">
							  <td colspan="5">
								<p>
									${insurance.i_detali}
								</p>
							  </td>
							</tr>
							`;
						$('#Insurance').append(template);
					}
				}
			}
		})
	}
})
