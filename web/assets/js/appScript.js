/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var sessionid, userOnlineReferralCode, username, useremail;
var extension = "";
function performPageActions() {
    verifyUser();
    sessionid = $("#sessionid").val();
    var page = getCurrentPage();
    if (page === "index.jsp" || page === "") {
        var headerImage = "assets/img/logo-2.png";
        $(".headerImage").attr("src", headerImage);
        var footerImage = "assets/img/logo.png";
        $(".footerImage").attr("src", footerImage);
        extension = "";
    } else if (page === "loginAndRegistration.jsp") {
        var headerImage = "../../assets/img/logo-2.png";
        $(".headerImage").attr("src", headerImage);
        var footerImage = "../../assets/img/logo.png";
        $(".footerImage").attr("src", footerImage);
        extension = "../../";
    } else if (page === "sessiontimeout.jsp") {
        extension = "../../";
    } else if (page === "sub_profile.jsp") {
        extension = "../../../";
        subscriberPageFunctions();
    } else if (page === "admin_profile.jsp") {
        extension = "../../../";
    } else {
        var headerImage = "../../assets/img/logo-2.png";
        $(".headerImage").attr("src", headerImage);
        var footerImage1 = "../../assets/img/logo.png";
        $(".footerImage").attr("src", footerImage1);
    }

    userOnlineReferralCode = $("#userOnlineReferralCode").val();
    if (userOnlineReferralCode) {
        $("#regrefcode").val(userOnlineReferralCode);
    }
    btnEvents();
    AppFunctions();
}

function GetExtension() {
    return extension;
}
function AppFunctions() {

}

function btnEvents() {
    $("#tickets-tab").click(function () {
        $('#nav-tab a[href="#nav-tickets"]').tab('show');
    });
    $("#payments-tab").click(function () {
        $('#nav-tab a[href="#nav-payments"]').tab('show');
    });
    $("#wallet-tab").click(function () {
        $('#nav-tab a[href="#nav-wallet"]').tab('show');
    });
    $("#subscribers-tab").click(function () {
        $('#nav-tab a[href="#nav-subscribers"]').tab('show');
    });
    $("#notifications-tab").click(function () {
        $('#nav-tab a[href="#nav-notifications"]').tab('show');
    });

    $("form[name=loginForm]").submit(function (e) {
        var f = $(this);
        f.parsley().validate();
        if (f.parsley().isValid()) {
            var email_phone = $("#email").val();
            var password = $("#password").val();
            var data = [email_phone, password];
            showLoader();
            GetData("User", "Login", "LoadUserLogin", data);
        } else {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-success',
                    cancelButton: 'btn btn-danger'
                },
                buttonsStyling: false
            });
            swalWithBootstrapButtons.fire({
                title: 'Login Error!',
                text: "Please check your login details!",
                icon: 'error',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok!'
            });
        }
        e.preventDefault();
    });

    $("form[name=registerForm]").submit(function (e) {
        var referalCode = "";
        var regform = $(this);
        regform.parsley().validate();
        if (regform.parsley().isValid()) {
            var firstname = $("#regfirstname").val();
            var lastname = $("#reglastname").val();
            var phonenumber = $("#regphone").val();
            var password = $("#regpassword").val();
            var emailaddress = $("#regemail").val();
            if (userOnlineReferralCode) {
                referalCode = userOnlineReferralCode;
            } else if ($("#regrefcode").val() === "NIL") {
                referalCode = "";
            } else {
                referalCode = $("#regrefcode").val();
            }
            if ($("#checkTerms").is(':checked')) {
                var data = [firstname, lastname, emailaddress, phonenumber, password, referalCode];
                showLoader();
                GetData("User", "MemberRegistration", "LoadRegistration", data);
            } else {
                const swalWithBootstrapButtons = Swal.mixin({
                    customClass: {
                        confirmButton: 'btn btn-success',
                        cancelButton: 'btn btn-danger'
                    },
                    buttonsStyling: false
                });
                swalWithBootstrapButtons.fire({
                    title: 'Registration Error!',
                    text: "Please tick the box to accept our Terms & Conditions!",
                    icon: 'error',
                    showCancelButton: false,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Ok!'
                });
            }

        } else {
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    confirmButton: 'btn btn-success',
                    cancelButton: 'btn btn-danger'
                },
                buttonsStyling: false
            });
            swalWithBootstrapButtons.fire({
                title: 'Registration Error!',
                text: "Please check your registration input details!",
                icon: 'error',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok!'
            });
        }
        e.preventDefault();
    });

    $(".logOutBtn").click(function () {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure you want to log out?',
            text: "Press No if you want to continue work. Press Yes to logout current user.",
            icon: 'info',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {
                window.location = extension + "ControllerServlet?action=Link&type=LogOut";
            } else if (result.dismiss === Swal.DismissReason.cancel) {
                swalWithBootstrapButtons.fire('Cancelled', 'You work is safe :)', 'success');
            }
        });
    });

    $("#BuySingleTicket").click(function () {
        var quantity = $("#quantity").val();
        var newQty = parseInt(quantity) * 2000;
        PreparePaymentInfo(newQty, "Single", quantity);
    });
    $("#Buy5in1Ticket").click(function () {
        var newQty = 10000;
        PreparePaymentInfo(newQty, "Five-In-One", 6);
    });
    $("#Buy10in1Ticket").click(function () {
        var newQty = 20000;
        PreparePaymentInfo(newQty, "Ten-In-One", 10);
    });



    $("#buySingleTicket").click(function () {
        buySingleTicket("Single");
    });
    $("#buyFiveTickets").click(function () {
        var newQty = 10000;
        PaystackPay(newQty, "Five-In-One", 6);
    });
    $("#buyTenTickets").click(function () {
        var newQty = 20000;
        PaystackPay(newQty, "Ten-In-One", 10);
    });

    $(".copyRefLinkBtn").click(function () {
        /* Get the text field */
        var copyText = $(".copyreflink").text();
        /* Select the text field */
        copyText.select();
        copyText.setSelectionRange(0, 99999); /*For mobile devices*/

        /* Copy the text inside the text field */
        document.execCommand("copy");

        /* Alert the copied text */
        alert("Copied the text: " + copyText.value);
        alert("ey");
    });
}

