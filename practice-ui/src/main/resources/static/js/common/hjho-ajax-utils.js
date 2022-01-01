/**
 * 공통 Java Script AJAX function 
 */
var AjaxUtils = function() {
	return {
		INS : "/post",
		UPD : "/put",
		DEL : "/delete",
		getParam : function(formEl, before, after) {
			var disbleEl = $(formEl + ' input[disabled]');
			
			disbleEl.attr('disabled', false);
			
			if(typeof before == "function") {
				before();
			}
			
			var param = $(formEl).serialize();
			
			if(typeof after == "function") {
				param = after(param);
			}
			
			disbleEl.attr('disabled', true);
			
			return param;
		},
		/**
		 * function run
		 * parameter obj.url, obj.method, obj.param, obj.funcOk(), obj.funcErr()
		 */
		run : function(obj) {
			var defContentType = "application/x-www-form-urlencoded; charset=utf-8";
			$.ajax({
				url         : obj.url,
				method      : (StringUtils.isEmpty(obj.method)) ? "POST" : obj.method,
				data        : obj.param,
				contentType : (StringUtils.isEmpty(obj.contentType)) ? defContentType : obj.contentType,
				dataType    : "json"
			// Ajax Success
			}).done(function(data, textStatus, jqXHR) {
				var result = data.result;
				alert(result.message);
				
				// 정상 응답 코드
				if("0000" == result.code) {
					if(typeof obj.funcOk == "function") {
						obj.funcOk(result);
					}
				// 에러 응답 코드
				} else {
					if(typeof obj.funcErr == "function") {
						obj.funcErr(result);
					}
				}
				
			// Ajax Error	
			}).fail(function(jqXHR, textStatus, errorThrown) {
				var errorMsg = '요청 중 알수 없는 오류가 발생했습니다.';
				if(jqXHR.responseJSON != undefined) {
					errorMsg = jqXHR.responseJSON.status + ', ' 
					         + jqXHR.responseJSON.error  + ', ' 
					         + jqXHR.responseJSON.path;
				}
			    alert(errorMsg);
			});
		}
	}
}();

