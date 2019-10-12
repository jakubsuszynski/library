$(function () {
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/all',
            dataType : 'json',
            success: function (data) {
                console.log('success', data)
            }
        })
});