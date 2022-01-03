/**
 * 공통 Java Script String function 
 */
var StringUtils = function() {
	return {
		/**
		 * function isEmpty
		 * parameter string
		 */
		isEmpty : function(str) {
			if(str == null || str == "" || str == undefined) {
				return true;
			}
			return false;
		},
		/**
		 * function isNotEmpty
		 * parameter string
		 */
		isNotEmpty : function(str) {
			return !this.isEmpty(str);
		},
		/**
		 * function nvl2
		 * parameter string, default
		 */
		nvl2 : function(str, def) {
			def = (this.isEmpty(def)) ? "" : def;
			return (this.isEmpty(str)) ? def : str;
		},
		/**
		 * function nvl2
		 * parameter string
		 */
		nvl : function(str) {
			return this.nvl2(str, "");
		},
		/**
		 * function lpad
		 * parameter string, 요청길이, 채울문자
		 */
		lpad : function(str, len, padStr) {
			if(padStr.length > len) {
				console.log('LPAD 에러 : 요청길이와 채울문자의 길이를 확인해주세요.');
				return str;
			}
			str += "";
			padStr += "";
			
			while(len > str.length) {
				str = padStr + str;
			}
			str = (str.length >= len) ? str.substring(0, len) : str;
			return str;
		},
		/**
		 * function rpad
		 * parameter string, 요청길이, 채울문자
		 */
		rpad : function(str, len, padStr) {
			if(padStr.length > len) {
				console.log('RPAD 에러 : 요청길이와 채울문자의 길이를 확인해주세요.');
				return str;
			}
			str += "";
			padStr += "";
			
			while(len > str.length) {
				str += padStr;
			}
			str = (str.length >= len) ? str.substring(0, len) : str;
			return str;
		},
		/**
		 * function numberFormat
		 * parameter string
		 */
		numberFormat : function(str) {
			var text = this.nvl(str).replace(/[^0-9]/g,"");
			var text2 = (this.isEmpty(text)) ? "0" : text;
			return parseInt(text2);
		},
		/**
		 * function commaFormat
		 * parameter string
		 */
		commaFormat : function(str) {
			var number = "" + this.numberFormat(str);
			return number.replace(/(\d)(?=(?:\d{3})+(?!\d))/g,"$1,");
		}
	}
}();