function buySingleTicket(paymenttype) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    });

    swalWithBootstrapButtons.fire({
        title: 'Buy Ticket?',
        text: "Enter the number of Tickets.",
        icon: 'info',
        input: 'text',
        showCancelButton: true,
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        reverseButtons: true,
    }).then((result) => {
        if (result.value) {
            var newQty = parseInt(result.value) * 2000;
            PaystackPay(newQty, paymenttype, result.value);
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            swalWithBootstrapButtons.fire('Cancelled', 'You work is safe :)', 'success');
        }
    });
}


function subscriberPageFunctions() {
    GetData("User", "GetMemberDetails", "LoadMemberDetails", sessionid);
    GetData("Ticket", "GetUserTickets", "LoadUserTickets", sessionid);
}

function PreparePaymentInfo(newQty, paymenttype, numberofticket) {
    Swal.mixin({
//        title: 'We would love to know you.',
//        input: 'text',
        confirmButtonText: 'Next &rarr;',
        showCancelButton: true,
        progressSteps: ['1', '2', '3', '4', '5']
    }).queue([
        {
            title: 'First Name',
            input: 'text',
            text: 'We would like to know you, please enter you First Name'
        },
        {
            title: 'Last Name',
            input: 'text',
            text: 'Please enter you Last Name'
        },
        {
            title: 'Email',
            input: 'email',
            text: 'Please enter you Email Address correctly'
        },
        {
            title: 'Phone',
            input: 'number',
            text: 'Please enter you Phone Number'
        },
        {
            title: 'Password',
            input: 'password',
            text: 'Please type your password'
        },
        {
            title: 'Referal Code',
            input: 'text',
            text: 'Please type the Referal Code if you have',
            value: "NIL"
        }
    ]).then((result) => {
        if (result.value) {
            const answers = JSON.stringify(result.value);
            Swal.fire({
                title: 'All Done!',
                html: `Your Details:<br/>FirstName: <b>${result.value[0]}</b><br/>LastName: <b>${result.value[1]}</b>
                                        <br/>Email: <b>${result.value[2]}</b><br/>Phone<b>: ${result.value[3]} </b>
                                        <br/>Password: <b>${result.value[4]}</b><br/>Referal Code:<b> ${result.value[5]}</b><br/><b>Amount To Pay: ₦${newQty}</b>`,
                icon: 'info',
                showCancelButton: true,
                confirmButtonText: '<i class="fa fa-thumbs-up"></i> Continue to payment!',
                confirmButtonAriaLabel: 'Thumbs up, great!',
                cancelButtonText: '<i class="fa fa-thumbs-down"></i> Cancel',
                cancelButtonAriaLabel: 'Thumbs down'
            }).then((res) => {
                if (res.value) {
                    var fname = result.value[0];
                    var lname = result.value[1];
                    var email = result.value[2];
                    var phone = result.value[3];
                    var password = result.value[4];
                    var regrefcode = result.value[5];
                    var amount = newQty;
                    payWithPaystack(fname, lname, email, phone, password, amount, paymenttype, numberofticket, regrefcode);
                } else {
                    Swal.fire('Cancelled!', 'Nothing has been saved, you can try again later.', 'info');
                }
            });
        }
    });
}


