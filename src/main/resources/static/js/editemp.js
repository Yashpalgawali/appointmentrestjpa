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
		$('#status').select2({
			theme	:	'classic',
			width	:	'resolve'
			
		});
		$('#designation').select2({
			theme	:	'classic',
			width	:	'resolve'
			
		});
			
// 			$('#emp_name').blur(function(){
				
// 				var ename = $('#emp_name').val();
// 				if(ename!=""){
// 				$.ajax({
					
// 						url		:	'emppresent/'+ename,
// 						datatype:	'json',
// 						type	:	'GET',
// 						success	:	function(result)
// 						{ 
// 							//alert("result is "+result);
// 							if(result=='yes')
// 							{
// 								alert("Record for "+ename+" already present");	
// 								$('#emp_name').val('');
								
// 							}
// 							else
// 							{
								
// 							}
							
// 						},
// 						error	:	function(result)
// 						{
// 							alert("in Error function result is "+JSON.stringify(result));
// 						}
// 				});
// 			 }
// // 				else{alert("Please Enter the name");}
// 			});
			
	});
	
	 function getDeptByCompId(compid)
	 {
			var app_name = $('#app_name').val();
			$.ajax({
					async    : true,
					type     : "GET",
				    url      : "/"+app_name+"/getdeptbycompid/"+compid,
				    url      : "/getdeptbycompid/"+compid,
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
	 