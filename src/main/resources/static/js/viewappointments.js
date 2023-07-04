/**
 * 
 */
   $(document).ready(function(){
	  var vmail	=	$('#vis_email').val();
	  var burl	=	$('#burl').val();
	  let app_name = $('#app_name').val();
	  var aparr = "",tapp="";
	 $.ajax({
				type     : "GET",
			  	url      : "/"+app_name+"/getallappointmentsbyemail/"+vmail,
//			  	url    : "/getallappointmentsbyemail/"+vmail,
				dataType : "json",
				success  : function(result) {
				
				for (var i = 0; i < result.length; i++) 
				{
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
								+ "</td></tr>";
				}
					$(aparr).appendTo('#apbody');
					$("#aptable").DataTable({
						
						responsive	:	true,
						"order"		: [ 3, "desc" ],
						language	: {
											"zeroRecords": "No Appointments to Show",
									  },
						"fnRowCallback": function(nRow, result, iDisplayIndex, iDisplayIndexFull) {
				        if (result[0] == "pending") {
				          	$('td:eq(0)', nRow).css("color" , "#F69828");
				        }
				        else if (result[0] == "confirmed") {
					          $('td:eq(0)', nRow).css("color" , "green");
					     }
				        else if (result[0] == "declined") {
					          $('td:eq(0)', nRow).css("color" , "red");
					     }
				       } 
					});
			   }
		});
	 
	  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
	        $($.fn.dataTable.tables(true)).DataTable()
	           .columns.adjust()
	           .responsive.recalc();
	    });  
	  	var date 	= new Date();
		var tyear 	= (parseInt(date.getFullYear()));
		var tmonth 	= (parseInt(String(date.getMonth()+1).padStart(2,'0')));
		var tdate  	= (parseInt(String(date.getDate()).padStart(2 , '0'))); 
		
		if(tmonth <10){
				tmonth	=	"0"+tmonth;	
		}
		
		var todaydate	=	tyear+"-"+tmonth+"-"+tdate;
		
		$.ajax({
				type     : "GET",
			    url      : "/"+app_name+"/gettodaysappointmentsbyemail/"+vmail,
//			    url      : "/gettodaysappointmentsbyemail/"+vmail,
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
								+ "</td></tr>";
					}
					$(tapp).appendTo('#aptodaybody');
					$("#aptodaytable").DataTable({
						
						responsive	:	true,
						"order": [ 4, "asc" ],
						"language": {
										"zeroRecords": "No Appointments For Today",	
									},
						"fnRowCallback": function(nRow, result, iDisplayIndex, iDisplayIndexFull) {
					        if (result[0] == "pending") {
					          $('td:eq(0)', nRow).css("color" , "#F69828");
					        }
					        if (result[0] == "confirmed") {
						          $('td:eq(0)', nRow).css("color" , "green");
						    }
					 		if (result[0] == "declined") {
						          $('td:eq(0)', nRow).css("color" , "red");
						    }
					    }
					});
				}
	});
	$('#datetime').datetimepicker({
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
 });
  