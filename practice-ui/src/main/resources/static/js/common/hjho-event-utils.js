/**
 * 공통 Java Script Event function 
 */
var EventUtils = function() {
	return {
		/**
		 * function numberFormat
		 * parameter element
		 */
		numberFormat : function(elId) {
			var text = StringUtils.numberFormat($(elId).val());
			$(elId).val(text);
		},
		/**
		 * function commaFormat
		 * parameter element
		 */
		commaFormat : function(elId) {
			var text = StringUtils.commaFormat($(elId).val());
			$(elId).val(text);
		},
		/**
		 * function onlyInt
		 * parameter element
		 */
		onlyInt : function(elId) {
			var text = $(elId).val();
			$(elId).val(text.replace(/[^0-9]/g,""));
		},
		/**
		 * function dtmFormat
		 * parameter element
		 */
		dtmFormat : function(elId) {
			$(elId).val(DateUtils.dtmFormat($(elId).val()));
		},
		/**
		 * function checkBoxVal
		 * parameter element
		 */
		checkBoxVal : function(elId) {
			if($(elId).is(':checked')) {
				$(elId).val("Y");
			} else {
				$(elId).val("N");
			}
		}
	}
}();

