						function format ( d ) {
						    var evaluateData = new Array();
						    evaluateData[7] = new Array(65,78,90,76,82);
						    evaluateData[8] = new Array(67,88,92,66,97);
						    evaluateData[9] = new Array(76,79,90,69,89);
						    evaluateData[10] = new Array(86,92,79,81,89);
						    evaluateData[11] = new Array(72,86,90,90,87);
						    evaluateData[12] = new Array(79,92,70,73,82);
						    evaluateData[13] = new Array(78,69,84,69,66);
						    evaluateData[14] = new Array(83,85,67,91,64);
						    evaluateData[15] = new Array(81,83,69,78,91);
						    evaluateData[16] = new Array(79,91,88,88,90);
						    evaluateData[17] = new Array(79,86,83,92,93);
						    evaluateData[18] = new Array(85,81,76,67,78);
						    evaluateData[19] = new Array(85,81,76,67,78);
						    evaluateData[20] = new Array(85,81,76,67,78);
						    evaluateData[21] = new Array(85,81,76,67,78);
						    evaluateData[22] = new Array(85,81,76,67,78);
							var averagevalue = 0;
							var sum = 0;
							for(i=0; i<5;i++) {
								sum = sum + evaluateData[d.id][i];	
							}
							

							
							averagevalue = Math.floor(sum/5);
							$(".progress-bar-success").attr("style", "width: "+ evaluateData[d.id][0] +"%");
							$(".progress-bar-success").html("<span>"+ evaluateData[d.id][0] +"</span>");
							
							$(".progress-bar-info").attr("style", "width: "+ evaluateData[d.id][1] +"%");
							$(".progress-bar-info").html("<span>"+ evaluateData[d.id][1] +"</span>");
							
							$(".progress-bar-warning").attr("style", "width: "+ evaluateData[d.id][2] +"%");
							$(".progress-bar-warning").html("<span>"+ evaluateData[d.id][2] +"</span>");
							
							$(".progress-bar-danger").attr("style", "width: "+ evaluateData[d.id][3] +"%");
							$(".progress-bar-danger").html("<span>"+ evaluateData[d.id][3] +"</span>");
							
							$(".progress-bar-orange").attr("style", "width: "+ evaluateData[d.id][4] +"%");
							$(".progress-bar-orange").html("<span>"+ evaluateData[d.id][4] +"</span>");
							
							$(".progress-bar-primary").attr("style", "width: "+ averagevalue +"%");
							$(".progress-bar-primary").html("<span>"+ averagevalue +"</span>");
							
							$('#review-result-template').text("Scores "+ averagevalue +" out of 100 based on reviews");
							
							$('#current_computer_series_template').html("<span >"+ d.series + " " +d.computername+"</span>");
							$('#current_computer_system_template').html("<span > System : " + d.system +"</span>");
							$('#current_computer_processor_template').html("<span > Processor : " + d.processor +"</span>");
							$('#current_computer_display_template').html("<span > Display : " + d.display +"</span>");
							$('#current_computer_memory_template').html("<span > Memory : " + d.memory +"</span>");
							

							
							return $('#collapseTemplate').html();
						}

