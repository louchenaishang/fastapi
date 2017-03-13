/*
 * Important Need jquery.js
 * commons-framework.js
 * @author eric lou
 * @date 2015-06-08
 * */

var environment = "DEBUG"; //or  PRD

/**
 * public EmptyUtil
 * @type {{isEmpty: Function, isNotEmpty: Function}}
 */
var EmptyUtil = {
    isEmpty: function (arg0) {
        if (arg0 == undefined || arg0 == null) {
            return true;
        }
        if (typeof arg0 == "string") {
            if (arg0 == "") {
                return true;
            } else {
                return false;
            }
        }
        if (typeof arg0 == "number") {
            if (arg0 == 0) {
                return true;
            } else {
                return false;
            }
        }
        if (typeof arg0 == "boolean") {
            if (!arg0) {
                return true;
            } else {
                return false;
            }
        }
        if (typeof arg0 == "object" && arg0.constructor == Array) {
            if (arg0.length == 0) {
                return true;
            } else {
                return false;
            }
        }
        return jQuery.isEmptyObject(arg0);
    }
    , isNotEmpty: function (arg0) {
        return !this.isEmpty(arg0);
    }
};

/**
 * private NumberUtil
 * @type {{static_default_scale: number, digital: Function, calculate: Function}}
 */
var NumberUtil = {
    static_default_scale: 2
    , digital: function (x) {
        if (isNaN(x) || EmptyUtil.isEmpty(x)) {
            return 0;
        } else {
            if (typeof x == "string") {
                x = parseFloat(x);
            }
            return x;
        }
    }
    , calculate: function (x, y, scale, mode) {
        if (typeof scale != "number") {
            scale = this.static_default_scale;
        }
        x = this.digital(x);
        y = this.digital(y);
        var z = eval(x + "" + mode + "" + y);
        return z.toFixed(scale);
    }
};

/**
 * public DigitUtil
 * @type {{add: Function, substract: Function, multiply: Function, divide: Function, digital: Function, compare: Function}}
 */
var DigitUtil = {
    add: function (x, y, scale) {
        return NumberUtil.calculate(x, y, scale, "+");
    }
    , substract: function (x, y, scale) {
        return NumberUtil.calculate(x, y, scale, "-");
    }
    , multiply: function (x, y, scale) {
        return NumberUtil.calculate(x, y, scale, "*");
    }
    , divide: function (x, y, scale) {
        return NumberUtil.calculate(x, y, scale, "/");
    }
    , digital: function (x) {
        return NumberUtil.digital(x);
    }
    , compare: function (x, y) {
        x = this.digital(x);
        y = this.digital(y);
        if (x > y) {
            return 1;
        }
        if (x == y) {
            return 0
        }
        if (x < y) {
            return -1;
        }
    }
};

/**
 * public ClockUtil
 * @type {{static_default_id: string, format: Function, init: Function, doInterval: Function}}
 */
var ClockUtil = {
    static_default_id: 'clock'
    , format: function (str) {
        var num;
        parseInt(str) > 9 ? num = str : num = "0" + str;
        return num;
    }
    , init: function (containerId) {
        if (EmptyUtil.isEmpty(containerId)) {
            containerId = this.static_default_id;
        }
        var container = $('#' + containerId);
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth();
        var day = date.getDate();
        var hours = date.getHours();
        var minute = date.getMinutes();
        var second = date.getSeconds();
        var time = year + "-" + this.format((month + 1)) + "-" + this.format(day)
            + " " + this.format(hours) + ":" + this.format(minute) + ":" + this.format(second);
        container.html(time);
    }
    , doInterval: function (containerId) {
        setInterval(function () {
            ClockUtil.init(containerId);
        }, 1000);
    }
};

/**
 * public LogUtil
 * @type {{log: Function, deBuglog: Function}}
 */
var LogUtil = {
    log: function (txt) {
        console.log(txt);
    }
    , deBuglog: function (txt) {
        if (environment == "DEBUG") {
            console.log(txt);
        }
    }
};

