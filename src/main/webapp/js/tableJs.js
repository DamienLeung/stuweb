$("td .update").click(function () {
    var var1 = $(this);
    var parentNode = var1[0].parentNode.parentNode;
    var childNodes = parentNode.childNodes;
    for (var i = 3; i < 10; i += 2) {
        var nodeValue = $(childNodes[i]).html();
        console.log(nodeValue);
        if (i === 3)
            $(".id").val(nodeValue);
        else if (i === 5)
            $(".name").val(nodeValue);
        else if (i === 7)
            $(".phone").val(nodeValue);
        else
            $(".addr").val(nodeValue);
    }
    $("#update").val("更新");
    $(".update").attr("action", "/student/save");
    $(".id").attr("readonly", true);

});

$(".selectAll").click(function () {
    if ($(this).prop("checked")) {
        $(".select").prop("checked", true);
        $("#delete").removeAttr('disabled');
    } else {
        $(".select").prop("checked", false);
        $("#delete").prop('disabled', true);
    }

});

$(".select").click(function () {
    if ($(".select").length === $(".select:checked").length) {
        $(".selectAll").prop("checked", true);
    } else {
        $(".selectAll").prop("checked", false);
    }
    if ($(".select:checked").length >= 1) {
        $("#delete").removeAttr('disabled');

        console.log(1);
    }
    else {
        $("#delete").attr('disabled', true);
        console.log(2);
    }
});

$("td .delete").click(function () {
    var var1 = $(this);
    var parentNode = var1[0].parentNode.parentNode;
    var childNodes = parentNode.childNodes;

    var nodeValue = $(childNodes[3]).html();
    console.log(nodeValue);
    window.location.href = "/student/del?msg=" + nodeValue;
});

$("#refresh").click(function () {
    location.href = "/studentTable.jsp";
});

$(".id").blur(function () {
    var reg = /^[1-9]\d*$/;
    if (reg.test($(".id").val())) {
        $("#update").removeAttr("disabled");
        $(".info").hide();

    }

    else {
        $("#update").attr("disabled", true);
        $(".info").show();
    }
});

$(".phone").blur(function () {
    var reg = /^1[3|5|6|7|8]\d{9}|0\d{11}|0\d{10}$/;
    if (reg.test($(".phone").val()) || $(".phone").val() === "") {
        $(".info2").hide();
        $("#update").removeAttr("disabled");
    }
    else {
        $(".info2").show();
        $("#update").attr("disabled", true);
    }
    console.log(reg.test($(".phone").val()));
});
