$(document)
		.ready(
				function() {
					var initPage = function() {

						ComputerSales.dataTable = $('#customer-table')
								.DataTable(
										{
											// 'serverSide' : true,
											'ajax' : {
												url : 'customer/list?customerId=0',
												type : 'GET',
												contentType : "application/json",
												data : function(d) {
													// Modify data into what is
													// required by backend API
													// d.searchParam =
													// d.search.value;
													delete (d.search);
													delete (d.columns);
													delete (d.order);
													return JSON.stringify(d);
												},
												dataSrc : ""
											},
											columns : [  {
												data : 'id',visible : false
											}, {
												data : 'customername'
											}, {
												data : 'employee_num'
											}, {
												data : 'level'
											}, {
												data : 'industry'
											},{
												data : 'ordernumber'
											},{
												data : 'computer'
											},{
												data : 'targetprice'											
											},{
												data : 'leadname',
												visible : false
											}, {
												data : 'birthday',
												visible : false
											}, {
												data : 'habit',
												visible : false
											}, {
												data : 'telephone',
												visible : false
											}, {
												data : 'address',
												visible : false
											},

											{
												data : null
											},

											],
											"columnDefs" : [ {
												"targets" : -1,
												"data" : null,
												"defaultContent" : '<button class="btn btn-default btn-md" data-toggle="modal" data-target="#customer-view-modal"><span class="glyphicon glyphicon-phone-alt" aria-hidden="true"></span>Contact Info</button>'
											} ],
											select : 'single',
											filter : true
										});

						$
								.ajax({
									url : 'customer/levels',
									type : 'GET',
									contentType : "application/json",
									data : function(d) {
										JSON.stringify(d);
									}
								})
								.done(
										function(data) {
											var levelSelects = $('.level-selects');
											$
													.each(
															data,
															function(i, level) {
																levelSelects
																		.append($('<option data-display = "'
																				+ level.level
																				+ '" value="'
																				+ level.id
																				+ '">'
																				+ level.level
																				+ '</li>'));
															});
										});

						$
								.ajax({
									url : 'customer/industries',
									type : 'GET',
									contenttype : "application/json",
									data : function(d) {
										JSON.stringify(d);
									}
								})
								.done(
										function(data) {
											var industrySelects = $('.industry-selects');
											$
													.each(
															data,
															function(i,
																	industry) {
																industrySelects
																		.append($('<option data-display = "'
																				+ industry.industry
																				+ '" value="'
																				+ industry.id
																				+ '">'
																				+ industry.industry
																				+ '</li>'));
															});
										});
						
						
						$
						.ajax({
							url : 'order/computers?computerId=0',
							type : 'GET',
							contenttype : "application/json",
							data : function(d) {
								JSON.stringify(d);
							}
						})
						.done(
								function(data) {
									var industrySelects = $('.computer-selects');
									$
											.each(
													data,
													function(i,
															computer) {
														industrySelects
																.append($('<option data-display = "'
																		+ computer.computername
																		+ '" value="'
																		+ computer.id
																		+ '">'
																		+ computer.computername
																		+ '</li>'));
													});
								});

						$('#customer-add-button').click(
								ComputerSales.addEditCustomer);
						$('#customer-delete-button').click(
								ComputerSales.deleteCustomer);

						// When edit button is clicked initialize the dialog
						$('#customer-edit-modal-btn')
								.on(
										'click',
										function() {
											var selectedData = ComputerSales.dataTable
													.row('.selected').data();
											$('#id').prop('readonly', true);
											$('#customer-id-form').show();
											$('#customer-add-modal #myModalLabel').data().mode = 'update';
											$('#customer-add-modal #myModalLabel').html('Edit employee');
											$('#id').val(selectedData.id);
											$('#customername').val(
													selectedData.customername);
											$('#employee_num').val(
													selectedData.employee_num);
											$('#ordernumber').val(selectedData.ordernumber);
											$(
													'#level option[data-display="'
															+ selectedData.level
															+ '"]').attr(
													'selected', 'selected');
											$(
													'#industry option[data-display="'
															+ selectedData.industry
															+ '"]').attr(
													'selected', 'selected');
											$(
													'#computer option[data-display="'
															+ selectedData.computer
															+ '"]').attr(
													'selected', 'selected');
											// $('#password').val(selectedData.password);
											$('#leadname').val(selectedData.leadname);
											$('#birthday').val(ChangeDate(new Date(selectedData.birthday)));
											$('#habit').val(selectedData.habit);
											$('#telephone').val(selectedData.telephone);
											$('#address').val(selectedData.address);
											$('#targetprice').val(selectedData.targetprice);
										});

						ComputerSales.dataTable.on('click', 'button',
								function() {
									var selectedData = ComputerSales.dataTable
											.row($(this).parents('tr')).data();
								//	alert(selectedData.id + "'s salary is: "
								//			+ selectedData.employee_num);
									$('#leadname1').prop('readonly', true);
									$('#birthday1').prop('readonly', true);
									$('#habit1').prop('readonly', true);
									$('#telephone1').prop('readonly', true);
									$('#address1').prop('readonly', true);
									$('#leadname1').val(selectedData.leadname);
									$('#birthday1').val(ChangeDate(new Date(selectedData.birthday)));
									$('#habit1').val(selectedData.habit);
									$('#telephone1').val(selectedData.telephone);
									$('#address1').val(selectedData.address);
								});
						$('.form_date').datetimepicker({
					        language:  'en',
					        weekStart: 1,
					        todayBtn:  1,
							autoclose: 1,
							todayHighlight: 1,
							startView: 2,
							minView: 2,
							forceParse: 0
					    });

						// Disable delete button if nothing selected
						ComputerSales.dataTable.on('select', function() {
							$('#customer-open-delete-modal-btn').prop(
									'disabled', false);
							$('#customer-edit-modal-btn').prop('disabled',
									false);
						});

						ComputerSales.dataTable.on('deselect', function() {
							$('#customer-open-delete-modal-btn').prop(
									'disabled', true);
							$('#customer-edit-modal-btn')
									.prop('disabled', true);
						});

						ComputerSales.dataTable.on('draw', function() {
							$('#customer-open-delete-modal-btn').prop(
									'disabled', true);
							$('#customer-edit-modal-btn')
									.prop('disabled', true);
						});
						// reset data when modal hides
						$('#customer-add-modal').on(
								'hidden.bs.modal',
								function() {
									$('#id').prop('readonly', false);
									$('#customer-add-modal #myModalLabel')
											.data().mode = 'add';
									$('#customer-add-modal #myModalLabel')
											.html('Add new customer');
									$('#customer-form')[0].reset();
								});

						$('#customer-add-modal-btn').on('click', function() {
							$('#customer-id-form').hide();
						});

						$('#customer-add-modal #myModalLabel').data().mode = 'add';
					};
					initPage();

				});

ComputerSales.addEditCustomer = function(event) {
	var formData = $('#customer-form').serializeObject();
	var d = new Date(formData.birthday);
	formData.birthday = d;
	formData = JSON.stringify(formData);
	console.log(formData)
	// modify role object to become an object if only one is selected

	var url = 'customer/' + $('#customer-add-modal #myModalLabel').data().mode
			+ 'customer';

	$.ajax({
		url : url,
		data : formData,
		type : 'POST',
		contentType : "application/json",
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#customer-add-modal').modal('hide');
		$('#customer-table').dataTable().fnReloadAjax();

	});
};

ComputerSales.deleteCustomer = function(evt) {
	var selectedId = ComputerSales.dataTable.row('.selected').data().id;
	$.ajax({
		url : "customer/delete?id=" + selectedId,
		type : 'DELETE',
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#customer-delete-modal').modal('hide');
		$('#customer-table').dataTable().fnReloadAjax();
	});
}