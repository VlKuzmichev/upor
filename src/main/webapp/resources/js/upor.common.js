const departmentAjaxUrl = "ajax/admin/departments/";

let context, form;

function makeEditable(ctx) {
    context = ctx;
    context.datatableApi = $("#datatable").DataTable(
        // https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
        $.extend(true, ctx.datatableOpts,
            {
                "ajax": {
                    "url": context.ajaxUrl,
                    "dataSrc": ""
                },
                "paging": false,
                "info": true,
                "language": {
                    "search": i18n["common.search"]
                }
            }
        ));

    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

}

function add() {
//    debugger;
    if(hasRoleSuAdmin == true){
        $(":input[id^='role']:disabled").prop("disabled", false);
    }
    hasRoleSuAdmin = false;
    $("#modalTitle").html(i18n["addTitle"]);
    $("input[id^='role']").each(function (key, val) {
        $(this).prop('checked', false);
    });
    $('#id').val("");
    $('#name').val("");
    $('#fullName').val("");
    $('#email').val("");
    $('#password').val("");
    $('#departmentId').find('option').remove();
    $.ajax({
        url: departmentAjaxUrl,
        type: 'GET',
        dataType: 'json'
    }).done(function (json) {
        $.each(json, function (i, value) {
            $('#departmentId').append($('<option>').text(value.name)
                .attr('value', value.id));
        });
    });
    $("#editRow").modal();
}


function deleteRow(id) {
    if (confirm(i18n['common.confirm'])) {
        $.ajax({
            url: context.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            context.updateTable();
            successNoty("common.deleted");
        });
    }
}

function updateRow(id) {
    var id;
    if(hasRoleSuAdmin == true){
        $(":input[id^='role']:disabled").prop("disabled", false);
    }
    $("#modalTitle").html(i18n["editTitle"]);
    $('#departmentId').find('option').remove();
    $("input[id^='role']").each(function (key, val) {
        $(this).prop('checked', false);
    });
    $.get(context.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
            if (key === "department") {
                id = value.id;
            }
            if (key === "roles") {
                value.forEach(setCheckboxes);
            }
        });
        $.ajax({
            url: departmentAjaxUrl,
            type: 'GET',
            dataType: 'json'
        }).done(function (json) {
            $.each(json, function (i, value) {
                form.find('selected').remove('selected');
                if (value.id === id) {
                    $('#departmentId').append($('<option>').text(value.name)
                        .attr('value', value.id).attr("selected", "selected"));
                } else {
                    $('#departmentId').append($('<option>').text(value.name)
                        .attr('value', value.id));
                }
            });
        });
        $('#editRow').modal();
    });
}

function setCheckboxes(item) {
    $('#'.concat(item.toLowerCase())).prop('checked', true);
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}

function save() {
    let disabled = form.find(":input:disabled").prop("disabled", false);
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize(),
    }).done(function () {
        $("#editRow").modal("hide");
        context.updateTable();
        successNoty("common.saved");
    });
    disabled.prop("disabled", true);
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    const errorInfo = JSON.parse(jqXHR.responseText);
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + errorInfo.typeMessage + "<br>" + errorInfo.details.join("<br>"),
        type: "error",
        layout: "bottomRight"
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}