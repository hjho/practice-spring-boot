/**
 * 공통 Java Script Date function 
 */
var DateUtils = function() {
	return {
		nowYyyy : function() {
			var dateObj = new Date();
			return "" + dateObj.getFullYear();
		},
		nowYy : function() {
			var dateObj = new Date();
			return ("" + dateObj.getFullYear()).substring(2);
		},
		nowMm : function() {
			var dateObj = new Date();
			return ((dateObj.getMonth()+1) < 10) ? "0" + (dateObj.getMonth()+1) : (dateObj.getMonth()+1);
		},
		nowDd : function() {
			var dateObj = new Date();
			return (dateObj.getDate().length < 10) ? "0" + dateObj.getDate() : dateObj.getDate();
		},
		nowHh : function() {
			var dateObj = new Date();
			return (dateObj.getHours() < 10)   ? "0" + dateObj.getHours()   : dateObj.getHours();
		},
		nowMi : function() {
			var dateObj = new Date();
			return (""+dateObj.getMinutes() < 10) ? "0" + dateObj.getMinutes() : dateObj.getMinutes();
		},
		nowSs : function() {
			var dateObj = new Date();
			return (""+dateObj.getSeconds() < 10) ? "0" + dateObj.getSeconds() : dateObj.getSeconds();
		},
		/**
		 * function nowDate
		 * return yyyyMMdd
		 */
		nowDate : function() {
			return "" + this.nowYyyy() + this.nowMm() + this.nowDd();
		},
		/**
		 * function nowTime
		 * return hh24Miss
		 */
		nowTime : function() {
			return "" + this.nowHh() + this.nowMi() + this.nowSs();
		},
		/**
		 * function nowDtm
		 * return yyyyMMddhh24Miss
		 */
		nowDtm : function() {
			return "" + this.nowDate() + this.nowTime();
		},
		nowFormat : function(format) {
			format = format.toLowerCase();
			format = format.replaceAll("yyyy", this.nowYyyy());
			format = format.replaceAll("yy"  , this.nowYy());
			format = format.replaceAll("mm"  , this.nowMm());
			format = format.replaceAll("dd"  , this.nowDd());
			format = format.replaceAll("hh"  , this.nowHh());
			format = format.replaceAll("mi"  , this.nowMi());
			format = format.replaceAll("ss"  , this.nowSs());
			return format;
		},
		// YYYY-MM-DD
		dateFormat : function(str) {
			var dateBuf = StringUtils.nvl(str).replace(/[^0-9]/g,"");
			// yyyymmddhhmiss
			if(dateBuf.length >= 14) {
				dateBuf = dateBuf.substring(0, 14);
				dateBuf = dateBuf.substring(0, 8);
			}
			// yyyymmdd
			if(dateBuf.length == 8) {
				dateBuf = dateBuf.substring(0, 4) + "-" + dateBuf.substring(4, 6) + "-" + dateBuf.substring(6); 
			}
			return dateBuf;
		},
		// HH24:MI:SS
		timeFormat : function(str) {
			var timeBuf = StringUtils.nvl(str).replace(/[^0-9]/g,"");
			// yyyymmddhhmiss
			if(timeBuf.length >= 14) {
				timeBuf = timeBuf.substring(0, 14);
				timeBuf = timeBuf.substring(8);
			}
			// hhmiss
			if(timeBuf.length == 6) {
				timeBuf = timeBuf.substring(0, 2) + ":" + timeBuf.substring(2, 4) + ":" + timeBuf.substring(4); 
			}
			return timeBuf;
		},
		// YYYY-MM-DD HH24:MI:SS
		dtmFormat : function(str) {
			var dtmBuf = StringUtils.nvl(str).replace(/[^0-9]/g,"");
			return this.dateFormat(dtmBuf) + " " + this.timeFormat(dtmBuf);
		}
		
	}
}();
