/** 
 * 공통으로 쓰여질 JAVA SCRIPT
 */
const utils = {
	isNull: function(str) {
		if(str == null || str == "" || typeof str == 'undefined') {
			return true;
		}
		return false;
	},
	isNotNull: function(str) {
		return !this.isNull(str);
	},
	isTrue: function(str) {
		if(str == true || str == 'true' || str == 'Y') {
			return true;
		}
		return false;
	},
	isNotTrue: function(str) {
		return !this.isTrue(str);
	},
	default: function(str, def) {
		def = (this.isNull(def)) ? "" : def;
		return (this.isNull(str)) ? def : str;
	},
	lpad: function(str, len, padStr) {
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
	rpad: function(str, len, padStr) {
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
	formatNum: function(str) {
		var numStr = this.default("" + str).replace(/[^0-9]/g, "");
		return (this.isNull(numStr)) ? "0" : numStr;
	},
	formatInt: function(str) {
		return parseInt(this.formatNum(str));
	},
	formatMny: function(str) {
		var number = "" + this.formatInt("" + str);
		return number.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, "$1,");
	}
}

/** 
 * onkeyup이나 onclick 시 공통으로 쓰여질 JAVA SCRIPT
 */
const event = {
	number: function(elId) {
		if(isNull($(elId).val())) return;
		
		var text = utils.formatNum($(elId).val());
		$(elId).val(text);
	},
	money: function(elId) {
		if(isNull($(elId).val())) return;
		
		var text = utils.formatMny($(elId).val());
		$(elId).val(text);
	},
	onlyInt: function(elId) {
		if(isNull($(elId).val())) return;
		
		var text = utils.formatInt($(elId).val());
		$(elId).val(text);
	},
	onlyFloat: function(elId, limit) {
		if(isNull($(elId).val())) return;
		
		var text = $(elId).val();
		var floatBuf = parseFloat(utils.default(text.replace(/[^0-9^.]/g, ""), "0"));
		$(elId).val(floatBuf.toFixed(limit));
	},
	checkYn : function(elId) {
		if(isNull($(elId).val())) return;
		
		if($(elId).is(':checked')) {
			$(elId).val("Y");
		} else {
			$(elId).val("N");
		}
	}	
}
/**
 * type : y, M, d, h, m, s 
 */
Date.prototype.plus = function(type, i) {
	if(i < 1) return this;
	
	switch(type) {
		case 'y': this.setFullYear(this.getFullYear()+i); break;
		case 'M': this.setMonth(this.getMonth()+i);       break;
		case 'd': this.setDate(this.getDate()+i);         break;
		case 'h': this.setHours(this.getHours()+i);       break;
		case 'm': this.setMinutes(this.getMinutes()+i);   break;
		case 's': this.setSeconds(this.getSeconds()+i);   break;
	}
	return this;
}
/**
 * type : y, M, d, h, m, s 
 */
Date.prototype.minus = function(type, i) {
	if(i < 1) return this;
	
	switch(type) {
		case 'y': this.setFullYear(this.getFullYear()-i); break;
		case 'M': this.setMonth(this.getMonth()-i);       break;
		case 'd': this.setDate(this.getDate()-i);         break;
		case 'h': this.setHours(this.getHours()-i);       break;
		case 'm': this.setMinutes(this.getMinutes()-i);   break;
		case 's': this.setSeconds(this.getSeconds()-i);   break;
	}
	return this;
}
/**
 * default: yyyy-mm-dd hh:mi:ss
 * pattern: yyyy, yy, mm, dd, HH, hh, mi, ss, ms
 * def    : ""면 1자리.
 */
Date.prototype.format = function(pattern, def) {
	pattern = utils.default(pattern, 'yyyy-mm-dd HH:mi:ss');
	def     = (def == "") ? "" : "0";
	
	var y4  = "" + this.getFullYear();
	var y2  = y4.substring(2);
	var mm  = (this.getMonth()+1 < 10) ? def+(this.getMonth()+1) : ""+(this.getMonth()+1);
	var dd  = (this.getDate()    < 10) ? def+this.getDate()      : ""+this.getDate();
	var HH  = (this.getHours()   < 10) ? def+this.getHours()     : ""+this.getHours();
	var hh  = (parseInt(HH)      > 11) ? parseInt(HH)-12         : parseInt(HH); 
	    hh  = (hh                < 10) ? def+hh                  : ""+hh
	var mi  = (this.getMinutes() < 10) ? def+this.getMinutes()   : ""+this.getMinutes();
	var ss  = (this.getSeconds() < 10) ? def+this.getSeconds()   : ""+this.getSeconds();
	var ms  = this.getMilliseconds();
	if(ms < 100) {
		ms = (ms < 10) ? def+def+ms : def+ms;
	}
	
	pattern = pattern.replaceAll("yyyy", y4);
	pattern = pattern.replaceAll("yy"  , y2);
	pattern = pattern.replaceAll("mm"  , mm);
	pattern = pattern.replaceAll("dd"  , dd);
	pattern = pattern.replaceAll("HH"  , HH);
	pattern = pattern.replaceAll("hh"  , hh);
	pattern = pattern.replaceAll("mi"  , mi);
	pattern = pattern.replaceAll("ss"  , ss);
	pattern = pattern.replaceAll("ms"  , ms);
	
	return pattern;
}


const ajax = {
	isLog   : true,
	homeUrl : "/login/page",
	success : "0000",
	setParam: function(formEl, before, after) {
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
	run : function(obj) {
		var defContentType = "application/x-www-form-urlencoded; charset=utf-8";
		$.ajax({
			url         : obj.url,
			method      : (utils.isNotNull(obj.method)) ? "POST" : obj.method,
			data        : obj.param,
			contentType : (utils.isNotNull(obj.contentType)) ? defContentType : obj.contentType,
			dataType    : "json"
		// Ajax Success
		}).done(function(data) {	// (data, textStatus, jqXHR)
			var result = data.result;
			alert(result.message);
			
			// 정상 응답 코드
			if(ajax.success == result.code) {
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
		}).fail(function(jqXHR) {
			var error = jqXHR.responseJSON;
			ajax.error(error);
		});
	},
	error : function(error) {
		var errorMsg = '요청 중 알수 없는 오류가 발생했습니다.';
		if(error != null) {
			if(ajax.isLog) {
				var errorLog = 'AJAX ERROR : ';
				errorLog += '(' + error.status + ') ' + error.error + ', ';
				errorLog += error.message;
				errorLog += ', URL [' + error.path + '], 시간 [' + new Date(error.timestamp).format() + ']';
				console.log(errorLog);
			}
			if(utils.isNotNull(error.message)) {
				errorMsg = error.message;
			}
		}
		switch(error.status) {
			case 404: alert("알 수 없는 페이지 입니다.");            break;
			case 500: alert("요청 중 알 수 없는 오류가 발생했습니다."); break;
			case 503:
				errorMsg += '\n로그인페이지로 이동하시겠습니까?'
				if(confirm(errorMsg)) {
					location.href = ajax.homeUrl;
				}
				break;
			default :
				alert(errorMsg);
				break;
		}
	}
}
