$(document).ready(function() {
	var initPage = function() {
//		switchActiveTab('nav-office');

		ComputerSales.dataTable = $('#strategy-table').DataTable({
//			'serverSide' : true,
			'ajax' : {
				url : 'list',
				type : 'GET',
				contentType : "application/json",
				data: function ( d ) {
					// send only data required by backend API
					delete(d.columns);
					delete(d.order);
					delete(d.search);
			      return JSON.stringify(d);
			    },
			    dataSrc: ""
			},
			columns: [
	          { data: 'id' },
	          { data: 'type'},
	          { data: 'name' }
			],
			select: "single",
			searching: true

		});

		$('#strategy-add-button').click(ComputerSales.addStrategy);
		$('#strategy-delete-button').click(ComputerSales.deleteStrategy);
		
		// disable delete button if nothing selected
		ComputerSales.dataTable.on('select', function () {
			$('#strategy-open-delete-modal-btn').prop('disabled', false);
			$('#strategy-edit-modal-btn').prop('disabled',false);	
		});
		
		ComputerSales.dataTable.on('deselect', function () {
			$('#strategy-open-delete-modal-btn').prop('disabled', true);
			$('#strategy-edit-modal-btn').prop('disabled',true);
	    });

		ComputerSales.dataTable.on('draw', function () {
			$('#strategy-open-delete-modal-btn').prop('disabled', true);
			$('#strategy-edit-modal-btn').prop('disabled',true);
	    });
		
		// When edit button is clicked initialize the dialog
        $('#strategy-edit-modal-btn').on('click', function() {
            var selectedData = ComputerSales.dataTable.row('.selected').data();
            $('#strategy-id-form').show();
            $('#strategy-id').prop('readonly',true);
            $('#strategy-add-modal #myModalLabel').data().mode = 'update';
            $('#strategy-add-modal #myModalLabel').html('Edit strategy');
            $('#strategy-id').val(selectedData.id);
            $('#strategy-type').val(selectedData.type);
            $('#strategy-name').val(selectedData.name);
            //$('#office option[data-display='+selectedData.officeName+']').attr('selected', 'selected');
            // $('#password').val(selectedData.password);
        });
        
        $('#strategy-add-modal-btn').on('click', function() {
        	$('#strategy-id-form').hide();
        });
        // reset data when modal hides
        $('#strategy-add-modal').on('hidden.bs.modal', function() {
        	//$('#strategy-id').hide();
            $('#strategy-add-modal #myModalLabel').data().mode = 'add';
            $('#strategy-add-modal #myModalLabel').html('Add new strategy');
            $('#strategy-form')[0].reset();
        });

        $('#strategy-add-modal #myModalLabel').data().mode = 'add';
	};

	initPage();
});

ComputerSales.addStrategy = function(evt) {
	var formData = $('#strategy-form').serializeObject();
	
	var url = 'strategy' + $('#strategy-add-modal #myModalLabel').data().mode;

	$.ajax({
		url : url,
		data : JSON.stringify(formData),
		type : 'POST',
		contentType : "application/json",
		xhrFields: {
	      withCredentials: true
	   }
	}).done(function() {
		$('#strategy-add-modal').modal('hide');
		$('#strategy-table').dataTable().fnReloadAjax();		
	});
};

ComputerSales.deleteStrategy = function(evt) {
	var selectedId = ComputerSales.dataTable.data()[ComputerSales.dataTable.row('.selected')[0]].id;
	$.ajax({
		url : "strategydelete?id=" + selectedId,
		type : 'DELETE',
		xhrFields: {
	      withCredentials: true
	   }
	}).done(function() {
		$('#strategy-delete-modal').modal('hide');
		$('#strategy-table').dataTable().fnReloadAjax();
		
	});
};