$(document)
		.ready(
				function() {
					

				

					
					var initPage = function() {

		

						$
								.ajax({
									url : 'order/status',
									type : 'GET',
									contentType : "application/json",
									data : function(d) {
										JSON.stringify(d);
									}
								})
								.done(
										function(data) {
											var levelSelects = $('.status-selects');
											$
													.each(
															data,
															function(i, status) {
																levelSelects
																		.append($('<option data-display = "'
																				+ status.type
																				+ '" value="'
																				+ status.id
																				+ '">'
																				+ status.type
																				+ '</li>'));
															});
										});
						
						$.ajax({
							url : 'customer/list?customerId=0',
							type : 'GET',
							contentType : "application/json",
							data : function(d) {
								JSON.stringify(d);
							}
						}).done(function(data) {
							var computerSelects = $('.customer-selects');
							$.each(data,function(i,customer) {
								computerSelects.append($('<option data-display = "'
										+ customer.customername
										+ '" value="'
										+ customer.id
										+ '">'
										+ customer.customername
										+ '</li>'));	
							});
							
							var computerList = $('#customer-list-group');
							$.each(data,function(i,customer) {
								if(customer.ordernumber > 0) {
								computerList.append($(
										'<a href="#" class="list-group-item " id="' + customer.id + '_cutsomer"><p id="cutomer-tag-'+customer.id+'" class = "customer-tags"></p>' + '<h4 class="list-group-item-heading">'+ customer.customername +'</h4>' +
										'<p class="list-group-item-text">'+ "Industry : " + customer.industry + '</p>' +
										'<p class="list-group-item-text">'+"Target Price : " + customer.targetprice + '</p>'+
										'<p class="list-group-item-text">'+"Contact : " + customer.leadname + " " +customer.telephone + '</p>'+
										'<p id="cutomer-tag-'+customer.id+'"></p></a>'
										));
								} else {
								computerList.append($(
											'<a href="#" class="list-group-item " id="' + customer.id + '_cutsomer"><p id="cutomer-tag-'+customer.id+'" class = "customer-tags"></p>' + '<h4 class="list-group-item-heading">'+ customer.customername +'</h4>' +
											'<p class="list-group-item-text">'+ "Industry : " + customer.industry + '</p>' +
											'<p class="list-group-item-text">'+"Target Price : " + customer.targetprice + '</p>'+
											'<p class="list-group-item-text">'+"Contact : " + customer.leadname + " " +customer.telephone + '</p>'+
											'</a>'
											));
									
								}
							});
						 $.each(data,function(i,customer){
							if(customer.ordernumber > 0) {
								$('#cutomer-tag-'+customer.id).tags({
						              readOnly: true,
						              tagSize: "sm",
						              tagClass: 'btn-success',
						              tagData: ["old customer"]
								});
							} else {
								$('#cutomer-tag-'+customer.id).tags({
						              readOnly: true,
						              tagSize: "sm",
						              tagClass: 'btn-warning',
						              tagData: ["new customer"]
								});
							} 
						 });
							
						});
						
						$.ajax({
							url : 'order/users',
							type : 'GET',
							contentType : "application/json",
							data : function(d) {
								JSON.stringify(d);
							}
						}).done(function(data) {
							var computerSelects = $('.user-selects');
							$.each(data,function(i,user) {
								computerSelects.append($('<option data-display = "'
										+ user.username
										+ '" value="'
										+ user.id
										+ '">'
										+ user.username
										+ '</li>'));
							});
						});
						
						$.ajax({
							url : 'order/computers?computerId=0',
							type : 'GET',
							contentType : "application/json",
							data : function(d) {
								JSON.stringify(d);
							}
						}).done(function(data) {
							var computerSelects = $('.computer-selects');
							$.each(data,function(i,computer) {
								computerSelects.append($('<option data-display = "'
										+ computer.computername
										+ '" value="'
										+ computer.id
										+ '">'
										+ computer.computername
										+ '</li>'));
							});
						});
						
					
						
						
						var currenttargetprice;
						var currentindustry;
						var industryname = new Array("Agriculture and Fishing","Mining and Quarrying","Manufacturing","Electricity, Gas and Steam","Construction","Wholesale and Retail Trade","Information and Communications","Financial and Insurance Activities","Real Estate Activities","Professional, Scientific and Technical Activities","Education","Public Administration and Defence","Health and Social Services");
					    $('#customer-list-group').on('click', 'a.list-group-item', function() {
					    	
					    	var customer_id = parseInt($(this).attr('id').toString());
					    	$('#order-history-modal #myModalLabel').data().mode = customer_id;
					    	$('#previous-order-detail').empty();
					    	
					    	
							$.ajax({
								url : 'customer/list?customerId=0',
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							}).done(function(data) {
								var currentcomputer = $('#current_customer_id');
								$.each(data,function(i,customer) {
									if(customer.id == customer_id) {
										$('#current_customer_name').text(customer.customername);
										$('#current_customer_industry').text("Industry : "+customer.industry);
										$('#current_customer_targetprice').text("Target Price :"+customer.targetprice);
										if(customer.ordernumber > 0)
											$('#order-history-modal-btn').show();
										else
											$('#order-history-modal-btn').hide();
										
										currenttargetprice = customer.targetprice;
										for(j = 0; j < 13; j++) {
											if(customer.industry == industryname[j]) {
												currentindustry = j;
											}
										}	
										
										$.ajax({
											url : 'customer/suggestedcomputer?targetprice='+customer.targetprice,
											type : 'GET',
											contentType : "application/json",
											data : function(d) {
												JSON.stringify(d);
											}
										}).done(function(data) {
											$('#suggestedcomputer_1').text(data[0].computername);
											var suggestedpecentage1 = (1-(Math.abs(data[0].price - currenttargetprice)/currenttargetprice)) * 100;
											$('#suggestedcomputerdegree_1').text("Overall Matching Degree : " + (Math.floor(suggestedpecentage1) - 10) + "%");
											$('#suggestedcomputer_2').text(data[1].computername);
											var suggestedpecentage2 = (1-(Math.abs(data[1].price - currenttargetprice)/currenttargetprice)) * 100;
											$('#suggestedcomputerdegree_2').text("Overall Matching Degree : " + (Math.floor(suggestedpecentage2) - 15) + "%");
											$('#suggestedcomputer_3').text(data[2].computername);
											var suggestedpecentage3 = (1-(Math.abs(data[2].price - currenttargetprice)/currenttargetprice)) * 100;
											$('#suggestedcomputerdegree_3').text("Overall Matching Degree : " + (Math.floor(suggestedpecentage3) - 18) + "%");
											
										});
										
										/*ComputerSales.dataTable = $('#computer-table')
										.DataTable(
												{
													'ajax' : {
														url : 'customer/othercomputer?targetprice='+customer.targetprice,
														type : 'GET',
														contentType : "application/json",						
														dataSrc : "",

													},
													columns : [ 
													{
										                "className":      'details-control',
										                "orderable":      false,
										                "data":           null,
										                "defaultContent": ''
													},
													{
							                            data : 'computername'
													}, {
														data : 'series'
													}, {
														data :  'processor'
													}, {
														data : 'memory'
													}, {
														data : 'display'
													}, {
														data : 'price'
													} ],
													select : 'single',
													filter : true,
													bDestroy: true,
													order : [[1, 'asc']]
												});*/
										
									}
														   
								});
								
							});
																    	
					    });
//-----------------------------------------------collapseOne module start-------------------------------------------------//
					    var evaluateData = new Array();
					    evaluateData[7] = new Array(65,78,90,76,82);
					    evaluateData[8] = new Array(67,88,92,66,97);
					    evaluateData[9] = new Array(76,79,90,69,89);
					    evaluateData[10] = new Array(86,92,79,81,89);
					    evaluateData[11] = new Array(72,86,90,90,87);
					    evaluateData[12] = new Array(79,92,70,73,82);
					    evaluateData[13] = new Array(78,69,84,69,66);
					    evaluateData[14] = new Array(83,85,67,91,64);
					    evaluateData[15] = new Array(81,83,69,78,91);
					    evaluateData[16] = new Array(79,91,88,88,90);
					    evaluateData[17] = new Array(79,86,83,92,93);
					    evaluateData[18] = new Array(85,81,76,67,78);
					    evaluateData[19] = new Array(85,81,76,67,78);
					    evaluateData[20] = new Array(85,81,76,67,78);
					    evaluateData[21] = new Array(85,81,76,67,78);
					    evaluateData[22] = new Array(85,81,76,67,78);
					    var usageData = new Array();
					    usageData[7] = new Array(65,11,9,13,2);
					    usageData[8] = new Array(27,29,21,14,9);
					    usageData[9] = new Array(49,5,2,26,18);
					    usageData[10] = new Array(27,25,20,26,2);
					    usageData[11] = new Array(56,18,12,5,9);
					    usageData[12] = new Array(36,24,10,17,13);
					    usageData[13] = new Array(46,18,22,5,9);
					    usageData[14] = new Array(36,20,8,21,15);
					    usageData[15] = new Array(27,15,30,16,12);
					    usageData[16] = new Array(37,25,10,16,12);
					    usageData[17] = new Array(32,20,15,26,7);
					    usageData[18] = new Array(20,25,5,33,17);
					    usageData[19] = new Array(33,25,6,26,10);
					    usageData[20] = new Array(32,20,12,16,20);
					    usageData[21] = new Array(27,20,17,21,15);
					    usageData[22] = new Array(38,18,15,22,9);
					    var price = new Array();
						
					    
					   
					    
					    $("#collapseOne").on("shown.bs.collapse", function(){
					    						    	
					    	$.ajax({
								url : 'order/computers?computerId=0',
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							}).done(function(data) {
								var currentcomputername = $('#suggestedcomputer_1').text();
								$.each(data,function(i,computer) {
									price[i] = computer.price;
									if(computer.computername == currentcomputername) {	
										
									$('#order-analysis-modal #myModalLabel').data().mode = currentcomputername;
									$('#order-analysis-modal #myModalLabel').html('Data Analysis for '+currentcomputername);
									$('#current_computer_series1').html("<span >"+ computer.series + " " +computer.computername+"</span>");
									$('#current_computer_system1').html("<span > System : " + computer.system +"</span>");
									$('#current_computer_processor1').html("<span > Processor : " + computer.processor +"</span>");
									$('#current_computer_display1').html("<span > Display : " + computer.display +"</span>");
									$('#current_computer_memory1').html("<span > Memory : " + computer.memory +"</span>");
									
									var averagevalue = 0;
									var sum = 0;
									for(i=0; i<5;i++) {
										sum = sum + evaluateData[computer.id][i];	
									}
									averagevalue = Math.floor(sum/5);
									$(".progress-bar-success").attr("style", "width: "+ evaluateData[computer.id][0] +"%");
									$(".progress-bar-success").html("<span>"+ evaluateData[computer.id][0] +"</span>");
									
									$(".progress-bar-info").attr("style", "width: "+ evaluateData[computer.id][1] +"%");
									$(".progress-bar-info").html("<span>"+ evaluateData[computer.id][1] +"</span>");
									
									$(".progress-bar-warning").attr("style", "width: "+ evaluateData[computer.id][2] +"%");
									$(".progress-bar-warning").html("<span>"+ evaluateData[computer.id][2] +"</span>");
									
									$(".progress-bar-danger").attr("style", "width: "+ evaluateData[computer.id][3] +"%");
									$(".progress-bar-danger").html("<span>"+ evaluateData[computer.id][3] +"</span>");
									
									$(".progress-bar-orange").attr("style", "width: "+ evaluateData[computer.id][4] +"%");
									$(".progress-bar-orange").html("<span>"+ evaluateData[computer.id][4] +"</span>");
									
									$(".progress-bar-primary").attr("style", "width: "+ averagevalue +"%");
									$(".progress-bar-primary").html("<span>"+ averagevalue +"</span>");
									
									$('#review-result-1').text("Scores "+ averagevalue +" out of 100 based on reviews");
									
									var doughnutData = [
									    				{
									    					value: usageData[computer.id][0],
									    					color:"#F7464A",
									    					highlight: "#FF5A5E",
									    					label: "Internet"
									    				},
									    				{
									    					value: usageData[computer.id][1],
									    					color: "#46BFBD",
									    					highlight: "#5AD3D1",
									    					label: "Writing/Editing"
									    				},
									    				{
									    					value: usageData[computer.id][2],
									    					color: "#FDB45C",
									    					highlight: "#FFC870",
									    					label: "Playing games"
									    				},
									    				{
									    					value: usageData[computer.id][3],
									    					color: "#949FB1",
									    					highlight: "#A8B3C5",
									    					label: "Office software"
									    				},
									    				{
									    					value: usageData[computer.id][4],
									    					color: "#4D5360",
									    					highlight: "#616774",
									    					label: "Research and Development"
									    				}

									    			];

									    				var ctx = document.getElementById("chart-area-1").getContext("2d");
									    				window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
								
									}		
								});
							});
					    	
					    	
					    	
					    });
					    
//-----------------------------------------------collapseOne module end-------------------------------------------------//
					    
//-----------------------------------------------collapseTwo module start-------------------------------------------------//					    
					    $("#collapseTwo").on("shown.bs.collapse", function(){
					    	
					    	$.ajax({
								url : 'order/computers?computerId=0',
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							}).done(function(data) {
								var currentcomputername = $('#suggestedcomputer_2').text();
								$.each(data,function(i,computer) {
									
									if(computer.computername == currentcomputername) {	
										
									$('#order-analysis-modal #myModalLabel').data().mode = currentcomputername;
									$('#order-analysis-modal #myModalLabel').html('Data Analysis for '+currentcomputername);
										
									$('#current_computer_series2').html("<span >"+ computer.series + " " +computer.computername+"</span>");
									$('#current_computer_system2').html("<span > System : " + computer.system +"</span>");
									$('#current_computer_processor2').html("<span > Processor : " + computer.processor +"</span>");
									$('#current_computer_display2').html("<span > Display : " + computer.display +"</span>");
									$('#current_computer_memory2').html("<span > Memory : " + computer.memory +"</span>");
									var averagevalue = 0;
									var sum = 0;
									for(i=0; i<5;i++) {
										sum = sum + evaluateData[computer.id][i];	
									}
									averagevalue = Math.floor(sum/5);
									
									$(".progress-bar-success").attr("style", "width: "+ evaluateData[computer.id][0] +"%");
									$(".progress-bar-success").html("<span>"+ evaluateData[computer.id][0] +"</span>");
									
									$(".progress-bar-info").attr("style", "width: "+ evaluateData[computer.id][1] +"%");
									$(".progress-bar-info").html("<span>"+ evaluateData[computer.id][1] +"</span>");
									
									$(".progress-bar-warning").attr("style", "width: "+ evaluateData[computer.id][2] +"%");
									$(".progress-bar-warning").html("<span>"+ evaluateData[computer.id][2] +"</span>");
									
									$(".progress-bar-danger").attr("style", "width: "+ evaluateData[computer.id][3] +"%");
									$(".progress-bar-danger").html("<span>"+ evaluateData[computer.id][3] +"</span>");
									
									$(".progress-bar-orange").attr("style", "width: "+ evaluateData[computer.id][4] +"%");
									$(".progress-bar-orange").html("<span>"+ evaluateData[computer.id][4] +"</span>");
									
									$(".progress-bar-primary").attr("style", "width: "+ averagevalue +"%");
									$(".progress-bar-primary").html("<span>"+ averagevalue +"</span>");
									
									$('#review-result-2').text("Scores "+ averagevalue +" out of 100 based on reviews");
									
									var doughnutData = [
									    				{
									    					value: usageData[computer.id][0],
									    					color:"#F7464A",
									    					highlight: "#FF5A5E",
									    					label: "Internet"
									    				},
									    				{
									    					value: usageData[computer.id][1],
									    					color: "#46BFBD",
									    					highlight: "#5AD3D1",
									    					label: "Writing/Editing"
									    				},
									    				{
									    					value: usageData[computer.id][2],
									    					color: "#FDB45C",
									    					highlight: "#FFC870",
									    					label: "Playing games"
									    				},
									    				{
									    					value: usageData[computer.id][3],
									    					color: "#949FB1",
									    					highlight: "#A8B3C5",
									    					label: "Office software"
									    				},
									    				{
									    					value: usageData[computer.id][4],
									    					color: "#4D5360",
									    					highlight: "#616774",
									    					label: "Research and Development"
									    				}

									    			];

									    				var ctx = document.getElementById("chart-area-2").getContext("2d");
									    				window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
								
									}		
								});
							});
					    	
					    	

					    	
					    });
//-----------------------------------------------collapseTwo module end-------------------------------------------------//
					    
//-----------------------------------------------collapseThree module start-------------------------------------------------//
					    
			    $("#collapseThree").on("shown.bs.collapse", function(){
					    	
					    	$.ajax({
								url : 'order/computers?computerId=0',
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							}).done(function(data) {
								var currentcomputername = $('#suggestedcomputer_3').text();
								$.each(data,function(i,computer) {
									
									if(computer.computername == currentcomputername) {
									
									$('#order-analysis-modal #myModalLabel').data().mode = currentcomputername;
									$('#order-analysis-modal #myModalLabel').html('Data Analysis for '+currentcomputername);
										
									$('#current_computer_series3').html("<span >"+ computer.series + " " +computer.computername+"</span>");
									$('#current_computer_system3').html("<span > System : " + computer.system +"</span>");
									$('#current_computer_processor3').html("<span > Processor : " + computer.processor +"</span>");
									$('#current_computer_display3').html("<span > Display : " + computer.display +"</span>");
									$('#current_computer_memory3').html("<span > Memory : " + computer.memory +"</span>");
									var averagevalue = 0;
									var sum = 0;
									for(i=0; i<5;i++) {
										sum = sum + evaluateData[computer.id][i];	
									}
									averagevalue = Math.floor(sum/5);
									$(".progress-bar-success").attr("style", "width: "+ evaluateData[computer.id][0] +"%");
									$(".progress-bar-success").html("<span>"+ evaluateData[computer.id][0] +"</span>");
									
									$(".progress-bar-info").attr("style", "width: "+ evaluateData[computer.id][1] +"%");
									$(".progress-bar-info").html("<span>"+ evaluateData[computer.id][1] +"</span>");
									
									$(".progress-bar-warning").attr("style", "width: "+ evaluateData[computer.id][2] +"%");
									$(".progress-bar-warning").html("<span>"+ evaluateData[computer.id][2] +"</span>");
									
									$(".progress-bar-danger").attr("style", "width: "+ evaluateData[computer.id][3] +"%");
									$(".progress-bar-danger").html("<span>"+ evaluateData[computer.id][3] +"</span>");
									
									$(".progress-bar-orange").attr("style", "width: "+ evaluateData[computer.id][4] +"%");
									$(".progress-bar-orange").html("<span>"+ evaluateData[computer.id][4] +"</span>");
									
									$(".progress-bar-primary").attr("style", "width: "+ averagevalue +"%");
									$(".progress-bar-primary").html("<span>"+ averagevalue +"</span>");
									
									$('#review-result-3').text("Scores "+ averagevalue +" out of 100 based on reviews");
									
									
									var doughnutData = [
									    				{
									    					value: usageData[computer.id][0],
									    					color:"#F7464A",
									    					highlight: "#FF5A5E",
									    					label: "Internet"
									    				},
									    				{
									    					value: usageData[computer.id][1],
									    					color: "#46BFBD",
									    					highlight: "#5AD3D1",
									    					label: "Writing/Editing"
									    				},
									    				{
									    					value: usageData[computer.id][2],
									    					color: "#FDB45C",
									    					highlight: "#FFC870",
									    					label: "Playing games"
									    				},
									    				{
									    					value: usageData[computer.id][3],
									    					color: "#949FB1",
									    					highlight: "#A8B3C5",
									    					label: "Office software"
									    				},
									    				{
									    					value: usageData[computer.id][4],
									    					color: "#4D5360",
									    					highlight: "#616774",
									    					label: "Research and Development"
									    				}

									    			];

									    				var ctx = document.getElementById("chart-area-3").getContext("2d");
									    				window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
								
									}		
								});
							});
					    	
					    	
					    	
					    });
//-----------------------------------------------collapseThree module end-------------------------------------------------//
//-----------------------------------------------delete datasets in myDoughnut-------------------------------------------//
			    $(".collapse").on("hidden.bs.collapse", function(){
			    	window.myDoughnut.destroy(); 
			    });
			    
//-------------------------------------------------end myDoughnut-------------------------------------------------------//
						/*$('#order-add-button').click(
								ComputerSales.addEditOrder);*/
						$('#order-delete-button').click(
								ComputerSales.deleteOrder);
						$('#order-type-filter').change(function() {
							$('#order-table').dataTable().fnReloadAjax(); 
							});
						
//*****************************add more than one computer in the modal****************************************************//
				computernumberIndex = 1;
				$('.addButton').on('click',function() {
					computernumberIndex++;
					var $template = $('#computernumbertemplate'),
					    $clone    = $template
					                .clone()
					                .removeClass('hide')
					                .removeAttr('id')
					                .attr('computernumber-index', computernumberIndex)
					                .insertBefore($template);
			           $clone
		                .find('[name="computerId"]').attr('name', 'computerId' ).end()
		                .find('[name="number"]').attr('name', 'number').end()
		                
		         $('.removeButton').on('click', function() {
		        	 
		                    var $row  = $(this).parents('.form-group'),
		                        index = $row.attr('computernumber-index');


		                    // Remove element containing the fields
		                    $row.remove();
		                });
					                
				});		
//*****************************add more than one computer in the modal end************************************************//
						

						// When add button is clicked initialize the dialog
						$('#order-add-modal-btn')
						.on(
								'click',
								function() {
									
									var customername = $('#current_customer_name').text();
									$('#order-price-form').hide();
									$('#order-add-modal #myModalLabel').html('New Order');
									
									$('#customer option[data-display="' + customername + '"]').attr('selected','selected');
								});
						
						$('#order-add-button').on('click',function() {
							
							var formData = $('#order-form').serializeObject();
							var computertotalnumber = computernumberIndex;

							
							var computerlist = new Array();
							for(i=0; i < computertotalnumber; i++) {
								var computer = {};
								computer.computerId = formData.computerId[i];
								computer.number = formData.number[i];
								computerlist.push(computer);
								formData.computerId[i] = undefined;
								formData.number[i] = undefined;
							}
					/*		var computer = {};
							computer.computerId = formData.computerId_0;
							computer.number = formData.number_0;
							computerlist.push(computer);
							var computer_1 = {};
							computer_1.computerId = formData.computerId_1;
							computer_1.number = formData.number_1;
							computerlist.push(computer_1);*/
							
							formData.computerlist=computerlist;
							formData.number = undefined;
							formData.computerId = undefined;
						/*	formData.number_0=undefined;
							formData.computerId_0=undefined;
							formData.number_1=undefined;
							formData.computerId_1=undefined;
							formData.number=undefined;
							formData.computerId=undefined;*/
							
							
							formData = JSON.stringify(formData);
							console.log(formData)

							$.ajax({
								url : 'order/addorder',
								data : formData,
								type : 'POST',
								contentType : "application/json",
								xhrFields : {
									withCredentials : true
								}
							}).done(function() {
								$('#order-add-modal').modal('hide');
								$('#order-table').dataTable().fnReloadAjax();
								computernumberIndex = 1;

							});
							
						});
						
						
						$('#order-history-modal-btn').on('click',function() {
							var customerId = 0;
							
							customerId = $('#order-history-modal #myModalLabel').data().mode;
							var customername = $('#current_customer_name').text();
							$('#order-history-modal #myModalLabel').html('Previous Orders for ' + customername);
							$('#previous-order-detail').empty();
							
							$.ajax({
								url : 'customer/orderlist?customerId='+customerId,
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							}).done(function(data) {
								//var computerSelects = $('.user-selects');
								$.each(data,function(i,order) {
									//var order_id = order.id
									var computer = '<br><table style="width:60%" class="computer-number"><tr class="computer-row"><th class="computer-cell">Computer Name</th><th colspan="2" class="computer-cell">Computer Number</th></tr>';
									$.each(order.computerlist, function(j,computernum){
										computer = computer + '<tr class="computer-row"><td class="computer-cell">'+computernum.computername + '</td><td class="computer-cell">'+computernum.number +'</td></tr>';	
									}); 
									computer = computer + '</table>';
									
									var user;
									if(order.user == null)
										user = '<td><label>Responsible Staff :</label>None</td>';
									else
										user = '<td><label>Responsible Staff :</label>'+order.user+'</td>';
			
									$('#previous-order-detail').append($(
											'<div class="panel panel-success">'+
									'<div class="panel-heading"><label>Order Id :</label>'+ order.id +'</div>'+
									'<div class="panel-body">'+
									'<table width="100%"><tr id="block_container"><td><label>Create Date :</label>'+ order.builddate +'</td>'+ 
									'<td><label>Total Price :</label>'+ order.price +'</td></tr>'+
									'<tr><td><label>Current Status :</label>'+ order.status +'</td>'+user+'</table>'									
									+computer+									
									'</div></div>'));
																
								});
							});
																					
						});
						

						
						
						
						$('.form_date').datetimepicker({
					        language:  'en',
					        weekStart: 1,
					        todayBtn:  1,
							autoclose: 1,
							todayHighlight: 1,
							startView: 2,
							minView: 2,
							forceParse: 0,
							format: 'yyyy-mm-dd',
					    });

						// reset data when modal hides
						$('#order-add-modal').on(
								'hidden.bs.modal',
								function() {
								});
						

						$('#order-analysis-modal').on('shown.bs.modal', function() {
							
					    	$.ajax({
								url : 'order/computers?computerId=0',
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							}).done(function(data) {
								var currentcomputername = $('#order-analysis-modal #myModalLabel').data().mode;
								$.each(data,function(i,computer) {
																								
								});
								
								console.log(price);
								var customername = $('#current_customer_name').text();
								var customerindustry = $('#current_customer_industry').text();
								var customertargetprice = $('#current_customer_targetprice').text();
								$('#currentcustomerindustry-modal').text(customername +"'s "+ customerindustry);
								$('#currentcustomertargetprice-modal').text(customername +"'s " + customertargetprice);
								
								
								var lineChartData = {
										labels : ["T440s","T450s","T460s","X1 Carbon","X250","E450","E455","E550","E555","L450","W530","Yoga 14"],
										datasets : [
											{
												label: "My First dataset",
												fillColor : "rgba(220,220,220,0.2)",
												strokeColor : "rgba(220,220,220,1)",
												pointColor : "rgba(220,220,220,1)",
												pointStrokeColor : "#fff",
												pointHighlightFill : "#fff",
												pointHighlightStroke : "rgba(220,220,220,1)",
												data : [price[0],price[1],price[2],price[3],price[4],price[5],price[6],price[7],price[8],price[9],price[10],price[11]]
											},
											{
												label: "My Second dataset",
												fillColor : "rgba(151,187,205,0.2)",
												strokeColor : "rgba(151,187,205,1)",
												pointColor : "rgba(151,187,205,1)",
												pointStrokeColor : "#fff",
												pointHighlightFill : "#fff",
												pointHighlightStroke : "rgba(151,187,205,1)",
												data : [currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice,currenttargetprice]
											}
										]

									}

								
									var ctx = document.getElementById("canvas_flow").getContext("2d");
									window.myLine = new Chart(ctx).Line(lineChartData, {
										responsive: true
									});
									
									
									var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
                                    var soldNumber = new Array(randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor(),randomScalingFactor());
                                    soldNumber[currentindustry] = Math.round(Math.random()*100) + 125;
                                    
									var barChartData = {
										labels : ["Agriculture","Mining","Manufacturing","Electricity","Construction","Retail Trade","Information","Financial","Real Estate","Reserach","Education","Government","Health"],
										datasets : [
											{
												fillColor : "rgba(151,187,205,0.5)",
												strokeColor : "rgba(151,187,205,0.8)",
												highlightFill : "rgba(151,187,205,0.75)",
												highlightStroke : "rgba(151,187,205,1)",
												data : [soldNumber[0],soldNumber[1],soldNumber[2],soldNumber[3],soldNumber[4],soldNumber[5],soldNumber[6],soldNumber[7],soldNumber[8],soldNumber[9],soldNumber[10],soldNumber[11],soldNumber[12]]
											}
										]

									}
								
										var ctx = document.getElementById("canvas_chart").getContext("2d");								    
										window.myBar = new Chart(ctx).Bar(barChartData, {
											responsive : true
										});
																																
							});
							
							

							
							
						});
						
						$('#order-analysis-modal').on('hidden.bs.modal', function() {
						   window.myBar.destroy(); 		
						});
						
						
						$.fn.dataTable.ext.search.push(
							    function( settings, data, dataIndex ) {
							        var min = parseInt( $('#min').val(), 10 );
							        var max = parseInt( $('#max').val(), 10 );
							        var price = parseFloat( data[6] ) || 0; // use data for the age column
							 
							        if ( ( isNaN( min ) && isNaN( max ) ) ||
							             ( isNaN( min ) && price <= max ) ||
							             ( min <= price   && isNaN( max ) ) ||
							             ( min <= price   && price <= max ) )
							        {
							            return true;
							        }
							        return false;
							    }
							);
						
			
						
							ComputerSales.dataTable = $('#computer-table')
						.DataTable(
								{
									'ajax' : {
										url : 'order/computers?computerId=0',
										type : 'GET',
										contentType : "application/json",						
										dataSrc : "",

									},
									columns : [ 
									{
						                "className":      'details-control',
						                "orderable":      false,
						                "data":           null,
						                "defaultContent": ''
									},
									{
			                            data : 'computername'
									}, {
										data : 'series'
									}, {
										data :  'processor'
									}, {
										data : 'memory'
									}, {
										data : 'display'
									}, {
										data : 'price'
									} ],
									select : 'single',
									filter : true,
									bDestroy: true,
									order : [[1, 'asc']],
									dom: '<"top">rt<"bottom"flpi><"clear">'
									//dom: '<"search"fl>rt<"bottom"ip><"clear">'
								});
							
						    $('#min, #max').keyup( function() {
						    	ComputerSales.dataTable.draw();
						    } );
						
					    $('#computer-table tbody').on('click', 'td.details-control', function () {
					        var tr = $(this).closest('tr');
					        var row = ComputerSales.dataTable.row( tr );
					        var selectedData = row.data();
					 
					        if ( row.child.isShown() ) {
					            // This row is already open - close it
					            row.child.hide();
					           // $('#canvas-holder-template').remove();
					            tr.removeClass('shown');
					        }
					        else {
					            // Open this row
					        	$('#canvas-holder-template').html('<canvas id="chart-area-template_' +selectedData.id +'" style="border: 1px; width: 100%; height: 100%;"></canvas>');					        	
					            row.child( format(row.data()) ).show();
					            tr.addClass('shown');
					            
							    var usageData = new Array();
							    usageData[7] = new Array(65,11,9,13,2);
							    usageData[8] = new Array(27,29,21,14,9);
							    usageData[9] = new Array(49,5,2,26,18);
							    usageData[10] = new Array(27,25,20,26,2);
							    usageData[11] = new Array(56,18,12,5,9);
							    usageData[12] = new Array(36,24,10,17,13);
							    usageData[13] = new Array(46,18,22,5,9);
							    usageData[14] = new Array(36,20,8,21,15);
							    usageData[15] = new Array(27,15,30,16,12);
							    usageData[16] = new Array(37,25,10,16,12);
							    usageData[17] = new Array(32,20,15,26,7);
							    usageData[18] = new Array(20,25,5,33,17);
							    usageData[19] = new Array(33,25,6,26,10);
							    usageData[20] = new Array(32,20,12,16,20);
							    usageData[21] = new Array(27,20,17,21,15);
							    usageData[22] = new Array(38,18,15,22,9);
					            
								var doughnutDataTemplate = [
										    				{
										    					value: usageData[selectedData.id][0],
										    					color:"#F7464A",
										    					highlight: "#FF5A5E",
										    					label: "Internet"
										    				},
										    				{
										    					value: usageData[selectedData.id][1],
										    					color: "#46BFBD",
										    					highlight: "#5AD3D1",
										    					label: "Writing/Editing"
										    				},
										    				{
										    					value: usageData[selectedData.id][2],
										    					color: "#FDB45C",
										    					highlight: "#FFC870",
										    					label: "Playing games"
										    				},
										    				{
										    					value: usageData[selectedData.id][3],
										    					color: "#949FB1",
										    					highlight: "#A8B3C5",
										    					label: "Office software"
										    				},
										    				{
										    					value: usageData[selectedData.id][4],
										    					color: "#4D5360",
										    					highlight: "#616774",
										    					label: "Research and Development"
										    				}

										    			];
										                   
			                                                var canvas_id = "chart-area-template_"+selectedData.id;
										    				var ctx = document.getElementById(canvas_id).getContext("2d");
										    				console.log(ctx);
										    				window.myDoughnuttemplate = new Chart(ctx).Doughnut(doughnutDataTemplate, {});
					            
					        }
					    });
					    

						//$('#order-add-modal #myModalLabel').data().mode = 'add';
					};
					initPage();

				});

