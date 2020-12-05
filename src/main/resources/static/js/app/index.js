var main = {
    init: function () {
        var _this = this;
        $('#btn-signup').on('click', function(){
            console.log("signup");
           _this.signup();
        });
    },
    signup: function () {
        var data = {
            name: $('#name').val(),
            email: $('#email').val(),
            passwd: $('#passwd').val()
        };

        $.ajax({
            type: 'POST',
            url: '/main/api/v1/signup',
            // dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/main/signIn2';
        }).fail(function (e) {
           alert(JSON.stringify(e));
        });
    }
};

main.init();