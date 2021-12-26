/**
 * 공통 Java Script DataTable function 
 */
var DataTableUtils = function() {
	var lengthMenu = [[10, 20, 40, -1], [10, 20, 40, 'All']];
	var language = {
		    "decimal":        "",
		    "emptyTable":     "검색 결과가 없습니다.",
		    "info":           "총 _TOTAL_ 중  _START_ 부터 _END_ 건",
		    "infoEmpty":      "총 0 건",
		    "infoFiltered":   "(filtered from _MAX_ total entries)",
		    "infoPostFix":    "",
		    "thousands":      ",",
		    "lengthMenu":     "_MENU_ 건 씩",
		    "loadingRecords": "로딩 중...",
		    "processing":     "처리 중...",
		    "search":         "검색:",
		    "zeroRecords":    "No matching records found",
		    "paginate": {
		        "first":      "처음",
		        "last":       "마지막",
		        "next":       "다음",
		        "previous":   "이전"
		    },
		    "aria": {
		        "sortAscending":  ": activate to sort column ascending",
		        "sortDescending": ": activate to sort column descending"
		    }
	};
	
	return {
		/**
		 * function init
		 * parameter tableId
		 */
		init : function(tableId) {
			$(tableId).dataTable( {
				"serverSide": false,
			    "paging": true,
			    "lengthMenu": lengthMenu,
			    "language" : language,
			    "autoWidth": false,
			    "select": "single",
			    "data": []
			});
		},
		get : function(data) {
			$(data.tableId).dataTable( {
				"destroy": true,
				"serverSide": true,
				"processing": true,
				"paging": true,
				"pagingType" : "full_numbers", // simple, simple_numbers, full, full_numbers
				"ordering": true,
				"autoWidth": false,
				"select": "single",
				"lengthMenu": lengthMenu,
				"language" : language,
				"ajax" : {
					"url": data.url,
					"type" : "GET",
					"data" : function(d) {
						var pageInfo = $(data.tableId).DataTable().page.info();
						var orderInfo = $(data.tableId).DataTable().order()[0];
						d = data.param;
						d.page = pageInfo.page + 1;
						d.length = pageInfo.length;
						d.sortCol = orderInfo[0];
						d.sortType = orderInfo[1];
						return d;
					},
					"dataType": "JSON",
					"dataSrc": "result"
				},
				"columns" : data.columns
			});
		}
	}
}();

