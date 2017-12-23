var RB = {};

RB.alert = function (confirm, message, url){
	if(confirm){
		$('#alertHead').html('恭喜');
		$('#alertdb').html(message || '操作成功');
		$('#my-alert').modal('open');
		
		$('#my-alert').on('closed.modal.amui',function(){
			window.location.href= url || "/user/toUserCenter.do";
		});
	}else {
		$('#alertHead').html(':(');
		$('#alertdb').html(message || '操作失败');
		$('#my-alert').modal('open');
	}
}
