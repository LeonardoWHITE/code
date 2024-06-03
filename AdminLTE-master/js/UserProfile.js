$(function(){
	$(document).ready(function(){
		let u_username = sessionStorage.getItem('u_username');
		$('#u_username').html(u_username);
		console.log(u_username)
		if(u_username){
			//查询用户信息
			$.ajax({
				type: 'POST',
				url: 'http://localhost:8989/user/user/getUser',
				data: {
					u_username : u_username
				},
				success: function (json) {
					let u_phone = json.u_phone;
					let u_career = json.u_career;
					let u_age = json.u_age;
					let u_address = json.u_address;
					$('#u_phone').html(u_phone)
					$('#u_career').html(u_career)
					$('#u_age').html(u_age)
					$('#u_address').html(u_address)
				}
			});
			
		}else{
			$('#u_username').html('未登录状态');
			$('#u_phone').html('未知')
			$('#u_career').html('未知')
			$('#u_age').html('未知')
			$('#u_address').html('未知')
		}
	});
	
	$('#update').click(function(){
		let u_username = sessionStorage.getItem('u_username');
		if(u_username == null){
			return alert('没有登录怎么能修改呢');
		}else{
			location.href = '../pages/UserUpdate.html';
		}
	});
	
	$('#test').click(function(){
	alert('yes')
	});
})

window.alert = function(msg){
		var maskBg = '#0000002b';						//蒙版展示色
		var zIndex = 999999;							//修改弹出层z-index,应为最顶层,避免被覆盖
		var desColor = '#1f0000'						//提示信息字体颜色
		var buttonVal = '确定';							//确定按钮名称
		var btnBgColor = '#1792f6';						//确定按钮背景颜色
		var btnColor = '#fff';							//确定按钮字体颜色
		var btnAlign = 'center';							//按钮在水平位置,默认居中,变量值:left,center,right
		var style = `
			<style class="mask-style">
				.box-sizing{
					box-sizing: border-box;
				}
				.alertMask{
					position: fixed;	/*生成绝对定位的元素，相对于浏览器窗口进行定位*/
					display: flex;
					display: webkit-flex;
					flex-direction: row;
					align-items: center;
					justify-content: center;
					width: 100%;
					height: 50%;
					top: 0;
					left: 0;
					z-index: `+zIndex+`;
				}
				.alertContainer{
					min-width: 240px;	/*容器最小240px*/
					max-width: 320px;	/*容器最大320px*/
					background:#fff;
					border: 1px solid `+maskBg+`;
					border-radius: 3px;
					color: `+desColor+`;
					overflow: hidden;
				}
				.alertDes{
					padding: 35px 30px;
					text-align: center;
					letter-spacing: 1px;
					font-size: 14px;
					color: `+desColor+`;
				}
				.alertDes img{
					max-width: 100%;
					height: auto;
				}
				.alertConfirmParent{
					width: 100%;
					padding: 10px 30px;
					text-align: `+btnAlign+`;
					box-sizing: border-box;
					background: #f2f2f2;
				}
				.alertConfirmBtn{
					cursor: pointer;
					padding: 1px 10px;
					border: none;
					border-radius: 2px;
					color: `+btnColor+`;
					background-color: `+btnBgColor+`;
					box-shadow: 0 0 2px `+btnBgColor+`;
				}
			</style>
		`;
 
		var head = document.getElementsByTagName('head')[0];
		head.innerHTML += style		//头部加入样式,注意不可使用document.write()写入文件,否则出错
 
		const body = document.getElementsByTagName('body')[0];
 
		var alertMask = document.createElement('div');
		var alertContainer = document.createElement('div');
		var alertDes = document.createElement('div');
		var alertConfirmParent = document.createElement('div');
		var alertConfirmBtn = document.createElement('button');
 
		body.append(alertMask);
		alertMask.classList.add('alertMask');
		alertMask.classList.add('box-sizing');
 
		alertMask.append(alertContainer);
		alertContainer.classList.add('alertContainer');
		alertContainer.classList.add('box-sizing');
 
		alertContainer.append(alertDes);
		alertDes.classList.add('alertDes');
		alertDes.classList.add('box-sizing');
 
		alertContainer.append(alertConfirmParent);
		alertConfirmParent.classList.add('alertConfirmParent');
		alertConfirmParent.classList.add('box-sizing');
 
		alertConfirmParent.append(alertConfirmBtn);
		alertConfirmBtn.classList.add('alertConfirmBtn');
		alertConfirmBtn.classList.add('box-sizing');
		alertConfirmBtn.innerText = buttonVal;
 
		//加载提示信息
		alertDes.innerHTML = msg;
		//关闭当前alert弹窗
		function alertBtnClick(){
			body.removeChild(alertMask);
			maskStyle = head.getElementsByClassName('mask-style')[0];
			head.removeChild(maskStyle);	//移除生成的css样式
 
		}
		alertConfirmBtn.addEventListener("click", alertBtnClick);
	}