/**
 * public AjaxUtil
 * @type {{main: Function, postAsync: Function, postSync: Function, getAsync: Function, getSync: Function}}
 */
var AjaxUtil = {
    main: function (url, data, success, error, type, async) {
        LogUtil.deBuglog("ajax will start,type[" + type + "],async[" + async + "],url[" + url + "],data[" + data + "]");
        $.ajax({
            dataType: 'json',
            cache: false,
            type: type,
            async: async,
            url: url,
            data: customParam(data),
            success: success,
            error: error
        });
    }
    ,
    postAsync: function (url, data, success, error) {
        AjaxUtil.main(url, data, success, error, 'post', true);
    }
    ,
    postSync: function (url, data, success, error) {
        AjaxUtil.main(url, data, success, error, 'post', false);
    }
    ,
    getAsync: function (url, data, success, error) {
        AjaxUtil.main(url, data, success, error, 'get', true);
    }
    , getSync: function (url, data, success, error) {
        AjaxUtil.main(url, data, success, error, 'get', false);
    }

};

var customParam=function( a ) {
    var s = [],
        add = function( key, value ) {
            // If value is a function, invoke it and return its value
            value = jQuery.isFunction( value ) ? value() : value;
            s[ s.length ] = encodeURIComponent( key ) + "=" + encodeURIComponent( value );
        };

    // If an array was passed in, assume that it is an array of form elements.
    if ( jQuery.isArray( a ) || ( a.jquery && !jQuery.isPlainObject( a ) ) ) {
        // Serialize the form elements
        jQuery.each( a, function() {
            add( this.name, this.value );
        });

    } else {
        for ( var prefix in a ) {
            buildParams( prefix, a[ prefix ], add );
        }
    }

    // Return the resulting serialization
    return s.join( "&" ).replace( /%20/g, "+" );
};

function buildParams( prefix, obj, add ) {
    if ( jQuery.isArray( obj ) ) {
        // Serialize array item.
        jQuery.each( obj, function( i, v ) {
            if (/\[\]$/.test( prefix ) ) {
                // Treat each array item as a scalar.
                add( prefix, v );

            } else {
                //buildParams( prefix + "[" + ( typeof v === "object" || jQuery.isArray(v) ? i : "" ) + "]", v, add );
                buildParams( prefix + "[" + i+ "]", v, add );
            }
        });

    } else if (obj != null && typeof obj === "object" ) {
        // Serialize object item.
        for ( var name in obj ) {
            buildParams( prefix + "." + name, obj[ name ], add );
        }

    } else {
        // Serialize scalar item.
        add( prefix, obj );
    }
};

/**
 * 验证工具
 * @type {{isQueryCallback: Function, isMobile: Function, isPhone: Function}}
 */
var ValidateUtil={
    isQueryCallback:function(value){
        return /^\[[a-zA-Z\d]+\](.+)?$/.test(value);
    },
    isMobile:function(value){
        return (value.length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));
    },
    isPhone:function(value){
        return (/^(\d{3,4}-?)?\d{7,9}$/).test(value);
    },
    isEnglish:function(value){
        return (/^[A-Za-z]+$/).test(value);
    },
    isEnglishOrDigit:function(value){
        return (/^[A-Za-z0-9]+$/).test(value);
    },
    isInt:function(value){
        return (/^-?\d+$/).test(value);
    },
    isPositiveInt:function(value){
        return (/^\d+$/).test(value);
    },
    isPositiveDigit:function(value){
        return (/^([1-9]\d*\.\d*|0\.\d+|[1-9]\d*|0)$/).test(value);
    },
    isNumber:function(value){
        return (/^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/).test(value);
    },
    isNegativeInt:function(value){
        return (/^-\d+$/).test(value);
    },isHanZi:function(value){
        return (/^[\u4e00-\u9fa5]+$/.test(value));
    },isEmail:function(value){
        return (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value));
    },isUrl:function(value){
        return (/^[a-zA-z]+:\/\/(\w+(-\w+)*)(\.(\w+(-\w+)*))*(\?\S*)?$/.test(value));
    }
}