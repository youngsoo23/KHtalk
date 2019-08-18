$(document).ready(function() {
	/*
	 * $('#signUp').on('click',function(){
	 * $('.form-inline').attr("action","signUp").submit(); });
	 */

	$('#signUp').on('click', function() {
		$('#mySignUp').modal();
	});

	$('#reset').on('click', function() {
		$('.form-control').val('');
	});

	$('#signup').on('click', function(){
		alert("가입되었습니다.")
		$('#signUpForm').attr('action','SignUpOk').submit();
	});
	
	$('#logOut').on('click', function() {
		alert("logout 되었습니다.")
		$('.form-inline').attr('action', 'logOut').submit();
	})

	$('#pw2').blur(function() {
		if ($('#pw1').val() != $('#pw2').val()) {
			if ($('#pw2').val() != '') {
				alert("비밀번호가 일치하지 않습니다.");
				$('#pw2').val('');
				$('#pw2').focus();
			}
		}
	})
	
	 $("#id").blur(function() {
         var userId = $('#id').val();
         if (!(userId == "")) {
            $.ajax({
               url:'idchk.do',
               type:'POST',
              data:({"userId":userId}),
                dataType : "json",
               success:function(object){
            	   console.log(object.res);
            	   $("#signup").attr("disabled", false);
                  if(object.res==1){
                	 
                     $("#id_check").text("사용중인 아이디입니다");
                     $("#id_check").css("color", "red");
                     $("#signup").attr("disabled", true);
                  }else{
                     $("#id_check").text("사용가능한 아이디입니다.");
                     $("#id_check").css("color", "blue");
                  }
               }
            })
         }
      })


	
});// ////

function form_submit() {
	alert("가입되었습니다.")
	document.sign.submit();
}