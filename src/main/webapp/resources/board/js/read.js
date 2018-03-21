$(function(){
	$("#modify").click(function(){
		$("#f").attr("action","modify");
		$("#f").submit();
	});
	
	$("#remove").click(function(){
		
		if(confirm("삭제하시겠습니까?")){
			$("#f").attr("action","remove");
			
			$("#f").submit();
		}
	})
})