ComputerSales.addEditOrder = function(event) {
	var formData = $('#order-form').serializeObject(); 

	
	var computerlist = new Array();
	var computer = {};
	computer.computerId = formData.computerId_0;
	computer.number = formData.number_0;
	computerlist.push(computer);
	var computer_1 = {};
	computer_1.computerId = formData.computerId_1;
	computer_1.number = formData.number_1;
	computerlist.push(computer_1);
	
	formData.computerlist=computerlist;
	formData.number_0=undefined;
	formData.computerId_0=undefined;
	formData.number_1=undefined;
	formData.computerId_1=undefined;
	formData.number=undefined;
	formData.computerId=undefined;
	
	
	formData = JSON.stringify(formData);
	console.log(formData)
	// modify role object to become an object if only one is selected

	//var url = 'order/' + $('#order-add-modal #myModalLabel').data().mode
		//	+ 'order';

	$.ajax({
		url : 'order/addorder',
		data : formData,
		type : 'POST',
		contentType : "application/json",
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#order-add-modal').modal('hide');
		$('#order-table').dataTable().fnReloadAjax();

	});
};

ComputerSales.deleteOrder = function(evt) {
	var selectedId = ComputerSales.dataTable.row('.selected').data().id;
	$.ajax({
		url : "order/delete?id=" + selectedId,
		type : 'DELETE',
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#order-delete-modal').modal('hide');
		$('#order-table').dataTable().fnReloadAjax();
	});
}