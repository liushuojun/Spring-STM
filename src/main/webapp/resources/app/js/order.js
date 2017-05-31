$(document)
		.ready(
				function() {
					var initPage = function() {
						

						var computernumbers = new Array('#computer_number_1','#computer_number_2','#computer_number_3','#computer_number_4','#computer_number_5');
						var computeridhtml = new Array('#computer_1','#computer_2','#computer_3','#computer_4','#computer_5');
						var numberhtml = new Array('#number_1','#number_2','#number_3','#number_4','#number_5');
						$.each(computernumbers, function(i,computernum) {
							$(computernum).hide();
						}); 
						var numberinorder;
						ComputerSales.dataTable = $('#order-table')
								.DataTable(
										{
											'ajax' : {
												url : 'order/list?typeId='+$('#search-text').val(),
												type : 'GET',
												contentType : "application/json",						
												dataSrc : ""
											},
											columns : [  {
					                            data : 'id'
											}, {
												data : 'customer'
											}, {
												data : 'builddate'
											}, {
												data : 'status'
											} ],
											select : 'single',
											filter : false
										});

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
											//ComputerSales.dataTable.row(':eq(12)', { page: 'current' }).select();
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
							url : 'customer/list?customerId='+$('#customer').val(),
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
							url : 'order/computers?computerId='+$('#computer_1').val(),
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
						
						

						

					//	$('#order-add-button').click(
					//			ComputerSales.addEditOrder);
						$('#order-delete-button').click(
								ComputerSales.deleteOrder);
						$('#status-change-button').click(
								ComputerSales.EditOrder);
						$('#report-add-button').click(
								ComputerSales.addReport);
	

						// Used for view specific computer details
						$('#computer-view-button_1')
								.on(
										'click',
										function() {
											
											$.ajax({
												url : 'order/computers?computerId='+$('#computer_1').val(),
												type : 'GET',
												contentType : "application/json",
												data : function(d) {
													JSON.stringify(d);
												}												
											}).done(function(data) {
												$('#computername').val(data[0].computername);
												$('#series').val(data[0].series);
												$('#computerprice').val(data[0].price);
												$('#processor').val(data[0].processor);
												$('#system').val(data[0].system);
												$('#memory').val(data[0].memory);
												$('#display').val(data[0].display);
												$('#computer-view-modal').modal();
											});

										});
						$('#computer-view-button_2')
						.on(
								'click',
								function() {
									
									$.ajax({
										url : 'order/computers?computerId='+$('#computer_2').val(),
										type : 'GET',
										contentType : "application/json",
										data : function(d) {
											JSON.stringify(d);
										}												
									}).done(function(data) {
										$('#computername').val(data[0].computername);
										$('#series').val(data[0].series);
										$('#computerprice').val(data[0].price);
										$('#processor').val(data[0].processor);
										$('#system').val(data[0].system);
										$('#memory').val(data[0].memory);
										$('#display').val(data[0].display);
										$('#computer-view-modal').modal();
									});

								});
						$('#computer-view-button_3')
						.on(
								'click',
								function() {
									
									$.ajax({
										url : 'order/computers?computerId='+$('#computer_3').val(),
										type : 'GET',
										contentType : "application/json",
										data : function(d) {
											JSON.stringify(d);
										}												
									}).done(function(data) {
										$('#computername').val(data[0].computername);
										$('#series').val(data[0].series);
										$('#computerprice').val(data[0].price);
										$('#processor').val(data[0].processor);
										$('#system').val(data[0].system);
										$('#memory').val(data[0].memory);
										$('#display').val(data[0].display);
										$('#computer-view-modal').modal();
									});

								});
						// Used for view customer details;
						$('#customer-view-button')
						.on(
								'click',
								function() {
									
									$.ajax({
										url : 'customer/list?customerId='+$('#customer').val(),
										type : 'GET',
										contentType : "application/json",
										data : function(d) {
											JSON.stringify(d);
										}												
									}).done(function(data) {
										
										$('#customerId').val(data[0].id);
										$('#customername').val(
												data[0].customername);
										$('#employee_num').val(
												data[0].employee_num);
										$('#level').val(data[0].level);
										$('#industry').val(data[0].industry);
										$('#leadname').val(data[0].leadname);
										$('#birthday').val(ChangeDate(new Date(data[0].birthday)));
										$('#habit').val(data[0].habit);
										$('#telephone').val(data[0].telephone);
										$('#address').val(data[0].address);
										$("#customer-add-modal").modal();
									});

								});
						
						$('#report-add-modal-btn').on('click',function() {
							
							var selectedData = ComputerSales.dataTable.row('.selected').data();
							$('#orderId').val(selectedData.id);
							$('#orderId').prop('readonly',true);
														
						});
						
						$('#report-view-modal-btn').on('click',function() {
							var selectedData = ComputerSales.dataTable.row('.selected').data();
																				
							ComputerSales.reportTable = $('#report-table')
							.DataTable(
									{
										'ajax' : {
											url : 'order/viewreport?orderId=' + selectedData.id,
											type : 'GET',
											contentType : "application/json",						
											dataSrc : ""
										},
										columns : [  {
				                            data : 'id'
										}, {
											data : 'description'
										}, {
											data : 'solution'
										} ],
										select : 'single',
										bDestroy : true,
										filter : false
									});
							
							
						});
						

						// Disable delete button if nothing selected
						ComputerSales.dataTable.on('select', function() {
							$('#customer-view-button').prop('disabled',false);
							$('#computer-view-button').prop('disabled',false);
							$('#status-change-button').prop('disabled',false);
							$('#report-view-modal-btn').prop('disabled',false);
							$('#report-add-modal-btn').prop('disabled',false);
							
							var selectedData = ComputerSales.dataTable
							.row('.selected').data();
							var number = selectedData.computerlist.length;
							numberinorder = number;
							for(i=0;i<number;i++) {
								$(computernumbers[i]).show();
								$(computeridhtml[i]+' option[data-display="' + selectedData.computerlist[i].computername + '"]').attr('selected','selected');
								$(numberhtml[i]).val(selectedData.computerlist[i].number);
							}
							$('#id').prop('readonly', true)
							$('#builddate').prop('readonly',true);
							$('#reason').prop('readonly',true);
							$('#number').prop('readonly',true);
							//$('#computer').attr('disabled',true);
							$('#price').prop('readonly',true);
							//$('#user').attr('disabled',true);
							//$('#customer').attr('disabled',true);
							$('#id').val(selectedData.id);
							$('#builddate').val(selectedData.builddate);
							//$('#computer option[data-display="' + selectedData.computer + '"]').attr('selected','selected');
							//$('#computer').val(selectedData.computer);
							
							$('#customer option[data-display="' + selectedData.customer + '"]').attr('selected','selected');
							$('#user option[data-display="' + selectedData.user + '"]').attr('selected','selected');
							$('#status option[data-display="' + selectedData.status + '"]').attr('selected','selected');
							$('#price').val(selectedData.price);
							$('#reason').val(selectedData.reason);
						});

						ComputerSales.dataTable.on('deselect', function() {
							$('#customer-view-button').prop('disabled',true);
							$('#computer-view-button').prop('disabled',true);
							$('#status-change-button').prop('disabled',true);
							$('#report-view-modal-btn').prop('disabled',true);
							$('#report-add-modal-btn').prop('disabled',true);
						});

						ComputerSales.dataTable.on('draw', function() {
							$('#customer-view-button').prop('disabled',true);
							$('#computer-view-button').prop('disabled',true);
							$('#status-change-button').prop('disabled',true);
							$('#report-view-modal-btn').prop('disabled',true);
							$('#report-add-modal-btn').prop('disabled',true);
						});
						// reset data when modal hides
						$('#order-add-modal').on(
								'hidden.bs.modal',
								function() {
									$('#id').prop('readonly', false);
									$('#order-add-modal #myModalLabel')
											.data().mode = 'add';
									$('#order-add-modal #myModalLabel')
											.html('Add new order');
									$('#order-form')[0].reset();
								});
						$('#search-order-button').on('click',function() {							
							$.ajax({
								url : 'order/search?typeId='+$('#search-text').val(),
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							})
							.done(
									function(data) {
										$('#load-time').val(data.loadTime);
										$('#order-number').val(data.totalNum);
									});
							$('#order-table').dataTable().fnReloadAjax('order/list?typeId='+$('#search-text').val());
						});
						
						$('#generate-data-button').on('click',function() {
							$.ajax({
								url : 'order/generate',
								type : 'GET',
								contentType : "application/json",
								data : function(d) {
									JSON.stringify(d);
								}
							})
							.done(
									function(data) {
										alert( 'System alreay create'+ data +'order' );
									});
						});
				

						$('#order-add-modal-btn').on('click', function() {
							$('#order-id-form').hide();
						});

						//$('#order-add-modal #myModalLabel').data().mode = 'add';
					};
					initPage();

				});

