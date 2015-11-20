function save() {
    var url = 'jsonvalid.do';

    $("#editForm").ajaxSubmit({
        type: "POST",
        dataType: "json",
        url: url,
        success: function (data) {
            if (data.success) {
                alert('success');
            } else {
                alert(data.resultDesc);
                $('#saveBtn').attr('disabled', false);
            }
        }
    });
}

//上传
function uploadFile() {
    $.ajaxFileUpload({
        url: 'uploadFile.do',
        secureuri: false,
        fileElementId: 'upfile',
        dataType: 'json',
        success: function (data) {
            if (data.success) {
                alert(data.fileName);
            } else {
                alert(data.message);
            }
        },
        error: function (data, status, e) {
            alert(e);
        }
    });
}
