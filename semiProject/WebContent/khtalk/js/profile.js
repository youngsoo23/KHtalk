$(document).ready(function() {
	$('#setting').on('click', function() {
		alert("setting");
		$('.form-inline').attr('action', 'setting').submit();
	});
	$('#save').on('click', function() {
		alert("저장되었습니다");
		$('[name=content]').val($('[name=content]').val().replace(/\n/gi,'<br/>'));
		$('.profileSetting').attr('action', 'fixedProfile').submit();
	});
	
	$('#logOut').on('click', function() {
		alert("logout 되었습니다.");
		$('.form-inline').attr('action', 'logOut').submit();
	})
	
    $('#pictureUpload').on('click',function(){
    	alert("picture");
    	$('#filepath').click();
    });
	
	$("#filepath").on('change', function() {
		var str = $("#filepath").val();
		console.log(str);
		// 이미지 첨부파일인지 체크
		var patt = /(.jpg$|.gif$|.png$)/gi;
		var result = str.match(patt);

		if (!result) {
			alert("jpg, gif, png만 가능합니다.");
			$('#filepath').val('');
			return false;
		}
		// 첨부파일 사이즈 체크
		console.log(this.files + "," + this.files[0]);
		// if (this.files && this.files[0]) {

		console.log("size: " + this.files[0].size);
		if (this.files[0].size > 1000000000) {
			alert("1GB 이하만 첨부할수 있습니다.");
			$("#filepath").val('');
			return false;
		}
		// }

		// 파일을 읽기 위한 fileReader 객체 생성.
		var reader = new FileReader();
		// file 내용을 읽어 dataURL형식의 문자열로 저장
		reader.readAsDataURL(this.files[0]);
		// 파일 읽어들이기를 성공했읋때 호출되는 이벤트 핸들러
		reader.onload = function(e) {
			// 이미지Tag의 src속성에 읽어들인 File내용을 지정
			$("#pic").attr('src', e.target.result);
		}

	});// end change

});