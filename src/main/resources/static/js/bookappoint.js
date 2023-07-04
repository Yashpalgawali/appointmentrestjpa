  $(document).ready(function(){
		
		var date = new Date();
		var mnth = (date.getMonth()+1);
		
		var dt	 =	date.getDate();
		if(dt<10){
			dt= "0"+dt;	
		}
		if(mnth <10 ){
				mnth = "0"+mnth;
		}
		
		today = date.getFullYear()+"-"+ mnth+"-"+ dt; 
		
	  	$('#sbapt').click(function(e){
			if($('#employee').val()==null){
				e.preventDefault();
				$('#employee').focus();
				alert("Please Select Employee ");
			}
		}); 
	  
  	$('#apdate').datetimepicker({
		minDate	: 	today,
		format	:	'YYYY-MM-DD'
	});

  	$('#company').select2({
			theme	:	'classic',
			width	:	'resolve'
	});
	
	$('#employee').select2({
		theme	:	'classic',
		width	:	'resolve'
	});
	
	$('#department').select2({
		theme	:	'classic',
		width	:	'resolve'
	});
	
	$('#apdate').focusout( function(){ 
		
		if($('#apdate').val()==today)
		{
		   $('#aptime').datetimepicker({ 
	    		format: 'hh:mm:ss A',
	    		minDate: moment(),
	    		icons: {
	    				time	: 'fa fa-clock-o',
	    				date	: 'fa fa-calendar',
	    				up		: 'fa fa-chevron-up',
	    				down	: 'fa fa-chevron-down',
	    				previous: 'fa fa-chevron-left',
	    				next	: 'fa fa-chevron-right',
	    				today	: 'fa fa-check',
	    				clear	: 'fa fa-trash',
	    				close	: 'fa fa-times'
	    			},
	    	});
		}
		else
		{
		     $('#aptime').datetimepicker({
	    		format: 'hh:mm:ss A',
	    		icons: {
	    				time	: 'fa fa-clock-o',
	    				date	: 'fa fa-calendar',
	    				up		: 'fa fa-chevron-up',
	    				down	: 'fa fa-chevron-down',
	    				previous: 'fa fa-chevron-left',
	    				next	: 'fa fa-chevron-right',
	    				today	: 'fa fa-check',
	    				clear	: 'fa fa-trash',
	    				close	: 'fa fa-times'
	    			},	
	    	});
		}
	 });
  });
  
	function getDeptByEmpId(empid)
	{	var app_name = $('#app_name').val();
	 	$('#department').empty();
 		$('#company').empty();
	 	
		$.ajax({
				async    : true,
				type     : "GET",
			    url      : "/"+app_name+"/getdeptbyempid/"+empid,
			    //url      : "/getdeptbyempid/"+empid, 
				success  : function(result) {
					for(let i=0;i<result.length;i++)
					{
						$('select[name="department"]').append('<option selected value="'+result[i].department.dept_id+'">'+result[i].department.dept_name+'</option>');
						$('select[name="company"]').append('<option selected value="'+result[i].department.company.company_id+'">'+result[i].department.company.comp_name+'</option>');	
					}
			}
	 	});
	}