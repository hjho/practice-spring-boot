/**
 * 공통 Java Script Date function 
 */
var DateUtils = function() {
	return {
		nowYyyy : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return "" + dateObj.getFullYear();
		},
		nowYy : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return ("" + dateObj.getFullYear()).substring(2);
		},
		nowMm : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return ((dateObj.getMonth()+1) < 10) ? "0" + (dateObj.getMonth()+1) : (dateObj.getMonth()+1);
		},
		nowDd : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return (dateObj.getDate().length < 10) ? "0" + dateObj.getDate() : dateObj.getDate();
		},
		nowHh : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return (dateObj.getHours() < 10)   ? "0" + dateObj.getHours()   : dateObj.getHours();
		},
		nowMi : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return (""+dateObj.getMinutes() < 10) ? "0" + dateObj.getMinutes() : dateObj.getMinutes();
		},
		nowSs : function(date) {
			var dateObj = (date == undefined) ? new Date() : date;
			return (""+dateObj.getSeconds() < 10) ? "0" + dateObj.getSeconds() : dateObj.getSeconds();
		},
		/**
		 * function nowDate
		 * return yyyyMMdd
		 */
		nowDate : function(date) {
			return "" + this.nowYyyy(date) + this.nowMm(date) + this.nowDd(date);
		},
		/**
		 * function nowTime
		 * return hh24Miss
		 */
		nowTime : function(date) {
			return "" + this.nowHh(date) + this.nowMi(date) + this.nowSs(date);
		},
		/**
		 * function nowDtm
		 * return yyyyMMddhh24Miss
		 */
		nowDtm : function() {
			return "" + this.nowDate() + this.nowTime();
		},
		/**
		 * function nowDtm
		 * return yyyyMMddhh24Miss
		 */
		getDtm : function(date) {
			return "" + this.nowDate(date) + this.nowTime(date);
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
		},
		longToDateForm : function(long) {
			var date = StringUtils.isEmpty(long) ? new Date(long) : new Date();
			var dateDtm = this.getDtm(date);
			return this.dateFormat(dateDtm);
		},
		longToTimeForm : function(long) {
			var date = StringUtils.isEmpty(long) ? new Date(long) : new Date();
			var dateDtm = this.getDtm(date);
			return this.timeFormat(dateDtm);
		},
		longToDtmForm : function(long) {
			return this.longToDateForm(long) + " " + this.longToTimeForm(long);
		}
		
	}
}();