function PaystackPay(amount, paymenttype, numberofticket) {
    var handler = PaystackPop.setup({
        key: 'pk_test_9c32fd37430710c4b34c9376c8133c7925e899a7',
        email: useremail,
        amount: amount + "00",
        ref: '' + Math.floor((Math.random() * 1000000000) + 1), // generates a pseudo-unique reference. Please replace with a reference you generated. Or remove the line entirely so our API will generate one for you
        metadata: {
            custom_fields: [
                {
                    display_name: "Customer Name",
                    variable_name: "Customer Name",
                    value: username
                },
                {
                    display_name: "Payment Type",
                    variable_name: "Payment Type",
                    value: paymenttype + " Ticket"
                }
            ]
        },
        callback: function (response) {
            var data = [paymenttype, amount, numberofticket, response.reference, response.trans, sessionid];
            showLoader();
            GetData("Ticket", "BuyTicket", "LoadBuyTicket", data);
        },
        onClose: function () {
            Swal.fire('Cancelled!', 'Nothing has been saved, you can try again later.', 'info');
        }
    });
    handler.openIframe();
}


function payWithPaystack(userfirstname, userlastname, useremail, userphone, userpassword, amount, paymenttype, numberofticket, regrefcode) {
    var lname = userlastname;
    var fname = userfirstname;
    var handler = PaystackPop.setup({
        key: 'pk_test_9c32fd37430710c4b34c9376c8133c7925e899a7',
        email: useremail,
        amount: amount + "00",
        ref: '' + Math.floor((Math.random() * 1000000000) + 1), // generates a pseudo-unique reference. Please replace with a reference you generated. Or remove the line entirely so our API will generate one for you
        metadata: {
            custom_fields: [
                {
                    display_name: "Customer Name",
                    variable_name: "Customer Name",
                    value: userlastname + " " + userfirstname
                },
                {
                    display_name: "Payment Type",
                    variable_name: "Payment Type",
                    value: paymenttype + " Ticket"
                }
            ]
        },
        callback: function (response) {
            var data = [fname, lname, useremail, userphone, userpassword, paymenttype, amount, response.reference, numberofticket, response.trans, regrefcode];
            showLoader();
            GetData("User", "RegistrationAndPayment", "LoadRegistrationAndPayment", data);
        },
        onClose: function () {
            Swal.fire('Cancelled!', 'Nothing has been saved, you can try again later.', 'info');
        }
    });
    handler.openIframe();
}

function DisplayUserLogin(data) {
    hideLoader();
    if (data === "Incorrect Login Details") {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-primary'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Uh Oh?',
            text: "Incorrect Login Details, Please try again!",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Ok!'
        }).then((result) => {
            window.location = extension + "ControllerServlet?action=Link&type=Index";
        });
    } else if (data === "Email or Phone Number Entered Doesn't Exist") {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-primary'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Uh Oh?',
            text: "Email or Phone Number Entered Doesn't Exist!",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Ok'
        }).then((result) => {
            window.location = extension + "ControllerServlet?action=Link&type=Index";
        });
    } else {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Welcome',
            text: "Successful login!",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Continue!'
        }).then((result) => {
            verifyUser();
            if (data === "Admin") {
                window.location = extension + "ControllerServlet?action=Link&type=AdminDashboard";
            } else {
                window.location = extension + "ControllerServlet?action=Link&type=SubDashboard";
            }
        });

    }
}

