const departmentAjaxUrl = "ajax/admin/departments/";

let context, form;

function makeEditable(ctx) {
    context = ctx;
    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    form.find(":input").val("");
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
    if (confirm('Are you sure?')) {
        $.ajax({
            url: context.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            context.updateTable();
            successNoty("Deleted");
        });
    }
}

function updateRow(id) {
    var id;
    $('#departmentId').find('option').remove();
    $.get(context.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value)
            if (key == "department"){
                id = value.id;
            }
        });
        $.ajax({
            url: departmentAjaxUrl,
            type: 'GET',
            dataType: 'json'
        }).done(function (json) {
            $.each(json, function (i, value) {
                debugger;
                //form.find('selected').remove('selected');
                if (value.id == id) {
                    $('#departmentId').append($('<option>').text(value.name)
                        .attr('value', value.id).attr("selected", "selected"));
                }else{
                    $('#departmentId').append($('<option>').text(value.name)
                        .attr('value', value.id));
                }
            });
        });
        $('#editRow').modal();
    });
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        context.updateTable();
        successNoty("Saved");
    });
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    }).show();
}