ComputerSales.EditOrder = function(event) {
	var changestatusorder ={};
	var formData = $('#order-form').serializeObject();
	
	var selectedData = ComputerSales.dataTable.row('.selected').data();
	
	changestatusorder.id = formData.id;
	changestatusorder.builddate = formData.builddate;
	changestatusorder.price = formData.price;
	changestatusorder.reason = formData.reason;
	changestatusorder.statusId = formData.statusId;
	changestatusorder.customerId = formData.customerId;
	changestatusorder.userId = formData.userId;
	changestatusorder.computerlist = selectedData.computerlist;
	
	$.each(changestatusorder.computerlist,function(i,computernum){
		computernum.computername=undefined;
	} );
	


	
	console.log(changestatusorder)
	changestatusorder = JSON.stringify(changestatusorder);

	$.ajax({
		url : 'order/updatestatus',
		data : changestatusorder,
		type : 'POST',
		contentType : "application/json",
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#order-table').dataTable().fnReloadAjax();

	});
};

ComputerSales.addReport = function(event) {
	var formData = $('#report-form').serializeObject();
	
	console.log(formData)
	formData = JSON.stringify(formData);
	
	$.ajax({
		url : 'order/addreport',
		data : formData,
		type : 'POST',
		contentType : "application/json",
		xhrFields : {
			withCredentials : true
	}
 }).done(function() {
	 $('#report-add-modal').modal('hide');
 })
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