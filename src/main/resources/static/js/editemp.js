/**
 * 
 */
 	$(document).ready(function(){
			  
		$('#company').select2({
			theme	:	'classic',
			width	:	'resolve'
		});
		$('#department').select2({
			theme	:	'classic',
			width	:	'resolve'
		});
		$('#emp_status').select2({
			theme	:	'classic',
			width	:	'resolve'
		});
		$('#designation').select2({
			theme	:	'classic',
			width	:	'resolve'
		});
	});
	
 function getDeptByCompId(compid)
 {
	var app_name = $('#app_name').val();
	$.ajax({
			async    : true,
			type     : "GET",
		    url      : "/"+app_name+"/getdeptbycompid/"+compid,
		    //url      : "/getdeptbycompid/"+compid,
			dataType : "json",
			success  : function(result) {
				
				$('select[name="department"]').empty();
				$('select[name="department"]').append('<option selected disabled value="">'+"Please Select Department"+'</option');
				
				$.each(result,function(i){
					$('select[name="department"]').append('<option value="'+result[i].dept_id+'">'+result[i].dept_name+'</option>');
				});
			}
		 });
	}	 