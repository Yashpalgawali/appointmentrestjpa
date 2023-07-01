/**
 * 
 */
   $(document).ready(function(){
	
	  var aparr = "",tapp="";
	  let app_name = $('#app_name').val();
	 
	  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        $($.fn.dataTable.tables(true)).DataTable()
	           .columns.adjust()
	           .responsive.recalc();
	    });  
	  
	  $.ajax({
			async    : true,
			type     : "GET",
			//url      : "getallappointments",
			url      : "/"+app_name+"/getallappointments",
			dataType : "json",
			success  : function(result) {
				var sr = 1;
				for (var i = 0; i < result.length; i++){
					aparr = aparr
								+ "<tr><td>" + result[i].status
								+ "</td><td>"+ result[i].vis_name
								+ "</td><td>"+ result[i].vis_purpose
								+ "</td><td>"+ result[i].apdate
								+ "</td><td>"+ result[i].aptime
								+ "</td><td>"+ result[i].vcomp_name
								+ "</td><td>"+ result[i].vis_email
								+ "</td><td>"+ result[i].vis_contact
								+ "</td><td>"+ result[i].employee.emp_name
								+ "</td><td>"+ result[i].employee.department.company.comp_name                          
								+ "</td><td style='text-align:center;'><a class='btn btn-primary' href='/editappointbyid/"+result[i].appoint_id+"'><i class='fa fa-edit ' aria-hidden='true'></i>&nbsp;&nbsp;Edit</a>";
								//								+ "</td><td style='text-align:center;'><a class='btn btn-primary' href='editappointbyid/"+result[i].appoint_id+"'><i class='fa fa-edit ' aria-hidden='true'></i>&nbsp;&nbsp;Edit</a>";
								+ "</td></tr>";
				}
				$(aparr).appendTo('#apbody');
				$("#aptable").DataTable({
					responsive	:	true,
					language	: {
										"zeroRecords": "No Appointments to Show",
								  },
								  
				"fnRowCallback": function(nRow, result, iDisplayIndex, iDisplayIndexFull) {
					
			        if (result[0]=="pending"){
			        	$('td:eq(0)', nRow).css("color" , "#F69828");
			        }
			        else if (result[0]=="confirmed"){
				    	$('td:eq(0)', nRow).css("color" , "green");
				    }
			        else if (result[0]=="declined"){
				    	$('td:eq(0)', nRow).css("color" , "red");
				    }
				}
				});
			   }
		});
	 	var date 	= new Date();
	 	var tyear 	= (parseInt(date.getFullYear()));
		var tmonth 	= (parseInt(String(date.getMonth()+1).padStart(2,'0')));
		var tdate  	= (parseInt(String(date.getDate()).padStart(2 , '0'))); 
		var todaydate	=	tyear+"-"+tmonth+"-"+tdate;
		
		$.ajax({
				async    : true,
				type     : "GET",
				//url      : "gettodaysappointments",
				url      : "/"+app_name+"/gettodaysappointments",
			    dataType : "json",
				success  : function(result) {
					var sr = 1;
					
					for (var i = 0; i < result.length; i++) 
					{
						tapp = tapp
								+ "<tr><td>" + result[i].status
								+ "</td><td>"+ result[i].vis_name
								+ "</td><td>"+ result[i].vis_purpose
								+ "</td><td>"+ result[i].apdate
								+ "</td><td>"+ result[i].aptime
								+ "</td><td>"+ result[i].vcomp_name
								+ "</td><td>"+ result[i].vis_email
								+ "</td><td>"+ result[i].vis_contact
								+ "</td><td>"+ result[i].employee.emp_name
								+ "</td><td>"+ result[i].employee.department.company.comp_name
								+ "</td><td style='text-align:center;'><a class='btn btn-primary' href='/editappointbyid/"+result[i].appoint_id+"'><i class='fa fa-edit ' aria-hidden='true'></i>&nbsp;&nbsp;Edit</a>";
//								+ "</td><td style='text-align:center;'><a class='btn btn-primary' href='editappointbyid/"+result[i].appoint_id+"'><i class='fa fa-edit ' aria-hidden='true'></i>&nbsp;&nbsp;Edit</a>";
								+ "</td></tr>";
					}
					$(tapp).appendTo('#aptodaybody');
					$("#aptodaytable").DataTable({
						
						responsive	:	true,
						language	: {
											"zeroRecords": "No Appointments to Show",
									  },
									  
						"fnRowCallback": function(nRow, result, iDisplayIndex, iDisplayIndexFull) {
					        if (result[0]=="pending"){
					        	$('td:eq(0)', nRow).css("color" , "#F69828");
					        }
					        else if (result[0]=="confirmed") {
						    	$('td:eq(0)', nRow).css("color" , "green");
						    }
					        else if (result[0]=="declined")  {
						    	$('td:eq(0)', nRow).css("color" , "red");
						    }
						}			
					});
				}
	});
 });