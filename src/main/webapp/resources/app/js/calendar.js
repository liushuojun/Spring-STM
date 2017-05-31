$(document).ready(function() {

		$('#calendar').fullCalendar({
			theme: true,
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			defaultDate: '2015-11-16',
			timezone:'local',
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: function(start,end,timezone,callback) {
				$.ajax({
					url: 'calendar/events',
					type: 'GET',
					datatype: 'json',
					data: {
						//start:start.format(),
				        //end:end.format()
					},
					success: function(doc) {
						var events = [];
						
							$.each(doc,function(i,r){
								if(r.title.toString().includes('order Id')){
									events.push({
										id: r.id,
										title: r.title,
										start: r.start,
										end: r.end,
										feedback: r.feedback,
										details: r.details,
										user: r.user,
										color: 'red'
									});
									}
								else {	
								events.push({
									id: r.id,
									title: r.title,
									start: r.start,
									end: r.end,
									feedback: r.feedback,
									details: r.details,
									user: r.user
								});
								}
							});
						callback(events);
					}
				});
				
			$.ajax({
					url : 'calendar/users',
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
			},
			eventDrop: function( event, delta, revertFunc) { 
//				alert(event.title + "was dropped on" + event.start.format());
				var date = event.start.format();
				console.log(event);
				$.ajax({
					headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
					url:"calendar/updateevent",
					type: 'POST',
					datatype: 'json',
					data:JSON.stringify({
						id:event.id,
						title:event.title,
						start:event.start,
						end:event.end,
						feedback:event.feedback,
						details:event.details,
						user:event.user
					})
				}).done(function() {
					//alert(event.title + "was dropped on" + event.start.format());
				})
			},
			
			
		 eventClick : function(event, element) {

			$('#target-id-form').show();
			$('#id').prop('readonly', true)
			$('#target-add-modal #myModalLabel').data().mode = 'update';
			$('#target-add-modal #myModalLabel').html('View and Edit Target');
			$('#id').val(event.id);
			$('#title').val(event.title);
			$('#start').val(ChangeDateTime(new Date(event.start)));
			$('#user option[data-display="' + event.user + '"]').attr('selected','selected');
			
			if(event.title.toString().includes('order Id')){
				$('#order-link').show();
				var order_id = parseInt(event.title.toString().substr(9,3));
				$('#view-order-link').attr("href", base +"order?orderId="+order_id); 
                $('#user-select-form').hide();
			} else
			   {
				$('#order-link').hide();
				}

			if (event.end == null)
				$('#end').val();
			else
				$('#end').val(ChangeDateTime(new Date(event.end)));
			
			$('#details').val(event.details);
			if(event.title.toString().includes('progress report')) {
				$('#feedback-form-id').show()
				$('#title').prop('readonly', true);
				$('#details').prop('readonly', true);
				//$('#user').attr('disabled',true);
				$('#feedback').val(event.feedback);
			} else {
				$('#feedback-form-id').hide();
			}
			

			console.log(event.start);
			$('#target-add-modal').modal();

			$('#calendar').fullCalendar('updateEvent', event);
		},
		
		

		dayClick : function(date, jsEvent, view) {
			
			$('#user-select-form').show();
			$('#title').prop('readonly', false);
			$('#details').prop('readonly', false);
			$('#datetimepicker_start').datetimepicker('update', date.format());
			$('#datetimepicker_end').datetimepicker('update', date.format());
			$('#target-id-form').hide();
			$('#feedback-form-id').hide();
			$('#order-link').hide();
			$('#target-add-modal #myModalLabel').data().mode = 'add';
			$('#target-add-modal').modal();
		}
		
		});
		$('#target-add-button').click(
				ComputerSales.addEditTarget);
		
		$('.form_datetime').datetimepicker({
		    language:  'en',
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView : 0,
			forceParse: 0,
		    showMeridian: 1,	
		    format: 'yyyy-mm-dd hh:ii',
		    //initialDate: '2015-11-06'
		});
		$('#target-add-modal').on(
				'hidden.bs.modal',
				function() {
					$('#id').prop('readonly', false);
					$('#target-add-modal #myModalLabel')
							.data().mode = 'add';
					$('#target-add-modal #myModalLabel')
							.html('Add new Target');
					$('#order-link').hide();
					$('#target-form')[0].reset();
				});
	});

ComputerSales.addEditTarget = function() {
	//$('#user').attr('disabled',false);
	var formData = $('#target-form').serializeObject();
	var start = new Date(formData.start);
	formData.start = start;
	if(formData.end != null)
	{
		var end = new Date(formData.end);
		formData.end = end;
	}	
	formData = JSON.stringify(formData);
	

	var url = 'calendar/' + $('#target-add-modal #myModalLabel').data().mode + 'event';
	console.log(formData);
	$.ajax({
		url : url,
		data : formData,
		type : 'POST',
		contentType : "application/json",
		xhrFields : {
			withCredentials : true
		}
	}).done(function() {
		$('#target-add-modal').modal('hide');
		$('#calendar').fullCalendar('refetchEvents');
	});
}


