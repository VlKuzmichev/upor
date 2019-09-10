let context, form;

function makeEditable(ctx) {
    context = ctx;
    form = $('#detailsForm');
    $(".delete").click(function () {
        if (confirm('Are you sure?')) {
            deleteRow($(this).attr("id"));
        }
    });
}

function add() {
    form.find(":input").val("");
    $("#editRow").modal();
}

function deleteRow(id) {
    $.ajax({
        url: context.ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
    });
}

function getDepartments() {
    $.ajax({
        url: 'ajax/admin/departments/',
        type: 'GET',
        data: 'name',
        dataType: 'json',
        success: function (json) {
            $.each(json, function (i, value) {
                $('#departmentId').append($('<option>').text(value.name)
                    .attr('value', value.id));
            });
        }
    });
}

function updateTable() {
    $.get(context.ajaxUrl, function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}

function save() {
    debugger;
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
    });
}