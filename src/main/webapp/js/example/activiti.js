function activiti(url) {
    $.ajax({
        type: "POST",
        dataType: "json",
        url: url,
        success: function (data) {
            $("#processId").setText(data.result);
        }
    });
}

function activitiWithId(url, idName, parmId, resultId) {
    var params ={};
    params[idName] = parmId;
    var result = "#" + resultId;

    $.ajax({
        type: "POST",
        data: params,
        dataType: "json",
        url: url,
        success: function (data) {
            $(result).textbox('setValue', data.result);
        }
    });
}

