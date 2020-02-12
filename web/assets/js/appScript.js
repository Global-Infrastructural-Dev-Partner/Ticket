/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var sessionid, userOnlineReferralLink;
var extension = "";
function performPageActions() {
    verifyUser();
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
    } else if (page === "admin_profile.jsp") {
        extension = "../../../";
    } else {
        var headerImage = "../../assets/img/logo-2.png";
        $(".headerImage").attr("src", headerImage);
        var footerImage1 = "../../assets/img/logo.png";
        $(".footerImage").attr("src", footerImage1);
    }
    sessionid = $("#sessionid").val();
    userOnlineReferralLink = $("#userOnlineReferralLink").val();
    if (userOnlineReferralLink) {
        $("#reglink").val(userOnlineReferralLink);
    }
    btnEvents();
    AppFunctions();
}

function GetExtension() {
    return extension;
}
function AppFunctions() {
    GetData("User", "GetMemberDetails", "LoadMemberDetails", sessionid);
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
        var link = "";
        var regform = $(this);
        regform.parsley().validate();
        if (regform.parsley().isValid()) {
            var firstname = $("#regfirstname").val();
            var lastname = $("#reglastname").val();
            var phonenumber = $("#regphone").val();
            var password = $("#regpassword").val();
            var emailaddress = $("#regemail").val();
            if (userOnlineReferralLink) {
                link = userOnlineReferralLink;
            } else {
                link = $("#reglink").val();
            }
            if ($("#checkTerms").is(':checked')) {
                var data = [firstname, lastname, emailaddress, phonenumber, password, link];
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
        }
    ]).then((result) => {
        if (result.value) {
            const answers = JSON.stringify(result.value);
            Swal.fire({
                title: 'All Done!',
                html: `Your Details:<br/>FirstName: ${result.value[0]}<br/>LastName: ${result.value[1]}
                                        <br/>Email: ${result.value[2]}<br/>Phone: ${result.value[3]} 
                                        <br/>Password: ${result.value[4]}<br/><b>Amount To Pay: ₦${newQty}</b>`,
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
                    var amount = newQty;
                    payWithPaystack(fname, lname, email, phone, password, amount, paymenttype, numberofticket);
                } else {
                    Swal.fire('Cancelled!', 'Nothing has been saved, you can try again later.', 'info');
                }
            });
        }
    });
}


function payWithPaystack(userfirstname, userlastname, useremail, userphone, userpassword, amount, paymenttype, numberofticket) {
    var lname = userlastname;
    var fname = userfirstname;
    var handler = PaystackPop.setup({
        key: 'pk_test_290ed7ac03485f7891f42fba7c49da7cd93e0a59', //menegbo
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
            var data = [fname, lname, useremail, userphone, userpassword, paymenttype, amount, response.reference, numberofticket, response.trans];
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

function DisplayMemberDetails(data) {
//    alert(JSON.stringify(data));
//    if (data !== "none") {
    var utype = data["usertype"];
//    alert(utype);
//    if (utype === "Admin") {
//        $(".admin_view").removeClass("hide");
//        $(".admin_view").show();
//    } else {
//        $(".admin_view").addClass("hide");
//        $(".admin_view").hide();
//    }
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
    $(".user-referral_link").text(data["referral_link"]);
    $(".user-referral_count").text(data["referral_count"]);
//        $(".UserStatus").text(data["status"]);
//        $(".UserofflineID").text(data["offlineID"]);
//        localStorage.UserPass = data["password"];
//        localStorage.phone = data["phone_number"];
//        localStorage.email = data["email"];
    $(".user-type").text(data["usertype"]);
//        $(".UserGender").text(data["sex"]);
//        $(".UserDateJoined").text(data["date_joined"]);
//        $(".UserDOB").text(data["dob"]);
//        $(".UserName").text(data["user_name"]);
//        loginuseremail = data["email"];
//        username = data["user_name"];
//        $(".bizName").text(data["Name"]);
//        $(".bizIndustry").text(data["BusinessIndustry"]);
//        $(".bizType").text(data["BusinessType"]);
//        $(".bizEmail").text(data["email"]);
//        $(".bizPhone").text(data["phone_number"]);
//        $(".bizDateFound").text(data["DateFounded"]);
//        $(".bizCACNumber").text(data["CACNumber"]);
//        $(".BizDesc").text(data["Description"]);
//        $(".BizWebAddress").text(data["Website"]);
//        $(".BankName").text(data["BankName"]);
//        $(".AccountType").text(data["accountType"]);
//        $(".AccountBVN").text(data["bvnNumber"]);
//        $(".AccountNumber").text(data["accountNumber"]);
//        var acctNumber = data["accountNumber"];
//        if (acctNumber === undefined || acctNumber === "undefined") {
//            $("#addbkinfo").removeClass("hide");
//            $("#addbkinfo").show();
//            $(".bankInfo").addClass("hide");
//            $(".bankInfo").hide();
//        } else {
//            $("#addbkinfo").addClass("hide");
//            $("#addbkinfo").hide();
//            $(".bankInfo").removeClass("hide");
//            $(".bankInfo").show();
//        }
//        var parent = $(".user-addresses");
//        var childclone = parent.find(".clone");
//        var res = data["addresses"];
//        var count = 0;
//        $.each(res, function (id, item) {
//            var newchild = childclone.clone();
//            newchild.removeClass("clone");
//            count++;
//            newchild.find(".address-sn").text(count);
//            newchild.find(".addressPickUp").text(item["addressPickUp"]);
//            newchild.find(".addressType").text(item["addresstype"] + " Address");
//            newchild.find(".UserAddress").text(item["fulladdress"]);
//            var btnDelete = newchild.find(".btnDeleteAdd");
//            btnDelete.click(function () {
//                data = item["id"];
//                swal({
//                    title: 'Are you sure?',
//                    text: "You won't be able to revert this!",
//                    type: 'warning',
//                    showCancelButton: true,
//                    confirmButtonText: 'Yes, delete it!',
//                    cancelButtonText: 'No, cancel!',
//                    confirmButtonClass: 'btn btn-success',
//                    cancelButtonClass: 'btn btn-danger',
//                    buttonsStyling: false
//                }).then(function (dismiss) {
//                    if (dismiss.value) {
//                        GetData("Address", "DeleteUserAddress", "LoadAddress", data);
//                    } else {
//                        swal({
//                            title: 'Safe',
//                            text: "Your address is safe!",
//                            type: 'success',
//                            showCancelButton: false,
//                            confirmButtonText: 'Ok!',
//                            confirmButtonClass: 'btn btn-success',
//                            buttonsStyling: false
//                        });
//                    }
//                });
//            });
//            newchild.appendTo(parent);
//        });
//        $(".useraddressCount").text(count);
//        childclone.hide();
    // }
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
    }
}