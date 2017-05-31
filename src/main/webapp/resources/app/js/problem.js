$(document)
		.ready(
				function() {
					var initPage = function() {

						ComputerSales.dataTable = $('#problem-table')
								.DataTable(
										{
											// 'serverSide' : true,
											'ajax' : {
												url : "problem/list?typeId="+$('#problem-type-filter').val(),
												type : 'GET',
												contentType : "application/json",
		
						
												dataSrc : ""
											},
											columns : [  {
												data : 'id'
											}, {
												data : 'type'
											}, {
												data : 'description'
											}, {
												data : 'solution'
											} ],
											select : 'single',
											filter : true
										});

						$
								.ajax({
									url : 'problem/types',
									type : 'GET',
									contentType : "application/json",
									data : function(d) {
										JSON.stringify(d);
									}
								})
								.done(
										function(data) {
											var levelSelects = $('.type-selects');
											$
													.each(
															data,
															function(i, type) {
																levelSelects
																		.append($('<option data-display = "'
																				+ type.name
																				+ '" value="'
																				+ type.id
																				+ '">'
																				+ type.name
																				+ '</li>'));
															});
										});


						$('#problem-add-button').click(
								ComputerSales.addEditProblem);
						$('#problem-delete-button').click(
								ComputerSales.deleteProblem);
						$('#problem-type-filter').change(function() {
							$('#problem-table').dataTable().fnReloadAjax("problem/list?typeId="+$('#problem-type-filter').val()); 
							});

						// When edit button is clicked initialize the dialog
						$('#problem-edit-modal-btn')
								.on(
										'click',
										function() {
											var selectedData = ComputerSales.dataTable
													.row('.selected').data();
											$('#id').prop('readonly', true)
											$('#problem-add-modal #myModalLabel').data().mode = 'update';
											$('#problem-add-modal #myModalLabel').html('Edit Problem');
											$('#id').val(selectedData.id);
											$('#description').val(
													selectedData.description);
											$('#solution').val(
													selectedData.solution);
											$('#type option[data-display="'
															+ selectedData.type
															+ '"]').attr(
													'selected', 'selected');
										});

						// Disable delete button if nothing selected
						ComputerSales.dataTable.on('select', function() {
							$('#problem-open-delete-modal-btn').prop(
									'disabled', false);
							$('#problem-edit-modal-btn').prop('disabled',
									false);
						});

						ComputerSales.dataTable.on('deselect', function() {
							$('#problem-open-delete-modal-btn').prop(
									'disabled', true);
							$('#problem-edit-modal-btn')
									.prop('disabled', true);
						});

						ComputerSales.dataTable.on('draw', function() {
							$('#problem-open-delete-modal-btn').prop(
									'disabled', true);
							$('#problem-edit-modal-btn')
									.prop('disabled', true);
						});
						// reset data when modal hides
						$('#problem-add-modal').on(
								'hidden.bs.modal',
								function() {
									$('#id').prop('readonly', false);
									$('#problem-add-modal #myModalLabel')
											.data().mode = 'add';
									$('#problem-add-modal #myModalLabel')
											.html('Add new problem');
									$('#problem-form')[0].reset();
								});

						$('#problem-add-modal-btn').on('click', function() {
							$('#problem-id-form').hide();
						});

						$('#problem-add-modal #myModalLabel').data().mode = 'add';
					};
					initPage();

				});

ComputerSales.addEditProblem = function(event) {
	var formData = $('#problem-form').serializeObject();
	formData = JSON.stringify(formData);
	console.log(formData)
	// modify role object to become an object if only one is selected

	var url = 'problem/' + $('#problem-add-modal #myModalLabel').data().mode
			+ 'problem';

	$.ajax({
		url : url,
		data : formData,
		type : 'POST',
		contentType : "application/json",
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#problem-add-modal').modal('hide');
		$('#problem-table').dataTable().fnReloadAjax();

	});
};

ComputerSales.deleteProblem = function(evt) {
	var selectedId = ComputerSales.dataTable.row('.selected').data().id;
	$.ajax({
		url : "problem/delete?id=" + selectedId,
		type : 'DELETE',
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#problem-delete-modal').modal('hide');
		$('#problem-table').dataTable().fnReloadAjax();
	});
}