//var switchActiveTab = function(id) {
//	$.each($('#book-store-navbar').children(), function(i, el) {
//		if (el.id === id) {
//			$(el).addClass('active');
//		} else {
//			$(el).removeClass('active');
//		}
//	});
//};

var ComputerSales = {};

function ChangeDate(date) {
	
	var monthNames = [
	                  "January", "February", "March",
	                  "April", "May", "June", "July",
	                  "August", "September", "October",
	                  "November", "December"
	                ];

	     
	                var day = date.getDate();
	                var monthIndex = date.getMonth();
	                var year = date.getFullYear();

	                console.log(day, monthNames[monthIndex], year);
	                return (day + ' ' + monthNames[monthIndex] + ' ' + year);
	
};

function ChangeDateTime(datetime) {
	if(datetime == null)
		return null;
	
	var minutes = datetime.getMinutes();
	minutes = minutes < 10 ? '0'+minutes : minutes;
	var hours = datetime.getHours();
	hours = hours ? hours : 12; // the hour '0' should be '12'
	hours = hours < 10 ? '0'+hours:hours;
	var day = datetime.getDate();
	day = day < 10 ? '0'+day:day;
	var month = datetime.getMonth() + 1;
	month = month < 10 ? '0'+ month : month;
	var year = datetime.getFullYear();
	
	return (year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ' ');
	
};
function ChangeToDate(date) {
	
}

jQuery.fn.serializeObject = function() {
	var arrayData, objectData;
	arrayData = this.serializeArray();
	objectData = {};

	$.each(arrayData, function() {
		var value;

		if (this.value != null) {
			value = this.value;
		} else {
			value = '';
		}

		if (objectData[this.name] != null) {
			if (!objectData[this.name].push) {
				objectData[this.name] = [ objectData[this.name] ];
			}

			objectData[this.name].push(value);
		} else {
			objectData[this.name] = value;
		}
	});

	return objectData;
};

$.extend( $.fn.dataTable.defaults, {
    searching: false,
    ordering:  false
} );
