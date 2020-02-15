/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var extension = "";
var isMobile = false;
var currentSelection, myNicEditor;
$(document).ready(function () {
    performPageActions();

});
function getCurrentPage() {
//returns the current page the user is on
    var path = window.location.pathname;
    var page = path.split("/").pop();
    return page;
}

function General() {
    extension = GetExtension();
    Accordion();
}

function verifyUser() {
//This function checks if a user is signed in and responds     
    var sessionid = $("#sessionid").val();
    if (sessionid === "null" || sessionid === "") {
//        alert(sessionid);
        var page = getCurrentPage();
        if (page === "sub_profile.jsp") {
            extension = "../../../";
//            window.location = extension + "ControllerServlet?action=Link&type=TimeOut";
        } else if (page === "admin_profile.jsp") {
            extension = "../../../";
//            window.location = extension + "ControllerServlet?action=Link&type=TimeOut";
        }
    }
}



function GetData(action, type, callfunction, data) {
    extension = GetExtension();
    $.ajax({
        url: extension + 'ControllerServlet',
        type: 'GET',
        data: {
            action: action,
            type: type,
            data: data
        },
        success: function (data) {
            linkToFunction(callfunction, data);
        }
    });
}
function imageExists(image_url) {
    var http = new XMLHttpRequest();
    http.open('HEAD', image_url, false);
    http.send();
    return http.status != 404;
}
function tooltip() {
    $(".tooltipParent").mouseenter(function () {
        $(this).children(".tooltip").removeClass("hide");
        $(this).children(".tooltip").show();
    });
    $(".tooltipParent").mouseleave(function () {
        $(this).children(".tooltip").addClass("hide");
        $(this).children(".tooltip").hide();
    });
}
function capitaliseFirstLetter(text) {
    if (text !== undefined) {
        return text.replace(/\w\S*/g, function (txt) {
            return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
        });
    }
}

function PriceFormat(price) {
    var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'NGN',
        minimumFractionDigits: 2
    });
    price = formatter.format(price);
    price = price.replace("NGN", "₦");
    return price.replace(".00", "");
}
function cleanArray(actual) {
    var newArray = new Array();
    for (var i = 0; i < actual.length; i++) {
        if (actual[i]) {
            newArray.push(actual[i]);
        }
    }
    return newArray;
}
function showLoader() {
    $(".preloader").removeClass("d-none hide");
    $(".preloader").show();
}
function hideLoader() {
    $(".preloader").addClass("d-none hide");
    $(".preloader").hide();
}

function formatNumber(n) {
    // format number 1000000 to 1,234,567
    return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}
function formatCurrency(input, blur) {
    // appends $ to value, validates decimal side
    // and puts cursor back in right position.

    // get input value
    var input_val = input.val();

    // don't validate empty input
    if (input_val === "") {
        return;
    }

    // original length
    var original_len = input_val.length;

    // initial caret position 
    var caret_pos = input.prop("selectionStart");

    // check for decimal
    if (input_val.indexOf(".") >= 0) {

        // get position of first decimal
        // this prevents multiple decimals from
        // being entered
        var decimal_pos = input_val.indexOf(".");

        // split number by decimal point
        var left_side = input_val.substring(0, decimal_pos);
        var right_side = input_val.substring(decimal_pos);

        // add commas to left side of number
        left_side = formatNumber(left_side);

        // validate right side
        right_side = formatNumber(right_side);

        // On blur make sure 2 numbers after decimal
        if (blur === "blur") {
            right_side += "00";
        }

        // Limit decimal to only 2 digits
        right_side = right_side.substring(0, 2);

        // join number by .
        input_val = left_side;

    } else {
        // no decimal entered
        // add commas to number
        // remove all non-digits
        input_val = formatNumber(input_val);

        // final formatting
        if (blur === "blur") {
            input_val;
        }
    }

    // send updated string to input
    input.val(input_val);

    // put caret back in the right position
    var updated_len = input_val.length;
    caret_pos = updated_len - original_len + caret_pos;
    input[0].setSelectionRange(caret_pos, caret_pos);
}