function DisplayRegistration(data) {
    hideLoader();
    if (data[0] === "success") {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success'
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: 'Welcome',
            text: "Successful Registration!",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Continue!'
        }).then((result) => {
            verifyUser();
            if (data[1] === "Admin") {
                window.location = extension + "ControllerServlet?action=Link&type=AdminDashboard";
            } else {
                window.location = extension + "ControllerServlet?action=Link&type=SubDashboard";
            }
        });
    } else {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-primary'
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: 'Uh Oh?',
            text: data[1],
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Ok'
        }).then((result) => {
            window.location = extension + "ControllerServlet?action=Link&type=Register";
        });
    }
}

function DisplayMemberDetails(data) {
    if (data !== "none") {
        var utype = data["usertype"];
        if (utype === "Admin") {
            $(".admin_view").removeClass("hide");
            $(".admin_view").show();
        } else {
            $(".admin_view").addClass("hide");
            $(".admin_view").hide();
        }
//        var image_url = extension + "global_assets/img/ProfilePicture/user-" + data["userID"] + ".png";
//        if (imageExists(image_url) === false) {
//            image_url = extension + "global_assets/img/ProfilePicture/user-0.png";
//        }
//        $(".UserImage").attr("src", image_url);
//        $(".bgUserImage").css("background-image", "url('" + extension + "global_assets/img/ProfilePicture/user-" + data["userID"] + ".png')");
//        $(".bgUserImage").css("background-repeat", "no-repeat");
//        $(".bgUserImage").css("background-position", "center center");
        $(".user-name").text(data["firstname"] + " " + data["lastname"]);
        $(".user-firstname").text(data["firstname"]);
        $(".user-lastname").text(data["lastname"]);
        $(".user-email").text(data["email"]);
        $(".user-phone").text(data["phone"]);
        $(".user-referral_code").text(data["referral_code"]);
        $(".user-referral_count").text(data["referral_count"]);
        $(".user-type").text(data["usertype"]);
        $(".user_dateregistered").text(data["date_registered"]);
        username = data["firstname"] + " " + data["lastname"];
        useremail = data["email"];
    }
}

function DisplayRegistrationAndPayment(data) {
    hideLoader();
    if (data[0] === "success") {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Welcome',
            text: "Successful Registration and Payment",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Continue!'
        }).then((result) => {
            verifyUser();
            if (data === "Admin") {
                window.location = extension + "ControllerServlet?action=Link&type=AdminDashboard";
            } else {
                window.location = extension + "ControllerServlet?action=Link&type=SubDashboard";
            }
        });
    } else {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-primary'
            },
            buttonsStyling: false
        });

        swalWithBootstrapButtons.fire({
            title: 'Uh Oh?',
            text: data[1],
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Ok'
        }).then((result) => {
            window.location = extension + "ControllerServlet?action=Link&type=Register";
        });
    }
}

function DisplayBuyTicket(data) {
    hideLoader();
    if (data === "success") {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success'
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: 'Buy Ticket',
            text: "Successful!",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Ok!'
        }).then((result) => {

        });
    } else {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-primary'
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: 'Uh Oh?',
            text: "Something went wrong, if you card was debited, please contact PienMoney",
            icon: 'info',
            showCancelButton: false,
            confirmButtonText: 'Ok'
        }).then((result) => {
            window.location = extension + "ControllerServlet?action=Link&type=Register";
        });
    }
}



function DisplayUserTickets(data) {
//    alert("hey" +JSON.stringify(data));
}
function linkToFunction(action, params) {
    switch (action) {
        case "LoadUserLogin":
        {
            DisplayUserLogin(params);
            break;
        }
        case "LoadRegistration":
        {
            DisplayRegistration(params);
            break;
        }
        case "LoadMemberDetails":
        {
            DisplayMemberDetails(params);
            break;
        }
        case "LoadRegistrationAndPayment":
        {
            DisplayRegistrationAndPayment(params);
            break;
        }
        case "LoadUserTickets":
        {
            DisplayUserTickets(params);
            break;
        }
        case "LoadBuyTicket":
        {
            DisplayBuyTicket(params);
            break;
        }
    }
}