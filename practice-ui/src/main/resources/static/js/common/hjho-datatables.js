/**
 * 공통 Java Script DataTable function 
 */
var DataTableUtils = function() {
	var lengthMenu = [[10, 20, 40, 80], [10, 20, 40, '80']];
	var processingHtml = '';
	processingHtml += '<div class="d-flex justify-content-center">';
	processingHtml +=     '<div class="spinner-border text-success" role="status" aria-hidden="true"></div>';
	processingHtml += '</div>';
	var language = {
		    "decimal"       : "",
		    "emptyTable"    : "검색 결과가 없습니다.",
		    "info"          : "총 _TOTAL_ 중  _START_ 부터 _END_ 건",
		    "infoEmpty"     : "총 0 건",
		    "infoFiltered"  : "(filtered from _MAX_ total entries)",
		    "infoPostFix"   : "",
		    "thousands"     : ",",
		    "lengthMenu"    : "_MENU_ 건 씩",
		    "loadingRecords": "로딩 중...",
		    "processing"    : processingHtml,
		    "search"        : "검색:",
		    "zeroRecords"   : "No matching records found",
		    "paginate" : {
		        "first"   : "처음",
		        "last"    : "마지막",
		        "next"    : "다음",
		        "previous": "이전"
		    },
		    "aria" : {
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
				"destroy": true,
				"serverSide": false,
			    "paging": true,
			    "lengthMenu": lengthMenu,
			    "language" : language,
			    "autoWidth": false,
			    "ordering": false,
			    "select": "single",
			    "searching" : false,
			    "data": []
			});
		},
		get : function(data) {
			
			// 포멧별 컬럼 렌더링
			$.each(data.columns, function(index, item) {
				// column format type
				var type = item.format;
				
				if(StringUtils.isNotEmpty(type)) {
					switch(type) {
						case "dtm"  : // 일시 
							item["render"] = function(data, type, row) {
								return DateUtils.dtmFormat(data);
							}
							break;
						case "date" : // 일자
							item["render"] = function(data, type, row) {
								return DateUtils.dateFormat(data);
							}
							break;
						case "time" : // 시간
							item["render"] = function(data, type, row) {
								return DateUtils.timeFormat(data);
							}
							break;
						case "money" : // 돈, 콤마
							item["render"] = function(data, type, row) {
								return StringUtils.commaFormat(data);
							}
							break;
					}
				}
			});
			
			// 데이터 테이블 RUN
			$(data.tableId).dataTable( {
				"destroy": true,
				"serverSide": true,
				"processing": true,
				"paging": true,
				"pagingType" : "full_numbers", // simple, simple_numbers, full, full_numbers
				"ordering": (data.orderYn == "N") ? false : true,
				"autoWidth": false,
				"select": "single",
				"searching" : false,
				"lengthMenu": lengthMenu,
				"language" : language,
				"ajax" : {
					"url": data.url,
					"type" : "GET",
					"data" : function(d) {
						if(StringUtils.isEmpty(data.param)) {
							data.param = {};
						}
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
					"dataSrc": "result",
					"error": function (xhr, error, code) {
						AjaxUtils.error(xhr.responseJSON);
					}
				},
				"columns" : data.columns,
				"drawCallback": function(settings) {
					if(typeof data.callback == "function") {
	     			   	data.callback(settings);
					}
    			}
			});
		},
		refrash : function(tableId) {
			var pages = $(tableId).dataTable().api().page.info().pages;
			if(pages != 0) {
				$(tableId).dataTable().api().ajax.reload(null, false);
			} else {
				$(tableId).dataTable().api().ajax.reload(null, false);
			} 
		},
		isSeleted : function(tableId, tr) {
			if($(tr).attr('class').indexOf('selected') > -1) {
				$(tr).removeClass('selected');
				return true;
				
			} else {
				$(tableId + ' tbody tr').removeClass('selected');
				$(tr).addClass('selected');
				return false;
			}
		}
	}
}();

