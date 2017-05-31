$(document).ready(function() {
	var initPage = function() {

    var computers = new Array();
    var currentorder ={};
    var users = new Array();
    var customers = new Array();
		
		$
		.ajax({
			url : 'ordernew/list',
			type : 'GET',
			contentType : "application/json",
			data : function(d) {
				JSON.stringify(d);
			}
		})
		.done(
				function(data) {
					var orderList = $('#order-list-group');
					$.each(data,function(i,order) {
						orderList.append($(
								'<a href="#" class="list-group-item list-group-item-success" id="' + order.id + '_order">' + '<h4 class="list-group-item-heading">'+ order.customer +'</h4>' +
								'<p class="list-group-item-text">'+ "Price : " + order.price + '</p>' +
								'<p class="list-group-item-text">'+"Create Date : " + order.builddate + '</p></a>'	
								));
					});
				});
		
		
		
		var current_order_id;
		var stafflow = new Array();
		stafflow[39] = new Array(2,0,1);
		stafflow[40] = new Array(1,0,2);
		stafflow[41] = new Array(2,0,1);
		stafflow[42] = new Array(1,0,2);
		stafflow[43] = new Array(2,0,1);
		stafflow[44] = new Array(1,0,2);
		stafflow[45] = new Array(2,0,1);
		stafflow[46] = new Array(1,0,2);
		stafflow[47] = new Array(2,0,1);
		stafflow[48] = new Array(1,0,2);
		stafflow[49] = new Array(2,0,1);
		stafflow[50] = new Array(1,0,2);
		stafflow[51] = new Array(2,0,1);
		stafflow[52] = new Array(1,0,2);


		
		var stafforderNumber = new Array();
		stafforderNumber[0] = new Array(8,7,2);
		stafforderNumber[1] = new Array(10,3,3);
		stafforderNumber[2] = new Array(9,6,2);
	    $('#order-list-group').on('click', 'a.list-group-item', function() {
	    	
	    	var order_id = parseInt($(this).attr('id').toString());
	    	current_order_id = order_id;
	    	$('.append-text').remove();
	    	computers = [];
	    	
	    	
			$.ajax({
				url : 'ordernew/list',
				type : 'GET',
				contentType : "application/json",
				data : function(d) {
					JSON.stringify(d);
				}
			}).done(function(data) {
				
				$.each(data,function(i,order) {
					if(order.id == order_id) {
						currentorder = order; 
						$('#current_order_customer_name').text(order.customer);
						$('#current_order_create_date').text("Create Date : "+order.builddate);
						$('#current_order_price').text(" Price :"+order.price);
						
						$.each(order.computerlist,function(j,computernum) {
							computers.push(computernum.computername);
							$('#current_order_id').append($('<p class="list-group-item-text append-text">'+ computernum.computername + " : " + computernum.number + '</p>'));
						});
						
						
						$.ajax({
							url : 'order/suggestedusers',
							type : 'GET',
							contentType : "application/json",
							data : function(d) {
								JSON.stringify(d);
							}
						}).done(function(data) {
							users.push(data[0]);
							users.push(data[1]);
							users.push(data[2]);
							
							//var randomScalingFactor = function(){ return Math.round(Math.random()*3)};
							if(current_order_id < 39 || current_order_id >52) {
								number1 = 2;
								number2 = 0;
								number3 = 1;
							} else {
								number1 = stafflow[current_order_id][0];
								number2 = stafflow[current_order_id][1];
								number3 = stafflow[current_order_id][2];
							}

							
							$('#suggestedstaff_1').text(data[number1].username);
							
							$('#suggestedstaff_2').text(data[number2].username);
							
							$('#suggestedstaff_3').text(data[number3].username);
						});
						
																			
					}	
										   
				});
				
			});
												    	
	    });
	    
	    
	    var currentSelectedstaff;
	  //-----------------------------------------------collapseOne module start-------------------------------------------------//
		var soldNumber = new Array();
		var orderinstaffnumber = new Array();
	    $("#collapseOne").on("shown.bs.collapse", function(){
	    	var currentstaff = $('#suggestedstaff_1').text();
	    	currentSelectedstaff = currentstaff;
	    	var currentcustomer =$('#current_order_customer_name').text();
	    	$('#collapseOne-order-title').html('<span class="glyphicon glyphicon-th-list"></span> Current orders of '+ currentstaff);
	    	$('#collapseOne-sold-number').html('<span class="glyphicon glyphicon-th-list"></span> Sold Number of '+ computers);
            $('#collapseOne-order-number').html('<span class="glyphicon glyphicon-th-list"></span> Order Number for '+currentcustomer);
			var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
			var randomScalingFactorsmall = function(){ return Math.round(Math.random()*3)};
            soldNumber = new Array(randomScalingFactor(),randomScalingFactor(),randomScalingFactor());
            orderinstaffnumber = new Array(randomScalingFactorsmall(),randomScalingFactorsmall(),randomScalingFactorsmall());
            if(currentstaff == "Salestaff-1"){
            	soldNumber[0] = Math.round(Math.random()*100) + 125;
            orderinstaffnumber[0] = Math.round(Math.random()*3) + 3;
            }
            else if(currentstaff == "Salestaff-2"){
            	soldNumber[1] = Math.round(Math.random()*100) + 125;
            orderinstaffnumber[1] = Math.round(Math.random()*3) + 3;
            }
            else {
            	soldNumber[2] = Math.round(Math.random()*100) + 125;
            orderinstaffnumber[2] = Math.round(Math.random()*3) + 3;
            }
            
            var current_customer = $('#current_order_customer_name').text();
            
            if(current_customer == 'Autodesk Asia Pte Ltd' || current_customer == 'Micron Semiconductor ') {
            	orderinstaffnumber[0] = 0;
            	orderinstaffnumber[1] = 0;
            	orderinstaffnumber[2] = 0;
            }
            	
            	
            
            
            
			var doughnutData = [
			    				{
			    					value: stafforderNumber[0][0],
			    					color:"#F7464A",
			    					highlight: "#FF5A5E",
			    					label: "Successful order"
			    				},
			    				{
			    					value: stafforderNumber[0][1],
			    					color: "#46BFBD",
			    					highlight: "#5AD3D1",
			    					label: "Proceeding order"
			    				},
			    				{
			    					value: stafforderNumber[0][2],
			    					color: "#FDB45C",
			    					highlight: "#FFC870",
			    					label: "Failed order"
			    				}

			    			];

			    				var ctx = document.getElementById("chart-piesucess-1").getContext("2d");
			    				window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
            
            
			var barChartData = {
				labels : ["Salestaff-1","Salestaff-2","Salestaff-3"],
				datasets : [
					{
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,0.8)",
						highlightFill : "rgba(151,187,205,0.75)",
						highlightStroke : "rgba(151,187,205,1)",
						data : [soldNumber[0],soldNumber[1],soldNumber[2]]
					}
				]

			}
		
				var ctx = document.getElementById("canvas_productsales-1").getContext("2d");								    
				window.myBarproduct = new Chart(ctx).Bar(barChartData, {
					responsive : true
				});
				
			var customerbarChartData = {
						labels : ["Salestaff-1","Salestaff-2","Salestaff-3"],
						datasets : [
							{
								fillColor : "rgba(151,187,205,0.5)",
								strokeColor : "rgba(151,187,205,0.8)",
								highlightFill : "rgba(151,187,205,0.75)",
								highlightStroke : "rgba(151,187,205,1)",
								data : [orderinstaffnumber[0],orderinstaffnumber[1],orderinstaffnumber[2]]
							}
						]

					}
				
					var ctx = document.getElementById("canvas_customersales-1").getContext("2d");								    
					window.myBar = new Chart(ctx).Bar(customerbarChartData, {
							responsive : true
						});
			
			
				

			    	
			$('#successful-percentage-1').text('The success rate for ' + currentstaff +" : " + "80%");
			
			    	
			    });
	    
//-----------------------------------------------collapseOne module end-------------------------------------------------//
//-----------------------------------------------collapseTwo module start-------------------------------------------------//
	    
	    $("#collapseTwo").on("shown.bs.collapse", function(){
	    	var currentstaff = $('#suggestedstaff_2').text();
	    	currentSelectedstaff = currentstaff;
	    	var currentcustomer =$('#current_order_customer_name').text();
	    	$('#collapseTwo-order-title').html('<span class="glyphicon glyphicon-th-list"></span> Current orders of '+ currentstaff);
	    	$('#collapseTwo-sold-number').html('<span class="glyphicon glyphicon-th-list"></span> Sold Number of '+ computers);
            $('#collapseTwo-order-number').html('<span class="glyphicon glyphicon-th-list"></span> Order Number for '+currentcustomer);
		/*	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
            var soldNumber = new Array(randomScalingFactor(),randomScalingFactor(),randomScalingFactor());
            soldNumber[0] = Math.round(Math.random()*100) + 125;*/
            
            
			var doughnutData = [
			    				{
			    					value: stafforderNumber[1][0],
			    					color:"#F7464A",
			    					highlight: "#FF5A5E",
			    					label: "Successful order"
			    				},
			    				{
			    					value: stafforderNumber[1][1],
			    					color: "#46BFBD",
			    					highlight: "#5AD3D1",
			    					label: "Proceeding order"
			    				},
			    				{
			    					value: stafforderNumber[1][2],
			    					color: "#FDB45C",
			    					highlight: "#FFC870",
			    					label: "Failed order"
			    				}

			    			];

			    				var ctx = document.getElementById("chart-piesucess-2").getContext("2d");
			    				window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
            
            
			var barChartData = {
				labels : ["Salestaff-1","Salestaff-2","Salestaff-3"],
				datasets : [
					{
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,0.8)",
						highlightFill : "rgba(151,187,205,0.75)",
						highlightStroke : "rgba(151,187,205,1)",
						data : [soldNumber[0],soldNumber[1],soldNumber[2]]
					}
				]

			}
		
				var ctx = document.getElementById("canvas_productsales-2").getContext("2d");								    
				window.myBarproduct = new Chart(ctx).Bar(barChartData, {
					responsive : true
				});
				
			var customerbarChartData = {
						labels : ["Salestaff-1","Salestaff-2","Salestaff-3"],
						datasets : [
							{
								fillColor : "rgba(151,187,205,0.5)",
								strokeColor : "rgba(151,187,205,0.8)",
								highlightFill : "rgba(151,187,205,0.75)",
								highlightStroke : "rgba(151,187,205,1)",
								data : [orderinstaffnumber[0],orderinstaffnumber[1],orderinstaffnumber[2]]
							}
						]

					}
				
					var ctx = document.getElementById("canvas_customersales-2").getContext("2d");								    
					window.myBar = new Chart(ctx).Bar(customerbarChartData, {
							responsive : true
						});
			
			
				

			    	
			$('#successful-percentage-2').text('The success rate for ' + currentstaff +" : " + "86%");
			
			    	
			    });
	    
//-----------------------------------------------collapseTwo module end-------------------------------------------------//
//-----------------------------------------------collapseThree module start-------------------------------------------------//
	    
	    $("#collapseThree").on("shown.bs.collapse", function(){
	    	
	    	var currentstaff = $('#suggestedstaff_3').text();
	    	currentSelectedstaff = currentstaff;
	    	//$('#order-analysis-modal #myModalLabel').data().mode = currentcomputername;
	    	var currentcustomer =$('#current_order_customer_name').text();
	    	$('#collapseThree-order-title').html('<span class="glyphicon glyphicon-th-list"></span> Current orders of '+ currentstaff);
	    	$('#collapseThree-sold-number').html('<span class="glyphicon glyphicon-th-list"></span> Sold Number of '+ computers);
            $('#collapseThree-order-number').html('<span class="glyphicon glyphicon-th-list"></span> Order Number for '+currentcustomer);
		/*	var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
            var soldNumber = new Array(randomScalingFactor(),randomScalingFactor(),randomScalingFactor());
            soldNumber[0] = Math.round(Math.random()*100) + 125;*/
            
            
			var doughnutData = [
			    				{
			    					value: stafforderNumber[2][0],
			    					color:"#F7464A",
			    					highlight: "#FF5A5E",
			    					label: "Successful order"
			    				},
			    				{
			    					value: stafforderNumber[2][1],
			    					color: "#46BFBD",
			    					highlight: "#5AD3D1",
			    					label: "Proceeding order"
			    				},
			    				{
			    					value: stafforderNumber[2][2],
			    					color: "#FDB45C",
			    					highlight: "#FFC870",
			    					label: "Failed order"
			    				}

			    			];

			    				var ctx = document.getElementById("chart-piesucess-3").getContext("2d");
			    				window.myDoughnut = new Chart(ctx).Doughnut(doughnutData, {responsive : true});
            
            
			var barChartData = {
				labels : ["Salestaff-1","Salestaff-2","Salestaff-3"],
				datasets : [
					{
						fillColor : "rgba(151,187,205,0.5)",
						strokeColor : "rgba(151,187,205,0.8)",
						highlightFill : "rgba(151,187,205,0.75)",
						highlightStroke : "rgba(151,187,205,1)",
						data : [soldNumber[0],soldNumber[1],soldNumber[2]]
					}
				]

			}
		
				var ctx = document.getElementById("canvas_productsales-3").getContext("2d");								    
				window.myBarproduct = new Chart(ctx).Bar(barChartData, {
					responsive : true
				});
				
			var customerbarChartData = {
						labels : ["Salestaff-1","Salestaff-2","Salestaff-3"],
						datasets : [
							{
								fillColor : "rgba(151,187,205,0.5)",
								strokeColor : "rgba(151,187,205,0.8)",
								highlightFill : "rgba(151,187,205,0.75)",
								highlightStroke : "rgba(151,187,205,1)",
								data : [orderinstaffnumber[0],orderinstaffnumber[1],orderinstaffnumber[2]]
							}
						]

					}
				
					var ctx = document.getElementById("canvas_customersales-3").getContext("2d");								    
					window.myBar = new Chart(ctx).Bar(customerbarChartData, {
							responsive : true
						});
			
			
				

			    	
			$('#successful-percentage-3').text('The success rate for ' + currentstaff +" : " + "81%");
			
			    	
			    });
	    
//-----------------------------------------------collapseThree module end-------------------------------------------------//
	    $(".collapse").on("hidden.bs.collapse", function(){
	    	window.myDoughnut.destroy();
	    	window.myBar.destroy();
	    	window.myBarproduct.destroy();
	    });
	    
		$('.btn-lg')
		.on(
				'click',
				function() {
					$('#confirm-assign-order-msg').text('Are you sure to assign the order to '+ currentSelectedstaff);
				});
		
		$.ajax({
			url : 'customer/list?customerId=0',
			type : 'GET',
			contentType : "application/json",
			data : function(d) {
				JSON.stringify(d);
			}
		}).done(function(data) {
			$.each(data,function(i,customer) {
				customers.push(customer);	
			});
		});
		
		$('#order-assign-button').on('click',function() {
			
			var approvalorder ={};
			var approvalorder = currentorder;
			var current_customer_ID;
			
			for(i=0; i<3; i++) {
				if(users[i].username == currentSelectedstaff)
					current_user_id = users[i].id;
			}
			
			$.each(customers,function(i,customer) {
				if(customer.customername == approvalorder.customer)
					current_customer_ID = customer.id;
			});
						
			approvalorder.userId = current_user_id;
			approvalorder.statusId = 3;
			
			approvalorder.status=undefined;
			//approvalorder.computer=undefined;
			approvalorder.user=undefined;
			approvalorder.customer=undefined;
			approvalorder.computer=undefined;
			approvalorder.number=undefined;
			approvalorder.customerId = current_customer_ID;
			
			$.each(approvalorder.computerlist,function(i,computernum) {
				computernum.computername = undefined;
			});
			
			approvalorder = JSON.stringify(approvalorder);
			
			$.ajax({
				url : 'order/approvalorder',
				data : approvalorder,
				type : 'POST',
				contentType : "application/json",
				xhrFields : {
					withCredentials : true
				}
			}).done(function() {
				$('#order-assign-modal').modal('hide');
				$('#order-list-group').fnReloadAjax();

			});
						
						
		});
		

	};

	initPage();
});
